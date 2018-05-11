package io.osu.konosuba.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import components.map.Map;
import io.osu.konosuba.Konosuba;

@SuppressWarnings({"unused", "WeakerAccess"})
public class GearData {
	
	// |strength|magic|luck|dex |phys_def|magi_def|health|type|
	// ---------------------------------------------------
	// |INT     |INT  |INT |INT |INT     |INT     |INT   |INT |
	
	// = Cache  ======================================
	private String gear;
	private int strength;
	private int magic;
	private int luck;
	private int dex;
	private int phys_def;
	private int magi_def;
	private int health;
	private String type;
	

	public GearData(String gear) throws Exception {
		this.gear = gear;
		update(Konosuba.CONNECTION, "gear", gear);
	}

	
    
    private void update(Connection connection, String table, String gear) throws Exception {
		this.gear = gear;
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM '" +table+"' WHERE gear="+ gear + ";");
		if(result.next()) {
			
			strength = result.getInt("strength");
			magic    = result.getInt("magic");
			luck     = result.getInt("luck");
			dex      = result.getInt("dex");
			phys_def = result.getInt("phys_def");
			magi_def = result.getInt("magi_def");
			health   = result.getInt("health");
			type     = result.getString("type");
		
		}else {
			// Default	
			strength = 0;
			magic    = 0;
			luck     = 0;
			dex      = 0;
			phys_def = 0;
			magi_def = 0;
			health   = 0;
			type     = null;
			
			
		} 
		statement.close();
		
	}
    
    private void update(Connection connection, String table, String key, String value) throws Exception {
		Statement statement = connection.createStatement();
		statement.execute(
				"CREATE TABLE IF NOT EXISTS '"+ table + "' ("+
					"  gear     TEXT PRIMARY KEY NOT NULL," + 
					"  strength INTEGER NOT NULL DEFAULT 0," + 
					"  magic    INTEGER NOT NULL DEFAULT 0," + 
					"  luck     INTEGER NOT NULL DEFAULT 0," + 
					"  dex      INTEGER NOT NULL DEFAULT 0," + 
					"  def_phys INTEGER NOT NULL DEFAULT 0," + 
					"  def_magi INTEGER NOT NULL DEFAULT 0," + 
					"  health   INTEGER NOT NULL DEFAULT 0," + 
					"  type    TEXT" + 
					");"
	);
		statement.execute("INSERT OR IGNORE INTO '" + table + "' (gear, "+key+") VALUES ("+gear+","+value+");"+
							"UPDATE '"+ table + "' SET "+key+"="+value+" WHERE gear="+gear+";");
		statement.close();
	}



	/*
	 * To add more values just create a variable and any getter/setter methods
	 */


	

	
	
	
	
    public int getStrength() throws Exception {
		
		return strength;
	}
	
	public void setStrength(int strength) throws Exception {
		this.strength = strength;
		update(Konosuba.CONNECTION, "gear", "strength", Integer.toString(strength));
	}
	
	public int getMagic() throws Exception {
		
		return magic;
	}
	
	public void setMagic(int magic) throws Exception {
		this.magic = magic;
		update(Konosuba.CONNECTION, "gear", "magic", Integer.toString(magic));
	}
	
	public int getLuck() throws Exception {
		
		return luck;
	}
	
	public void setLuck(int luck) throws Exception {
		this.luck = luck;
		update(Konosuba.CONNECTION, "gear", "luck", Integer.toString(luck));
	}
	
	public int getDexterity() throws Exception {
		
		return dex;
	}
	
	public void setDexterity(int dexterity) throws Exception {
		this.dex = dexterity;
		update(Konosuba.CONNECTION, "gear", "dex", Integer.toString(dexterity));
	}
	
	public int getPhysicalDefense() throws Exception {
		
		return phys_def;
	}
	
	public void setPhysicalDefense(int physicalDefense) throws Exception {
		this.phys_def = physicalDefense;
		update(Konosuba.CONNECTION, "gear", "phys_def", Integer.toString(physicalDefense));
	}
	
	public int getMagicalDefense() throws Exception {
		
		return magi_def;
	}
	
	public void setMagicalDefense(int magicalDefense) throws Exception {
		this.magi_def = magicalDefense;
		update(Konosuba.CONNECTION, "gear", "magi_def", Integer.toString(magicalDefense));
	}
	
	public int getHitpoints() throws Exception {
		
		return health;
	}
	
	public void setHitpoints(int hitpoints) throws Exception {
		this.health = hitpoints;
		update(Konosuba.CONNECTION, "gear", "health", Integer.toString(hitpoints));
	}
	
	public String getType() throws Exception{
		
		return type;
	}
	
	public void setType(String type) throws Exception{
		this.type = type;
		update(Konosuba.CONNECTION, "gear", "type", type);
	}
}
