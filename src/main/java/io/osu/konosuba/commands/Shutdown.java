package io.osu.konosuba.commands;

import io.osu.konosuba.*;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Shutdown extends Command{

	public Shutdown() {
		super(null, "?", "shutdown", "Shutdowns the bot", null, 81911518148440064L);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(MessageReceivedEvent event, String[] args) {
		// TODO Auto-generated method stub
		if(args.length > 1) {
			send(event.getGuild(), event.getChannel(), "**ERROR**: too many argument(s)", true);
			return;
		}
		if(args[0].equalsIgnoreCase("shutdown")) {
			send(event.getGuild(), event.getChannel(), "**Shutting Down!**", true);
			event.getJDA().shutdown();
			System.exit(0);
		}
		
	}

}
