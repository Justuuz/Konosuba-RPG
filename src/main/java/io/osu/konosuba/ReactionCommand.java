package io.osu.konosuba;

import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;

public interface ReactionCommand{
	public void run(MessageReactionAddEvent event);
}
