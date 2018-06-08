package io.osu.konosuba.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import io.osu.konosuba.Konosuba;

@SuppressWarnings({"unused"})
public class ClassData {
	
	// |strength|magic|luck|dex |phys_def|magi_def|health|classtype|
	// -------------------------------------------------------------
	// |REAL     |REAL  |REAL |REAL |REAL     |REAL     |REAL   |TEXT     |
	
	// = Cache  ======================================
	private String classtype;
	private double strength;
	private double magic;
	private double luck;
	private double dex;
	private double phys_def;
	private double magi_def;
	private double health;
	// ===============================================
	private boolean first = true;
	

	public ClassData(String classtype) throws Exception {
		update(classtype);
	}

    
    private void update(String classtype) throws Exception {
		this.classtype = classtype;
		Statement statement = Konosuba.CONNECTION2.createStatement();
		if(first) {
			statement.execute(
					"CREATE TABLE IF NOT EXISTS 'classes' ("+
						"  classtype TEXT PRIMARY KEY NOT NULL," + 
						"  strength REAL NOT NULL DEFAULT 1.0," + 
						"  magic    REAL NOT NULL DEFAULT 1.0," + 
						"  luck     REAL NOT NULL DEFAULT 1.0," + 
						"  dex      REAL NOT NULL DEFAULT 1.0," + 
						"  def_phys REAL NOT NULL DEFAULT 1.0," + 
						"  def_magi REAL NOT NULL DEFAULT 1.0," + 
						"  health   REAL NOT NULL DEFAULT 1.0" +  
						");"
					);
			first = false;
		}
		ResultSet result = statement.executeQuery("SELECT * FROM 'classes' WHERE classtype="+ classtype + ";");
		boolean hasResult = result.next();
		strength = hasResult ? result.getDouble("strength") : 1.0;
		magic    = hasResult ? result.getDouble("magic") : 1.0;
		luck     = hasResult ? result.getDouble("luck") : 1.0;
		dex      = hasResult ? result.getDouble("dex") : 1.0;
		phys_def = hasResult ? result.getDouble("phys_def") : 1.0;
		magi_def = hasResult ? result.getDouble("magi_def") : 1.0;
		health   = hasResult ? result.getDouble("health") : 1.0;
		statement.close();
		
	}
    
    private void update(String key, Object value) throws Exception {
		Statement statement = Konosuba.CONNECTION2.createStatement();
		statement.execute("INSERT OR IGNORE REALO 'classes' (classtype, "+key+") VALUES ("+classtype+","+value+");"+
							"UPDATE 'classes' SET "+key+"="+value+" WHERE classtype="+classtype+";");
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
		update( "strength", (strength));
		this.strength = strength;
		
	}
	
	public double getMagic() {
		
		return magic;
	}
	
	public void setMagic(double magic) throws Exception {
		update( "magic", (magic));
		this.magic = magic;
		
	}
	
	public double getLuck() {
		
		return luck;
	}
	
	public void setLuck(double luck) throws Exception {
		update( "luck", (luck));
		this.luck = luck;
		
	}
	
	public double getDexterity() {
		
		return dex;
	}
	
	public void setDexterity(double dexterity) throws Exception {
		update( "dex", (dexterity));
		this.dex = dexterity;
		
	}
	
	public double getPhysicalDefense() {
		
		return phys_def;
	}
	
	public void setPhysicalDefense(double physicalDefense) throws Exception {
		update( "phys_def", (physicalDefense));
		this.phys_def = physicalDefense;
		
	}
	
	public double getMagicalDefense() {
		
		return magi_def;
	}
	
	public void setMagicalDefense(double magicalDefense) throws Exception {
		update( "magi_def", (magicalDefense));
		this.magi_def = magicalDefense;
		
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
		update( "health", (hitpodoubles));
		this.health = hitpodoubles;
		
	}
	
	
}
