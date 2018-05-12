package io.osu.konosuba.test;

import io.osu.konosuba.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public final class Check extends Command {
	/**
	 * Creates a generic command
	 */
	public Check() {
		super(null, "KONOSUBA>", "Check", null, null, 0);
	}

	@Override
	protected void run(MessageReceivedEvent event, String[] args) {
		try {
			Data data = new Data(event.getAuthor().getIdLong());

			data.setBalance(data.getBalance() + 50);

			event.getChannel().sendMessage("Number: " + data.getBalance()).queue();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}