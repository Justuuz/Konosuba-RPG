
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
import io.osu.konosuba.Konosuba;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
@SuppressWarnings({"unused"})
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
	private List<List<Integer>> invent  = new ArrayList<>();
	private HashMap<Integer, Integer> item = new HashMap<>();
	private int location;

	// ===========================================

	private boolean first = true;

	public UserData(long userid)  {
		update(userid);
	}

	private void update(long userid)  {
		this.userid = userid;
		try {
			Statement statement = Konosuba.CONNECTION1.createStatement();
			if(first) {
				statement.execute(
						"CREATE TABLE IF NOT EXISTS 'clients' ("+
								"userid   INTEGER PRIMARY KEY NOT NULL," +
								"balance  INTEGER NOT NULL DEFAULT 0," +
								"battling INTEGER NOT NULL DEFAULT 0," +
								"starting INTEGER NOT NULL DEFAULT 0," +
								"helm     INTEGER NOT NULL DEFAULT 0," +
								"chest    INTEGER NOT NULL DEFAULT 0," +
								"legs     INTEGER NOT NULL DEFAULT 0," +
								"boots    INTEGER NOT NULL DEFAULT 0," +
								"ring     INTEGER NOT NULL DEFAULT 0," +
								"neck     INTEGER NOT NULL DEFAULT 0," +
								"cape     INTEGER NOT NULL DEFAULT 0," +
								"on_hand  INTEGER NOT NULL DEFAULT 0," +
								"off_hand INTEGER NOT NULL DEFAULT 0," +
								"strength INTEGER NOT NULL DEFAULT 0," +
								"magic    INTEGER NOT NULL DEFAULT 0," +
								"luck     INTEGER NOT NULL DEFAULT 0," +
								"dex      INTEGER NOT NULL DEFAULT 0," +
								"phys_def INTEGER NOT NULL DEFAULT 0," +
								"magi_def INTEGER NOT NULL DEFAULT 0," +
								"health   INTEGER NOT NULL DEFAULT 0," +
								"classtype INTEGER NOT NULL DEFAULT 0," +
								"invent   INT NOT NULL DEFAULT '[]'," +
								"item     TEXT," +
								"location INTEGER NOT NULL DEFAULT 0"  +
								");"
						);
				first = false;
			}
			ResultSet result = statement.executeQuery("SELECT * FROM 'clients' WHERE userid="+ userid + ";");
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
			strength = hasResult ? result.getInt("strength") : 5;
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
				stringList.clear();
				stringList.clear();

				JSONObject raw2 = new JSONObject(result.getInt("item"));
				HashMap<Integer, Integer> stringMap = new HashMap<>();
				raw2.toMap().forEach((k,v) -> stringMap.put(Integer.parseInt(k), (int)v));
				item = stringMap;


			}else {
				invent = null;
				item = null;
			}

			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void update(String key, Object value) {
		try {
			Statement statement = Konosuba.CONNECTION1.createStatement();
			statement.addBatch("INSERT OR IGNORE INTO 'clients' (userid,"+key+") VALUES ("+userid+",'"+value+"');");
			statement.addBatch("UPDATE 'clients' SET "+key+"='"+value+"' WHERE userid="+userid+";");
			statement.executeBatch();
			statement.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}



	public int getBalance() {

		return balance;
	}

	public void setBalance(int balance)  {
		update( "balance", balance);
		this.balance = balance;

	}

	public boolean getBattleStatus() {

		return battling;
	}

	public void setBattleStatus(boolean battleStatus)  {
		update( "battling",battleStatus? 1 : 0);
		this.battling = battleStatus;

	}

	public boolean getStartStatus() {
		return starting;
	}

	public void setStartStatus(boolean startStatus)  {
		update( "starting", startStatus ? 1 : 0);
		this.starting = startStatus;

	}

	public int getClasses() {
		return classes;
	}

	public void setClasses(int classtype)  {
		update("classtype" ,classtype);
		this.classes = classtype;

	}

	public int getHelmet() {

		return helm;
	}

	public void setHelmet(int helmet)  {
		update( "helm",helmet);
		this.helm = helmet;

	}

	public int getChest() {

		return chest;
	}

	public void setChest(int chest)  {
		update( "chest", chest);
		this.chest = chest;

	}

	public int getLegs() {

		return legs;
	}

	public void setLegs(int legs)  {
		update( "legs", legs);
		this.legs = legs;

	}

	public int getBoots() {

		return boots;
	}

	public void setBoots(int boots)  {
		update( "boots" ,boots);
		this.boots = boots;

	}

	public int getRing() {

		return ring;
	}

	public void setRing(int ring)  {
		update( "ring" ,ring);
		this.ring = ring;

	}

	public int getNecklace() {

		return neck;
	}

	public void setNecklace(int necklace)  {
		update( "neck" ,necklace);
		this.neck =necklace ;

	}

	public int getCape() {
		return cape;
	}

	public void setCape(int cape)  {
		update("cape" ,cape);
		this.cape = cape;

	}

	public int getPrimary() {
		return getOnhand();
	}

	public void setPrimary(int primary)  {
		setOnhand(primary);
	}

	public int getOnhand() {
		return on_hand;
	}

	public void setOnhand(int onhand)  {
		update( "on_hand" ,onhand);
		this.on_hand = onhand;

	}

	public int getOffhand() {

		return off_hand;
	}

	public void setOffhand(int offhand)  {
		update( "off_hand" ,offhand);
		this.off_hand = offhand;

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



	public List<List<Integer>> getInventory() {
		return invent;
	}

	public void setInventory(List<List<Integer>> inventory)  {
		update("invent", inventory);
		this.invent = inventory;

	}

	public void addInventory(int position, int item)  {
		List<Integer> subInv = this.invent.remove(position);
		subInv.add(item);	
		this.invent.add(position, subInv);
		update("invent", this.invent);

	}

	public void removeInventory(int position, int item)  {
		List<Integer> subInv = this.invent.remove(position);
		subInv.remove(item);	
		this.invent.add(position, subInv); 

	}

	public HashMap<Integer, Integer> getItems() {

		return item;

	}

	public void setItems(HashMap<Integer, Integer> item)  {
		update( "item", item);
		this.item = item;

	}
	public void addItems(int item, int amount)  {
		if(this.getItems().containsKey(item)) {
			int itemValue = this.getItems().remove(item);
			int amt = itemValue + amount;
			this.getItems().put(item, amt);

		}
		else {
			this.getItems().put(item, amount);
		}



	}

	public void removeItems(int item, int amount)  {
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

	public void setLocation(int location)  {
		update( "location" ,location);
		this.location = location;

	}


}

