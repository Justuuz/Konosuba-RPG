
package io.osu.konosuba.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import io.osu.konosuba.Konosuba;

public class UserData {
	
	//  |userid|balance|battling|starting|classes|helm|chest|legs|boots|cape|ring|neck|on_hand|off_hand|strength|magic|luck|dex |phys_def|magi_def|health|invent|item|location|
	//	----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//  |INT  |TEXT   |INT     |INT     |TEXT   |TEXT|TEXT |TEXT|TEXT |TEXT|TEXT|TEXT|TEXT   |TEXT    |INT     |INT  |INT |INT |INT     |INT     |INT   |TEXT  |TEXT|TEXT    |
	
	
	// = Cache ===================================
	private long userid;
	private int balance;
	private boolean battling;
	private boolean starting;
	private String classes;
	private String helm;
	private String chest;
	private String legs;
	private String boots;
	private String cape;
	private String ring;
	private String neck;
	private String on_hand;
	private String off_hand;
	private int strength;
	private int magic;
	private int luck;
	private int dex;
	private int phys_def;
	private int magi_def;
	private int health;
	private List<List<String>> invent;
	private HashMap<String, Integer> item;
	private String location;
	 
	// ===========================================
	
	
	public UserData(long userid) throws Exception {
		this.userid = userid;
		update(Konosuba.CONNECTION, this.userid);
	}	
	
	private void update(Connection connection, long userid) throws Exception {
		this.userid = userid;
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM 'client' WHERE userid="+ userid + ";");
		if(result.next()) {
			balance  = result.getInt("balance");
			battling = result.getInt("battling") == 1;
			starting = result.getInt("starting") == 1;
			classes  = result.getString("classes");
			helm     = result.getString("helm");
			chest    = result.getString("chest");
			legs     = result.getString("legs");
			boots    = result.getString("boots");
			cape     = result.getString("cape");
			ring     = result.getString("ring");
			neck     = result.getString("neck");
			on_hand  = result.getString("on_hand");
			off_hand = result.getString("off_hand");
			strength = result.getInt("strength");
			magic    = result.getInt("magic");
			luck     = result.getInt("luck");
			dex      = result.getInt("dex");
			phys_def = result.getInt("phys_def");
			magi_def = result.getInt("magi_def");
			health   = result.getInt("health");
			
			JSONArray raw = new JSONArray(result.getString("invent"));
			List<List<String>> stringListList = new ArrayList<>();
			List<String> stringList = new ArrayList<>();
			for (Object obj : raw) {
			    for (Object obj2 : ((JSONArray) obj)) {
			        stringList.add((String) obj2);
			    }
			    stringListList.add(new ArrayList<>(stringList)); // Fixes clearing
			    stringList.clear();  
			}
			invent  = stringListList;
			
			JSONObject raw2 = new JSONObject(result.getString("item"));
			HashMap<String, Integer> stringMap = new HashMap<>();
			raw2.toMap().forEach((k,v) -> stringMap.put(k, (int)v));
			item = stringMap;
			
			location = result.getString("location");
			
			
			
		}else {
			// Default
			balance  = 0;
			battling = false;
			starting = false;
			classes  = null;
			helm     = null;
			chest    = null;
			legs     = null;
			boots    = null;
			cape     = null;
			ring     = null;
			neck     = null;
			on_hand  = null;
			off_hand = null;
			strength = 0;
			magic    = 0;
			luck     = 0;
			dex      = 0;
			phys_def = 0;
			magi_def = 0;
			health   = 0;
			invent = null;
			item = null;
			location = null;
			
		} 
		statement.close();
		
		
	}
	
