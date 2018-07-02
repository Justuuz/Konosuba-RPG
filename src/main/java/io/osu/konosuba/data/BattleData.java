package io.osu.konosuba.data;

import java.sql.ResultSet;
import java.sql.Statement;

import io.osu.konosuba.Konosuba;

public class BattleData {

	// = Cache ==========================
	private long userid;
	private int monsterid;
	private int userhealth;
	private int monsterhealth;
	private long messageid;
	private long channelid;
	private long guildid;

	// ===================================

	private boolean first = true;

	public BattleData(long userid) {
		update(userid);
	}

	private void update(long userid) {
		this.userid = userid;
		try {
			Statement statement = Konosuba.DATABASE.createStatement();
			if(first) {
				statement.execute(
						"CREATE TABLE IF NOT EXISTS 'battles' (" +
								"userid     INTEGER PRIMARY KEY NOT NULL," +
								"monsterid  INTEGER NOT NULL," +
								"userhealth INTEGER NOT NULL DEFAULT 0," +
								"monsterhealth INTEGER NOT NULL DEFAULT 0," +
								"messageid  INTEGER NOT NULL DEFAULT 0," +
								"channelid  INTEGER NOT NULL DEFAULT 0,"+
								"guildid    INTEGER NOT NULL DEFAULT 0" +
								");"
						);
				first = false;
			}
			ResultSet result = statement.executeQuery("SELECT * FROM 'battles' WHERE userid=" + userid +";");
			boolean hasResult = result.next();

			monsterid     = hasResult ? result.getInt("monsterid") : 0;
			userhealth    = hasResult ? result.getInt("userhealth") : 0;
			monsterhealth = hasResult ? result.getInt("monsterhealth") : 0;
			messageid     = hasResult ? result.getLong("messageid") : 0;
			channelid     = hasResult ? result.getLong("channelid") : 0;
			guildid       = hasResult ? result.getLong("guildid") : 0;

			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void update(String key, Object value) {
		try {
			Statement statement = Konosuba.DATABASE.createStatement();
			statement.addBatch("INSERT OR IGNORE INTO 'battles' (userid,"+key+") VALUES ("+userid+",'"+value+"');");
			statement.addBatch("UPDATE 'battles' SET "+key+"='"+value+"' WHERE userid="+userid+";");
			statement.executeBatch();
			statement.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(long userid) {
		try {
			Statement statement = Konosuba.DATABASE.createStatement();
			statement.execute("DELETE FROM 'battles' WHERE userid=" +userid + ";");
			statement.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	// ============================================


	public long getUserId () {
		return userid;
	}

	public int getMonsterId () {
		return monsterid;
	}

	public void setMonsterId (int monsterid) {
		update("monsterid", monsterid);
		this.monsterid = monsterid;
	}

	public int getUserHealth() {
		return userhealth;
	}

	public void setUserHealth(int userhealth) {
		update("userhealth", userhealth);
		this.userhealth = userhealth;
	}

	public int getMonsterHealth() {
		return monsterhealth;
	}


	public void setMonsterHealth(int monsterhealth) {
		update("monsterhealth", monsterhealth);
		this.monsterhealth = monsterhealth;
	}


	public long getMessageId() {
		return messageid;
	}

	public void setMessageId(long messageid) {
		update("messageid",messageid);
		this.messageid = messageid;
	}

	public long getChannelId() {
		return channelid;
	}

	public void setChannelId(long channelid) {
		update("channelid",channelid);
		this.channelid = channelid;
	}

	public long getGuildId() {
		return guildid;
	}

	public void setGuildId(long guildid) {
		update("guildid",guildid);
		this.guildid = guildid;
	}

	public void endSession (long userid) {
		delete(userid);
	}

}
