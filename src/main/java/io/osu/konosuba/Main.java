package io.osu.konosuba;

import net.dv8tion.jda.bot.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.bot.sharding.ShardManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import javax.security.auth.login.LoginException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

final class Main {
	@SuppressWarnings("WeakerAccess")
	public static final Logger LOGGER = LoggerFactory.getLogger("Konosuba");
	@SuppressWarnings("WeakerAccess")
	public static Connection DATABASE;
	@SuppressWarnings({"FieldCanBeLocal", "unused"})
	private static ShardManager SHARD_MANAGER;

	public static void main(String[] args) throws LoginException {
		Yaml yaml = new Yaml(new SafeConstructor());
		Map<String,Object> map = yaml.load(Main.class.getClassLoader().getResourceAsStream("data.yaml"));

		// Check map for keys
		if (!map.containsKey("token") || !(map.get("token") instanceof String)) throw new NullPointerException("Discord bot token not in data file");

		// Directory (hardcoded for now)
		// Windows: 'C:\Users\YOU'
		// Linux:   '/home/YOU'
		String directory = System.getProperty("user.home");

		// Database connection
		try {
			//noinspection SpellCheckingInspection
			Class.forName("org.sqlite.JDBC");
			//noinspection SpellCheckingInspection
			DATABASE = DriverManager.getConnection("jdbc:sqlite:" + directory + "/konosuba.db");
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error("Failed to connect to database: " + e.getMessage());
			System.exit(0);
		}

		// Konosuba start
		SHARD_MANAGER = new DefaultShardManagerBuilder()
				.setToken((String) map.get("token"))
				.setBulkDeleteSplittingEnabled(false)
				.build();
	}
}