	private void update(Connection connection, String key, String value) throws Exception {
		Statement statement = connection.createStatement();
		statement.execute(
				"CREATE TABLE IF NOT EXISTS `client` ("+
					"  id       INTEGER PRIMARY KEY NOT NULL," + 
					"  balance  INTEGER NOT NULL DEFAULT 0," + 
					"  battling INTEGER NOT NULL DEFAULT 0," + 
					"  starting INTEGER NOT NULL DEFAULT 0," + 
					"  helm     TEXT," + 
					"  chest    TEXT," + 
					"  legs     TEXT," + 
					"  boots    TEXT," + 
					"  ring     TEXT," + 
					"  neck     TEXT," + 
					"  on_hand  TEXT," + 
					"  off_hand TEXT," + 
					"  strength INTEGER NOT NULL DEFAULT 0," + 
					"  magic    INTEGER NOT NULL DEFAULT 0," + 
					"  luck     INTEGER NOT NULL DEFAULT 0," + 
					"  dex      INTEGER NOT NULL DEFAULT 0," + 
					"  def_phys INTEGER NOT NULL DEFAULT 0," + 
					"  def_magi INTEGER NOT NULL DEFAULT 0," + 
					"  health   INTEGER NOT NULL DEFAULT 0," + 
					"  class    TEXT," + 
					"  invent   TEXT," + 
					"  item     TEXT," +
					"  location TEXT"  +
					");"
	);
		statement.execute("INSERT OR IGNORE INTO `" + "client" + "` (userid, "+key+") VALUES ("+userid+","+value+");"+
							"UPDATE `"+ "client" + "` SET "+key+"="+value+" WHERE userid="+userid+";");
		statement.close();
	}
	
	
	
	public int getBalance() {
		
		return balance;
	}
	
	public void setBalance(int balance) throws Exception {
		this.balance = balance;
		update(Konosuba.CONNECTION, "balance", Integer.toString(balance));
	}
	
	public boolean getBattleStatus() {
		
		return battling;
	}
	
	public void setBattleStatus(boolean battleStatus) throws Exception {
		this.battling = battleStatus;
		update(Konosuba.CONNECTION, "battling",String.valueOf(battleStatus));
	}
	
	public boolean getStartStatus() {
	
		return starting;
	}
	
	public void setStartStatus(boolean startStatus) throws Exception {
		this.starting = startStatus;
		update(Konosuba.CONNECTION, "starting", String.valueOf(startStatus));
	}

    public String getClasses() {
        
        return classes;
    }

    public void setClasses(String classes) throws Exception {
        this.classes = classes;
        update(Konosuba.CONNECTION, "classes" ,classes);
    }

	public String getHelmet() {
		
		return helm;
	}
	
	public void setHelmet(String helmet) throws Exception {
		this.helm = helmet;
		update(Konosuba.CONNECTION, "helm",helmet);
	}

	public String getChest() {
		
		return chest;
	}

	public void setChest(String chest) throws Exception {
	    this.chest = chest;
	    update(Konosuba.CONNECTION, "chest", chest);
    }

    public String getLegs() {
	    
	    return legs;
    }

    public void setLegs(String legs) throws Exception {
	    this.legs = legs;
	    update(Konosuba.CONNECTION, "legs", legs);
    }

    public String getBoots() {
	   
	    return boots;
    }

    public void setBoots(String boots) throws Exception {
	    this.boots = boots;
	    update(Konosuba.CONNECTION, "boots" ,boots);
    }

    public String getRing() {
        
        return ring;
    }

    public void setRing(String ring) throws Exception {
        this.ring = ring;
        update(Konosuba.CONNECTION, "ring" ,ring);
    }

    public String getNecklace() {
        
        return neck;
    }

    public void setNecklace(String necklace) throws Exception {
        this.neck =necklace ;
        update(Konosuba.CONNECTION, "neck" ,necklace);
    }

    public String getCape() {
        
        return cape;
    }

    public void setCape(String cape) throws Exception {
        this.cape = cape;
        update(Konosuba.CONNECTION, "cape" ,cape);
    }

    public String getPrimary() {
		return getOnhand();
	}

	public void setPrimary(String primary) throws Exception {
		setOnhand(primary);
	}

    public String getOnhand() {
        
        return on_hand;
    }

    public void setOnhand(String onhand) throws Exception {
        this.on_hand = onhand;
        update(Konosuba.CONNECTION, "on_hand" ,onhand);
    }

    public String getOffhand() {
        
        return off_hand;
    }

    public void setOffhand(String offhand) throws Exception {
        this.off_hand = offhand;
        update(Konosuba.CONNECTION, "off_hand" ,offhand);
    }
	
    public int getStrength() {
		
		return strength;
	}
	
	public void setStrength(int strength) throws Exception {
		this.strength = strength;
		update(Konosuba.CONNECTION, "strength", Integer.toString(strength));
	}
	
	public int getMagic() {
		
		return magic;
	}
	
	public void setMagic(int magic) throws Exception {
		this.magic = magic;
		update(Konosuba.CONNECTION, "magic", Integer.toString(magic));
	}
	
	public int getLuck() {
		
		return luck;
	}
	
