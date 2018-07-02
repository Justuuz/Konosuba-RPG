package io.osu.konosuba.util;

public class BattleCalculator {
	
	public static double physicalCalculation(int strength, int phyDef) {
		 double randomN = (Math.random()*(0.2) + 0.9); //random number from 0.9 to 1.1
	     return Math.log(strength) / Math.log(Math.pow(phyDef,0.8)) * Math.pow(strength, 0.667) * randomN;
	}
	
	public static double magicalCalculation(int magic, int magDef) {
		double randomN = (Math.random()*(0.2) + 0.9); //random number from 0.9 to 1.1
        return Math.log(magic) / Math.log(Math.pow(magDef,0.8)) * Math.pow(magic, 0.667) * randomN;
	}

	public static double physicalHitChance(int strength, int dex) {
		return (100/Math.PI) * Math.atan(Math.pow(dex, 1.2)/Math.pow(strength, .8)) + 50;
	}
	
	public static double magicalHitChance(int magic, int dex) {
		return (100/Math.PI) * Math.atan(Math.pow(dex, 1.2)/Math.pow(magic, .8)) + 50;
		
	}
	
	public static double evadeChance(int evadeDex, int otherDex) {
		return  ((80/Math.PI) * Math.atan(evadeDex/otherDex));
	}
	
	public static double Luck(int luck, int otherLuck) {
        return (150/Math.PI) * Math.atan(luck / otherLuck);
    }
}
