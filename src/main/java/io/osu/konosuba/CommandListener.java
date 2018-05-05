package io.osu.konosuba;

import java.io.IOException;


import io.osu.konosuba.commands.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {
	
	private final Command[] commands = {
		new Example(),
		new Battle(),
		new Shutdown(),
		new Start(),
		new Equip(),
		new Remove(),
		new Inventory(),
		new Buy_admin(),
		new Profile()
	};

	CommandListener() throws IOException {
		
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		
		
			
		for (Command command : commands) command.check(event); // This is all ya need to fire the event for every command
	}
}
