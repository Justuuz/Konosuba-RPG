package io.osu.konosuba.commands;

import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.ClientData;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Item_admin extends Command {
	
	public Item_admin() {
		super(Konosuba.COLOR, Konosuba.PREFIX, "item_admin", "all related battle commands", null, 0);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * framework
	 */
	
	
	@Override
	public void run(MessageReceivedEvent event, String[] args) {
		
		ClientData player = Konosuba.CLIENT_DATA_MANAGER.getData(event.getAuthor().getIdLong());
	/*
	 * Show UI here
	 */
		
		if(args[1].equalsIgnoreCase("stick")) {
			player.addInventory("admin_hat", 0);
			
		}
		
		if(args[1].equalsIgnoreCase("admin_plate")) {
			player.addInventory("admin_plate", 1);
		}
		
		
		if(args[1].equalsIgnoreCase("admin_hat2")) {
			player.addInventory("admin_hat2", 0);
			 
		}
		
		if(args[1].equalsIgnoreCase("admin_hat3")) {
			player.addInventory("admin_hat3", 0);
			
		}
		Konosuba.CLIENT_DATA_MANAGER.trySave();
		
	}
}