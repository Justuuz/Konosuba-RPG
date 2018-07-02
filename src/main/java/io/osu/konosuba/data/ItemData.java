package io.osu.konosuba.data;

import io.osu.konosuba.Konosuba;
import org.json.JSONArray;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused", "WeakerAccess"})
public class ItemData {

	// |strength|magic|luck|dex |phys_def|magi_def|health|type|
	// ---------------------------------------------------
	// |INT     |INT  |INT |INT |INT     |INT     |INT   |INT |

	// = Cache  ======================================
	private int itemid;
	private String name;
	private int strength;
	private int magic;
	private int luck;
	private int dex;
	private int phys_def;
	private int magi_def;
	private int health;
	private int buyvalue;
	private int sellvalue;
	private boolean sellable;
	private String description;
	private int type;
	private List<Integer> craft1 = new ArrayList<>();
	private List<Integer> craft2 = new ArrayList<>();
	private List<Integer> craft3 = new ArrayList<>();
	// ===============================================

	private boolean first = true;

	public ItemData(int gearid)  {
		update(gearid);
	}



	private void update(int itemid) {
		try {
			this.itemid = itemid;
			Statement statement = Konosuba.REGISTRY.createStatement();
			if(first) {
				statement.execute(
						"CREATE TABLE IF NOT EXISTS 'items' ("+
								"  itemid       INT PRIMARY KEY NOT NULL," +
								"  name     TEXT NOT NULL," + 
								"  strength INTEGER NOT NULL DEFAULT 0," + 
								"  magic    INTEGER NOT NULL DEFAULT 0," + 
								"  luck     INTEGER NOT NULL DEFAULT 0," + 
								"  dex      INTEGER NOT NULL DEFAULT 0," + 
								"  phys_def INTEGER NOT NULL DEFAULT 0," + 
								"  magi_def INTEGER NOT NULL DEFAULT 0," + 
								"  health   INTEGER NOT NULL DEFAULT 0," + 
								"  buyvalue INTEGER NOT NULL DEFAULT 0," +
								"  sellvalue INTEGER NOT NULL DEFAULT 0," +
								"  sellable  INTEGER NOT NULL DEFAULT 0," +
								" description TEXT NOT NULL," +
								" type    INTEGER NOT NULL," + 
								"craft1 TEXT NOT NULL  DEFAULT '[]'," +
								"craft2 TEXT NOT NULL DEFAULT '[]' ," +
								"craft3 TEXT NOT NULL DEFAULT '[]'" +
								");"
						);
				first = false;
			}
			ResultSet result = statement.executeQuery("SELECT * FROM 'items' WHERE itemid="+ itemid + ";");
			boolean hasResult = result.next();

			name     = hasResult ? result.getString("name") : null;
			strength = hasResult ? result.getInt("strength") : 0;
			magic    = hasResult ? result.getInt("magic") : 0;
			luck     = hasResult ? result.getInt("luck") : 0;
			dex      = hasResult ? result.getInt("dex") : 0;
			phys_def = hasResult ? result.getInt("phys_def") : 0;
			magi_def = hasResult ? result.getInt("magi_def") : 0;
			health   = hasResult ? result.getInt("health") : 0;
			buyvalue = hasResult ? result.getInt("buyvalue") : 0;
			sellvalue = hasResult ? result.getInt("sellvalue") : 0;
			sellable = hasResult && result.getInt("sellable") == 1;
			description = hasResult ? result.getString("description") : null;
			type     = hasResult ? result.getInt("type") : null;
			
			if(hasResult) {
				for(Object c1 : new JSONArray(result.getString("craft1"))) craft1.add((int) c1);
				
				for(Object c2 : new JSONArray(result.getString("craft2"))) craft2.add((int) c2);
				
				for(Object c3 : new JSONArray(result.getString("craft3"))) craft3.add((int) c3);
			}else {
				craft1 = null;
				craft2 = null;
				craft3 = null;
			}
			statement.close();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	private void update(String key, Object value) {
		try {
			Statement statement = Konosuba.REGISTRY.createStatement();
			statement.execute("INSERT OR IGNORE INTO 'items' (itemid, "+key+") VALUES ("+itemid+","+value+");"+
					"UPDATE 'items' SET "+key+"="+value+" WHERE itemid="+itemid+";");
			statement.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public int search(String item){
		try {
			Statement statement = Konosuba.REGISTRY.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM 'items' WHERE name LIKE '" + item + "';");
			if(result.next()) {
				return result.getInt(item);
			}else {
				return 0;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}



	/*
	 * To add more values just create a variable and any getter/setter methods
	 */


	public int getItemId() {
		return itemid;
	}

	public String getName() {
		return name;
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


	public int getBuyValue() {
		return buyvalue;
	}

	public void setBuyValue(int buyvalue)  {
		update("buyvalue", buyvalue);
		this.buyvalue = buyvalue;
	}

	public int getSellValue() {
		return sellvalue;
	}

	public void setSellValue(int sellvalue)  {
		update("sellvalue", sellvalue);
		this.sellvalue = sellvalue;
	}

	public boolean getSellable() {
		return sellable;
	}

	public void setSellable(boolean sellable) {
		update("sellable", sellable ? 1: 0);
		this.sellable = sellable;
	}

	public String getDescription () {
		return description;
	}

	public void setDescription(String description)  {
		update("description", description);
		this.description = description;
	}

	public int getType() {

		return type;
	}

	public void setType(int type) {
		this.type = type;
		update("type", type);
	}
	
	public List<Integer> getCraft1() {
		return craft1;
	}
	
	public List<Integer> getCraft2() {
		return craft2;
	}
	
	public List<Integer> getCraft3() {
		return craft3;
	}
}
