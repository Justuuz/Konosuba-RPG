package io.osu.konosuba.data;

import java.sql.ResultSet;
import java.sql.Statement;

import io.osu.konosuba.Konosuba;

public class ItemData {

	
	// = Cache ==============================
	private int itemid;
	private String name;
	
	
	// ======================================
	
	private boolean first = true;
	
	public ItemData(int itemid) throws Exception {
		update(itemid);
	}
	
	
	private void update(int itemid) {
		this.itemid = itemid;
		try {
			Statement statement = Konosuba.CONNECTION2.createStatement();
			if(first) {
				statement.execute(
						"CREATE TABLE IF NOT EXISTS 'items' ("+
							" itemid  INT PRIMARY KEY NOT NULL DEFAULT 0," +
							" name    TEXT NOT NULL,"
									
						);
				ResultSet result = statement.executeQuery("SELECT * FROM 'items' WHERE itemid=" + itemid + ";");
				boolean hasResult = result.next();
				name    = hasResult ?  result.getString("name") : null;
				
				statement.close();
			}
		}catch (Exception e) {
			
		}
	}
}
