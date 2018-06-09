package io.osu.konosuba.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import io.magiccraftmaster.util.StringUtils;
import io.osu.konosuba.Konosuba;

@SuppressWarnings({"unused", "WeakerAccess"})
public class GearData {
	
	// |strength|magic|luck|dex |phys_def|magi_def|health|type|
	// ---------------------------------------------------
	// |INT     |INT  |INT |INT |INT     |INT     |INT   |INT |
	
	// = Cache  ======================================
	private int gearid;
	private String name;
	private int strength;
	private int magic;
	private int luck;
	private int dex;
	private int phys_def;
	private int magi_def;
	private int health;
	private String type;
	// ===============================================
	
	private boolean first = true;

	public GearData(int gearid) throws Exception {
		update(gearid);
	}

	
    
    private void update(int gearid) throws Exception {
    	this.gearid = gearid;
		Statement statement = Konosuba.CONNECTION2.createStatement();
		if(first) {
			statement.execute(
					"CREATE TABLE IF NOT EXISTS 'gear' ("+
						"  gearid       INT PRIMARY KEY NOT NULL," +
						"  name     TEXT NOT NULL," + 
						"  strength INT NOT NULL DEFAULT 0," + 
						"  magic    INT NOT NULL DEFAULT 0," + 
						"  luck     INT NOT NULL DEFAULT 0," + 
						"  dex      INT NOT NULL DEFAULT 0," + 
						"  phys_def INT NOT NULL DEFAULT 0," + 
						"  magi_def INT NOT NULL DEFAULT 0," + 
						"  health   INT NOT NULL DEFAULT 0," + 
						"  type    TEXT" + 
						");"
					);
			first = false;
		}
		ResultSet result = statement.executeQuery("SELECT * FROM 'gear' WHERE gearid="+ gearid + ";");
		boolean hasResult = result.next();
		
		name     = hasResult ? result.getString("name") : null;
		strength = hasResult ? result.getInt("strength") : 0;
		magic    = hasResult ? result.getInt("magic") : 0;
		luck     = hasResult ? result.getInt("luck") : 0;
		dex      = hasResult ? result.getInt("dex") : 0;
		phys_def = hasResult ? result.getInt("phys_def") : 0;
		magi_def = hasResult ? result.getInt("magi_def") : 0;
		health   = hasResult ? result.getInt("health") : 0;
		type     = hasResult ? result.getString("type") : null;
		statement.close();
		
	}
    
    private void update(String key, Object value) throws Exception {
		Statement statement = Konosuba.CONNECTION2.createStatement();
		statement.execute("INSERT OR IGNORE INTO 'gear' (gearid, "+key+") VALUES ("+gearid+","+value+");"+
							"UPDATE 'gear' SET "+key+"="+value+" WHERE gearid="+gearid+";");
		statement.close();
	}
    
    public int search(String item) throws Exception {
		Statement statement = Konosuba.CONNECTION2.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM 'gear' WHERE name LIKE '" + item + "';");
		if(result.next()) {
			return result.getInt(item);
		}else {
			return 0;
		}
    }



	/*
	 * To add more values just create a variable and any getter/setter methods
	 */


    public int getGearId() {
		return gearid;
	}
    
    public String getName() {
    	return name;
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
	
	public String getType() {
		
		return type;
	}
	
	public void setType(String type) throws Exception{
		this.type = type;
		update("type", type);
	}
}
