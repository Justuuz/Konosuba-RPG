package io.osu.konosuba.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.JSONArray;

import com.google.gson.JsonArray;

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
	private ArrayList<ArrayList<String>> invent;
	private List<HashMap<String, Integer>> map; 
	 
	// ===========================================
	
	
	public UserData(long userid) {
		this.userid = userid;
		SQLiteJDBC();
	}
	
	private Connection connect;
	
	void SQLiteJDBC() throws Exception{
		Connection c = null;
		Statement statement = null;
		
			Class.forName("org.postgresql.Driver");
			c= DriverManager
					.getConnection("jdbc:sqlite:" + System.getProperty("user.home") + "/git/KonosubaBot/Data/data.db");
			c.setAutoCommit(false);
			System.out.println("Connection success");
			connect = c;
	}
	
	
	
	private void update(Connection connection, String table, long userid) throws Exception {
		this.userid = userid;
		Statement statement = connection.createStatement();
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
			// invent   = new JsonArray(result.getString("invent"))); Figure out later
			// item     = result.get Figure out later
			
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
			
		} 
		statement.close();
		
		
	}
	
	private void update(Connection connection, String table, String key, String value) throws Exception {
		Statement statement = connection.createStatement();
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
					"  invent   TEXT" + 
					");"
	);
		statement.execute("INSERT OR IGNORE INTO '" + table + "' (userid, "+key+") VALUES ("+userid+","+value+");"+
							"UPDATE '"+ table + "' SET "+key+"="+value+" WHERE userid="+userid+";");
		statement.close();
	}
	
	
	
	public int getBalance() throws Exception {
		update(connect, "client", userid);
		return balance;
	}
	
	public void setBalance(int balance) throws Exception {
		this.balance = balance;
		update(connect, "client", "balance", Integer.toString(balance));
	}
	
	public boolean getBattleStatus() throws Exception {
		update(connect,  "battling", userid);
		return battling;
	}
	
	public void setBattleStatus(boolean battleStatus) throws Exception {
		this.battling = battleStatus;
		update(connect, "client", "battling",String.valueOf(battleStatus));
	}
	
	public boolean getStartStatus() throws Exception {
		update(connect,"starting" ,userid);
		return starting;
	}
	
	public void setStartStatus(boolean startStatus) throws Exception {
		this.starting = startStatus;
		update(connect, "client", "starting", String.valueOf(startStatus));
	}

    public String getClasses() throws Exception {
        update(connect, "client", userid);
        return classes;
    }

    public void setClasses(String classes) throws Exception {
        this.classes = classes;
        update(connect, "client", "classes" ,classes);
    }

	public String getHelmet() throws Exception {
		update(connect, "client", userid);
		return helm;
	}
	
	public void setHelmet(String helmet) throws Exception {
		this.helm = helmet;
		update(connect, "client","helm",helmet);
	}

	public String getChest() throws Exception {
		update(connect, "client", userid);
		return chest;
	}

	public void setChest(String chest) throws Exception {
	    this.chest = chest;
	    update(connect, "client","chest", chest);
    }

    public String getLegs() throws Exception {
	    update(connect, "client", userid);
	    return legs;
    }

    public void setLegs(String legs) throws Exception {
	    this.legs = legs;
	    update(connect, "client", "legs", legs);
    }

    public String getBoots() throws Exception {
	    update(connect, "client", userid);
	    return boots;
    }

    public void setBoots(String boots) throws Exception {
	    this.boots = boots;
	    update(connect, "client", "boots" ,boots);
    }

    public String getRing() throws Exception {
        update(connect, "client", userid);
        return ring;
    }

    public void setRing(String ring) throws Exception {
        this.ring = ring;
        update(connect, "client", "ring" ,ring);
    }

    public String getNecklace() throws Exception {
        update(connect, "client", userid);
        return neck;
    }

    public void setNecklace(String necklace) throws Exception {
        this.neck =necklace ;
        update(connect, "client", "neck" ,necklace);
    }

    public String getCape() throws Exception {
        update(connect, "client", userid);
        return cape;
    }

    public void setCape(String cape) throws Exception {
        this.cape = cape;
        update(connect, "client", "cape" ,cape);
    }

    public String getOnhand() throws Exception {
        update(connect, "client", userid);
        return on_hand;
    }

    public void setOnhand(String onhand) throws Exception {
        this.on_hand = onhand;
        update(connect, "client", "on_hand" ,onhand);
    }

    public String getOffhand() throws Exception {
        update(connect, "client", userid);
        return off_hand;
    }

    public void setOffhand(String offhand) throws Exception {
        this.off_hand = offhand;
        update(connect, "client", "off_hand" ,offhand);
    }
	
	
		
	
}
