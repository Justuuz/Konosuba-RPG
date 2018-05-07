package io.osu.konosuba.commands;

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
		String A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, B1, B2, B3, B4, B5, C1, C2, C3, C4, C5;
		
		A1 = "axel";
		A2 = "axel_mansion";
		A3 = "axel fields";
		A4 = "axel_cave";
		A5 = "axel_abandoned_castle";
		A6 = "axel_road_east";
		A7 = "axel_undead_road";
		A8 = "axel_plains";
		A9 = "axel_trail";
		A10 = "axel_snow_mountains";
		A11 = "axel_snow_mountains_peak";
		A12 = "axel_snow_mountains_cave";
		A13 = "axel lake";
		A14 = "axel_big_tree";
		A15 = "axel_mystery_forest";

		
	}
}