package io.osu.konosuba.listeners;

import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;



public class StartupListener extends ListenerAdapter {
    
    @Override
	public void onReady(ReadyEvent event) {
		event.getJDA().getPresence().setGame(Game.playing("Under Development"));
	}
    
    
	
	
}
