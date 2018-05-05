package io.osu.konosuba.util;

import java.util.ArrayList;

import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.ClientData;
import io.osu.konosuba.data.GearData;


public class PointsHandler {
	
	public PointsHandler() {
	}

	public void recalibratePoints(ClientData player) {
		
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
		
		gear.add(Konosuba.GEAR_DATA_MANAGER.getData(player.getHelmet()));
		
		gear.add(Konosuba.GEAR_DATA_MANAGER.getData(player.getChest()));
		
		gear.add(Konosuba.GEAR_DATA_MANAGER.getData(player.getLeggings()));
		
		gear.add(Konosuba.GEAR_DATA_MANAGER.getData(player.getBoots()));
		
		gear.add(Konosuba.GEAR_DATA_MANAGER.getData(player.getCape()));
		
		gear.add(Konosuba.GEAR_DATA_MANAGER.getData(player.getRing()));
		
		gear.add(Konosuba.GEAR_DATA_MANAGER.getData(player.getOnHand()));
		
		gear.add(Konosuba.GEAR_DATA_MANAGER.getData(player.getOffHand()));
		
		gear.add(Konosuba.GEAR_DATA_MANAGER.getData(player.getNecklace()));
		
		
		
		int strength = 0;
		int phyDef = 0;
		int magDef = 0;
		int magic = 0;
		int dex = 0;
		int luck = 0;
		
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
		}
		
		System.out.println(Konosuba.CLASS_DATA_MANAGER.getData(player.getClassType()).getStrength());
		strength = strength + Konosuba.CLASS_DATA_MANAGER.getData(player.getClassType()).getStrength();
		phyDef = phyDef + Konosuba.CLASS_DATA_MANAGER.getData(player.getClassType()).getPhysicalDefense();
		magDef = magDef + Konosuba.CLASS_DATA_MANAGER.getData(player.getClassType()).getMagicalDefense();
		magic = magic + Konosuba.CLASS_DATA_MANAGER.getData(player.getClassType()).getMagic();
		dex = dex + Konosuba.CLASS_DATA_MANAGER.getData(player.getClassType()).getDexterity();
		luck = luck + Konosuba.CLASS_DATA_MANAGER.getData(player.getClassType()).getLuck();
		
		
		player.setStrength(strength);
		player.setPhysicalDefense(phyDef);
		player.setMagicalDefense(magDef);
		player.setMagic(magic);
		player.setDexterity(dex);
		player.setLuck(luck);
		
		Konosuba.CLIENT_DATA_MANAGER.trySave();
		
	}
}
