package io.osu.konosuba;

import io.osu.konosuba.Listeners.CommandListener;
import io.osu.konosuba.Listeners.StartupListener;
import net.dv8tion.jda.bot.sharding.DefaultShardManagerBuilder;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Konosuba  {

	//public static final Logger LOGGER = LoggerFactory.getLogger(Konosuba.class);

	private static Scanner key = new Scanner((System.getProperty("user.home") + "/Desktop/Konosuba/key.txt"));
	private static final String KOBOSUBA_TOKEN = key.nextLine();

	// This is now the default color for commands
	public static final Color COLOR = new Color(153,50,204);

	// This is now the default prefix for commands
	public static final String PREFIX = "*";

	// This is the default logger
	public static final Logger LOGGER = LoggerFactory.getLogger(Konosuba.class);

	//this is for pulling information
	public static Connection CONNECTION1;
	static {
		try {
			String dbPath = System.getProperty("user.home") + (System.getProperty("user.home").startsWith("/home") ? "/konosuba.db" : "/Desktop/Stuff/git/KonosubaBot/Data/structure.db");

			//noinspection SpellCheckingInspection
			Class.forName("org.sqlite.JDBC");
			//noinspection SpellCheckingInspection
			CONNECTION1 = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
		} catch (Exception e) {
			e.printStackTrace();
			CONNECTION1 = null;
			System.exit(0);
		}
	}
	
	public static Connection CONNECTION2;
	static {
		try {
			String dbPath = System.getProperty("user.home") + (System.getProperty("user.home").startsWith("/home") ? "/konosuba.db" : "/Desktop/Stuff/git/KonosubaBot/Data/readData.db");

			//noinspection SpellCheckingInspection
			Class.forName("org.sqlite.JDBC");
			//noinspection SpellCheckingInspection
			CONNECTION2 = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
		} catch (Exception e) {
			e.printStackTrace();
			CONNECTION2 = null;
			System.exit(0);
		}
	}


	public static void main(String[] args) throws LoginException {
		//noinspection SpellCheckingInspection
		new DefaultShardManagerBuilder()
				.setToken(KOBOSUBA_TOKEN)
				.addEventListeners(new CommandListener())
				.addEventListeners(new StartupListener())
				.setBulkDeleteSplittingEnabled(false)
				// .setShardsTotal(1) // XXX DO NOT USE THIS!
				.build();


	}
}
