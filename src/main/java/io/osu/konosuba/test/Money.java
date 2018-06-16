package io.osu.konosuba.test;

import java.awt.Color;
import java.util.List;

import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.UserData;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Money extends Command{

	public Money() {
		super(null, Konosuba.PREFIX, "money", "test", null, 81911518148440064L);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run(MessageReceivedEvent event, String[] args) {
		
		if(args.length == 1) {
			return;
		}
		if(args[1].matches("\\d+")) {
			UserData player = new UserData(event.getAuthor().getIdLong());
			int  money  = Integer.parseInt(args[1	]);
			player.setBalance(player.getBalance() + money);
			event.getChannel().sendMessage("Money added").queue();
		}
		
		if(args[1].equalsIgnoreCase("give")) {
			List<User> user = event.getMessage().getMentionedUsers();
			for(User players : user) {
				UserData player = new UserData(players.getIdLong());
				int money = Integer.parseInt(args[3]);
				player.setBalance(player.getBalance() + money);
				event.getChannel().sendMessage("Money added").queue();
			}
		}
		
	}

}
