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
					"  helm     TEXT NOT NULL," + 
					"  chest    TEXT NOT NULL," + 
					"  legs     TEXT NOT NULL," + 
					"  boots    TEXT NOT NULL," + 
					"  ring     TEXT NOT NULL," + 
					"  neck     TEXT NOT NULL," + 
					"  cape     TEXT NOT NULL," +
					"  on_hand  TEXT NOT NULL," + 
					"  off_hand TEXT NOT NULL," + 
					"  strength INT NOT NULL DEFAULT 0," + 
					"  magic    INT NOT NULL DEFAULT 0," + 
					"  luck     INT NOT NULL DEFAULT 0," + 
					"  dex      INT NOT NULL DEFAULT 0," + 
					"  phys_def INT NOT NULL DEFAULT 0," + 
					"  magi_def INT NOT NULL DEFAULT 0," + 
					"  health   INT NOT NULL DEFAULT 0," + 
					"  classtype TEXT NOT NULL," + 
					"  invent   TEXT NOT NULL," + 
					"  item     TEXT NOT NULL," +
					"  location TEXT NOT NULL"  +
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
			classes  = hasResult ? result.getString("classtype") : null;
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
			if(hasResult) {
				JSONArray raw = new JSONArray(result.getString("invento"));
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
				
				JSONObject raw2 = new JSONObject(result.getString("itemo"));
				HashMap<String, Integer> stringMap = new HashMap<>();
				raw2.toMap().forEach((k,v) -> stringMap.put(k, (int)v));
				item = stringMap;
				
				
			}else {
				invent = null;
				item = null;
			}
			
			
			
			
			
			
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
		statement.addBatch("INSERT OR IGNORE INTO 'client' (userid,"+key+") VALUES ("+userid+",'"+value+"');");
		statement.addBatch("UPDATE 'client' SET "+key+"="+value+" WHERE userid="+userid+";");
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
		update( "starting", startStatus? 1: 0);
		this.starting = startStatus;
		
	}

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classtype) throws Exception {
    	update("classtype" ,classtype);
        this.classes = classtype;
        
    }

	public String getHelmet() {
		
		return helm;
	}
	
	public void setHelmet(String helmet) throws Exception {
		update( "helm",helmet);
		this.helm = helmet;
		
	}

	public String getChest() {
		
		return chest;
	}

	public void setChest(String chest) throws Exception {
		update( "chest", chest);
	    this.chest = chest;
	   
    }

    public String getLegs() {
	    
	    return legs;
    }

    public void setLegs(String legs) throws Exception {
    	update( "legs", legs);
	    this.legs = legs;
	   
    }

    public String getBoots() {
	   
	    return boots;
    }

    public void setBoots(String boots) throws Exception {
    	update( "boots" ,boots);
	    this.boots = boots;
	    
    }

    public String getRing() {
        
        return ring;
    }

    public void setRing(String ring) throws Exception {
    	update( "ring" ,ring);
        this.ring = ring;
        
    }

    public String getNecklace() {
        
        return neck;
    }

    public void setNecklace(String necklace) throws Exception {
    	update( "neck" ,necklace);
        this.neck =necklace ;
        
    }

    public String getCape() {
        return cape;
    }

    public void setCape(String cape) throws Exception {
    	update("cape" ,cape);
        this.cape = cape;
        
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
    	update( "on_hand" ,onhand);
        this.on_hand = onhand;
        
    }

    public String getOffhand() {
        
        return off_hand;
    }

    public void setOffhand(String offhand) throws Exception {
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
	
	public List<List<String>> getInventory() {
		return invent;
	}
	
	public void setInventory(List<List<String>> inventory) throws Exception {
		update("invento", inventory);
		this.invent = inventory;
		
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
		update( "itemo", item);
		this.item = item;
		
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
    	update( "location" ,location);
        this.location = location;
        
    }
		
	
}
