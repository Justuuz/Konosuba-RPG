package io.osu.konosuba.util;

import io.osu.konosuba.data.ClassData;
import io.osu.konosuba.data.ItemData;
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
		
		ArrayList<ItemData> gear = new ArrayList<>();
		
		gear.add(new ItemData(player.getHelmet()));
		
		gear.add(new ItemData(player.getChest()));
		
		gear.add(new ItemData(player.getLegs()));
		
		gear.add(new ItemData(player.getBoots()));
		
		gear.add(new ItemData(player.getCape()));
		
		gear.add(new ItemData(player.getRing()));
		
		gear.add(new ItemData(player.getPrimary()));
		
		gear.add(new ItemData(player.getOffhand()));
		
		gear.add(new ItemData(player.getNecklace()));
		
		
		
		
		
		int strength = 0;
		int phyDef = 0;
		int magDef = 0;
		int magic = 0;
		int dex = 0;
		int luck = 0;
		int health = 0;
		
		for(ItemData obj: gear) {	
			strength = strength + obj.getStrength();
			phyDef = phyDef + obj.getPhysicalDefense();
			magDef = magDef + obj.getMagicalDefense();
			magic = magic + obj.getDexterity();
			dex = dex + obj.getLuck();
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
