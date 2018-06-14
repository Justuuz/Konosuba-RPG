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
	private String name;
	private int strength;
	private int magic;
	private int luck;
	private int dex;
	private int phys_def;
	private int magi_def;
	private int health;
	private int mana;
	private int mincash;
	private int maxcash;
	private List<Integer> spells;
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
								"monsterid INTEGER PRIMARY KEY NOT NULL DEFAULT 0," +
								"name      TEXT NOT NULL," +		
								"strength  INTEGER NOT NULL DEFAULT 0," +
								"magic     INTEGER NOT NULL DEFAULT 0," +
								"luck      INTEGER NOT NULL DEFAULT 0," +
								"dex       INTEGER NOT NULL DEFAULT 0," +
								"phys_def  INTEGER NOT NULL DEFAULT 0," +
								"magi_def  INTEGER NOT NULL DEFAULT 0," +
								"health    INTEGER NOT NULL DEFAULT 0," +
								"mincash   INTEGER NOT NULL DEFAULT 0," +
								"maxcash   INTEGER NOT NULL DEFAULT 0," +
								"spells    INT NOT NULL DEFAULT '[]'," +
								"drops     INT NOT NULL DEFAULT '[]'," +
								"location  INT NOT NULL DEFAULT '[]'" +
								");"
						);
				first = false;
			}

			ResultSet result = statement.executeQuery("SELECT * FROM 'monster' WHERE monsterid=" + monsterid + ";");
			boolean hasResult = result.next();

			name     = hasResult ? result.getString("name") : null;
			strength = hasResult ? result.getInt("strength") : 0;
			magic    = hasResult ? result.getInt("magic") : 0;
			luck     = hasResult ? result.getInt("luck") : 0;
			dex      = hasResult ? result.getInt("dex") : 0;
			phys_def = hasResult ? result.getInt("phys_def") : 0;
			magi_def = hasResult ? result.getInt("magi_def") : 0;
			health   = hasResult ? result.getInt("health") : 0;
			mana     = hasResult ? result.getInt("mana") : 0;

			if(hasResult) {
				JSONArray raw = new JSONArray(result.getString("drops"));
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

				raw = new JSONArray(result.getString("spells"));
				for(Object obj :  raw) {
					stringList.add((int) obj);
				}
				spells = stringList;
				stringList.clear();
			}else {
				drop = null;
				location = null;
				spells = null;
			}

			statement.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void update(String key, Object value) {
		try {
			Statement statement = Konosuba.CONNECTION2.createStatement();
			statement.addBatch("INSERT OR IGNORE INTO 'monsters' (monsterid,"+key+") VALUES ("+monsterid+",'"+value+"');");
			statement.addBatch("UPDATE 'monsters' SET "+key+"='"+value+"' WHERE monsterid="+monsterid+";");
			statement.executeBatch();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		update("name", name);
		this.name = name;
	}

	public int getStrength() {

		return strength;
	}

	public void setStrength(int strength)  {
		update( "strength", (strength));
		this.strength = strength;

	}

	public int getMagic() {

		return magic;
	}

	public void setMagic(int magic)  {
		update( "magic", (magic));
		this.magic = magic;

	}

	public int getLuck() {

		return luck;
	}

	public void setLuck(int luck)  {
		update( "luck", (luck));
		this.luck = luck;

	}

	public int getDexterity() {

		return dex;
	}

	public void setDexterity(int dexterity)  {
		update( "dex", (dexterity));
		this.dex = dexterity;

	}

	public int getPhysicalDefense() {

		return phys_def;
	}

	public void setPhysicalDefense(int physicalDefense)  {
		update( "phys_def", (physicalDefense));
		this.phys_def = physicalDefense;

	}

	public int getMagicalDefense() {

		return magi_def;
	}

	public void setMagicalDefense(int magicalDefense)  {
		update( "magi_def", (magicalDefense));
		this.magi_def = magicalDefense;

	}

	public int getHealth() {
		return getHitpoints();
	}

	public void setHealth(int health)  {
		setHitpoints(health);
	}

	public int getHitpoints() {

		return health;
	}

	public void setHitpoints(int hitpoints)  {
		update( "health", (hitpoints));
		this.health = hitpoints;

	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana)  {
		update("mana", mana);
		this.mana = mana;
	}

	public int getMinCash() {
		return mincash;
	}

	public int getMaxCash() {
		return maxcash;
	}

	public List<Integer> getSpells() {
		return spells;
	}

	public void setSpells(List<Integer> spells) {
		update("spells", spells);
		this.spells = spells;
	}

	public void addSpells(int spell)  {
		this.spells.add(spell);
		update("spells", this.spells);
	}

	public void removeSpells(int spell)  {
		this.spells.remove(spell);
		update("spells", this.spells);
	}

	public List<Integer> getLocation() {
		return location;
	}

	public List<Integer> getDrop() {
		return drop;
	}


}
