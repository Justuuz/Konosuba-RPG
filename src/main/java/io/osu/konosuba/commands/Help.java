package io.osu.konosuba.commands;

import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Help extends Command{

	public Help() {
		super(Konosuba.COLOR, Konosuba.PREFIX, "help", "List all commands", null, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run(MessageReceivedEvent event, String[] args) {
		// TODO Auto-generated method stub
		 if(args.length == 1 && !hasArgument(args, 1)) {
			 EmbedBuilder help = new EmbedBuilder();
			 help.appendDescription("test");
			 event.getTextChannel().sendMessage("Command have been sent to your DMs").queue();
			 event.getAuthor().openPrivateChannel().queue((m) -> m.sendMessage(help.build()).queue());
		 }
	}

}
