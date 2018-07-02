package io.osu.konosuba.data.item;

public enum ItemType {
	GENERIC("Plain"),
	POTION("Potion"),

	WEAPON_MELEE("Melee weapon"),
	WEAPON_RANGED("Ranged weapon"),

	ARMOR_HELMET("Helmet"),
	ARMOR_CHEST("Chest-plate"),
	ARMOR_LEGS("Leggings"),
	ARMOR_FEET("Boots"),

	CHARM("Neck charm"),
	CAPE("Cape/cloak");

	private final String displayName;

	ItemType(String displayName) {
		this.displayName = displayName;
	}

	public final String getDisplayName() {
		return displayName;
	}
}
