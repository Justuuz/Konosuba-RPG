package io.osu.konosuba.commands;

import java.awt.Color;

import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.ClientData;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Start extends Command {

	protected Start() {
		super(Konosuba.COLOR, Konosuba.PREFIX, "start", "the journey begins", null, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run(MessageReceivedEvent event, String[] args) {
		
		if(args.length == 1) {
			if(Konosuba.CLIENT_DATA_MANAGER.hasData(event.getChannel().getIdLong())) {
				send(event.getGuild(), event.getChannel(), "You already have started!", true);
				return;
			}else {
				ClientData player = new ClientData(event.getChannel().getIdLong());
				player.setBoots("None");
				player.setCape("None");
				player.setChest("None");
				player.setHelmet("None");
				player.setLeggings("None");
				player.setNecklace("None");
				player.setOffHand("None");
				player.setRing("None");
				player.setBalance(0);
				player.setOnHand("None");
				String name = (event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName());
				send(event.getGuild(), event.getChannel(), "Welcome " + name +"! Today is the day you start your adventure! Before we can start, I must ask"
						+ "traveller, what class are?",false);
				return;
			}
		}
		
		if(args[1].equalsIgnoreCase("classes")) {
			send(event.getGuild(), event.getChannel(), "||Classes||")
		}
		
		
	}

}
