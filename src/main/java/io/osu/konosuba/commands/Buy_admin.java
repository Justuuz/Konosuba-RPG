package io.osu.konosuba.commands;

import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.ClientData;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Buy_admin extends Command {
	
	public Buy_admin() {
		super(Konosuba.COLOR, Konosuba.PREFIX, "buy_admin", "all related battle commands", null, 0);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * framework
	 */
	
	
	@Override
	public void run(MessageReceivedEvent event, String[] args) {
		
		ClientData player = Konosuba.CLIENT_DATA_MANAGER.getData(event.getChannel().getIdLong());
	/*
	 * Show UI here
	 */
		
		if(args[1].equalsIgnoreCase("admin_hat")) {
			player.getInventory().get(0).add("Admin_hat");
			
		}
		
		if(args[1].equalsIgnoreCase("admin_plate")) {
			player.getInventory().get(1).add("Admin_plate");
		}
		
		if(args[1].equalsIgnoreCase("admin_sword")) {
			player.getInventory().get(3).add("Admin_sword");
		}
		
		if(args[1].equalsIgnoreCase("admin_hat2")) {
			player.getInventory().get(0).add("Admin_hat2");
			
		}
		
		if(args[1].equalsIgnoreCase("admin_hat3")) {
			player.getInventory().get(0).add("Admin_hat3");
			
		}
		Konosuba.CLIENT_DATA_MANAGER.trySave();
		
	}
}