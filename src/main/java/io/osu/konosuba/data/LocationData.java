package io.osu.konosuba.data;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import io.osu.konosuba.Konosuba;

public class LocationData {
	
	// Cache ==============================
	private int locationid;
	private boolean hasBlacksmith;
	private boolean hasItemShop;
	private boolean hasWeaponShop;
	private boolean hasMonsters;
	private String itemShopName;
	private String weaponShopName;
	private String blacksmithName;
	private List<Integer> itemShop;
	private List<Integer> weaponShop;
	private List<Integer> monsterList;
	private List<Integer> locationList;
	
	// ====================================
	
	private boolean first = true;
	
	public LocationData(int locationid) {
		update(locationid);
	}
	
	private void update(int locationid) {
		this.locationid = locationid;
		try {
			Statement statement = Konosuba.CONNECTION2.createStatement();
			if(first) {
				statement.execute(
				"CREATE TABLE IF NOT EXISTS 'locations' (" +
						"locationid    INTEGER PRIMARY KEY NOT NULL DEFAULT 0," +
						"hasblacksmith INTEGER NOT NULL DEFAULT 0," +
						"hasitemshop   INTEGER NOT NULL DEFAULT 0," +
						"hasweaponshop INTEGER NOT NULL DEFAULT 0," +
						"hasmonsters   INTEGER NOT NULL DEFAULT 0," +
						"itemshopname  TEXT NOT NULL," +
						"weaponshopname TEXT NOT NULL," +
						"blacksmithname TEXT NOT NULL," +
						"itemshop      TEXT NOT NULL DEFAULT '[]'," +
						"weaponshop    TEXT NOT NULL DEFAULT '[]'," +
						"monsterlist   TEXT NOT NULL DEFAULT '[]'," +
						"locationlist  TEXT NOT NULL DEFAULT '[]'" +
						");"
					);
				first = false;
			}
			ResultSet result = statement.executeQuery("SELECT * FROM 'locations' WHERE locationid=" + locationid + ";");
			boolean hasResult =  result.next();
			
			hasBlacksmith   = hasResult && result.getInt("hasblacksmith") == 1;
			hasItemShop     = hasResult && result.getInt("hasitemshop") == 1;
			hasWeaponShop   = hasResult && result.getInt("hasweaponshop") == 1;
			hasMonsters     = hasResult && result.getInt("hasmonsters") == 1;
			itemShopName    = hasResult ? result.getString("itemshopname") : null;
			weaponShopName  = hasResult ? result.getString("weaponshopname") : null;
			blacksmithName  = hasResult ? result.getString("blacksmithname") : null;
			
			if(hasResult) {
				JSONArray raw = new JSONArray(result.getString("itemshop"));
				List<Integer> stringList = new ArrayList<>();
				for(Object obj : raw) {
					stringList.add((int) obj);
				}
				
				itemShop = stringList;
				stringList.clear();
				
				raw = new JSONArray(result.getString("weaponshop"));
				for(Object obj : raw) {
					stringList.add((int) obj);
				}

				weaponShop = stringList;
				stringList.clear();
				
				raw = new JSONArray(result.getString("monsterlist"));
				for(Object obj : raw) {
					stringList.add((int) obj);
				}

				monsterList = stringList;
				stringList.clear();
				
				raw = new JSONArray(result.getString("locationlist"));
				for(Object obj : raw) {
					stringList.add((int) obj);
				}

				locationList = stringList;
				stringList.clear();
			}else {
				itemShop = null;
				weaponShop = null;
				monsterList = null;
				locationList = null;
			}
			
			statement.close();
		} catch (Exception e) {
			
		}
		
		
	}
	private void update(String key, Object value) {
		try {
			Statement statement = Konosuba.CONNECTION2.createStatement();
			statement.addBatch("INSERT OR IGNORE INTO 'locations' (locationid,"+key+") VALUES ("+locationid+",'"+value+"');");
			statement.addBatch("UPDATE 'location' SET "+key+"='"+value+"' WHERE locationid="+locationid+";");
			statement.executeBatch();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean getHasBlacksmith() {
		
		return hasBlacksmith;
	}
	
	public void setBattleStatus(boolean hasBlacksmith)  {
		update( "hasblacksmith",hasBlacksmith? 1 : 0);
		this.hasBlacksmith = hasBlacksmith;
		
	}
	
	public boolean getHasItemShop() {
		
		return hasItemShop;
	}
	
	public void setHasItemShop(boolean hasItemShop) {
		update( "hasitemshop",hasItemShop? 1 : 0);
		this.hasItemShop = hasItemShop;
		
	}
	
	public boolean getHasWeaponShop() {
		
		return hasWeaponShop;
	}
	
	public void setHasWeaponShop(boolean hasWeaponShop) {
		update( "hasweaponshop",hasWeaponShop? 1 : 0);
		this.hasWeaponShop = hasWeaponShop;
		
	}
	
	public boolean getHasMonsters() {
		return hasMonsters;
	}
	
	public void setHasMonsters(boolean hasMonsters) {
		update("hasMonsters", hasMonsters? 1 : 0);
		this.hasMonsters = hasMonsters;
	}
	
	public String getItemShopName() {
		return itemShopName;
	}
	
	// Realized won't be needing to set anything. gonna leave setters out. 
	
	public String getWeaponShopName() {
		return weaponShopName;
	}
	
	public String getBlacksmithName() {
		return blacksmithName;
	}
	
	public List<Integer> getItemShop() {
		return itemShop;
	}
	
	public List<Integer> getWeaponShop() {
		return weaponShop;
	}
	
	public List<Integer> getLocationList() {
		return locationList;
	}
	
	public List<Integer> getMonsterList() {
		return monsterList;
	}
	

}
