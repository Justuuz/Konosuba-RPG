package io.osu.konosuba.util;

import io.magiccraftmaster.util.Calculator;

public class BattleCalculator {
	
	public static int physicalCalculation(int Strength, int PhyDef) {
		int value  = (int) ((int) Math.log((Strength/Math.sqrt(PhyDef))) * Math.pow(Strength, .33));
		value = value + (int)(value * Calculator.randomIn( -.10, .10, false));
		return value;
	}
	
	public static int magicalCalculation(int magic, int magDef) {
		int value = (int) ((int) Math.log((magic/Math.sqrt(magDef)))* Math.pow(magic, .33));
		value  = value + (int)(value*Calculator.randomIn(-.10, .10, false));
		return value;
	}

	public static double physicalHitChance(int strength, int dex) {
		return (100/Math.PI) * Math.atan(Math.pow(dex, 1.2)/Math.pow(strength, .8)) + 50;
	}
	
	public static double magicalHitChance(int magic, int dex) {
		return (100/Math.PI) * Math.atan(Math.pow(dex, 1.2)/Math.pow(magic, .8)) + 50;
		
	}
	
	public static double evadeChance(int evadeDex, int otherDex) {
		return (int) ((80/71) * Math.atan(evadeDex/otherDex));
	}
}
