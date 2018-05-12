package io.osu.konosuba.commands;

import java.util.List;

import io.magiccraftmaster.util.StringUtils;
import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;

import io.osu.konosuba.data.UserData;
import io.osu.konosuba.util.PointsHandler;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Equip extends Command {
	
	public Equip() {

		super(Konosuba.COLOR, Konosuba.PREFIX, "equip", "all related battle commands", null, 0);

		

		// TODO Auto-generated constructor stub
	}
	
	/*
	 * framework
	 */

	private final PointsHandler correctStats = new PointsHandler();
	
	@Override
	public void run(MessageReceivedEvent event, String[] args) {
		
		/*
		 * equips an item
		 * 
		 * (important) cannot equip an item if there is already an item in that place
		 * helmet, chest, leggings, onHand, offHand, cape, necklace, ring, boots;
		 */
		
		try {
		UserData player = new UserData(event.getAuthor().getIdLong());
		
		
		if(player.getStartStatus()) {
			List<List<String>> inv = player.getInventory();
		if(args[1].equalsIgnoreCase("helmet")) {
				

					
					String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
					
					if(inv.get(0).contains(item)) {
					
						player.setHelmet(item);
						correctStats.recalibratePoints(player);
						String message = "Equipped " + item;
						send(event.getGuild(), event.getChannel(), message , true);
					}
					else {
						send(event.getGuild(), event.getChannel(), "not a valid item" , true);
					}
				
				
			}
			
			if(args[1].equalsIgnoreCase("chest")) {
				

					
					String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
					
					if(inv.get(1).contains(item)) {
							
						player.setChest(item);
						correctStats.recalibratePoints(player);
						String message = "Equipped " + item;
						send(event.getGuild(), event.getChannel(), message , true);
					
					}
					else {
						send(event.getGuild(), event.getChannel(), "not a valid item" , true);
					}
				
				
			}
			
			if(args[1].equalsIgnoreCase("leggings")) {
				

					
					String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
					
					if(inv.get(2).contains(item)) {
					
						player.setLegs(item);
						correctStats.recalibratePoints(player);
						String message = "Equipped " + item;
						send(event.getGuild(), event.getChannel(), message , true);
					
					}
					else {
						send(event.getGuild(), event.getChannel(), "not a valid item" , true);
					}
				
				
			}
			
			if(args[1].equalsIgnoreCase("onhand")) {
				

					
					String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
					
					
					if(inv.get(3).contains(item)) {
						
						
					
						player.setOnhand(item);
						correctStats.recalibratePoints(player);
						String message = "Equipped " + item;
						send(event.getGuild(), event.getChannel(), message , true);
					
					}
					else {
						send(event.getGuild(), event.getChannel(), "not a valid item" , true);
					}
				
				
			}
			
			if(args[1].equalsIgnoreCase("offhand")) {
				
				

					
					String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
					
					if(inv.get(4).contains(item)) {
					
						player.setOffhand(item);
						correctStats.recalibratePoints(player);
						String message = "Equipped " + item;
						send(event.getGuild(), event.getChannel(), message , true);
					}
					else {
						send(event.getGuild(), event.getChannel(), "not a valid item" , true);
					}
				
				
			}
			
			if(args[1].equalsIgnoreCase("cape")) {
				

					
					String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
					
					
					if(inv.get(5).contains(item)) {
						
					
						player.setCape(item);
						correctStats.recalibratePoints(player);
						String message = "Equipped " + item;
						send(event.getGuild(), event.getChannel(), message , true);
					}
					else {
						send(event.getGuild(), event.getChannel(), "not a valid item" , true);
					}
				
				
			}
			
			if(args[1].equalsIgnoreCase("necklace")) {
				


					
					String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
					
					if(inv.get(6).contains(item)) {
						
						player.setNecklace(item);
						correctStats.recalibratePoints(player);
						String message = "Equipped " + item;
						send(event.getGuild(), event.getChannel(), message , true);
					}
					else {
						send(event.getGuild(), event.getChannel(), "not a valid item" , true);
					}
				
				
			}
			
			if(args[1].equalsIgnoreCase("ring")) {
				


					
					String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
					
					if(inv.get(7).contains(item)) {
						
						player.setRing(item);
						correctStats.recalibratePoints(player);
						String message = "Equipped " + item;
						send(event.getGuild(), event.getChannel(), message , true);
					}
					else {
						send(event.getGuild(), event.getChannel(), "not a valid item" , true);
					}
				
				
			}
			
			if(args[1].equalsIgnoreCase("boots")) {
				

					
					String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
					 
					if(inv.get(8).contains(item)) {
						
						player.setBoots(item);
						correctStats.recalibratePoints(player);
						String message = "Equipped " + item;
						send(event.getGuild(), event.getChannel(), message , true);
					}
					else {
						send(event.getGuild(), event.getChannel(), "not a valid item" , true);
					}
				
				
			}
			
			
		}else {
			send(event.getGuild(), event.getChannel(), "You haven't started yet!", true);
			return;
		}
	}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
}
}