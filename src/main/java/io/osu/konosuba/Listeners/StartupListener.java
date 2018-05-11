package io.osu.konosuba.Listeners;

import java.sql.Connection;
import java.sql.DriverManager;

import io.osu.konosuba.Command;
import io.osu.konosuba.data.UserData;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;



public class StartupListener extends ListenerAdapter {

	public static Connection CONNECTION;
    static {
    	System.out.println("Error");
        try {
            Class.forName("org.sqlite.JDBC");
            CONNECTION = DriverManager.getConnection("jdbc:sqlite:" + System.getProperty("user.home") + "/git/KonosubaBot/Data/data.db");
        } catch (Exception e) {
            e.printStackTrace();
            CONNECTION=null;
            System.exit(0);
        }
    }
    
    @Override
	public void onReady(ReadyEvent event) {
		System.out.println("Online");
	}
    
    
	
	
}
