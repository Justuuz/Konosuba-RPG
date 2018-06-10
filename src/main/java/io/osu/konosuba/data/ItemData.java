package io.osu.konosuba.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import io.magiccraftmaster.util.StringUtils;
import io.osu.konosuba.Konosuba;

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
	private int mana;
	private int buyvalue;
	private int sellvalue;
	private boolean sellable;
	private String description;
	private int type;
	// ===============================================
	
	private boolean first = true;

	public ItemData(int gearid) throws Exception {
		update(gearid);
	}

	
    
    private void update(int itemid) throws Exception {
    	this.itemid = itemid;
		Statement statement = Konosuba.CONNECTION2.createStatement();
		if(first) {
			statement.execute(
					"CREATE TABLE IF NOT EXISTS 'items' ("+
						"  gearid       INT PRIMARY KEY NOT NULL," +
						"  name     TEXT NOT NULL," + 
						"  strength INT NOT NULL DEFAULT 0," + 
						"  magic    INT NOT NULL DEFAULT 0," + 
						"  luck     INT NOT NULL DEFAULT 0," + 
						"  dex      INT NOT NULL DEFAULT 0," + 
						"  phys_def INT NOT NULL DEFAULT 0," + 
						"  magi_def INT NOT NULL DEFAULT 0," + 
						"  health   INT NOT NULL DEFAULT 0," + 
						"  mana     INT NOT NULL DEFAULT 0," +
						"  buyvalue INT NOT NULL DEFAULT 0," +
						"  sellvalue INT NOT NULL DEFAULT 0," +
						"  sellable  INT NOT NULL DEFAULT 0," +
						" description TEXT NOT NULL," +
						"  type    INT" + 
						");"
					);
			first = false;
		}
		ResultSet result = statement.executeQuery("SELECT * FROM 'items' WHERE gearid="+ itemid + ";");
		boolean hasResult = result.next();
		
		name     = hasResult ? result.getString("name") : null;
		strength = hasResult ? result.getInt("strength") : 0;
		magic    = hasResult ? result.getInt("magic") : 0;
		luck     = hasResult ? result.getInt("luck") : 0;
		dex      = hasResult ? result.getInt("dex") : 0;
		phys_def = hasResult ? result.getInt("phys_def") : 0;
		magi_def = hasResult ? result.getInt("magi_def") : 0;
		health   = hasResult ? result.getInt("health") : 0;
		mana     = hasResult ?  result.getInt("mana") : 0;
		buyvalue = hasResult ? result.getInt("buyvalue") : 0;
		sellvalue = hasResult ? result.getInt("sellvalue") : 0;
		sellable = hasResult && result.getInt("sellable") == 1;
		description = hasResult ? result.getString("description") : null;
		type     = hasResult ? result.getInt("type") : null;
		statement.close();
		
	}
    
    private void update(String key, Object value) throws Exception {
		Statement statement = Konosuba.CONNECTION2.createStatement();
		statement.execute("INSERT OR IGNORE INTO 'gear' (gearid, "+key+") VALUES ("+itemid+","+value+");"+
							"UPDATE 'gear' SET "+key+"="+value+" WHERE gearid="+itemid+";");
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


    public int getItemId() {
		return itemid;
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
	
	public int getMana() {
		return mana;
	}
	
	public void setMana(int mana) throws Exception {
		update("mana", mana);
		this.mana = mana;
	}
	
	public int getBuyValue() {
		return buyvalue;
	}
	
	public void setBuyValue(int buyvalue) throws Exception {
		update("buyvalue", buyvalue);
		this.buyvalue = buyvalue;
	}
	
	public int getSellValue() {
		return sellvalue;
	}
	
	public void setSellValue(int sellvalue) throws Exception {
		update("sellvalue", sellvalue);
		this.sellvalue = sellvalue;
	}
	
	public boolean getSellable() {
		return sellable;
	}
	
	public void setSellable(boolean sellable) throws Exception{
		update("sellable", sellable ? 1: 0);
		this.sellable = sellable;
	}
	
	public String getDescription () {
		return description;
	}
	
	public void setDescription(String description) throws Exception {
		update("description", description);
		this.description = description;
	}
	
	public int getType() {
		
		return type;
	}
	
	public void setType(int type) throws Exception{
		this.type = type;
		update("type", type);
	}
}
