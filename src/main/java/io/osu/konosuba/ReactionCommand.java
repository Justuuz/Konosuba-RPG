package io.osu.konosuba;

import java.awt.Color;

import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;

public interface ReactionCommand{
	public void run(MessageReactionAddEvent event);
}
