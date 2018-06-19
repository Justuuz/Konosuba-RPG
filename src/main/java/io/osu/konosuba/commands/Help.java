package io.osu.konosuba.commands;

import java.awt.Color;

import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Help extends Command{

	protected Help() {
		super(Konosuba.COLOR, Konosuba.PREFIX, "help", "List all commands", null, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run(MessageReceivedEvent event, String[] args) {
		// TODO Auto-generated method stub
		 
	}

}
