package io.osu.konosuba;

import java.awt.Color;

import java.io.File;
import java.io.IOException;

import javax.security.auth.login.LoginException;
import io.osu.konosuba.data.*;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import net.dv8tion.jda.bot.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.bot.sharding.ShardManager;

public class Konosuba  {
	
	static SimpleReader key = new SimpleReader1L((System.getProperty("user.home") + "/Desktop/Konosuba/key.txt"));
	private static final String KOBOSUBA_TOKEN = key.nextLine();
	
	// This is now the default color for commands
		public static final Color COLOR = new Color(153,50,204);

		// This is now the default prefix for commands
		public static final String PREFIX = "*";
		
		//this is for pulling information
		public static final ClientDataManager CLIENT_DATA_MANAGER = new ClientDataManager(new File(System.getProperty("user.home") + "/git\\KonosubaBot\\Data\\User.JSON"));
		public static final GearDataManager GEAR_DATA_MANAGER =  new GearDataManager(new File(System.getProperty("user.home") + "/git\\KonosubaBot\\Data\\Gears.JSON"));
		public static final ClassDataManager CLASS_DATA_MANAGER =  new ClassDataManager(new File(System.getProperty("user.home") + "/git\\KonosubaBot\\Data\\Classes.JSON"));

	
	
	public static void main(String[] args) throws LoginException, IOException {
		ShardManager SHARD_MANAGER = new DefaultShardManagerBuilder()
				.setToken(KOBOSUBA_TOKEN)
				.setShardsTotal(1)
				.addEventListeners(new CommandListener())
				
				.setBulkDeleteSplittingEnabled(false)
				
				.build();
		Konosuba.CLIENT_DATA_MANAGER.load();
		Konosuba.GEAR_DATA_MANAGER.load();
		Konosuba.CLASS_DATA_MANAGER.load();
		key.close();
		
	}
}
