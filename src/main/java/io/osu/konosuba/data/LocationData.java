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
	private String mainLocation;
	private String subLocation;
	private String itemShopName;
	private String weaponShopName;
	private String blacksmithName;
	private String magicShopName;
	private List<Integer> itemShop;
	private List<Integer> weaponShop;
	private List<Integer> magicShop;
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
						"mainlocation  TEXT NOT NULL," +
						"sublocation   TEXT NOT NULL," +
						"itemshopname  TEXT NOT NULL," +
						"weaponshopname TEXT NOT NULL," +
						"blacksmithname TEXT NOT NULL," +
						"magicshopname  TEXT NOT NULL," +
						"itemshop      TEXT NOT NULL DEFAULT '[]'," +
						"weaponshop    TEXT NOT NULL DEFAULT '[]'," +
						"magicshop     TEXT NOT NULL DEFAULT '[]'," +
						"monsterlist   TEXT NOT NULL DEFAULT '[]'," +
						"locationlist  TEXT NOT NULL DEFAULT '[]'" +
						");"
					);
				first = false;
			}
			ResultSet result = statement.executeQuery("SELECT * FROM 'locations' WHERE locationid=" + locationid + ";");
			boolean hasResult =  result.next();
			
			mainLocation    = hasResult ? result.getString("mainlocation") : null;
			subLocation     = hasResult ? result.getString("sublocation") : null;
			itemShopName    = hasResult ? result.getString("itemshopname") : null;
			weaponShopName  = hasResult ? result.getString("weaponshopname") : null;
			blacksmithName  = hasResult ? result.getString("blacksmithname") : null;
			magicShopName   = hasResult ? result.getString("magicshopname") : null;
			
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
				
				raw = new JSONArray(result.getString("magicshop"));
				for(Object obj : raw) {
					stringList.add((int) obj);
				}
				magicShop = stringList;
				stringList.clear();
				
				
			}else {
				itemShop = null;
				weaponShop = null;
				monsterList = null;
				locationList = null;
				magicShop = null;
			}
			
			statement.close();
		} catch (Exception e) {
			
		}
		
		
	}
//	private void update(String key, Object value) {
//		try {
//			Statement statement = Konosuba.CONNECTION2.createStatement();
//			statement.addBatch("INSERT OR IGNORE INTO 'locations' (locationid,"+key+") VALUES ("+locationid+",'"+value+"');");
//			statement.addBatch("UPDATE 'location' SET "+key+"='"+value+"' WHERE locationid="+locationid+";");
//			statement.executeBatch();
//			statement.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	// Realized won't be needing to set anything. gonna leave setters out. 
	
	public int getLocationId(String locationName) {
		int value = -1;
		try {
			Statement statement = Konosuba.CONNECTION2.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM 'locations' WHERE sublocation=" +locationName+ ";");
			boolean hasResult = result.next();
			value = hasResult ? result.getInt("locationid") : -1;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return value;
	}
	
	public String getMainLocation() {
		return mainLocation;
	}
	
	public String getSubLocation() {
		return subLocation;
	}
	
	public String getItemShopName() {
		return itemShopName;
	}
	
	public String getWeaponShopName() {
		return weaponShopName;
	}
	
	public String getBlacksmithName() {
		return blacksmithName;
	}
	
	public String getMagicShopName() {
		return magicShopName;
	}
	
	public List<Integer> getItemShop() {
		return itemShop;
	}
	
	public List<Integer> getWeaponShop() {
		return weaponShop;
	}
	
	public List<Integer> getMagicShop() {
		return magicShop;
	}
	
	public List<Integer> getLocationList() {
		return locationList;
	}
	
	public List<Integer> getMonsterList() {
		return monsterList;
	}
	

}
