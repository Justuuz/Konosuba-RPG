package io.osu.konosuba.commands;

import java.awt.Color;

import io.osu.konosuba.Command;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Example extends Command {

	//Replace each formal parameter
	protected Example() {
		super(embedColor, prefix, name, description, requiredPermission, requiredID);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run(MessageReceivedEvent event, String[] args) {
		// TODO Auto-generated method stub
		
	}

}
