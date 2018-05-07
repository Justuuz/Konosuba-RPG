package io.osu.konosuba.commands;

import java.awt.Color;
import java.util.ArrayList;

import components.map.Map;
import components.map.Map2;
import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.ClientData;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Start extends Command {

	public Start() {
		super(Konosuba.COLOR, Konosuba.PREFIX, "start", "the journey begins", null, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run(MessageReceivedEvent event, String[] args) {

		ClientData player = Konosuba.CLIENT_DATA_MANAGER.getData(event.getAuthor().getIdLong());


		//			send(event.getGuild(), event.getChannel(), "embeded means getStartStatus = true (this is before first if statement" ,player.getStartStatus());
		if(args.length == 1) {
			if(!player.getStartStatus()) {
				//Map<String, Integer> items = new Map2<String, Integer>();



				player.setStartStatus(true);
				player.setLocation("Axel");
				player.setClassType("None");
				player.setBoots("None");
				player.setCape("None");
				player.setChest("None");
				player.setHelmet("None");
				player.setLeggings("None");
				player.setNecklace("None");
				player.setOffHand("None");
				player.setRing("None");
				player.setBalance(0);
				player.setOnHand("None");
				for(int i = 9; i >= 0; i--) {

				}
				//					/*
				//					 * add "none" to all inventory types
				//					 */

				player.setInventory(new ArrayList<ArrayList<String>>());
				player.getInventory().add(new ArrayList<String>());
				player.getInventory().get(0).add("None");
				player.getInventory().add(new ArrayList<String>());
				player.getInventory().get(1).add("None"); 
				player.getInventory().add(new ArrayList<String>());
				player.getInventory().get(2).add("None");
				player.getInventory().add(new ArrayList<String>());
				player.getInventory().get(3).add("None");
				player.getInventory().add(new ArrayList<String>());
				player.getInventory().get(4).add("None");
				player.getInventory().add(new ArrayList<String>());
				player.getInventory().get(5).add("None");
				player.getInventory().add(new ArrayList<String>());
				player.getInventory().get(6).add("None");
				player.getInventory().add(new ArrayList<String>());
				player.getInventory().get(7).add("None");
				player.getInventory().add(new ArrayList<String>());
				player.getInventory().get(8).add("None");
				player.setItems(new Map2<String, Integer>());

				String name = (event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName());
				send(event.getGuild(), event.getChannel(), "Welcome " + name +"! Today is the day you start your adventure! Before we can start, I must ask "
						+ "traveler, what class are? Do *start choose Class Name to begin!",true);

				send(event.getGuild(), event.getChannel(), "||Classes||\nWizard\r\n" + 
						"Arch_Wizard\r\n" + 
						"Crusader\r\n" + 
						"Adventurer\r\n" + 
						"Cleric\r\n" + 
						"Mage\r\n" + 
						"Thief\r\n" + 
						"Priest\r\n" + 
						"Warrior\r\n" + 
						"Rogue\r\n" + 
						"Merchant",true);
				Konosuba.CLIENT_DATA_MANAGER.trySave();
				return;	
			}else{

				send(event.getGuild(), event.getChannel(), "You already have started!", true);
				return;
			}

		}
		
		if(player.getStartStatus()) {
			if(args[1].equalsIgnoreCase("classes")) {
				send(event.getGuild(), event.getChannel(), "||Classes||\nWizard\r\n" + 
						"Arch_wizard\r\n" + 
						"Crusader\r\n" + 
						"Adventurer\r\n" + 
						"Cleric\r\n" + 
						"Mage\r\n" + 
						"Thief\r\n" + 
						"Priest\r\n" + 
						"Warrior\r\n" + 
						"Rogue\r\n" + 
						"Merchant",true);
				return;

			}

			if(args[1].equalsIgnoreCase("choose")) {
				if(args[2].equalsIgnoreCase("wizard") & player.getClassType().equals("None")) {
					send(event.getGuild(), event.getChannel(), "You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
					player.setClassType("wizard");
				}else if(args[2].equalsIgnoreCase("arch_wizard") & player.getClassType().equals("None")) {
					send(event.getGuild(), event.getChannel(), "You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
					player.setClassType("arch_wizard");
				}else if(args[2].equalsIgnoreCase("crusader") & player.getClassType().equals("None")) {
					send(event.getGuild(), event.getChannel(), "You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
					player.setClassType("crusader");
				}else if(args[2].equalsIgnoreCase("adventurer") & player.getClassType().equals("None")) {
					send(event.getGuild(), event.getChannel(), "You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
					player.setClassType("adventurer");
				}else if(args[2].equalsIgnoreCase("cleric") & player.getClassType().equals("None")) {
					send(event.getGuild(), event.getChannel(), "You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
					player.setClassType("cleric");
				}else if(args[2].equalsIgnoreCase("thief") & player.getClassType().equals("None")) {
					send(event.getGuild(), event.getChannel(), "You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
					player.setClassType("thief");
				}else if(args[2].equalsIgnoreCase("priest") & player.getClassType().equals("None")) {
					send(event.getGuild(), event.getChannel(), "You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
					player.setClassType("priest");
				}else if(args[2].equalsIgnoreCase("warrior") & player.getClassType().equals("None")) {
					send(event.getGuild(), event.getChannel(), "You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
					player.setClassType("warrior");
				}else if(args[2].equalsIgnoreCase("rogue") & player.getClassType().equals("None")) {
					send(event.getGuild(), event.getChannel(), "You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
					player.setClassType("rogue");
				}else if(args[2].equalsIgnoreCase("merchant") & player.getClassType().equals("None")) {
					send(event.getGuild(), event.getChannel(), "You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
					player.setClassType("merchant");
				}else {
					send(event.getGuild(), event.getChannel(), "Not one of the classes!", true);
				}

				Konosuba.CLIENT_DATA_MANAGER.trySave();
			}

		}else {
			send(event.getGuild(), event.getChannel(), "You haven't started yet!", true);
			return;
		}
	}
}











