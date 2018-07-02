package io.osu.konosuba.data.item;

import io.magiccraftmaster.util.ArrayUtils;
import io.osu.konosuba.Konosuba;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class Item {
	// = Item data cache ==================================================================================
	private static final int CACHE_SIZE = 25; // This can be altered. Default: 25 (be careful)
	private static final Item[] CACHE = new Item[CACHE_SIZE];

	public static Item getItem(int id) {
		Item item = null;

		for (int i=0; i<CACHE_SIZE; i++) {
			if (CACHE[i] == null) continue; // Fixes possible NPE
			if (CACHE[i].getId() == id) {
				item = CACHE[i];
				CACHE[i] = null;
				ArrayUtils.cacheDrop(CACHE);
				CACHE[0] = item;
				return item;
			}
		}

		CACHE[CACHE_SIZE-1] = null;
		ArrayUtils.cacheDrop(CACHE);
		CACHE[0] = new Item(id);
		item = CACHE[0];

		return item;
	}

	// = Item data ========================================================================================
	private final int id;

	private String name, description;
	private int type, buyValue, sellValue;
	private int luck, magic, health, strength, dexterity, magicalDefence, physicalDefence;

	private Item(int id) {
		this.id = id;
		this.type = -1; // This is to prevent SQL query spam
	}

	public int getId() {
		return id;
	}

	private void snatch(boolean typeOnly) {
		if (type != -1   && typeOnly) return;
		if (name != null && !typeOnly) return;

		try {
			Statement statement = Konosuba.REGISTRY.createStatement();
			ResultSet result = statement.executeQuery("SELECT " + (typeOnly ? "type" : "*") + " FROM 'items' WHERE itemid="+id+";");

			type = result.getInt("type");
			if (!typeOnly) {
				name        = result.getString("name");
				description = result.getString("description");
			}

			statement.close();
		} catch (SQLException e) {
			Konosuba.LOGGER.error("Failed to get item data for id '" + id + "' received error message: " + e.getMessage());
		}
	}

	public String getName() {
		snatch(false);
		return name;
	}

	public String getDescription() {
		snatch(false);
		return description;
	}

	public int getType() {
		snatch(true);
		return type;
	}

	public ItemType getEnumType() {
		if (getType() >= 0 && type < ItemType.values().length) return ItemType.values()[getType()];
		return ItemType.GENERIC;
	}

	public int getBuyValue() {
		snatch(false);
		return buyValue;
	}

	public int getSellValue() {
		snatch(false);
		return sellValue;
	}

	public int getLuck() {
		snatch(false);
		return luck;
	}

	public int getMagic() {
		snatch(false);
		return magic;
	}

	public int getHealth() {
		snatch(false);
		return health;
	}

	public int getStrength() {
		snatch(false);
		return strength;
	}

	public int getDexterity() {
		snatch(false);
		return dexterity;
	}

	public int getMagicalDefence() {
		snatch(false);
		return magicalDefence;
	}

	public int getPhysicalDefence() {
		snatch(false);
		return physicalDefence;
	}

	public boolean canSell() {
		return sellValue > 0;
	}
}
