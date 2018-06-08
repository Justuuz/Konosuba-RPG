package io.osu.konosuba.Listeners;

import io.osu.konosuba.Command;
import io.osu.konosuba.commands.*;
import io.osu.konosuba.commands.Shutdown;
import io.osu.konosuba.test.Buy_admin;
import io.osu.konosuba.test.Check;
import io.osu.konosuba.test.Creator;
import io.osu.konosuba.test.ItemToss_Admin;
import io.osu.konosuba.test.Item_admin;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {
	
	private final Command[] commands = {
		new Creator(),
		new Battle(),
		new Shutdown(),
		new Start(),
		new Start2(),
		new Equip(),
		new Remove(),
		new Inventory(),
		new Buy_admin(),
		new Profile(),
		new Item_admin(),
		new ItemToss_Admin(),
		new Check(),
	};

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		for (Command command : commands) command.check(event); // This is all ya need to fire the event for every command
	}
}
