package io.magiccraftmaster.dawn.data;

import io.magiccraftmaster.dawn.Dawn;
import io.magiccraftmaster.dawn.data.sql.StatementBuilder;
import io.magiccraftmaster.util.ArrayUtils;
import io.magiccraftmaster.util.SqlUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class GuildData {
	private static final GuildData[] CACHE = new GuildData[15];
	private static int OBJECT_CREATIONS = 0;

	/*
	 * +--+------+-----+------+---------+----------+-------------+--------------+-------------+
	 * |id|prefix|rules|colors|team_swap|use_filter|show_filtered|global_economy|local_economy|
	 * +--+------+-----+------+---------+----------+-------------+--------------+-------------+
	 */

	// ====================================================================================================
	private static final String KEY_PREFIX         = "prefix";
	private static final String KEY_RULES          = "rules";
	private static final String KEY_COLORS         = "colors";
	private static final String KEY_TEAM_SWAP      = "team_swap";
	private static final String KEY_USE_FILTER     = "use_filter";
	private static final String KEY_SHOW_FILTERED  = "show_filtered";
	private static final String KEY_GLOBAL_ECONOMY = "global_economy";
	private static final String KEY_LOCAL_ECONOMY  = "local_economy";
	// ====================================================================================================

	/**
	 * @param bot a bot or null
	 * @param id the guild id
	 * @return the requested guild data object
	 */
	@Nonnull
	public static GuildData request(Dawn bot, long id) {
		GuildData requestedData = null;
		for (int i=0, cacheLength=CACHE.length; i<cacheLength; i++) if (CACHE[i] != null && CACHE[i].bot == bot && CACHE[i].id == id) {requestedData = CACHE[i]; CACHE[i] = null; break;}

		if (requestedData == null && CACHE[CACHE.length-1] != null) {requestedData = CACHE[CACHE.length-1];  CACHE[CACHE.length-1].updateTo(bot, id); CACHE[CACHE.length-1] = null;}

		ArrayUtils.cacheDrop(CACHE);
		if (requestedData == null) {
			OBJECT_CREATIONS++;
			requestedData = new GuildData(bot, id);
			if (OBJECT_CREATIONS > CACHE.length) bot.getLogger().error("Creating junk data");
		}
		CACHE[0] = requestedData;

		return CACHE[0];
	}

	private static String getTable(Dawn bot) {return "discord_v2_" + (bot != null ? bot.getData().getName().toLowerCase() : "shared") + "_guilds";}

	private final StatementBuilder statementBuilder = new StatementBuilder();
	private Dawn bot;
	private long id;

	// = Cache ============================================================================================
	private String customPrefix;      // Custom prefix
	private String[] rules;           // Rules
	private boolean colorEnabled;     // Allow default colors
	private boolean teamSwap;         // Allow team jumping
	private boolean useFilter;        // Allow curse filter
	private boolean showFiltered;     // Show filtered messages
	private boolean useGlobalEconomy; // Allow global econ usage
	private boolean useLocalEconomy;  // Allow local econ usage
	// ====================================================================================================

	private GuildData(Dawn bot, long id) {
		updateTo(bot, id);
	}

	private void updateTo(Dawn bot, long id) {
		this.bot = bot;
		this.id = id;
		try {
			Statement statement = Dawn.DATABASE.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM '" + getTable(bot) + "' WHERE id=" + id + ";");
			if (resultSet.next()) {
				customPrefix     = resultSet.getString(KEY_PREFIX);
				rules            = SqlUtils.fromJsonArray(resultSet.getString(KEY_RULES));
				colorEnabled     = resultSet.getInt(KEY_COLORS) == 1;
				teamSwap         = resultSet.getInt(KEY_TEAM_SWAP) == 1;
				useFilter        = resultSet.getInt(KEY_USE_FILTER) == 1;
				showFiltered     = resultSet.getInt(KEY_SHOW_FILTERED) == 1;
				useGlobalEconomy = resultSet.getInt(KEY_GLOBAL_ECONOMY) == 1;
				useLocalEconomy  = resultSet.getInt(KEY_LOCAL_ECONOMY) == 1;
			} else {
				// Defaults
				customPrefix     = null;
				rules            = new String[0];
				colorEnabled     = false;
				teamSwap         = false;
				useFilter        = false;
				showFiltered     = false;
				useGlobalEconomy = true;
				useLocalEconomy  = false;
			}
			statement.close();
		} catch (SQLException e) {
			bot.getLogger().error("SQL database query failed");
			e.printStackTrace();
		}
	}

	private void update(String key, Object value) {
		try {
			statementBuilder.bind("id", id);
			statementBuilder.bind(key, value);
			Statement statement = Dawn.DATABASE.createStatement();

			// Create table if necessary
			statement.execute(
					"CREATE TABLE IF NOT EXISTS '" + getTable(bot) + "' (" +
								"id INTEGER PRIMARY KEY NOT NULL," +
								"prefix TEXT," +
								"rules TEXT NOT NULL DEFAULT '[]'," +
								"colors INTEGER NOT NULL DEFAULT 0," +
								"team_swap INTEGER NOT NULL DEFAULT 0," +
								"use_filter INTEGER NOT NULL DEFAULT 0," +
								"show_filtered INTEGER NOT NULL DEFAULT 0," +
								"global_economy INTEGER NOT NULL DEFAULT 1," +
								"local_economy INTEGER NOT NULL DEFAULT 0" +
							");"
			);

			statement.execute(statementBuilder.getStatement(getTable(bot), id));
			statement.close();
			statementBuilder.reset();
		} catch (Exception e) {
			Dawn.LOGGER.error("SQL database update failed: " + e.getMessage());
			//e.printStackTrace();
		}
	}

	// = Getters and setters ==============================================================================

	/**
	 * @return the custom guild prefix or <code>null</code>
	 */
	@Nullable
	public String getCustomPrefix() {
		return customPrefix;
	}

	/**
	 * Sets the custom guild prefix
	 * @param customPrefix the custom prefix
	 */
	public void setCustomPrefix(String customPrefix) {
		this.customPrefix = customPrefix;
		update(KEY_PREFIX, customPrefix);
	}

	/**
	 * @return the guild rules
	 */
	@Nonnull
	public String[] getRules() {
		return rules;
	}

	/**
	 * Sets the guild rules
	 * @param rules the array of rules
	 */
	public void setRules(String[] rules) {
		this.rules = rules;
		update(KEY_RULES, rules);
	}

	/**
	 * @return true if the bot's colors are enabled (defaults to <code>false</code>)
	 */
	public boolean isColorEnabled() {
		return colorEnabled;
	}

	/**
	 * Sets whether the bot should use it's colors in the guild
	 * @param colorEnabled if the bot's colors are enabled
	 */
	public void setColorEnabled(boolean colorEnabled) {
		this.colorEnabled = colorEnabled;
		update(KEY_COLORS, colorEnabled);
	}

	/**
	 * @return true if users can swap/leave teams freely (defaults to <code>false</code>)
	 */
	public boolean isTeamSwap() {
		return teamSwap;
	}

	/**
	 * Sets if users can swap/leave teams freely
	 * @param teamSwap if swapping/leaving is allowed
	 */
	public void setTeamSwap(boolean teamSwap) {
		this.teamSwap = teamSwap;
		update(KEY_TEAM_SWAP, teamSwap);
	}

	/**
	 * @return true if the guild should use the curse filter
	 */
	public boolean isUseFilter() {
		return useFilter;
	}

	/**
	 * Sets if the curse filter should be used
	 * @param useFilter if the curse filter should be used
	 */
	public void setUseFilter(boolean useFilter) {
		this.useFilter = useFilter;
		update(KEY_USE_FILTER, useFilter);
	}

	/**
	 * @return true if the guild should show the blocked message with the filtered content
	 */
	public boolean isShowFiltered() {
		return showFiltered;
	}

	/**
	 * Sets if the filtered message should be shown
	 * @param showFiltered if the filtered content should be shown
	 */
	public void setShowFiltered(boolean showFiltered) {
		this.showFiltered = showFiltered;
		update(KEY_SHOW_FILTERED, showFiltered);
	}

	/**
	 * @return true if the global economy is supported (default: <code>true</code>)
	 */
	public boolean isUseGlobalEconomy() {
		return useGlobalEconomy;
	}

	/**
	 * Enables/disabled global economy
	 * @param useGlobalEconomy if the global economy should be used
	 */
	public void setUseGlobalEconomy(boolean useGlobalEconomy) {
		this.useGlobalEconomy = useGlobalEconomy;
		update(KEY_GLOBAL_ECONOMY, useGlobalEconomy);
	}

	/**
	 * @return true if the local economy is supported (default: <code>false</code>)
	 */
	public boolean isUseLocalEconomy() {
		return useLocalEconomy;
	}

	/**
	 * Enables/disabled local economy
	 * @param useLocalEconomy if the local economy should be used
	 */
	public void setUseLocalEconomy(boolean useLocalEconomy) {
		this.useLocalEconomy = useLocalEconomy;
		update(KEY_LOCAL_ECONOMY, useLocalEconomy);
	}
}
