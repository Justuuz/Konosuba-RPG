package io.osu.konosuba.commands;

import io.magiccraftmaster.util.StringUtils;
import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.ClientData;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Equip extends Command {
	
	public Equip() {

		super(Konosuba.COLOR, Konosuba.PREFIX, "equip", "all related battle commands", null, 0);

		

		// TODO Auto-generated constructor stub
	}
	
	/*
	 * framework
	 */

	
	
	@Override
	public void run(MessageReceivedEvent event, String[] args) {
		
		/*
		 * equips an item
		 * 
		 * (important) cannot equip an item if there is already an item in that place
		 * helmet, chest, leggings, onHand, offHand, cape, necklace, ring, boots;
		 */
		if(Konosuba.CLIENT_DATA_MANAGER.hasData(event.getChannel().getIdLong())) {
		ClientData player = Konosuba.CLIENT_DATA_MANAGER.getData(event.getChannel().getIdLong());
		
		if(args[1].equalsIgnoreCase("helmet")) {
				
				if(!player.getHelmet().equalsIgnoreCase("none")) {
					String message = "Please remove the current item first";
	
					send(event.getGuild(), event.getChannel(), message, true);
				}
				else {
					
					String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
					
					if(player.getInventory().get(0).contains(item)) {
					
						player.setHelmet(item);
						String message = "Equipped " + item;
						send(event.getGuild(), event.getChannel(), message , true);
					}
					else {
						send(event.getGuild(), event.getChannel(), "not a valid item" , true);
					}
				}
				
			}
			
			if(args[1].equalsIgnoreCase("chest")) {
				
				if(!player.getChest().equalsIgnoreCase("none")) {
					String message = "Please remove the current item first";
	
					send(event.getGuild(), event.getChannel(), message, true);
				}
				else {
					
					String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
					
					if(player.getInventory().get(1).contains(item)) {
							
						player.setChest(item);
						String message = "Equipped " + item;
						send(event.getGuild(), event.getChannel(), message , true);
					
					}
					else {
						send(event.getGuild(), event.getChannel(), "not a valid item" , true);
					}
				}
				
			}
			
			if(args[1].equalsIgnoreCase("leggings")) {
				
				if(!player.getLeggings().equalsIgnoreCase("none")) {
					String message = "Please remove the current item first";
	
					send(event.getGuild(), event.getChannel(), message, true);
				}
				else {
					
					String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
					
					if(player.getInventory().get(2).contains(item)) {
					
						player.setLeggings(item);
						String message = "Equipped " + item;
						send(event.getGuild(), event.getChannel(), message , true);
					
					}
					else {
						send(event.getGuild(), event.getChannel(), "not a valid item" , true);
					}
				}
				
			}
			
			if(args[1].equalsIgnoreCase("onhand")) {
				
				if(!player.getOnHand().equalsIgnoreCase("none")) {
					String message = "Please remove the current item first";
	
					send(event.getGuild(), event.getChannel(), message, true);
				}
				else {
					
					String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
					
					
					if(player.getInventory().get(3).contains(item)) {
						
						
					
						player.setOnHand(item);
						String message = "Equipped " + item;
						send(event.getGuild(), event.getChannel(), message , true);
					
					}
					else {
						send(event.getGuild(), event.getChannel(), "not a valid item" , true);
					}
				}
				
			}
			
			if(args[1].equalsIgnoreCase("offhand")) {
				
				
				if(!player.getOffHand().equalsIgnoreCase("none")) {
					String message = "Please remove the current item first";
	
					send(event.getGuild(), event.getChannel(), message, true);
				}
				else {
					
					String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
					
					if(player.getInventory().get(4).contains(item)) {
					
						player.setOffHand(item);
						String message = "Equipped " + item;
						send(event.getGuild(), event.getChannel(), message , true);
					}
					else {
						send(event.getGuild(), event.getChannel(), "not a valid item" , true);
					}
				}
				
			}
			
			if(args[1].equalsIgnoreCase("cape")) {
				
				if(!player.getCape().equalsIgnoreCase("none")) {
					String message = "Please remove the current item first";
	
					send(event.getGuild(), event.getChannel(), message, true);
				}
				else {
					
					String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
					
					
					if(player.getInventory().get(5).contains(item)) {
						
					
						player.setCape(item);
						String message = "Equipped " + item;
						send(event.getGuild(), event.getChannel(), message , true);
					}
					else {
						send(event.getGuild(), event.getChannel(), "not a valid item" , true);
					}
				}
				
			}
			
			if(args[1].equalsIgnoreCase("necklace")) {
				
				if(!player.getNecklace().equalsIgnoreCase("none")) {
					String message = "Please remove the current item first";
	
					send(event.getGuild(), event.getChannel(), message, true);
				}
				else {
					
					String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
					
					if(player.getInventory().get(6).contains(item)) {
						
						player.setNecklace(item);
						String message = "Equipped " + item;
						send(event.getGuild(), event.getChannel(), message , true);
					}
					else {
						send(event.getGuild(), event.getChannel(), "not a valid item" , true);
					}
				}
				
			}
			
			if(args[1].equalsIgnoreCase("ring")) {
				
				if(!player.getRing().equalsIgnoreCase("none")) {
					String message = "Please remove the current item first";
	
					send(event.getGuild(), event.getChannel(), message, true);
				}
				else {
					
					String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
					
					if(player.getInventory().get(7).contains(item)) {
						
						player.setRing(item);
						String message = "Equipped " + item;
						send(event.getGuild(), event.getChannel(), message , true);
					}
					else {
						send(event.getGuild(), event.getChannel(), "not a valid item" , true);
					}
				}
				
			}
			
			if(args[1].equalsIgnoreCase("boots")) {
				
				if(!player.getBoots().equalsIgnoreCase("none")) {
					String message = "Please remove the current item first";
	
					send(event.getGuild(), event.getChannel(), message, true);
				}
				else {
					
					String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
					 
					if(player.getInventory().get(8).contains(item)) {
						
						player.setBoots(item);
						String message = "Equipped " + item;
						send(event.getGuild(), event.getChannel(), message , true);
					}
					else {
						send(event.getGuild(), event.getChannel(), "not a valid item" , true);
					}
				}
				
			}
			if(args[1].equalsIgnoreCase("weapons")) {
				
				if(!player.getWeapon().equalsIgnoreCase("none")) {
					String message = "Please remove the current item first";
	
					send(event.getGuild(), event.getChannel(), message, true);
				}
				else {
					
					String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
					 
					if(player.getInventory().get(9).contains(item)) {
						
						player.setWeapon(item);
						String message = "Equipped " + item;
						send(event.getGuild(), event.getChannel(), message , true);
					}
					else {
						send(event.getGuild(), event.getChannel(), "not a valid item" , true);
					}
				}
				
			}
			
			//update client data
			Konosuba.CLIENT_DATA_MANAGER.trySave();
			
		}
	}
}