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
	public void run(MessageReceivedEvent event, String[] args) {
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
		
		if(position.equalsIgnoreCase(A1)) {
			location.setTitle("Axel");
			location.setDescription("\bCOMMANDS:\b");
			location.addField("Adventurer's Guild", "*buy\n*sell", true);
			location.addField("Blacksmith", "*craft", true);
			location.addField("Cafe", "*talk", true);
			location.addField("*move [location]", "mansion, east road, fields, plains", true);
			event.getChannel().sendMessage(location.build()).queue();
	
		}
	
		//axel_mansion
		if(position.equalsIgnoreCase(A2)) {
			
			location.setTitle("Axel Haunted Mansion");
			location.setDescription("\bCOMMANDS:\b");
			location.addField("Battle", "*battle", true);
			location.addField("*move [location]", "axel", true);
			event.getChannel().sendMessage(location.build()).queue();
	
		}
	
		//axel fields
		if(position.equalsIgnoreCase(A3)) {
			location.setTitle("Axel Fields");
			location.setDescription("\bCOMMANDS:\b");
			location.addField("Battle", "*battle", true);
			location.addField("*move [location]", "axel, forest", true);
			event.getChannel().sendMessage(location.build()).queue();
	
	
		}
		if(position.equalsIgnoreCase(A4)) {
			location.setTitle("Axel Cave");
			location.setDescription("\bCOMMANDS:\b");
			location.addField("Battle", "*battle", true);
			location.addField("search", "*search (planning for this to be a boss event)", true);
			location.addField("*move [location]", "axel, east road", true);
			event.getChannel().sendMessage(location.build()).queue();

	
		}
		if(position.equalsIgnoreCase(A5)) {
			
			location.setTitle("Axel Abandoned Castle");
			location.setDescription("\bCOMMANDS:\b");
			location.addField("Battle", "*battle", true);
			location.addField("search", "*search (planning for this to be a boss event)", true);
			location.addField("*move [location]", "axel, forest", true);
			event.getChannel().sendMessage(location.build()).queue();
	
		}
		if(position.equalsIgnoreCase(A6)) {
			
			location.setTitle("Axel East Road");
			location.setDescription("\bCOMMANDS:\b");
			location.addField("Battle", "*battle", true);
			location.addField("*move [location]", "axel, undead road, cave", true);
			event.getChannel().sendMessage(location.build()).queue();
	
	
		}
		
		if(position.equalsIgnoreCase(A7)) {
			
			location.setTitle("Axel Undead Road");
			location.setDescription("\bCOMMANDS:\b");
			location.addField("Battle", "*battle", true);
			location.addField("*move [location]", "axel, east road, (to alcanteria)", true);
			event.getChannel().sendMessage(location.build()).queue();
	
	
		}
		if(position.equalsIgnoreCase(A8)) {
	
			location.setTitle("Axel Plains");
			location.setDescription("\bCOMMANDS:\b");
			location.addField("Battle", "*battle", true);
			location.addField("*move [location]", "axel, trail, big tree", true);
			event.getChannel().sendMessage(location.build()).queue();
	
		}
		
		if(position.equalsIgnoreCase(A9)) {
			
			location.setTitle("Axel Trail");
			location.setDescription("\bCOMMANDS:\b");
			location.addField("Battle", "*battle", true);
			location.addField("*move [location]", "axel, plains, snow mountain", true);
			event.getChannel().sendMessage(location.build()).queue();
	
		}
		
		if(position.equalsIgnoreCase(A10)) {
			
			location.setTitle("Axel Snow Mountain");
			location.setDescription("\bCOMMANDS:\b");
			location.addField("Battle", "*battle", true);
			location.addField("*move [location]", "axel, trail, peak, cave", true);
			event.getChannel().sendMessage(location.build()).queue();
	
		}
		
		if(position.equalsIgnoreCase(A11)) {
			
			location.setTitle("Axel Snow Mountain Peak");
			location.setDescription("\bCOMMANDS:\b");
			location.addField("Battle", "*battle", true);
			location.addField("Climb", "*climb (another boss event)", true);
			location.addField("*move [location]", "axel, snow mountain", true);
			event.getChannel().sendMessage(location.build()).queue();
	
	
		}
		
		if(position.equalsIgnoreCase(A12)) {
			location.setTitle("Axel Snow Mountain Cave");
			location.setDescription("\bCOMMANDS:\b");
			location.addField("Battle", "*battle", true);
			location.addField("Search", "*search (another boss event)", true);
			location.addField("*move [location]", "axel, snow mountain", true);
			event.getChannel().sendMessage(location.build()).queue();
	
		}
		
		if(position.equalsIgnoreCase(A13)) {
			
			location.setTitle("Axel Lake");
			location.setDescription("\bCOMMANDS:\b");
			location.addField("Battle", "*battle", true);
			location.addField("fish", "*fish (lol)", true);
			location.addField("*move [location]", "axel, mystery forest, big tree, forest", true);
			event.getChannel().sendMessage(location.build()).queue();
	
		}
		
		if(position.equalsIgnoreCase(A14)) {
			
			location.setTitle("Axel Big Tree");
			location.setDescription("\bCOMMANDS:\b");
			location.addField("Battle", "*battle", true);
			location.addField("Climb", "*climb (boss event)", true);
			location.addField("*move [location]", "axel, plains, lake", true);
			event.getChannel().sendMessage(location.build()).queue();
	
		}
		
		if(position.equalsIgnoreCase(A15)) {
			
			location.setTitle("Axel Mystery Forest");
			location.setDescription("\bCOMMANDS:\b");
			location.addField("Battle", "*battle", true);

			location.addField("*move [location]", "axel, lake", true);
			event.getChannel().sendMessage(location.build()).queue();
	
		}
		
		if(position.equalsIgnoreCase(A16)) {
			
			location.setTitle("Axel Forest");
			location.setDescription("\bCOMMANDS:\b");
			location.addField("Battle", "*battle", true);

			location.addField("*move [location]", "axel, lake, abandoned castle, fields", true);
			event.getChannel().sendMessage(location.build()).queue();
	
		}
		
		
	}
}