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
						"CREATE TABLE IF NOT EXISTS 'battle' (" +
								"userid     INT PRIMARY KEY NOT NULL," +
								"monsterid  INT NOT NULL," +
								"userhealth INT NOT NULL," +
								"monsterhealth INT NOT NULL" +
								");"
						);
					first = false;
			}
			ResultSet result = statement.executeQuery("SELECT * FROM 'battle' WHERE userid=" + userid +";");
			boolean hasResult = result.next();
			
			monsterid     = hasResult ? result.getInt("monsterid") : null;
			userhealth    = hasResult ? result.getInt("userhealth") : 0;
			monsterhealth = hasResult ? result.getInt("monsterhealth") : 0;
			
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void update(String key, Object value) {
		try {
		Statement statement = Konosuba.CONNECTION1.createStatement();
		statement.addBatch("INSERT OR IGNORE INTO 'client' (userid,"+key+") VALUES ("+userid+",'"+value+"');");
		statement.addBatch("UPDATE 'client' SET "+key+"='"+value+"' WHERE userid="+userid+";");
		statement.executeBatch();
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
	
	public void setMonsterHealth(int monsterhealth) {
		update("monsterhealth", monsterhealth);
		this.monsterhealth = monsterhealth;
	}

}
