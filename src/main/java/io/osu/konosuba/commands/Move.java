package io.osu.konosuba.commands;

import io.magiccraftmaster.util.StringUtils;
import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.ClientData;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Move extends Command {
	
	public Move() {
		super(Konosuba.COLOR, Konosuba.PREFIX, "move", "all related moving commands", null, 0);
		// TODO Auto-generated constructor stub
	}
	

	
	
	@Override
	public void run(MessageReceivedEvent event, String[] args) {
		ClientData player = Konosuba.CLIENT_DATA_MANAGER.getData(event.getAuthor().getIdLong());
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
		
		String position = player.getLocation();
		String place = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 1), " ");
		
		//axel
		if(position.equalsIgnoreCase(A1)) {
				if(place.equalsIgnoreCase("mansion")) {
					player.setLocation(A2);
			}
				else if(place.equalsIgnoreCase("fields")) {
					player.setLocation(A3);
			}
				else if(place.equalsIgnoreCase("plains")) {
					player.setLocation(A8);
			}
				else if(place.equalsIgnoreCase("east road")) {
					player.setLocation(A6);
			}

		
		}
		
		//axel_mansion
		if(position.equalsIgnoreCase(A2)) {
			if(place.equalsIgnoreCase("axel")) {
					player.setLocation(A1);
			}
		}
		
		//axel fields
		if(position.equalsIgnoreCase(A3)) {
			if(place.equalsIgnoreCase("axel")) {
				player.setLocation(A1);
			}
			else if(place.equalsIgnoreCase("forest")) {
				player.setLocation(A16);
			}

		}
		if(position.equalsIgnoreCase(A4)) {
			if(place.equalsIgnoreCase("axel")) {
				player.setLocation(A1);
			}
			else if(place.equalsIgnoreCase("east road")) {
				player.setLocation(A6);
			}

		}
		if(position.equalsIgnoreCase(A5)) {
			if(place.equalsIgnoreCase("axel")) {
				player.setLocation(A1);
			}
			else if(place.equalsIgnoreCase("forest")) {
				player.setLocation(A16);
			}

		}
		if(position.equalsIgnoreCase(A6)) {
			if(place.equalsIgnoreCase("axel")) {
				player.setLocation(A1);
			}
			else if(place.equalsIgnoreCase("cave")) {
				player.setLocation(A4);
			}
			else if(place.equalsIgnoreCase("undead road")) {
				player.setLocation(A7);
			}

		}
		
		if(position.equalsIgnoreCase(A7)) {
			if(place.equalsIgnoreCase("axel")) {
				player.setLocation(A1);
			}
			else if(place.equalsIgnoreCase("east road")) {
				player.setLocation(A6);
			}
			/*
			 * add road to alcanteria 
			 */

		}
		if(position.equalsIgnoreCase(A8)) {
			if(place.equalsIgnoreCase("axel")) {
				player.setLocation(A1);
			}
			else if(place.equalsIgnoreCase("big tree")) {
				player.setLocation(A14);
			}
			else if(place.equalsIgnoreCase("trail")) {
				player.setLocation(A9);
			}

		}
		
		if(position.equalsIgnoreCase(A9)) {
			if(place.equalsIgnoreCase("axel")) {
				player.setLocation(A1);
			}
			else if(place.equalsIgnoreCase("plains")) {
				player.setLocation(A8);
			}
			else if(place.equalsIgnoreCase("snow mountains")) {
				player.setLocation(A10);
			}

		}
		
		if(position.equalsIgnoreCase(A10)) {
			if(place.equalsIgnoreCase("axel")) {
				player.setLocation(A1);
			}
			else if(place.equalsIgnoreCase("trail")) {
				player.setLocation(A9);
			}
			else if(place.equalsIgnoreCase("cave")) {
				player.setLocation(A12);
			}
			else if(place.equalsIgnoreCase("peak")) {
				player.setLocation(A11);
			}

		}
		
		if(position.equalsIgnoreCase(A11)) {
			if(place.equalsIgnoreCase("axel")) {
				player.setLocation(A1);
			}
			else if(place.equalsIgnoreCase("snow mountain")) {
				player.setLocation(A10);
			}


		}
		
		if(position.equalsIgnoreCase(A12)) {
			if(place.equalsIgnoreCase("axel")) {
				player.setLocation(A1);
			}
			else if(place.equalsIgnoreCase("snow mountain")) {
				player.setLocation(A10);
			}


		}
		
		if(position.equalsIgnoreCase(A13)) {
			if(place.equalsIgnoreCase("axel")) {
				player.setLocation(A1);
			}
			else if(place.equalsIgnoreCase("big tree")) {
				player.setLocation(A14);
			}
			else if(place.equalsIgnoreCase("mystery forest")) {
				player.setLocation(A15);
			}
			else if(place.equalsIgnoreCase("forest")) {
				player.setLocation(A16);
			}

		}
		
		if(position.equalsIgnoreCase(A14)) {
			if(place.equalsIgnoreCase("axel")) {
				player.setLocation(A1);
			}
			else if(place.equalsIgnoreCase("plains")) {
				player.setLocation(A8);
			}
			else if(place.equalsIgnoreCase("lake")) {
				player.setLocation(A13);
			}


		}
		
		if(position.equalsIgnoreCase(A15)) {
			if(place.equalsIgnoreCase("axel")) {
				player.setLocation(A1);
			}
			else if(place.equalsIgnoreCase("lake")) {
				player.setLocation(A13);
			}
			
			/*
			 * add road to another location
			 */

		}
		
		if(position.equalsIgnoreCase(A16)) {
			if(place.equalsIgnoreCase("axel")) {
				player.setLocation(A1);
			}
			else if(place.equalsIgnoreCase("fields")) {
				player.setLocation(A3);
			}
			else if(place.equalsIgnoreCase("abandoned castle")) {
				player.setLocation(A5);
			}
			else if(place.equalsIgnoreCase("lake")) {
				player.setLocation(A13);
			}

		}
		
		Konosuba.CLIENT_DATA_MANAGER.trySave();

		

	
	
		
	}
}