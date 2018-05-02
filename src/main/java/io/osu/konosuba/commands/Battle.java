package io.osu.konosuba.commands;

import io.osu.konosuba.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Battle extends Command {
	
	public Battle() {
		super(embedColor, prefix, name, description, requiredPermission, requiredID);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * framework
	 */
	@Override
	public void run(MessageReceivedEvent event, String[] args) {
		
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
