package io.osu.konosuba.test;

import java.awt.Color;

import io.osu.konosuba.Command;
import io.osu.konosuba.ReactionCommand;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;

public  class Test1 extends Command implements ReactionCommand{

	protected Test1() {
		super(embedColor, prefix, name, description, requiredPermission, requiredID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(MessageReactionAddEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void run(MessageReceivedEvent event, String[] args) {
		// TODO Auto-generated method stub
		
	}

	

	

}
