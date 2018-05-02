package io.osu.konosuba;

import java.io.IOException;


import io.magiccraftmaster.util.Calculator;
import io.osu.konosuba.commands.Example;
import io.osu.konosuba.commands.Stats;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {
	
	private final Command[] commands = {
		new Example(),
		new Stats()
	};

	CommandListener() throws IOException {
		
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		
		
			
		for (Command command : commands) command.check(event); // This is all ya need to fire the event for every command
	}
}
