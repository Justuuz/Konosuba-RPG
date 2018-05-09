package io.osu.konosuba.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;

import components.map.Map;

import java.sql.DriverManager;

public class UserData {
	
	//  |userid|balance|battling|starting|classes|helm|chest|legs|boots|cape|ring|neck|on_hand|off_hand|strength|magic|luck|dex |phys_def|magi_def|health|invent|item    |
	//	-----------------------------------------------------------------------------------------------------------------------------------------------------
	//  |TEXT  |TEXT   |INT     |INT     |TEXT   |TEXT|TEXT |TEXT|TEXT |TEXT|TEXT|TEXT|TEXT   |TEXT    |INT     |INT  |INT |INT |INT     |INT     |INT   |TEXT  |TEXT    |
	
	
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
	 
	// ===========================================
	
	
	public UserData(long userid) {
		this.userid = userid;
		
	}
	
	
	    private static Connection CONNECTION;
	    static {
	        try {
	            Class.forName("org.sqlite.JDBC");
	            CONNECTION = DriverManager.getConnection("jdbc:sqlite:" + System.getProperty("user.home") + "/git/KonosubaBot/Data/data.db");
	        } catch (Exception e) {
	            e.printStackTrace();
	            CONNECTION=null;
	            System.exit(0);
	        }
	    }

	
//	static void SQLiteJDBC() throws Exception{
//		Connection CONNECTION = null;
//		Statement statement = null;
//		
//			Class.forName("org.postgresql.Driver");
//			c= DriverManager
//					.getConnection("jdbc:sqlite:" + System.getProperty("user.home") + "/git/KonosubaBot/Data/data.db");
//			c.setAutoCommit(false);
//			System.out.println("Connection success");
//			
//	}
	
	
	