	public void setLuck(int luck) throws Exception {
		this.luck = luck;
		update(Konosuba.CONNECTION, "luck", Integer.toString(luck));
	}
	
	public int getDexterity() {
		
		return dex;
	}
	
	public void setDexterity(int dexterity) throws Exception {
		this.dex = dexterity;
		update(Konosuba.CONNECTION, "dex", Integer.toString(dexterity));
	}
	
	public int getPhysicalDefense() {
		
		return phys_def;
	}
	
	public void setPhysicalDefense(int physicalDefense) throws Exception {
		this.phys_def = physicalDefense;
		update(Konosuba.CONNECTION, "phys_def", Integer.toString(physicalDefense));
	}
	
	public int getMagicalDefense() {
		
		return magi_def;
	}
	
	public void setMagicalDefense(int magicalDefense) throws Exception {
		this.magi_def = magicalDefense;
		update(Konosuba.CONNECTION, "magi_def", Integer.toString(magicalDefense));
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
		this.health = hitpoints;
		update(Konosuba.CONNECTION, "health", Integer.toString(hitpoints));
	}
	
	public List<List<String>> getInventory() {
		
		return invent;
	}
	
	public void setInventory(List<List<String>> inventory) throws Exception {
		this.invent = inventory;
		update(Konosuba.CONNECTION, "invent", inventory.toString());
	}
	
	public void addInventory(int position, String item) throws Exception {
		List<String> subInv = this.invent.remove(position);
		subInv.add(item);	
		this.invent.add(position, subInv);
		update(Konosuba.CONNECTION, userid);
	}
	
	public void removeInventory(int position, String item) throws Exception {
		List<String> subInv = this.invent.remove(position);
		subInv.remove(item);	
		this.invent.add(position, subInv); 
		update(Konosuba.CONNECTION, userid);
	}
	
	public HashMap<String, Integer> getItems() {
		
		return item;
		
	}
	
	public void setItems(HashMap<String, Integer> item) throws Exception {
		this.item = item;
		update(Konosuba.CONNECTION, "item", item.toString());
	}
	public void addItems(String item, int amount) throws Exception {
		if(this.getItems().containsKey(item)) {
			int itemValue = this.getItems().remove(item);
			int amt = itemValue + amount;
			this.getItems().put(item, amt);
			
		}
		else {
			this.getItems().put(item, amount);
		}
		
		update(Konosuba.CONNECTION, userid);
		
	}
	
	public void removeItems(String item, int amount) throws Exception {
		/*
		 * amount will be less than or equal to the value
		 * precondition xdddd
		 * 
		 * also there IS an item in the map when called
		 */
		
		int itemValue = this.getItems().remove(item);
		int amt = itemValue - amount;
		if(amt > 0) {
			this.getItems().put(item,  amt);
		}
		update(Konosuba.CONNECTION, userid);
		
		// don't add the map if amt == 0

	}
	
	public String getLocation() {
        
        return location;
    }

    public void setLocation(String location) throws Exception {
        this.location = location;
        update(Konosuba.CONNECTION, "location" ,location);
    }
		
	
}
=======
package io.osu.konosuba.data;

