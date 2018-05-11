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
	
	private boolean first = true;
	
	public UserData(long userid) throws Exception {
		update(userid);	
		this.userid = userid;
			
	}	
	
	private void update(long userid) throws Exception {
		this.userid = userid;
		Statement statement = Konosuba.CONNECTION.createStatement();
		if(first) {
		statement.execute(
				"CREATE TABLE IF NOT EXISTS 'client' ("+
					"  userid   INT PRIMARY KEY NOT NULL," + 
					"  balance  INT NOT NULL DEFAULT 0," + 
					"  battling INT NOT NULL DEFAULT 0," + 
					"  starting INT NOT NULL DEFAULT 0," + 
					"  helm     TEXT," + 
					"  chest    TEXT," + 
					"  legs     TEXT," + 
					"  boots    TEXT," + 
					"  ring     TEXT," + 
					"  neck     TEXT," + 
					"  on_hand  TEXT," + 
					"  off_hand TEXT," + 
					"  strength INT NOT NULL DEFAULT 0," + 
					"  magic    INT NOT NULL DEFAULT 0," + 
					"  luck     INT NOT NULL DEFAULT 0," + 
					"  dex      INT NOT NULL DEFAULT 0," + 
					"  def_phys INT NOT NULL DEFAULT 0," + 
					"  def_magi INT NOT NULL DEFAULT 0," + 
					"  health   INT NOT NULL DEFAULT 0," + 
					"  classes  TEXT," + 
					"  invent   TEXT," + 
					"  item     TEXT," +
					"  location TEXT"  +
					");"
	);
		first = false;
		}
		
		ResultSet result = statement.executeQuery("SELECT * FROM 'client' WHERE userid="+ userid + ";");
//		if(result.next()) {
		boolean hasResult = result.next();
		
			balance  = hasResult ? result.getInt("balance") : 0;
			battling = hasResult ? result.getInt("battling") == 1 : false;
			starting = hasResult ? result.getInt("starting") == 1 : false;
			classes  = hasResult ? result.getString("classes") : null;
			helm     = hasResult ? result.getString("helm") : null;
			chest    = hasResult ? result.getString("chest") : null;
			legs     = hasResult ? result.getString("legs") : null;
			boots    = hasResult ? result.getString("boots") : null;
			cape     = hasResult ? result.getString("cape") : null;
			ring     = hasResult ? result.getString("ring") : null;
			neck     = hasResult ? result.getString("neck") : null;
			on_hand  = hasResult ? result.getString("on_hand") : null;
			off_hand = hasResult ? result.getString("off_hand") : null;
			strength = hasResult ? result.getInt("strength") : 0;
			magic    = hasResult ? result.getInt("magic") : 0;
			luck     = hasResult ? result.getInt("luck") : 0;
			dex      = hasResult ? result.getInt("dex") : 0;
			phys_def = hasResult ? result.getInt("phys_def") : 0;
			magi_def = hasResult ? result.getInt("magi_def") : 0;
			health   = hasResult ? result.getInt("health") : 0;
			location = hasResult ? result.getString("location") : null;
//			if(hasResult) {
//				JSONArray raw = new JSONArray(result.getString("invent"));
//				List<List<String>> stringListList = new ArrayList<>();
//				List<String> stringList = new ArrayList<>();
//				for (Object obj : raw) {
//				    for (Object obj2 : ((JSONArray) obj)) {
//				        stringList.add((String) obj2);
//				    }
//				    stringListList.add(new ArrayList<>(stringList)); // Fixes clearing
//				    stringList.clear();  
//				}
//				invent  = stringListList;
//				
//				JSONObject raw2 = new JSONObject(result.getString("item"));
//				HashMap<String, Integer> stringMap = new HashMap<>();
//				raw2.toMap().forEach((k,v) -> stringMap.put(k, (int)v));
//				item = stringMap;
//				
//				
//			}else {
//				invent = null;
//				item = null;
//			}
			
			
			
			
			
			
//		}else {
//			// Default
//			balance  = 0;
//			battling = false;
//			starting = false;
//			classes  = null;
//			helm     = null;
//			chest    = null;
//			legs     = null;
//			boots    = null;
//			cape     = null;
//			ring     = null;
//			neck     = null;
//			on_hand  = null;
//			off_hand = null;
//			strength = 0;
//			magic    = 0;
//			luck     = 0;
//			dex      = 0;
//			phys_def = 0;
//			magi_def = 0;
//			health   = 0;
//			invent = null;
//			item = null;
//			location = null;
//			
//		} 
		statement.close();
		
		
	}
	
	private void update(String key, Object value) throws Exception {
		Statement statement = Konosuba.CONNECTION.createStatement();
		statement.addBatch("INSERT OR IGNORE INTO 'client' (userid,"+key+") VALUES ("+userid+","+value+");");
		statement.addBatch("UPDATE 'client' SET "+key+"="+value+" WHERE userid="+userid+";");
		statement.executeBatch();
		statement.close();
	}
	
	
	
	public int getBalance() {
		
		return balance;
	}
	
	public void setBalance(int balance) throws Exception {
		this.balance = balance;
		update( "balance", balance);
	}
	
	public boolean getBattleStatus() {
		
		return battling;
	}
	
	public void setBattleStatus(boolean battleStatus) throws Exception {
		this.battling = battleStatus;
		update( "battling",battleStatus? 1 : 0);
	}
	
	public boolean getStartStatus() {
		return starting;
	}
	
	public void setStartStatus(boolean startStatus) throws Exception {
		this.starting = startStatus;
		update( "starting", startStatus? 1: 0);
	}

    public String getClasses() {
        
        return classes;
    }

    public void setClasses(String classes) throws Exception {
        this.classes = classes;
        update("classes" ,classes);
    }

	public String getHelmet() {
		
		return helm;
	}
	
	public void setHelmet(String helmet) throws Exception {
		this.helm = helmet;
		update( "helm",helmet);
	}

	public String getChest() {
		
		return chest;
	}

	public void setChest(String chest) throws Exception {
	    this.chest = chest;
	    update( "chest", chest);
    }

    public String getLegs() {
	    
	    return legs;
    }

    public void setLegs(String legs) throws Exception {
	    this.legs = legs;
	    update( "legs", legs);
    }

    public String getBoots() {
	   
	    return boots;
    }

    public void setBoots(String boots) throws Exception {
	    this.boots = boots;
	    update( "boots" ,boots);
    }

    public String getRing() {
        
        return ring;
    }

    public void setRing(String ring) throws Exception {
        this.ring = ring;
        update( "ring" ,ring);
    }

    public String getNecklace() {
        
        return neck;
    }

    public void setNecklace(String necklace) throws Exception {
        this.neck =necklace ;
        update( "neck" ,necklace);
    }

    public String getCape() {
        
        return cape;
    }

    public void setCape(String cape) throws Exception {
        this.cape = cape;
        update( "cape" ,cape);
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
        update( "on_hand" ,onhand);
    }

    public String getOffhand() {
        
        return off_hand;
    }

    public void setOffhand(String offhand) throws Exception {
        this.off_hand = offhand;
        update( "off_hand" ,offhand);
    }
	
    public int getStrength() {
		
		return strength;
	}
	
	public void setStrength(int strength) throws Exception {
		this.strength = strength;
		update( "strength", (strength));
	}
	
	public int getMagic() {
		
		return magic;
	}
	
	public void setMagic(int magic) throws Exception {
		this.magic = magic;
		update( "magic", (magic));
	}
	
	public int getLuck() {
		
		return luck;
	}
	
	public void setLuck(int luck) throws Exception {
		this.luck = luck;
		update( "luck", (luck));
	}
	
	public int getDexterity() {
		
		return dex;
	}
	
	public void setDexterity(int dexterity) throws Exception {
		this.dex = dexterity;
		update( "dex", (dexterity));
	}
	
	public int getPhysicalDefense() {
		
		return phys_def;
	}
	
	public void setPhysicalDefense(int physicalDefense) throws Exception {
		this.phys_def = physicalDefense;
		update( "phys_def", (physicalDefense));
	}
	
	public int getMagicalDefense() {
		
		return magi_def;
	}
	
	public void setMagicalDefense(int magicalDefense) throws Exception {
		this.magi_def = magicalDefense;
		update( "magi_def", (magicalDefense));
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
		update( "health", (hitpoints));
	}
	
	public List<List<String>> getInventory() {
		
		return invent;
	}
	
	public void setInventory(List<List<String>> inventory) throws Exception {
		this.invent = inventory;
		update( "invent", inventory);
	}
	
	public void addInventory(int position, String item) throws Exception {
		List<String> subInv = this.invent.remove(position);
		subInv.add(item);	
		this.invent.add(position, subInv);
		
	}
	
	public void removeInventory(int position, String item) throws Exception {
		List<String> subInv = this.invent.remove(position);
		subInv.remove(item);	
		this.invent.add(position, subInv); 
		
	}
	
	public HashMap<String, Integer> getItems() {
		
		return item;
		
	}
	
	public void setItems(HashMap<String, Integer> item) throws Exception {
		this.item = item;
		update( "item", item);
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
		
		
		// don't add the map if amt == 0

	}
	
	public String getLocation() {
        
        return location;
    }

    public void setLocation(String location) throws Exception {
        this.location = location;
        update( "location" ,location);
    }
		
	
}
