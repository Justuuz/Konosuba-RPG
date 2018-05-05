package io.osu.konosuba.data;

import java.util.ArrayList;

import components.map.Map;

@SuppressWarnings({"unused", "WeakerAccess"})
public class ClientData {
	private final long clientId;
	private int balance;

	public ClientData(long clientId) {
		this.clientId = clientId;
	}

	public long getClientId() {
		return clientId;
	}


	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}


	/*
	 * To add more values just create a variable and any getter/setter methods
	 */


	// RPG stuff

	private String helmet, chest, leggings, boots, classType, onHand, offHand, cape, necklace, ring, weapon;
	private ArrayList<ArrayList<String>> inventory;
	private boolean battleStatus, startStatus, classStatus;
	private int strength, physicalDefense, magicalDefense, dexterity, magic, luck, hitpoints, durability;
	private Map<String, Integer> items;
	

	public String getHelmet() {
		return helmet;
	}

	public void setHelmet(String helmet) {
		this.helmet = helmet;
	}

	public String getChest() {
		return chest;
	}

	public void setChest(String chest) {
		this.chest = chest;
	}

	public String getLeggings() {
		return leggings;
	}

	public void setLeggings(String leggings) {
		this.leggings = leggings;
	}

	public String getBoots() {
		return boots;
	}

	public void setBoots(String boots) {
		this.boots = boots;
	}

	public ArrayList<ArrayList<String>> getInventory() {
		return inventory;
		/*
		 * notice that inventory returns a ArrayList<ArrayList<String>>
		 */
	}
	
	public void setInventory(ArrayList<ArrayList<String>> inventory) {
		this.inventory = inventory;
	}

	public void addInventory(String item, int type) {
		ArrayList<String> subInv = this.inventory.remove(type);
		subInv.add(item);	
		this.inventory.add(type, subInv);
	}
	
	public void removeInventory(String item, int type) {
		ArrayList<String> subInv = this.inventory.remove(type);
		subInv.remove(item);	
		this.inventory.add(type, subInv); 

	}
	
	public String getClassType() {
		return classType;
	}
	
	public void setClassType(String classType) {
		this.classType = classType;
	}
	
	public String getOnHand() {
		return onHand;
	}
	
	public void setOnHand(String onHand) {
		this.onHand = onHand;
	}
	
	//Removed For Now
//	public int getDurability() {
//		return durability;
//	}
//	
//	public void setDurability(int durability) {
//		this.durability = durability;
//	}

	public String getOffHand() {
		return offHand;
	}
	
	public void setOffHand(String offHand) {
		this.offHand = offHand;
	}
	
	public String getCape() {
		return cape;
	}
	
	public void setCape(String cape) {
		this.cape = cape;
	}
	
	public String getNecklace() {
		return necklace;
	}
	
	public void setNecklace(String necklace) {
		this.necklace = necklace;
	}
	
	public String getRing() {
		return ring;
	}
	
	public void setRing(String ring) {
		this.ring = ring;
	}
	
	public String getWeapon() {
		return weapon;
	}
	
	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}
	
	public boolean getBattleStatus() {
		return battleStatus;
	}
	
	public void setBattleStatus(boolean battleStatus) {
		this.battleStatus = battleStatus;
	}
	
	public boolean getStartStatus() {
		return startStatus;
	}
	
	public void setStartStatus(boolean startStatus) {
		this.startStatus = startStatus;
	}
	
	public boolean getClassStatus() {
		return startStatus;
	}
	
	public void setClassStatus(boolean classStatus) {
		this.classStatus = classStatus;
	}
	
	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}
	
	public int getPhysicalDefense() {
		return physicalDefense;
	}

	public void setPhysicalDefense(int physicalDefense) {
		this.physicalDefense = physicalDefense;
	}
	
	public int getMagicalDefense() {
		return magicalDefense;
	}

	public void setMagicalDefense(int magicalDefense) {
		this.magicalDefense = magicalDefense;
	}
	
	public int getMagic() {
		return magic;
	}

	public void setMagic(int magic) {
		this.magic = magic;
	}
	
	public int getDexterity() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}
	
	public int getLuck() {
		return luck;
	}
	
	public void setLuck(int luck) {
		this.luck = luck;
	}
	
	
	public int getHitpoints() {
		return hitpoints;
	}
	
	public void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
	}
	
	public Map<String, Integer> getItems(){
		return items;
	}
	
	public void setItems(Map<String, Integer> items) {
		this.items = items;
	}
	public void addItems(String item, int amount) {
		if(this.getItems().hasKey(item)) {
			Map.Pair<String, Integer> itemPair = this.getItems().remove(item);
			int amt = itemPair.value() + amount;
			this.getItems().add(item, amt);
			
		}
		else {
			this.getItems().add(item, amount);
		}
	}
	
	public void removeItems(String item, int amount) {
		/*
		 * amount will be less than or equal to the value
		 * precondition xdddd
		 * 
		 * also there IS an item in the map when called
		 */
		
		Map.Pair<String, Integer> itemPair = this.getItems().remove(item);
		int amt = itemPair.value() - amount;
		if(amt > 0) {
			this.getItems().add(item,  amt);
		}
		// don't add the map if amt == 0
		
		


	}
	
}
