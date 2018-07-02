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
	private List<Integer> itemShop = new ArrayList<>();
	private List<Integer> weaponShop = new ArrayList<>();
	private List<Integer> blacksmith = new ArrayList<>();
	private List<Integer> monsterList = new ArrayList<>();
	private List<Integer> locationList = new ArrayList<>();

	// ====================================

	private boolean first = true;

	public LocationData(int location_id) {
		update(location_id);
	}

	private void update(int location_id) {
		this.locationid = location_id;
		try {
			Statement statement = Konosuba.REGISTRY.createStatement();
			if(first) {
				statement.execute(
						"CREATE TABLE IF NOT EXISTS 'locations' (" +
								"locationid    INTEGER PRIMARY KEY NOT NULL DEFAULT 0," +
								"mainlocation  TEXT NOT NULL," +
								"sublocation   TEXT NOT NULL," +
								"itemshopname  TEXT NOT NULL," +
								"weaponshopname TEXT NOT NULL," +
								"blacksmithname TEXT NOT NULL," +
								"itemshop      TEXT NOT NULL DEFAULT '[]'," +
								"weaponshop    TEXT NOT NULL DEFAULT '[]'," +
								"blacksmith    TEXT NOT NULL DEFAULT '[]'," +
								"monsterlist   TEXT NOT NULL DEFAULT '[]'," +
								"locationlist  TEXT NOT NULL DEFAULT '[]'" +
								");"
						);
				first = false;
			}
			ResultSet result = statement.executeQuery("SELECT * FROM 'locations' WHERE locationid=" + location_id + ";");
			boolean hasResult =  result.next();

			mainLocation    = hasResult ? result.getString("mainlocation") : null;
			subLocation     = hasResult ? result.getString("sublocation") : null;
			itemShopName    = hasResult ? result.getString("itemshopname") : null;
			weaponShopName  = hasResult ? result.getString("weaponshopname") : null;
			blacksmithName  = hasResult ? result.getString("blacksmithname") : null;

			if(hasResult) {
				for(Object item : new JSONArray(result.getString("itemshop"))) itemShop.add((int) item);

				for(Object wea : new JSONArray(result.getString("weaponshop"))) weaponShop.add((int) wea);

				for(Object mon : new JSONArray(result.getString("monsterlist"))) monsterList.add((int) mon);	

				for(Object loc : new JSONArray(result.getString("locationlist"))) locationList.add((int) loc);

				for(Object bla : new JSONArray(result.getString("blacksmith"))) blacksmith.add((int) bla);
				
			}else {
				itemShop = null;
				weaponShop = null;
				monsterList = null;
				locationList = null;
				blacksmith = null;
			}

			statement.close();
		} catch (Exception e) {

		}


	}
	//	private void update(String key, Object value) {
	//		try {
	//			Statement statement = Konosuba.REGISTRY.createStatement();
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
			Statement statement = Konosuba.REGISTRY.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM 'locations' WHERE sublocation='" +locationName+ "';");
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

	public List<Integer> getBlackSmith() {
		return blacksmith;
	}
	
	

}
