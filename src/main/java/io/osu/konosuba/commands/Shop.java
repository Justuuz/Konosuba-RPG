package io.osu.konosuba.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import io.magiccraftmaster.util.StringUtils;
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
	
	private long GlobalId;
	private LocationData globalShop;
	

	@Override
	protected void run(MessageReceivedEvent event, String[] args) {
		UserData player = new UserData(event.getAuthor().getIdLong());
		if(player.getStartStatus()) {
			player.getLocation();
			LocationData shops = new LocationData(player.getLocation());
			globalShop = shops;
			Consumer<Message> call = (m) -> {
				GlobalId = m.getIdLong();
				if(!shops.getItemShopName().isEmpty() && event.getAuthor() != event.getJDA().getSelfUser()) event.getTextChannel().addReactionById(GlobalId, event.getJDA().getEmoteById(456959750836584459L)).queue();
				if(!shops.getWeaponShop().isEmpty()&& event.getAuthor() != event.getJDA().getSelfUser()) event.getTextChannel().addReactionById(GlobalId, event.getJDA().getEmoteById(456959760877486090L)).queue();
			};
			if(args.length == 1 && !hasArgument(args, 1)) {
				if(!shops.getItemShopName().isEmpty()) {
					event.getChannel().sendMessage(shopHelper(1,shops, player).build()).queue(call);
					return;
				}else {
					send(event.getGuild(), event.getChannel(), "A Item Shop doesn't exist here", true);
					return;
				}	
			}
			if(hasArgument(args, 1) && args[1].equalsIgnoreCase("1")  && !hasArgument(args, 2)) {
				if(!shops.getItemShopName().isEmpty()) {
					event.getChannel().sendMessage(shopHelper(1,shops, player).build()).queue(call);
				}else {
					send(event.getGuild(), event.getChannel(), "A Item Shop doesn't exist here", true);
					return;
				}
			}
			if(hasArgument(args, 1) && args[1].equalsIgnoreCase("2")  &&!hasArgument(args, 2)) {
				if(!shops.getWeaponShopName().isEmpty()) {
					event.getChannel().sendMessage(shopHelper(2,shops, player).build()).queue(call);
					return;
				}else {
					send(event.getGuild(), event.getChannel(), "A Weapon Shop doesn't exist here", true);
					return;
				}
			}
			if(hasArgument(args, 1) && args[1].equalsIgnoreCase("buy")) {
				if(!args[2].matches("\\d+")) {
					send(event.getGuild(), event.getChannel(), "Remember! : *shop buy/sell [ID] [Amount]", true);
					return;
				}
				int id = Integer.parseInt(args[2]);
				ItemData item = new ItemData(id);
				if(globalShop.getItemShop().contains(id) || globalShop.getWeaponShop().contains(id)) {
					if(!args[3].matches("\\d+")) {
						send(event.getGuild(), event.getChannel(), "Remember! : *shop buy/sell [ID] [Amount]", true);
						return;
					}
					int amount = Integer.parseInt(args[3]);
					if(item.getBuyValue() * amount > player.getBalance()) {
						send(event.getGuild(), event.getChannel(), "Insufficient Funds", true);
						return;
					}
						if(globalShop.getItemShop().contains(id)) {
							player.addItems(id, amount);
							player.setBalance(player.getBalance() - (item.getBuyValue() * amount));
					}else {
						player.setBalance(player.getBalance() - (item.getBuyValue() * amount));
						for(int i = 0; i < amount; i++) {
							if(globalShop.getWeaponShop().contains(id)) {
								player.addInventory(item.getType(), id);
							}
						}
					}
					send(event.getGuild(), event.getChannel(), "You bought " + amount  + " " + item.getName(), true);
				}else {
					send(event.getGuild(), event.getChannel(), "The item you are looking for is currently not in that shop!", true);
				}
			}
		}
	}
	
	@Override
	public void run(MessageReactionAddEvent event) {
		UserData player = new UserData(event.getUser().getIdLong());
		
		Consumer<Message> m = (response) -> {
			event.getReaction().removeReaction(event.getUser()).queue();
		};
		if(event.getReactionEmote().getName().equalsIgnoreCase("1_") && event.getUser() != event.getJDA().getSelfUser()) {
			event.getTextChannel().editMessageById(GlobalId, shopHelper(1, globalShop, player).build()).queue((m));
		}else if(event.getReactionEmote().getName().equalsIgnoreCase("2_") && event.getUser() != event.getJDA().getSelfUser()) {
			event.getTextChannel().editMessageById(GlobalId, shopHelper(2, globalShop, player).build()).queue((m));
		}
		
	};

	private EmbedBuilder shopHelper(int shop,LocationData data, UserData player) {
		EmbedBuilder shopBuild = new EmbedBuilder();
		if(shop == 1) {
			shopBuild.setTitle(data.getItemShopName() + " || Balance: " + player.getBalance() );
			for(int items : data.getItemShop()) {
				ItemData itemData = new ItemData(items);
				shopBuild.addField("**" +itemData.getName() +" (ID**: "  + items + ")", "**Cost**: " + itemData.getBuyValue() , true);
			}
		}

		if(shop == 2) {
			shopBuild.setTitle(data.getWeaponShopName()+ " || Balance: " + player.getBalance());
			for(int items : data.getWeaponShop()) {
				ItemData itemData = new ItemData(items);
				shopBuild.addField("**" +itemData.getName() +" (ID**: "  + items + ")", "**Cost**: " + itemData.getBuyValue(), true);
			}
		}

		shopBuild.setFooter("to buy or sell: *shop buy/sell [ID] [Amount]", "https://cdn130.picsart.com/250247865001212.png");
		return(shopBuild);
	}

	
}
