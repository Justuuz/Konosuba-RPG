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
		GearData helmet = Konosuba.GEAR_DATA_MANAGER.getData(player.getHelmet());
		gear.add(helmet);
		GearData chest = Konosuba.GEAR_DATA_MANAGER.getData(player.getChest());
		gear.add(chest);
		GearData legs = Konosuba.GEAR_DATA_MANAGER.getData(player.getLeggings());
		gear.add(legs);
		GearData boot = Konosuba.GEAR_DATA_MANAGER.getData(player.getBoots());
		gear.add(boot);
		GearData cape = Konosuba.GEAR_DATA_MANAGER.getData(player.getCape());
		gear.add(cape);
		GearData ring = Konosuba.GEAR_DATA_MANAGER.getData(player.getRing());
		gear.add(ring);
		GearData onhand = Konosuba.GEAR_DATA_MANAGER.getData(player.getOnHand());
		gear.add(onhand);
		GearData offhand = Konosuba.GEAR_DATA_MANAGER.getData(player.getOffHand());
		gear.add(offhand);
		GearData necklace = Konosuba.GEAR_DATA_MANAGER.getData(player.getNecklace());
		gear.add(necklace);
		
		
		
		int strength = 0;
		int phyDef = 0;
		int magDef = 0;
		int magic = 0;
		int dex = 0;
		int luck = 0;
		
		for(GearData obj: gear) {
			strength += obj.getStrength();
			phyDef += obj.getPhysicalDefense();
			magDef += obj.getMagicalDefense();
			magic += obj.getMagic();
			dex += obj.getDexterity();
			luck += obj.getLuck();
		}
		
		strength += Konosuba.CLASS_DATA_MANAGER.getData(player.getClassType()).getStrength();
		phyDef += Konosuba.CLASS_DATA_MANAGER.getData(player.getClassType()).getPhysicalDefense();
		magDef += Konosuba.CLASS_DATA_MANAGER.getData(player.getClassType()).getMagicalDefense();
		magic += Konosuba.CLASS_DATA_MANAGER.getData(player.getClassType()).getMagic();
		dex += Konosuba.CLASS_DATA_MANAGER.getData(player.getClassType()).getDexterity();
		luck += Konosuba.CLASS_DATA_MANAGER.getData(player.getClassType()).getLuck();
		
		
		player.setStrength(strength);
		player.setPhysicalDefense(phyDef);
		player.setMagicalDefense(magDef);
		player.setMagic(magic);
		player.setDexterity(dex);
		player.setLuck(luck);
		
		Konosuba.CLIENT_DATA_MANAGER.trySave();
		
	}
}