	private void update(Connection CONNECTIONion, String table, long userid) throws Exception {
		this.userid = userid;
		Statement statement = CONNECTIONion.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM '" +table+"' WHERE userid="+ userid + ";");
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
			
		} 
		statement.close();
		
		
	}
	
	private void update(Connection CONNECTIONion, String table, String key, String value) throws Exception {
		Statement statement = CONNECTIONion.createStatement();
		statement.execute(
				"CREATE TABLE IF NOT EXISTS '"+ table + "' ("+
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
					"  item     TEXT" +
					");"
	);
		statement.execute("INSERT OR IGNORE INTO '" + table + "' (userid, "+key+") VALUES ("+userid+","+value+");"+
							"UPDATE '"+ table + "' SET "+key+"="+value+" WHERE userid="+userid+";");
		statement.close();
	}
	
	
	
	public int getBalance() throws Exception {
		update(CONNECTION, "client", userid);
		return balance;
	}
	
	public void setBalance(int balance) throws Exception {
		this.balance = balance;
		update(CONNECTION, "client", "balance", Integer.toString(balance));
	}
	
	public boolean getBattleStatus() throws Exception {
		update(CONNECTION,  "battling", userid);
		return battling;
	}
	
	public void setBattleStatus(boolean battleStatus) throws Exception {
		this.battling = battleStatus;
		update(CONNECTION, "client", "battling",String.valueOf(battleStatus));
	}
	
	public boolean getStartStatus() throws Exception {
		update(CONNECTION,"starting" ,userid);
		return starting;
	}
	
	public void setStartStatus(boolean startStatus) throws Exception {
		this.starting = startStatus;
		update(CONNECTION, "client", "starting", String.valueOf(startStatus));
	}

    public String getClasses() throws Exception {
        update(CONNECTION, "client", userid);
        return classes;
    }

    public void setClasses(String classes) throws Exception {
        this.classes = classes;
        update(CONNECTION, "client", "classes" ,classes);
    }

	public String getHelmet() throws Exception {
		update(CONNECTION, "client", userid);
		return helm;
	}
	
	public void setHelmet(String helmet) throws Exception {
		this.helm = helmet;
		update(CONNECTION, "client","helm",helmet);
	}

	public String getChest() throws Exception {
		update(CONNECTION, "client", userid);
		return chest;
	}

	public void setChest(String chest) throws Exception {
	    this.chest = chest;
	    update(CONNECTION, "client","chest", chest);
    }

    public String getLegs() throws Exception {
	    update(CONNECTION, "client", userid);
	    return legs;
    }

    public void setLegs(String legs) throws Exception {
	    this.legs = legs;
	    update(CONNECTION, "client", "legs", legs);
    }

    public String getBoots() throws Exception {
	    update(CONNECTION, "client", userid);
	    return boots;
    }

    public void setBoots(String boots) throws Exception {
	    this.boots = boots;
	    update(CONNECTION, "client", "boots" ,boots);
    }

    public String getRing() throws Exception {
        update(CONNECTION, "client", userid);
        return ring;
    }

    public void setRing(String ring) throws Exception {
        this.ring = ring;
        update(CONNECTION, "client", "ring" ,ring);
    }

    public String getNecklace() throws Exception {
        update(CONNECTION, "client", userid);
        return neck;
    }

    public void setNecklace(String necklace) throws Exception {
        this.neck =necklace ;
        update(CONNECTION, "client", "neck" ,necklace);
    }

    public String getCape() throws Exception {
        update(CONNECTION, "client", userid);
        return cape;
    }

    public void setCape(String cape) throws Exception {
        this.cape = cape;
        update(CONNECTION, "client", "cape" ,cape);
    }

    public String getOnhand() throws Exception {
        update(CONNECTION, "client", userid);
        return on_hand;
    }

    public void setOnhand(String onhand) throws Exception {
        this.on_hand = onhand;
        update(CONNECTION, "client", "on_hand" ,onhand);
    }

    public String getOffhand() throws Exception {
        update(CONNECTION, "client", userid);
        return off_hand;
    }

    public void setOffhand(String offhand) throws Exception {
        this.off_hand = offhand;
        update(CONNECTION, "client", "off_hand" ,offhand);
    }
	
    public int getStrength() throws Exception {
		update(CONNECTION, "client", userid);
		return strength;
	}
	
	public void setStrength(int strength) throws Exception {
		this.strength = strength;
		update(CONNECTION, "client", "strength", Integer.toString(strength));
	}
	
	public int getMagic() throws Exception {
		update(CONNECTION, "client", userid);
		return magic;
	}
	
	public void setMagic(int magic) throws Exception {
		this.magic = magic;
		update(CONNECTION, "client", "magic", Integer.toString(magic));
	}
	
	public int getLuck() throws Exception {
		update(CONNECTION, "client", userid);
		return luck;
	}
	
	public void setLuck(int luck) throws Exception {
		this.luck = luck;
		update(CONNECTION, "client", "luck", Integer.toString(luck));
	}
	
	public int getDexterity() throws Exception {
		update(CONNECTION, "client", userid);
		return dex;
	}
	
	public void setDexterity(int dexterity) throws Exception {
		this.dex = dexterity;
		update(CONNECTION, "client", "dex", Integer.toString(dexterity));
	}
	
	public int getPhysicalDefense() throws Exception {
		update(CONNECTION, "client", userid);
		return phys_def;
	}
	
	public void setPhysicalDefense(int physicalDefense) throws Exception {
		this.phys_def = physicalDefense;
		update(CONNECTION, "client", "phys_def", Integer.toString(physicalDefense));
	}
	
	public int getMagicalDefense() throws Exception {
		update(CONNECTION, "client", userid);
		return magi_def;
	}
	
	public void setMagicalDefense(int magicalDefense) throws Exception {
		this.magi_def = magicalDefense;
		update(CONNECTION, "client", "magi_def", Integer.toString(magicalDefense));
	}
	
	public int getHitpoints() throws Exception {
		update(CONNECTION, "client", userid);
		return health;
	}
	
	public void setHitpoints(int hitpoints) throws Exception {
		this.health = hitpoints;
		update(CONNECTION, "client", "health", Integer.toString(hitpoints));
	}
	
	public List<List<String>> getInventory() throws Exception {
		update(CONNECTION, "client", userid);
		return invent;
	}
	
	public void addInventory(int position, String item) throws Exception {
		update(CONNECTION, "client", userid);
		List<String> subInv = this.invent.remove(position);
		subInv.add(item);	
		this.invent.add(position, subInv);
	}
	
	public void removeInventory(int position, String item) throws Exception {
		List<String> subInv = this.invent.remove(position);
		subInv.remove(item);	
		this.invent.add(position, subInv); 
	}
	
	public HashMap<String, Integer> getItems(){
		return item;
	}
	
	public void setItems(HashMap<String, Integer> item) {
		this.item = item;
	}
	public void addItems(String item, int amount) {
		if(this.getItems().containsKey(item)) {
			int itemValue = this.getItems().remove(item);
			int amt = itemValue + amount;
			this.getItems().put(item, amt);
			
		}
		else {
			this.getItems().put(item, amount);
		}
	}
	
	public void removeItems(String item, int amount) {
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
		
	
}
