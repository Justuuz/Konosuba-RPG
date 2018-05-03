package io.osu.konosuba.commands;

import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.ClientData;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Equip extends Command {
	
	public Equip() {

		super(Konosuba.COLOR, Konosuba.PREFIX, "equip", "all related battle commands", null, 0);

		

		// TODO Auto-generated constructor stub
	}
	
	/*
	 * framework
	 */

	
	
	@Override
	public void run(MessageReceivedEvent event, String[] args) {
		
		/*
		 * equips an item
		 * 
		 * (important) cannot equip an item if there is already an item in that place
		 * helmet, chest, leggings, boots, onHand, offHand, cape, necklace, ring;
		 */
		
		ClientData player = new ClientData(event.getChannel().getIdLong());
		
		if(args[1].equalsIgnoreCase("helmet")) {
			
			if(!player.getHelmet().equalsIgnoreCase("none")) {
				String message = "Please remove the" + "helmet" + "first";

				send(event.getGuild(), event.getChannel(), message, true);
			}
			else {
				player.setHelmet(helmet);
			}
			
		}
		
		if(args[1].equalsIgnoreCase("chest")) {
			
		}
		
		if(args[1].equalsIgnoreCase("leggings")) {
			
		}
		
		if(args[1].equalsIgnoreCase("onhand")) {
			
		}
		
		if(args[1].equalsIgnoreCase("offhand")) {
			
		}
		
		if(args[1].equalsIgnoreCase("cape")) {
			
		}
		
		if(args[1].equalsIgnoreCase("necklace")) {
			
		}
		
		if(args[1].equalsIgnoreCase("ring")) {
			
		}
		
	}
}