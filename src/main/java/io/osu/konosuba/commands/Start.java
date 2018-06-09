package io.osu.konosuba.commands;

import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.UserData;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
					player.setClasses(0);
					player.setBoots(0);
					player.setCape(0);
					player.setChest(0);
					player.setHelmet(0);
					player.setLegs(0);
					player.setNecklace(0);
					player.setOffhand(0);
					player.setRing(0);
					player.setBalance(0);
					player.setOnhand(0);
					List<List<Integer>> list = new ArrayList<List<Integer>>();
					for(int i=0; i < 9; i++) {
						list.add(new ArrayList<Integer>());
					}
					player.setInventory(list);
					player.setItems(new HashMap<>());

					String name = (event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName());
					send(event.getGuild(), event.getChannel(), "Welcome " + name +"! Today is the day you start your adventure! Before we can start, I must ask "
							+ "traveler, what class are? Do *start choose Class Name to begin!"
							+ "\n" +
							 "||Classes||\nWizard\r\n" + 
								"Arch_wizard\r\n" + 
								"Crusader\r\n" + 
								"Adventurer\r\n" + 
								"Cleric\r\n" + 
								"Mage\r\n" + 
								"Thief\r\n" + 
								"Priest\r\n" + 
								"Warrior\r\n" + 
								"Rogue\r\n" + 
								"Merchant"
							,true);

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
					if(args[2].equalsIgnoreCase("wizard") & player.getClasses() == 0) {
						send(event.getGuild(), event.getChannel(), "You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
						player.setClasses(1);
					}else if(args[2].equalsIgnoreCase("arch_wizard") & player.getClasses() == 0) {
						send(event.getGuild(), event.getChannel(), "You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
						player.setClasses(2);
					}else if(args[2].equalsIgnoreCase("crusader") & player.getClasses() == 0) {
						send(event.getGuild(), event.getChannel(), "You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
						player.setClasses(3);
					}else if(args[2].equalsIgnoreCase("adventurer") & player.getClasses() == 0) {
						send(event.getGuild(), event.getChannel(), "You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
						player.setClasses(4);
					}else if(args[2].equalsIgnoreCase("cleric") & player.getClasses() == 0) {
						send(event.getGuild(), event.getChannel(), "You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
						player.setClasses(5);
					}else if(args[2].equalsIgnoreCase("thief") & player.getClasses() == 0) {
						send(event.getGuild(), event.getChannel(), "You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
						player.setClasses(6);
					}else if(args[2].equalsIgnoreCase("priest") & player.getClasses() == 0) {
						send(event.getGuild(), event.getChannel(), "You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
						player.setClasses(7);
					}else if(args[2].equalsIgnoreCase("warrior") & player.getClasses() == 0) {
						send(event.getGuild(), event.getChannel(), "You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
						player.setClasses(8);
					}else if(args[2].equalsIgnoreCase("rogue") & player.getClasses() == 0) {
						send(event.getGuild(), event.getChannel(), "You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
						player.setClasses(8);
					}else if(args[2].equalsIgnoreCase("merchant") & player.getClasses() == 0) {
						send(event.getGuild(), event.getChannel(), "You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
						player.setClasses(9);
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











