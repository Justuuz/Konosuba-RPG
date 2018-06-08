package io.osu.konosuba.util;

import io.osu.konosuba.data.ClassData;
import io.osu.konosuba.data.GearData;
import io.osu.konosuba.data.UserData;

import java.util.ArrayList;


public class PointsHandler {
	
	public PointsHandler() {
	}

	public void recalibratePoints(UserData player) throws Exception{
		
//		Object obj = new JsonParser().parse(new FileReader("Data/User.JSON"));
//		JsonObject user = (JsonObject) obj; 
//		
//		Object obj2 = new JsonParser().parse(new FileReader("Data/Armor.JSON"));
//		JsonObject armor = (JsonObject) obj2;
//		
//		Object obj3 = new JsonParser().parse(new FileReader("Data/Classes.JSON"));
//		JsonObject classes = (JsonObject) obj3;
//		
//		Object obj4 = new JsonParser().parse(new FileReader("Data/Weapons.JSON"));
//		JsonObject wep = (JsonObject) obj4;
		
		ArrayList<GearData> gear = new ArrayList<>();
		
		gear.add(new GearData(player.getHelmet()));
		
		gear.add(new GearData(player.getChest()));
		
		gear.add(new GearData(player.getLegs()));
		
		gear.add(new GearData(player.getBoots()));
		
		gear.add(new GearData(player.getCape()));
		
		gear.add(new GearData(player.getRing()));
		
		gear.add(new GearData(player.getPrimary()));
		
		gear.add(new GearData(player.getOffhand()));
		
		gear.add(new GearData(player.getNecklace()));
		
		
		
		
		
		int strength = 0;
		int phyDef = 0;
		int magDef = 0;
		int magic = 0;
		int dex = 0;
		int luck = 0;
		int health = 0;
		
		for(GearData obj: gear) {
			System.out.println(obj.getStrength());
			strength = strength + obj.getStrength();
			System.out.println(obj.getPhysicalDefense());
			phyDef = phyDef + obj.getPhysicalDefense();
			System.out.println(obj.getMagicalDefense());
			magDef = magDef + obj.getMagicalDefense();
			System.out.println(obj.getMagic());
			magic = magic + obj.getDexterity();
			System.out.println(obj.getStrength());
			dex = dex + obj.getLuck();
			System.out.println(obj.getStrength());
			luck = luck + obj.getLuck();
			health = luck + obj.getHitpoints();
		}
		

		strength = (int)(strength * new ClassData(player.getClasses()).getStrength());
		phyDef = (int)(phyDef * new ClassData(player.getClasses()).getPhysicalDefense());
		magDef = (int)(magDef * new ClassData(player.getClasses()).getMagicalDefense());
		magic = (int)(magic * new ClassData(player.getClasses()).getMagic());
		dex = (int)(dex + new ClassData(player.getClasses()).getDexterity());
		luck =(int)(luck + new ClassData(player.getClasses()).getLuck());
		health = (int)(health + new ClassData(player.getClasses()).getHealth());
		
		
		player.setStrength(strength);
		player.setPhysicalDefense(phyDef);
		player.setMagicalDefense(magDef);
		player.setMagic(magic);
		player.setDexterity(dex);
		player.setLuck(luck);
		player.setHealth(health);
		

		
	}
}
