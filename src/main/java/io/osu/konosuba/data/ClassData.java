package io.osu.konosuba.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import io.osu.konosuba.Konosuba;

@SuppressWarnings({"unused"})
public class ClassData {

	// |strength|magic|luck|dex |phys_def|magi_def|health|name|
	// -------------------------------------------------------------
	// |REAL     |REAL  |REAL |REAL |REAL     |REAL     |REAL   |TEXT     |

	// = Cache  ======================================
	private int classid;
	private String name;
	private double strength;
	private double magic;
	private double luck;
	private double dex;
	private double phys_def;
	private double magi_def;
	private double health;
	private double mana;
	// ===============================================
	private boolean first = true;


	public ClassData(int classid)  {
		update(classid);
	}


	private void update(int classid)  {
		try {
			this.classid = classid;
			Statement statement = Konosuba.CONNECTION2.createStatement();
			if(first) {
				statement.execute(
						"CREATE TABLE IF NOT EXISTS 'classes' ("+
								"  classid  INTEGER PRIMARY KEY NOT NULL DEFAULT 0, " +
								"  name     TEXT NOT NULL," + 
								"  strength REAL NOT NULL DEFAULT 1.0," + 
								"  magic    REAL NOT NULL DEFAULT 1.0," + 
								"  luck     REAL NOT NULL DEFAULT 1.0," + 
								"  dex      REAL NOT NULL DEFAULT 1.0," + 
								"  phys_def REAL NOT NULL DEFAULT 1.0," + 
								"  magi_def REAL NOT NULL DEFAULT 1.0," + 
								"  health   REAL NOT NULL DEFAULT 1.0," +
								"  mana     REAL NOT NULL DEFAULT 1.0" +
								");"
						);
				first = false;
			}
			ResultSet result = statement.executeQuery("SELECT * FROM 'classes' WHERE classID="+ classid + ";");
			boolean hasResult = result.next();
			name = hasResult ?  result.getString("name") : null;
			strength = hasResult ? result.getDouble("strength") : 1.0;
			magic    = hasResult ? result.getDouble("magic") : 1.0;
			luck     = hasResult ? result.getDouble("luck") : 1.0;
			dex      = hasResult ? result.getDouble("dex") : 1.0;
			phys_def = hasResult ? result.getDouble("phys_def") : 1.0;
			magi_def = hasResult ? result.getDouble("magi_def") : 1.0;
			health   = hasResult ? result.getDouble("health") : 1.0;
			mana     = hasResult ?  result.getDouble("mana") : 1.0;
			statement.close();
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void update(String key, Object value)  {
		try {
			Statement statement = Konosuba.CONNECTION2.createStatement();
			statement.execute("INSERT OR IGNORE REALO 'classes' (classid, "+key+") VALUES ("+classid+","+value+");"+
					"UPDATE 'classes' SET "+key+"="+value+" WHERE classid="+classid+";");
			statement.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}



	/*
	 * To add more values just create a variable and any getter/setter methods
	 */


	// Stats


	public String getName() {
		return name;
	}

	public double getStrength() {

		return strength;
	}

	public void setStrength(double strength)  {
		update( "strength", (strength));
		this.strength = strength;

	}

	public double getMagic() {

		return magic;
	}

	public void setMagic(double magic)  {
		update( "magic", (magic));
		this.magic = magic;

	}

	public double getLuck() {

		return luck;
	}

	public void setLuck(double luck)  {
		update( "luck", (luck));
		this.luck = luck;

	}

	public double getDexterity() {

		return dex;
	}

	public void setDexterity(double dexterity)  {
		update( "dex", (dexterity));
		this.dex = dexterity;

	}

	public double getPhysicalDefense() {

		return phys_def;
	}

	public void setPhysicalDefense(double physicalDefense)  {
		update( "phys_def", (physicalDefense));
		this.phys_def = physicalDefense;

	}

	public double getMagicalDefense() {

		return magi_def;
	}

	public void setMagicalDefense(double magicalDefense)  {
		update( "magi_def", (magicalDefense));
		this.magi_def = magicalDefense;

	}

	public double getHealth() {
		return getHitpodoubles();
	}

	public void setHealth(double health)  {
		setHitpodoubles(health);
	}

	public double getHitpodoubles() {

		return health;
	}

	public void setHitpodoubles(double hitpodoubles)  {
		update( "health", (hitpodoubles));
		this.health = hitpodoubles;

	}

	public double getMana() {
		return mana;
	}

	public void setMana(double mana)  {
		update("mana", mana);
		this.mana = mana;
	}


}