import io.osu.konosuba.Konosuba;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class UserData {
	
	//  |userid|balance|battling|starting|classes|helm|chest|legs|boots|cape|ring|neck|on_hand|off_hand|strength|magic|luck|dex |phys_def|magi_def|health|invent|item|location|
	//	----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//  |INT  |INT   |INT     |INT     |INT   |INT|INT |INT|INT |INT|INT|INT|INT   |INT    |INT     |INT  |INT |INT |INT     |INT     |INT   |INT  |INT|INT    |
	
	
	// = Cache ===================================
	private long userid;
	private int balance;
	private boolean battling;
	private boolean starting;
	private int classes;
	private int helm;
	private int chest;
	private int legs;
	private int boots;
	private int cape;
	private int ring;
	private int neck;
	private int on_hand;
	private int off_hand;
	private int strength;
	private int magic;
	private int luck;
	private int dex;
	private int phys_def;
	private int magi_def;
	private int health;
	private List<List<Integer>> invent;
	private HashMap<Integer, Integer> item;
	private int location;
	 
	// ===========================================

	private boolean first = true;
	
	public UserData(long userid) throws Exception {
		update(userid);
	}

	private void update(long userid) throws Exception {
		this.userid = userid;
		Statement statement = Konosuba.CONNECTION1.createStatement();
		if(first) {
			statement.execute(
				"CREATE TABLE IF NOT EXISTS 'client' ("+
						"userid   INT PRIMARY KEY NOT NULL," +
						"balance  INT NOT NULL DEFAULT 0," +
						"battling INT NOT NULL DEFAULT 0," +
						"starting INT NOT NULL DEFAULT 0," +
						"helm     INT NOT NULL DEFAULT 0," +
						"chest    INT NOT NULL DEFAULT 0," +
						"legs     INT NOT NULL DEFAULT 0," +
						"boots    INT NOT NULL DEFAULT 0," +
						"ring     INT NOT NULL DEFAULT 0," +
						"neck     INT NOT NULL DEFAULT 0," +
						"cape     INT NOT NULL DEFAULT 0," +
						"on_hand  INT NOT NULL DEFAULT 0," +
						"off_hand INT NOT NULL DEFAULT 0," +
						"strength INT NOT NULL DEFAULT 0," +
						"magic    INT NOT NULL DEFAULT 0," +
						"luck     INT NOT NULL DEFAULT 0," +
						"dex      INT NOT NULL DEFAULT 0," +
						"phys_def INT NOT NULL DEFAULT 0," +
						"magi_def INT NOT NULL DEFAULT 0," +
						"health   INT NOT NULL DEFAULT 0," +
						"classtype INT NOT NULL DEFAULT 0," +
						"invent   INT NOT NULL DEFAULT '[]'," +
						"item     INT," +
						"location INT"  +
					");"
					);
			first = false;
		}
		
		ResultSet result = statement.executeQuery("SELECT * FROM 'client' WHERE userid="+ userid + ";");
		boolean hasResult = result.next();

		balance  = hasResult ? result.getInt("balance") : 0;
		battling = hasResult && result.getInt("battling") == 1;
		starting = hasResult && result.getInt("starting") == 1;
		classes  = hasResult ? result.getInt("classtype") : 0;
		helm     = hasResult ? result.getInt("helm") : 0;
		chest    = hasResult ? result.getInt("chest") : 0;
		legs     = hasResult ? result.getInt("legs") : 0;
		boots    = hasResult ? result.getInt("boots") : 0;
		cape     = hasResult ? result.getInt("cape") : 0;
		ring     = hasResult ? result.getInt("ring") : 0;
		neck     = hasResult ? result.getInt("neck") : 0;
		on_hand  = hasResult ? result.getInt("on_hand") : 0;
		off_hand = hasResult ? result.getInt("off_hand") : 0;
		strength = hasResult ? result.getInt("strength") : 0;
		magic    = hasResult ? result.getInt("magic") : 0;
		luck     = hasResult ? result.getInt("luck") : 0;
		dex      = hasResult ? result.getInt("dex") : 0;
		phys_def = hasResult ? result.getInt("phys_def") : 0;
		magi_def = hasResult ? result.getInt("magi_def") : 0;
		health   = hasResult ? result.getInt("health") : 0;
		location = hasResult ? result.getInt("location") : 0;

		if(hasResult) {
			JSONArray raw = new JSONArray(result.getString("invent"));
			List<List<Integer>> stringListList = new ArrayList<>();
			List<Integer> stringList = new ArrayList<>();
			for (Object obj : raw) {
			    for (Object obj2 : ((JSONArray) obj)) {
			        stringList.add((int) obj2);
			    }
			    stringListList.add(new ArrayList<>(stringList)); // Fixes clearing
			    stringList.clear();  
			}
			invent  = stringListList;
			
			JSONObject raw2 = new JSONObject(result.getInt("item"));
			HashMap<Integer, Integer> stringMap = new HashMap<>();
			raw2.toMap().forEach((k,v) -> stringMap.put(Integer.parseInt(k), (int)v));
			item = stringMap;
			
			
		}else {
			invent = null;
			item = null;
		}
		
		statement.close();
	}
	
	private void update(String key, Object value) throws Exception {
		Statement statement = Konosuba.CONNECTION1.createStatement();
		statement.addBatch("INSERT OR IGNORE INTO 'client' (userid,"+key+") VALUES ("+userid+",'"+value+"');");
		statement.addBatch("UPDATE 'client' SET "+key+"='"+value+"' WHERE userid="+userid+";");
		statement.executeBatch();
		statement.close();
	}
	
	
	
	public int getBalance() {
		
		return balance;
	}
	
	public void setBalance(int balance) throws Exception {
		update( "balance", balance);
		this.balance = balance;
		
	}
	
	public boolean getBattleStatus() {
		
		return battling;
	}
	
	public void setBattleStatus(boolean battleStatus) throws Exception {
		update( "battling",battleStatus? 1 : 0);
		this.battling = battleStatus;
		
	}
	
	public boolean getStartStatus() {
		return starting;
	}
	
	public void setStartStatus(boolean startStatus) throws Exception {
		update( "starting", startStatus ? 1 : 0);
		this.starting = startStatus;
		
	}

    public int getClasses() {
        return classes;
    }

    public void setClasses(int classtype) throws Exception {
    	update("classtype" ,classtype);
        this.classes = classtype;
        
    }

	public int getHelmet() {
		
		return helm;
	}
	
	public void setHelmet(int helmet) throws Exception {
		update( "helm",helmet);
		this.helm = helmet;
		
	}

	public int getChest() {
		
		return chest;
	}

	public void setChest(int chest) throws Exception {
		update( "chest", chest);
	    this.chest = chest;
	   
    }

    public int getLegs() {
	    
	    return legs;
    }

    public void setLegs(int legs) throws Exception {
    	update( "legs", legs);
	    this.legs = legs;
	   
    }

    public int getBoots() {
	   
	    return boots;
    }

    public void setBoots(int boots) throws Exception {
    	update( "boots" ,boots);
	    this.boots = boots;
	    
    }

    public int getRing() {
        
        return ring;
    }

    public void setRing(int ring) throws Exception {
    	update( "ring" ,ring);
        this.ring = ring;
        
    }

    public int getNecklace() {
        
        return neck;
    }

    public void setNecklace(int necklace) throws Exception {
    	update( "neck" ,necklace);
        this.neck =necklace ;
        
    }

    public int getCape() {
        return cape;
    }

    public void setCape(int cape) throws Exception {
    	update("cape" ,cape);
        this.cape = cape;
        
    }

    public int getPrimary() {
		return getOnhand();
	}

	public void setPrimary(int primary) throws Exception {
		setOnhand(primary);
	}

    public int getOnhand() {
        return on_hand;
    }

    public void setOnhand(int onhand) throws Exception {
    	update( "on_hand" ,onhand);
        this.on_hand = onhand;
        
    }

    public int getOffhand() {
        
        return off_hand;
    }

    public void setOffhand(int offhand) throws Exception {
    	update( "off_hand" ,offhand);
        this.off_hand = offhand;
        
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
	
	public List<List<Integer>> getInventory() {
		return invent;
	}
	
	public void setInventory(List<List<Integer>> inventory) throws Exception {
		update("invent", inventory);
		this.invent = inventory;
		
	}
	
	public void addInventory(int position, int item) throws Exception {
		List<Integer> subInv = this.invent.remove(position);
		subInv.add(item);	
		this.invent.add(position, subInv);
		update("invent", this.invent);
		
	}
	
	public void removeInventory(int position, int item) throws Exception {
		List<Integer> subInv = this.invent.remove(position);
		subInv.remove(item);	
		this.invent.add(position, subInv); 
		
	}
	
	public HashMap<Integer, Integer> getItems() {
		
		return item;
		
	}
	
	public void setItems(HashMap<Integer, Integer> item) throws Exception {
		update( "item", item);
		this.item = item;
		
	}
	public void addItems(int item, int amount) throws Exception {
		if(this.getItems().containsKey(item)) {
			int itemValue = this.getItems().remove(item);
			int amt = itemValue + amount;
			this.getItems().put(item, amt);
			
		}
		else {
			this.getItems().put(item, amount);
		}
		
		
		
	}
	
	public void removeItems(int item, int amount) throws Exception {
		/*
		 * amount will be less than or equal to the value
		 * precondition xdddd
		 * 
		 * also there IS an item in the map when called
		 */
		
		int itemValue = this.getItems().remove(item);
		int amt = itemValue - amount;
		if(amt > 0) {
			this.getItems().put(item,  amt);
		}
		
		
		// don't add the map if amt == 0

	}
	
	public int getLocation() {
        
        return location;
    }

    public void setLocation(int location) throws Exception {
    	update( "location" ,location);
        this.location = location;
        
    }
		
	
}

