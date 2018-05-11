package io.osu.konosuba.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import io.osu.konosuba.Konosuba;

@SuppressWarnings({"unused"})
public class ClassData {
	
	// |strength|magic|luck|dex |phys_def|magi_def|health|classtype|
	// -------------------------------------------------------------
	// |INT     |INT  |INT |INT |INT     |INT     |INT   |TEXT     |
	
	// = Cache  ======================================
	private String classtype;
	private double strength;
	private double magic;
	private double luck;
	private double dex;
	private double phys_def;
	private double magi_def;
	private double health;


	public ClassData(String classtype) throws Exception {
		this.classtype = classtype;
		update(Konosuba.CONNECTION, "classes", classtype);
	}

    
    private void update(Connection connection, String table, String classtype) throws Exception {
		this.classtype = classtype;
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM '" +table+"' WHERE classtype="+ classtype + ";");
		if(result.next()) {
			
			strength = result.getInt("strength");
			magic    = result.getInt("magic");
			luck     = result.getInt("luck");
			dex      = result.getInt("dex");
			phys_def = result.getInt("phys_def");
			magi_def = result.getInt("magi_def");
			health   = result.getInt("health");
			
		
		}else {
			// Default	
			strength = 0;
			magic    = 0;
			luck     = 0;
			dex      = 0;
			phys_def = 0;
			magi_def = 0;
			health   = 0;
			
			
		} 
		statement.close();
		
	}
    
    private void update(Connection connection, String table, String key, String value) throws Exception {
		Statement statement = connection.createStatement();
		statement.execute(
				"CREATE TABLE IF NOT EXISTS '"+ table + "' ("+
					"  classtype TEXT PRIMARY KEY NOT NULL," + 
					"  strength INTEGER NOT NULL DEFAULT 0," + 
					"  magic    INTEGER NOT NULL DEFAULT 0," + 
					"  luck     INTEGER NOT NULL DEFAULT 0," + 
					"  dex      INTEGER NOT NULL DEFAULT 0," + 
					"  def_phys INTEGER NOT NULL DEFAULT 0," + 
					"  def_magi INTEGER NOT NULL DEFAULT 0," + 
					"  health   INTEGER NOT NULL DEFAULT 0" +  
					");"
	);
		statement.execute("INSERT OR IGNORE INTO '" + table + "' (classtype, "+key+") VALUES ("+classtype+","+value+");"+
							"UPDATE '"+ table + "' SET "+key+"="+value+" WHERE classtype="+classtype+";");
		statement.close();
	}



	/*
	 * To add more values just create a variable and any getter/setter methods
	 */


	// Stats

	
	
	
    public double getStrength() {
		
		return strength;
	}
	
	public void setStrength(double strength) throws Exception {
		this.strength = strength;
		update(Konosuba.CONNECTION, "classtype", "strength", Double.toString(strength));
	}
	
	public double getMagic() {
		
		return magic;
	}
	
	public void setMagic(double magic) throws Exception {
		this.magic = magic;
		update(Konosuba.CONNECTION, "classtype", "magic", Double.toString(magic));
	}
	
	public double getLuck() {
		
		return luck;
	}
	
	public void setLuck(double luck) throws Exception {
		this.luck = luck;
		update(Konosuba.CONNECTION, "classtype", "luck", Double.toString(luck));
	}
	
	public double getDexterity() {
		
		return dex;
	}
	
	public void setDexterity(double dexterity) throws Exception {
		this.dex = dexterity;
		update(Konosuba.CONNECTION, "classtype", "dex", Double.toString(dexterity));
	}
	
	public double getPhysicalDefense() {
		
		return phys_def;
	}
	
	public void setPhysicalDefense(double physicalDefense) throws Exception {
		this.phys_def = physicalDefense;
		update(Konosuba.CONNECTION, "classtype", "phys_def", Double.toString(physicalDefense));
	}
	
	public double getMagicalDefense() {
		
		return magi_def;
	}
	
	public void setMagicalDefense(double magicalDefense) throws Exception {
		this.magi_def = magicalDefense;
		update(Konosuba.CONNECTION, "classtype", "magi_def", Double.toString(magicalDefense));
	}

	public double getHealth() {
		return getHitpodoubles();
	}

	public void setHealth(double health) throws Exception {
		setHitpodoubles(health);
	}
	
	public double getHitpodoubles() {
		
		return health;
	}
	
	public void setHitpodoubles(double hitpodoubles) throws Exception {
		this.health = hitpodoubles;
		update(Konosuba.CONNECTION, "classtype", "health", Double.toString(hitpodoubles));
	}
	
	
}
