package io.osu.konosuba.data;

import java.util.ArrayList;

import components.map.Map;

@SuppressWarnings({"unused", "WeakerAccess"})
public class GearData {
	private String gear;

	public GearData(String gear) {
		this.gear = gear;
	}

	public String getGear() {
		return gear;
	}



	/*
	 * To add more values just create a variable and any getter/setter methods
	 */


	// Stats

	
	private int strength, physicalDefense, magicalDefense, dexterity, magic, luck, hitpoints;
	
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
	
	public int getLuck() {
		return luck;
	}
	
	public void setLuck(int luck) {
		this.luck = luck;
	}
	
}
