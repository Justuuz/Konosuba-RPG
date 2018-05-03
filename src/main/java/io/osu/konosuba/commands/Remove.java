package io.osu.konosuba.commands;

import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.ClientData;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Remove extends Command {
	
	public Remove() {

		super(Konosuba.COLOR, Konosuba.PREFIX, "remove", "all related battle commands", null, 0);

		// TODO Auto-generated constructor stub
	}
	
	/*
	 * framework
	 */

	
	
	@Override
	public void run(MessageReceivedEvent event, String[] args) {
		
		/*
		 * removes an item
		 * 
		 * can't remove item if there isn't one
		 * helmet, chest, leggings, boots, onHand, offHand, cape, necklace, ring;
		 */
		
		ClientData player = new ClientData(event.getChannel().getIdLong());
		EmbedBuilder help  = new EmbedBuilder();
		
		if(args[1].equalsIgnoreCase("helmet")) {
			
			if(!player.getHelmet().equalsIgnoreCase("none")) {
				String message = "No item to remove";

				send(event.getGuild(), event.getChannel(), message, true);
				
			}
			else {
				player.setHelmet("None");
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