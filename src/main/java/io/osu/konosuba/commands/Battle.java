package io.osu.konosuba.commands;

import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.ClientData;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Battle extends Command {
	
	public Battle() {
		super(Konosuba.COLOR, Konosuba.PREFIX, "battle", "all related battle commands", null, 0);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * framework
	 */
	
	
	@Override
	public void run(MessageReceivedEvent event, String[] args) {
		
		//Getting the players information from here.
		ClientData user = new ClientData(event.getChannel().getIdLong());
	/*
	 * Show UI here
	 */
		
		if(args[1].equalsIgnoreCase("attack")) {
			
		}
		
		if(args[1].equalsIgnoreCase("magic")) {
			
		}
		
		if(args[1].equalsIgnoreCase("heal")) {
			
		}
		
		if(args[1].equalsIgnoreCase("item")) {
			
		}
		
		if(args[1].equalsIgnoreCase("run")) {
			
		}
	}

}