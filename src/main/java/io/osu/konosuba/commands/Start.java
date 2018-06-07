package io.osu.konosuba.commands;

import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.UserData;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class Start extends Command {

	public Start() {
		super(Konosuba.COLOR, Konosuba.PREFIX, "start", "the journey begins", null, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run(MessageReceivedEvent event, String[] args) {


		try {
			UserData player =new UserData(event.getAuthor().getIdLong());


			//			send(event.getGuild(), event.getChannel(), "embeded means getStartStatus = true (this is before first if statement" ,player.getStartStatus());
			if(args.length == 1) {
				if(!player.getStartStatus()) {
					player.setStartStatus(true);
					player.setClasses("None");
					player.setBoots("None");
					player.setCape("None");
					player.setChest("None");
					player.setHelmet("None");
					player.setLegs("None");
					player.setNecklace("None");
					player.setOffhand("None");
					player.setRing("None");
					player.setBalance(0);
					player.setOnhand("None");
					player.setInventory(new ArrayList<>());
					player.getInventory().add(new ArrayList<String>());				
					player.getInventory().add(new ArrayList<String>());					
					player.getInventory().add(new ArrayList<String>());					
					player.getInventory().add(new ArrayList<String>());
					player.getInventory().add(new ArrayList<String>());
					player.getInventory().add(new ArrayList<String>());
					player.getInventory().add(new ArrayList<String>());
					player.getInventory().add(new ArrayList<String>());
					player.getInventory().add(new ArrayList<String>());
					player.setItems(new HashMap<>());

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
					if(args[2].equalsIgnoreCase("wizard") & player.getClasses().equals("None")) {
						send(event.getGuild(), event.getChannel(), "You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
						player.setClasses("wizard");
					}else if(args[2].equalsIgnoreCase("arch_wizard") & player.getClasses().equals("None")) {
						send(event.getGuild(), event.getChannel(), "You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
						player.setClasses("arch_wizard");
					}else if(args[2].equalsIgnoreCase("crusader") & player.getClasses().equals("None")) {
						send(event.getGuild(), event.getChannel(), "You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
						player.setClasses("crusader");
					}else if(args[2].equalsIgnoreCase("adventurer") & player.getClasses().equals("None")) {
						send(event.getGuild(), event.getChannel(), "You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
						player.setClasses("adventurer");
					}else if(args[2].equalsIgnoreCase("cleric") & player.getClasses().equals("None")) {
						send(event.getGuild(), event.getChannel(), "You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
						player.setClasses("cleric");
					}else if(args[2].equalsIgnoreCase("thief") & player.getClasses().equals("None")) {
						send(event.getGuild(), event.getChannel(), "You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
						player.setClasses("thief");
					}else if(args[2].equalsIgnoreCase("priest") & player.getClasses().equals("None")) {
						send(event.getGuild(), event.getChannel(), "You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
						player.setClasses("priest");
					}else if(args[2].equalsIgnoreCase("warrior") & player.getClasses().equals("None")) {
						send(event.getGuild(), event.getChannel(), "You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
						player.setClasses("warrior");
					}else if(args[2].equalsIgnoreCase("rogue") & player.getClasses().equals("None")) {
						send(event.getGuild(), event.getChannel(), "You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
						player.setClasses("rogue");
					}else if(args[2].equalsIgnoreCase("merchant") & player.getClasses().equals("None")) {
						send(event.getGuild(), event.getChannel(), "You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
						player.setClasses("merchant");
					}else {
						send(event.getGuild(), event.getChannel(), "Not one of the classes!", true);
					}


				}

			}else {
				send(event.getGuild(), event.getChannel(), "You haven't started yet!", true);
				return;
			}
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
	}
}











