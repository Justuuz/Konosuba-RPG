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
	private int usermana;
	private int monstermana;
	
	// ===================================
	
	private boolean first = true;
	
	public BattleData(long userid) {
		update(userid);
	}
	
	private void update(long userid) {
		this.userid = userid;
		try {
			Statement statement = Konosuba.CONNECTION1.createStatement();
			if(first) {
				statement.execute(
						"CREATE TABLE IF NOT EXISTS 'battles' (" +
								"userid     INTEGER PRIMARY KEY NOT NULL," +
								"monsterid  INTEGER NOT NULL," +
								"userhealth INTEGER NOT NULL DEFAULT 0," +
								"usermana   INTEGER NOT NULL DEFAULT 0," +
								"monsterhealth INTEGER NOT NULL DEFAULT 0," +
								"monstermana INTEGER NOT NULL DEFAULT 0" +
								");"
						);
					first = false;
			}
			ResultSet result = statement.executeQuery("SELECT * FROM 'battles' WHERE userid=" + userid +";");
			boolean hasResult = result.next();
			
			monsterid     = hasResult ? result.getInt("monsterid") : null;
			userhealth    = hasResult ? result.getInt("userhealth") : 0;
			usermana      = hasResult ? result.getInt("usermana") : 0;
			monsterhealth = hasResult ? result.getInt("monsterhealth") : 0;
			monstermana   = hasResult ? result.getInt("monstermana") : 0;
			
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void update(String key, Object value) {
		try {
		Statement statement = Konosuba.CONNECTION1.createStatement();
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
			Statement statement = Konosuba.CONNECTION1.createStatement();
			statement.execute("DELETE * FROM 'battles' WHERE userid=" +userid + ";");
			statement.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// ============================================
	
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
	
	public int getUserMana() {
		return usermana;
	}
	
	public void setUserMana(int usermana)  {
		update("usermana", usermana);
		this.usermana = usermana;
	}
	
	public void setMonsterHealth(int monsterhealth) {
		update("monsterhealth", monsterhealth);
		this.monsterhealth = monsterhealth;
	}
	
	public int getMonsterMana() {
		return monstermana;
	}
	
	public void setMana(int monstermana) {
		update("monstermana", monstermana);
		this.monstermana = monstermana;
	}
	
	public void endSession (long userid) {
		delete(userid);
	}

}
