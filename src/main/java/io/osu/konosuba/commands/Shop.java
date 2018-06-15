package io.osu.konosuba.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.ReactionCommand;
import io.osu.konosuba.data.ItemData;
import io.osu.konosuba.data.LocationData;
import io.osu.konosuba.data.UserData;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;

public class Shop extends Command implements ReactionCommand{

	public Shop() {
		super(Konosuba.COLOR, Konosuba.PREFIX, "shop", "depending on place, will show a list of shop", null, 0);
		// TODO Auto-generated constructor stub
	}
	
	long id;
	

	@Override
	protected void run(MessageReceivedEvent event, String[] args) {
		UserData player = new UserData(event.getAuthor().getIdLong());
		if(player.getStartStatus()) {
			LocationData shops = new LocationData(player.getLocation());
			
			Consumer<Message> call = (m) -> {
				id = m.getIdLong();
				if(!shops.getItemShopName().isEmpty()) event.getTextChannel().addReactionById(id, event.getJDA().getEmoteById(456959750836584459L)).queue();
				if(!shops.getWeaponShop().isEmpty()) event.getTextChannel().addReactionById(id, event.getJDA().getEmoteById(456959760877486090L)).queue();
				if(!shops.getMagicShopName().isEmpty()) event.getTextChannel().addReactionById(id, event.getJDA().getEmoteById(456959916901662741L)).queue();
			};
			
			if(args[1].equalsIgnoreCase("1")) {
				if(!shops.getItemShopName().isEmpty()) {
					event.getChannel().sendMessage(shopHelper(1,shops).build()).queue(call);
				}else {
					send(event.getGuild(), event.getChannel(), "A Item Shop doesn't exist here", true);
				}
			}
			if(args[1].equalsIgnoreCase("2")) {
				if(!shops.getWeaponShopName().isEmpty()) {
					event.getChannel().sendMessage(shopHelper(2,shops).build()).queue(call);
				}
			}
		}
	}
	
	@Override
	public void run(MessageReactionAddEvent event) {
		
		// TODO Auto-generated method stub
		UserData player = new UserData(event.getUser().getIdLong());
		LocationData  location = new LocationData(player.getLocation());
		Consumer<Message> m = (response) -> {
			response.clearReactions();
			if(!location.getItemShopName().isEmpty()) event.getTextChannel().addReactionById(id, event.getJDA().getEmoteById(456959750836584459L)).queue();
			if(!location.getWeaponShop().isEmpty()) event.getTextChannel().addReactionById(id, event.getJDA().getEmoteById(456959760877486090L)).queue();
			if(!location.getMagicShopName().isEmpty()) event.getTextChannel().addReactionById(id, event.getJDA().getEmoteById(456959916901662741L)).queue();
		};
		System.out.println(event.getReactionEmote().getName());
		if(event.getReactionEmote().getName().equalsIgnoreCase("1_")) {
			event.getTextChannel().editMessageById(id, shopHelper(1, location).build()).queue((m));
		}else if(event.getReactionEmote().getName().equalsIgnoreCase("2_") && !location.getWeaponShopName().isEmpty()) {
			event.getTextChannel().editMessageById(id, shopHelper(2, location).build()).queue((m));
		}
		
	};

	private EmbedBuilder shopHelper(int shop,LocationData data) {
		EmbedBuilder shopBuild = new EmbedBuilder();
		if(shop == 1) {
			shopBuild.setTitle(data.getItemShopName());
			for(int items : data.getItemShop()) {
				ItemData itemData = new ItemData(items);
				shopBuild.addField("**" +itemData.getName() +"**", "**ID**: " + items + "\n**Cost**: " + itemData.getBuyValue() , true);
			}
		}

		if(shop == 2) {
			shopBuild.setTitle(data.getWeaponShopName());
			for(int items : data.getWeaponShop()) {
				ItemData itemData = new ItemData(items);
				shopBuild.addField("**" +itemData.getName() +"**", "**ID**: " + items+ "\n**Cost**: " + itemData.getBuyValue(), true);
			}
		}

		if(shop == 3) {
			shopBuild.setTitle(data.getMagicShopName());
			for(int items : data.getMagicShop()) {
				ItemData itemData = new ItemData(items);
				shopBuild.addField("**" +itemData.getName() +"**", "**ID**: " + items+ "\n**Cost**: " + itemData.getBuyValue(), true);
			}
		}
		shopBuild.setFooter("to buy or sell: *shop buy/sell [ID]", "https://cdn130.picsart.com/250247865001212.png");
		return(shopBuild);
	}

	
}
