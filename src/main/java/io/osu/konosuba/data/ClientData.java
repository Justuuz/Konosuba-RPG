package io.osu.konosuba.data;

import java.util.ArrayList;

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

	private String helmet, chest, leggings, boots, classType, onHand, offHand, cape;
	private ArrayList<String> inventory;
	private boolean battleStatus;
	private int strength, physicalDefense, magicalDefense, dexterity, magic, luck, hitpoints;
	

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

	public ArrayList<String> getInventory() {
		return inventory;
	}

	public void addInventory(String item) {
		this.inventory.add(item);	
		}
	
	public void removeInventory(String item) {
		this.inventory.remove(item);
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
	
	public boolean getBattleSatus() {
		return battleStatus;
	}
	
	public void setBattleStatus(boolean battleStatus) {
		this.battleStatus = battleStatus;
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
	
	public int getHitpoints() {
		return hitpoints;
	}
	
	public void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
	}
	
	
}
