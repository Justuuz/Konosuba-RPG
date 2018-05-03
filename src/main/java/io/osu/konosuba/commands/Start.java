package io.osu.konosuba.commands;

import java.awt.Color;

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
		if(Konosuba.CLIENT_DATA_MANAGER.hasData(event.getChannel().getIdLong())) {
		ClientData player = new ClientData(event.getChannel().getIdLong());
		if(!player.getStartStatus()) {
			if(args.length == 1) {
					player.setStartStatus(true);
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
					String name = (event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName());
					send(event.getGuild(), event.getChannel(), "Welcome " + name +"! Today is the day you start your adventure! Before we can start, I must ask "
							+ "traveler, what class are? Do *start choose Class Name to begin!",true);
					
					send(event.getGuild(), event.getChannel(), "||Classes||\nwizard\r\n" + 
							"arch_wizard \r\n" + 
							"crusader \r\n" + 
							"adventurer\r\n" + 
							"Cleric\r\n" + 
							"Mage\r\n" + 
							"Thief\r\n" + 
							"Priest\r\n" + 
							"Warrior\r\n" + 
							"Rogue\r\n" + 
							"Merchant",true);
					Konosuba.CLIENT_DATA_MANAGER.trySave();
					return;
				}
		}else{
			send(event.getGuild(), event.getChannel(), "You already have started!", true);
			return;
			
		}
		if(player.getStartStatus() && !player.getClassStatus()) {
			if(args[1].equalsIgnoreCase("classes")) {
				send(event.getGuild(), event.getChannel(), "||Classes||\nWizard\r\n" + 
						"Arch_wizard\r\n" + 
						"Crusader r\n" + 
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
				if(args[2].equalsIgnoreCase("wizard")) {
					send(event.getGuild(), event.getChannel(), "You choose to be a" + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
					player.setClassType("wizard");
					player.setClassStatus(true);
				}else if(args[2].equalsIgnoreCase("arch_wizard")) {
					send(event.getGuild(), event.getChannel(), "You choose to be a" + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
					player.setClassType("arch_wizard");
					player.setClassStatus(true);
				}else if(args[2].equalsIgnoreCase("crusader")) {
					send(event.getGuild(), event.getChannel(), "You choose to be a" + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
					player.setClassType("crusader");
					player.setClassStatus(true);
				}else if(args[2].equalsIgnoreCase("adventurer")) {
					send(event.getGuild(), event.getChannel(), "You choose to be a" + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
					player.setClassType("adventurer");
					player.setClassStatus(true);
				}else if(args[2].equalsIgnoreCase("cleric")) {
					send(event.getGuild(), event.getChannel(), "You choose to be a" + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
					player.setClassType("cleric");
					player.setClassStatus(true);
				}else if(args[2].equalsIgnoreCase("thief")) {
					send(event.getGuild(), event.getChannel(), "You choose to be a" + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
					player.setClassType("thief");
					player.setClassStatus(true);
				}else if(args[2].equalsIgnoreCase("priest")) {
					send(event.getGuild(), event.getChannel(), "You choose to be a" + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
					player.setClassType("priest");
					player.setClassStatus(true);
				}else if(args[2].equalsIgnoreCase("warrior")) {
					send(event.getGuild(), event.getChannel(), "You choose to be a" + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
					player.setClassType("warrior");
					player.setClassStatus(true);
				}else if(args[2].equalsIgnoreCase("rogue")) {
					send(event.getGuild(), event.getChannel(), "You choose to be a" + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
					player.setClassType("rogue");
					player.setClassStatus(true);
				}else if(args[2].equalsIgnoreCase("merchant")) {
					send(event.getGuild(), event.getChannel(), "You choose to be a" + args[2]+ "! Good choice! You are now set to go on your first adventure!", true);
					player.setClassType("merchant");
					player.setClassStatus(true);
				}
				
				Konosuba.CLIENT_DATA_MANAGER.trySave();
			}
			
		}else {
			send(event.getGuild(), event.getChannel(), "You haven't started yet!", true);
			return;
		}
		
		
		
	}
	}

}
