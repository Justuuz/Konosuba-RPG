package io.osu.konosuba;

import io.osu.konosuba.Listeners.CommandListener;
import net.dv8tion.jda.bot.sharding.DefaultShardManagerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

import javax.security.auth.login.LoginException;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;

public class Konosuba  {

	//public static final Logger LOGGER = LoggerFactory.getLogger(Konosuba.class);

	private static SimpleReader key = new SimpleReader1L((System.getProperty("user.home") + "/Desktop/Konosuba/key.txt"));
	private static final String KOBOSUBA_TOKEN = key.nextLine();

	// This is now the default color for commands
	public static final Color COLOR = new Color(153,50,204);

	// This is now the default prefix for commands
	public static final String PREFIX = "*>";

	//this is for pulling information
	public static Connection CONNECTION;
	static {
		try {
			String dbPath = System.getProperty("user.home") + (System.getProperty("user.home").startsWith("/home") ? "/konosuba.db" : "/git/KonosubaBot/structure.db");

			//noinspection SpellCheckingInspection
			Class.forName("org.sqlite.JDBC");
			//noinspection SpellCheckingInspection
			CONNECTION = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
		} catch (Exception e) {
			e.printStackTrace();
			CONNECTION = null;
			System.exit(0);
		}
	}


	public static void main(String[] args) throws LoginException {
		//noinspection SpellCheckingInspection
		new DefaultShardManagerBuilder()
<<<<<<< HEAD
				.setToken(KOBOSUBA_TOKEN)
=======
				.setToken(null)
>>>>>>> branch 'masterWithSQL' of https://github.com/Thrump/KonosubaBot
				.addEventListeners(new CommandListener())
				.setBulkDeleteSplittingEnabled(false)
				// .setShardsTotal(1) // XXX DO NOT USE THIS!
				.build();


	}
}
