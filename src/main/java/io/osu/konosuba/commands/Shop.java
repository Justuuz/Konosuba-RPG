package io.osu.konosuba.commands;

import java.awt.Color;

import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.UserData;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Shop extends Command{

	protected Shop() {
		super(Konosuba.COLOR, Konosuba.PREFIX, "shop", "depending on place, will show a list of shop", null, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run(MessageReceivedEvent event, String[] args) {
		try {
			UserData player = new UserData(event.getAuthor().getIdLong());
			if(player.getStartStatus()) {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
