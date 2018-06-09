package io.osu.konosuba.data;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import io.osu.konosuba.Konosuba;

public class MonsterData {
	
	// = Cache ===========================
	private int monsterid;
	private int strength;
	private int magic;
	private int luck;
	private int dex;
	private int phys_def;
	private int magi_def;
	private int health;
	private int mincash;
	private int maxcash;
	private List<Integer> drop;
	private List<Integer> location;
	
	// ======================================
	
	private boolean first = true;
	
	public MonsterData(int monsterid) {
		update(monsterid);
	}
	
	private void update(int monsterid) {
		this.monsterid = monsterid;
		try {
			Statement statement = Konosuba.CONNECTION2.createStatement();
			if(first) {
				statement.execute(
						"CREATE TABLE IF NOT EXISTS 'monster' (" +
						"monsterid INT PRIMARY KEY NOT NULL DEFAULT 0" +
						"strength  INT NOT NULL DEFAULT 0," +
						"magic     INT NOT NULL DEFAULT 0," +
						"luck      INT NOT NULL DEFAULT 0," +
						"dex       INT NOT NULL DEFAULT 0," +
						"phys_def  INT NOT NULL DEFAULT 0," +
						"magi_def  INT NOT NULL DEFAULT 0," +
						"health    INT NOT NULL DEFAULT 0," +
						"mincash   INT NOT NULL DEFAULT 0," +
						"maxcash   INT NOT NULL DEFAULT 0," +
						"drop      INT NOT NULL DEFAULT '[]'," +
						"location  INT NOT NULL DEFAULT '[]'" +
						");"
						);
				first = false;
			}
			
			ResultSet result = statement.executeQuery("SELECT * FROM 'monster' WHERE monsterid=" + monsterid + ";");
			boolean hasResult = result.next();
			
			strength = hasResult ? result.getInt("strength") : 0;
			magic    = hasResult ? result.getInt("magic") : 0;
			luck     = hasResult ? result.getInt("luck") : 0;
			dex      = hasResult ? result.getInt("dex") : 0;
			phys_def = hasResult ? result.getInt("phys_def") : 0;
			magi_def = hasResult ? result.getInt("magi_def") : 0;
			health   = hasResult ? result.getInt("health") : 0;
			
			if(hasResult) {
				JSONArray raw = new JSONArray(result.getString("drop"));
				List<Integer> stringList = new ArrayList<>();
				for(Object obj : raw) {
					stringList.add((int) obj);
				}
				
				drop = stringList;
				stringList.clear();
				raw = new JSONArray(result.getString("location"));
				for(Object obj : raw) {
					stringList.add((int) obj);
				}
				location = stringList;
				stringList.clear();
			}else {
				drop = null;
				location = null;
			}
			
			statement.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void update(String key, Object value) {
		try {
			Statement statement = Konosuba.CONNECTION2.createStatement();
			statement.addBatch("INSERT OR IGNORE INTO 'monster' (monsterid,"+key+") VALUES ("+monsterid+",'"+value+"');");
			statement.addBatch("UPDATE 'monsterid' SET "+key+"='"+value+"' WHERE monsterid="+monsterid+";");
			statement.executeBatch();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
public int getStrength() {
		
		return strength;
	}
	
	public void setStrength(int strength) throws Exception {
		update( "strength", (strength));
		this.strength = strength;
		
	}
	
	public int getMagic() {
		
		return magic;
	}
	
	public void setMagic(int magic) throws Exception {
		update( "magic", (magic));
		this.magic = magic;
		
	}
	
	public int getLuck() {
		
		return luck;
	}
	
	public void setLuck(int luck) throws Exception {
		update( "luck", (luck));
		this.luck = luck;
		
	}
	
	public int getDexterity() {
		
		return dex;
	}
	
	public void setDexterity(int dexterity) throws Exception {
		update( "dex", (dexterity));
		this.dex = dexterity;
		
	}
	
	public int getPhysicalDefense() {
		
		return phys_def;
	}
	
	public void setPhysicalDefense(int physicalDefense) throws Exception {
		update( "phys_def", (physicalDefense));
		this.phys_def = physicalDefense;
		
	}
	
	public int getMagicalDefense() {
		
		return magi_def;
	}
	
	public void setMagicalDefense(int magicalDefense) throws Exception {
		update( "magi_def", (magicalDefense));
		this.magi_def = magicalDefense;
		
	}

	public int getHealth() {
		return getHitpoints();
	}

	public void setHealth(int health) throws Exception {
		setHitpoints(health);
	}
	
	public int getHitpoints() {
		
		return health;
	}
	
	public void setHitpoints(int hitpoints) throws Exception {
		update( "health", (hitpoints));
		this.health = hitpoints;
		
	}
	
	public int getMinCash() {
		return mincash;
	}
	
	public int getMaxCash() {
		return maxcash;
	}
	
	public List<Integer> getLocation() {
        return location;
    }
	
	public List<Integer> getDrop() {
		return drop;
	}
	

}