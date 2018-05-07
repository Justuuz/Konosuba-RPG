package io.osu.konosuba.commands;

import java.awt.Color;

import io.magiccraftmaster.util.StringUtils;
import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.ClientData;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Location extends Command {

	public Location() {
		super(Konosuba.COLOR, Konosuba.PREFIX, "location", "commands that deal with location", null, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run(MessageReceivedEvent event, String[] args) {
		// TODO Auto-generated method stub
		
		ClientData player = Konosuba.CLIENT_DATA_MANAGER.getData(event.getAuthor().getIdLong());
		
		String position = player.getLocation();
		String todo;
		EmbedBuilder location = new EmbedBuilder();
		String place = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 1), " ");
		
		String A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, B1, B2, B3, B4, B5, C1, C2, C3, C4, C5;
		
		A1 = "Axel";
		A2 = "Axel_mansion";
		A3 = "Axel fields";
		A4 = "Axel_cave";
		A5 = "Axel_abandoned_castle";
		A6 = "Axel_east_road";
		A7 = "Axel_undead_road";
		A8 = "Axel_plains";
		A9 = "Axel_trail";
		A10 = "Axel_snow_mountains";
		A11 = "Axel_snow_mountains_peak";
		A12 = "Axel_snow_mountains_cave";
		A13 = "Axel lake";
		A14 = "Axel_big_tree";
		A15 = "Axel_mystery_forest";
		A16 = "Axel_forest";
		
		if(position.equals(A1)) {
			location.setTitle("Axel");
			location.setDescription("\bCOMMANDS:\b");
			location.addField("Adventurer's Guild", "*buy\n*sell", true);
			location.addField("Blacksmith", "*craft", true);
			location.addField("Cafe", "*some command", true);
			location.addField("*move [location]", "mansion, east road, fields, plains", true);
			event.getChannel().sendMessage(location.build()).queue();
	
		}
	
		//axel_mansion
		if(position.equals(A2)) {
	
		}
	
		//axel fields
		if(position.equals(A3)) {
	
	
		}
		if(position.equals(A4)) {

	
		}
		if(position.equals(A5)) {
	
		}
		if(position.equals(A6)) {
	
	
		}
		
		if(position.equals(A7)) {
	
	
		}
		if(position.equals(A8)) {
	
	
		}
		
		if(position.equals(A9)) {
	
		}
		
		if(position.equals(A10)) {
	
		}
		
		if(position.equals(A11)) {
	
	
		}
		
		if(position.equals(A12)) {
	
		}
		
		if(position.equals(A13)) {
	
		}
		
		if(position.equals(A14)) {
	
		}
		
		if(position.equals(A15)) {
	
		}
		
		if(position.equals(A16)) {
	
		}
		
		
	}
}