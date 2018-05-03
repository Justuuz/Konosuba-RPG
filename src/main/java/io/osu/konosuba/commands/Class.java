package io.osu.konosuba.commands;

import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Class extends Command {
	
	public Class() {
		super(Konosuba.COLOR, Konosuba.PREFIX, name, description, requiredPermission, requiredID);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * framework
	 */
	@Override
	public void run(MessageReceivedEvent event, String[] args) {
		
	
		/*
		 * Show UI here like class multipliers
		 */
			
			if(args[1].equalsIgnoreCase("change")) {
				//cahnge class
			}
			if(args[1].equalsIgnoreCase("info")) {
				//class info
			}
	}