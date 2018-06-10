package io.osu.konosuba.commands;

import java.awt.Color;

import io.magiccraftmaster.util.StringUtils;
import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.LocationData;
import io.osu.konosuba.data.UserData;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Location extends Command{

	public Location() {
		super(Konosuba.COLOR, Konosuba.PREFIX, "location", "lets people move from one location to another", null, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run(MessageReceivedEvent event, String[] args) {
		// TODO Auto-generated method stub
		try {
			UserData player = new UserData(event.getAuthor().getIdLong());
			if(player.getStartStatus()) {
				if(args.length == 1) {
					locationBuilder(player, event);
					return;
				}
				
				if(args[1].equalsIgnoreCase("move")) {
					String movement =  StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
					LocationData location = new LocationData(player.getLocation());
					int locationid = location.getLocationId(movement);
					if(location.getLocationList().contains(locationid)) {
						player.setLocation(locationid);
						locationBuilder(player, event);
					}else {
						send(event.getGuild(), event.getChannel(), "That location isn't near you!", true);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void locationBuilder(UserData player, MessageReceivedEvent event) {
		LocationData locationInfo = new LocationData(player.getLocation());
		EmbedBuilder location = new EmbedBuilder();
		String title = locationInfo.getMainLocation() + ": " + locationInfo.getSubLocation();
		location.setTitle(title);
		if(!locationInfo.getItemShopName().isEmpty()) {
			location.addField("**"+locationInfo.getItemShopName() + "**", "*shop 1", true);
		}
		if(!locationInfo.getWeaponShopName().isEmpty()) {
			location.addField("**"+locationInfo.getWeaponShopName() +"**", "*shop 2", true);
		}
		if(!locationInfo.getMagicShopName().isEmpty()) {
			location.addField("**"+locationInfo.getMagicShopName() + "**", "*shop 3", true);
		}
		if(!locationInfo.getBlacksmithName().isEmpty()) {
			location.addField("**" +locationInfo.getBlacksmithName() + "**", "*blacksmith", true);	
		}
		if(!locationInfo.getMonsterList().isEmpty()) {
			location.addField("**Monsters Nearby**", "*seach", true);
		}
		if(!locationInfo.getLocationList().isEmpty()) {
			StringBuilder list = new StringBuilder();
			list.append("|");
			for(int name : locationInfo.getLocationList()) {
				list.append(" **" + new LocationData(name).getSubLocation() + "**|");
			}
			location.addField("**Surrounding Areas**", list.toString(), true);
		}
		
		location.setFooter("To move locations: *location move [place]","");
		send(event, location.build());
		
	}
	
	private static void send(MessageReceivedEvent event, MessageEmbed embed) {
		if (event.isFromType(ChannelType.TEXT) && !event.getGuild().getSelfMember().hasPermission(event.getTextChannel(), Permission.MESSAGE_EMBED_LINKS)) {
			StringBuilder stringBuilder = new StringBuilder("**").append(embed.getTitle()).append("**\n").append(embed.getDescription());
			for (MessageEmbed.Field field : embed.getFields()) stringBuilder.append("\n\n**").append(field.getName()).append("**\n").append(field.getValue());
			event.getChannel().sendMessage(stringBuilder.toString()).queue();

		} else event.getChannel().sendMessage(embed).queue();
	}

}
