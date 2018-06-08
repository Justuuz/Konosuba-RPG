package io.osu.konosuba.test;

import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;

import io.osu.konosuba.data.UserData;
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
		try {
		
		UserData player = new UserData(event.getAuthor().getIdLong());
	/*
	 * Show UI here
	 */
		
		if(args[1].equalsIgnoreCase("admin_hat")) {
			player.addInventory(0, "admin_hat");
			
		}
		
		if(args[1].equalsIgnoreCase("admin_plate")) {
			player.addInventory(1, "admin_plate");
		}
		
		
		if(args[1].equalsIgnoreCase("admin_hat2")) {
			player.addInventory(0, "admin_hat2");
			 
		}
		
		if(args[1].equalsIgnoreCase("admin_hat3")) {
			player.addInventory(0, "admin_hat3");
			
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}