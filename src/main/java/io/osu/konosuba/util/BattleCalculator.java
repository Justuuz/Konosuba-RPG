package io.osu.konosuba.util;

import io.magiccraftmaster.util.Calculator;

public class BattleCalculator {
	
	public int physicalCalculation(int Strength, int PhyDef) {
		int value  = (int) ((int) Math.log((Strength/Math.sqrt(PhyDef))) * Math.pow(Strength, .33));
		value = value + (int)(value * Calculator.randomIn( -.10, .10, false));
		return value;
	}
	
	public int magicalCalculation(int magic, int magDef) {
		int value = (int) ((int) Math.log((magic/Math.sqrt(magDef)))* Math.pow(magic, .33));
		value  = value + (int)(value*Calculator.randomIn(-.10, .10, false));
		return value;
	}

	public double physicalHitChance(int strength, int dex) {
		return (100/71) * Math.atan(dex/strength) + 50;
	}
	
	public double magicalHitChance(int magic, int dex) {
		return (100/71) * Math.atan(dex/magic) + 50;
		
	}
	
	public double evadeChance(int evadeDex, int otherDex) {
		return (int) ((80/71) * Math.atan(evadeDex/otherDex));
	}
}
