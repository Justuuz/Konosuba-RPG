package io.osu.konosuba;

import java.io.IOException;

import javax.security.auth.login.LoginException;



import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import net.dv8tion.jda.bot.sharding.DefaultShardManagerBuilder;

public class KonoSuba  {
	
	static SimpleReader key = new SimpleReader1L("Desktop/Kobosuba/key.txt");
	private static final String KOBOSUBA_TOKEN = key.nextLine();
	
	
	public static void main(String[] args) throws LoginException, IOException {
		/*ShardManager SHARD_MANAGER = */new DefaultShardManagerBuilder()
				.setToken()
				.setShardsTotal(1)
				.addEventListeners(new Listener(KOBOSUBA_TOKEN))
				
				.setBulkDeleteSplittingEnabled(false)
				
				.build();
		
	}
}
