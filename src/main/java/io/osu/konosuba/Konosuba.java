package io.osu.konosuba;

import io.osu.konosuba.listeners.CommandListener;
import io.osu.konosuba.listeners.StartupListener;
import net.dv8tion.jda.bot.sharding.DefaultShardManagerBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.awt.Color;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Konosuba  {

	//public static final Logger LOGGER = LoggerFactory.getLogger(Konosuba.class);
	private static String KOBOSUBA_TOKEN; 
	private static File file  = new File((System.getProperty("user.home") + "/Desktop/Konosuba/key.txt"));
	static {
		try {
			Scanner key = new Scanner(file);
			KOBOSUBA_TOKEN = key.nextLine();
			key.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	// This is now the default color for commands
	public static final Color COLOR = new Color(153,50,204);

	// This is now the default prefix for commands
	public static final String PREFIX = "**";

	// This is the default logger
	public static final Logger LOGGER = LoggerFactory.getLogger(Konosuba.class);

	//this is for pulling information
	public static Connection DATABASE, REGISTRY;
	static {
		try {
			String path = System.getProperty("user.home") + (System.getProperty("os.name").equals("") ? "/Desktop/Konosuba/" : "/Desktop/Stuff/git/KonosubaBot/Data/");
			//noinspection SpellCheckingInspection
			Class.forName("org.sqlite.JDBC");

			//noinspection SpellCheckingInspection
			DATABASE = DriverManager.getConnection("jdbc:sqlite:" + path + "konosuba.db");
			//noinspection SpellCheckingInspection
			REGISTRY = DriverManager.getConnection("jdbc:sqlite:" + path + "konosuba-registry.db");

		} catch (Exception e) {
			e.printStackTrace();
			DATABASE = null;
			REGISTRY = null;
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
