package io.osu.konosuba.commands;

import java.awt.Color;
import java.util.ArrayList;

import components.map.Map;
import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.ClientData;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Inventory extends Command {

	public Inventory() {
		super(Konosuba.COLOR, Konosuba.PREFIX, "inventory", "commands that deal with inventory", null, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run(MessageReceivedEvent event, String[] args) {
		// TODO Auto-generated method stub
		
		
		if(!Konosuba.CLIENT_DATA_MANAGER.hasData(event.getChannel().getIdLong())) {
			ClientData player = Konosuba.CLIENT_DATA_MANAGER.getData(event.getChannel().getIdLong());
			EmbedBuilder help  = new EmbedBuilder();
			
			if(args[1].equalsIgnoreCase("helmet")) {
				ArrayList<String> helInv = player.getInventory().get(0);
				String items = "";
				
				for (String item: helInv) {
					items = items + item + "\n";
				}
				
					send(event.getGuild(), event.getChannel(), "``` " + items + "'''", true);
			}
			
			if(args[1].equalsIgnoreCase("chest")) {
				ArrayList<String> helInv = player.getInventory().get(1);
				String items = "";
				
				for (String item: helInv) {
					items = items + item + "\n";
				}
				
					send(event.getGuild(), event.getChannel(), "``` " + items + "'''", true);
				

				
			}
			
			if(args[1].equalsIgnoreCase("leggings")) {
				
				ArrayList<String> helInv = player.getInventory().get(2);
				String items = "";
				
				for (String item: helInv) {
					items = items + item + "\n";
				}
				
					send(event.getGuild(), event.getChannel(), "``` " + items + "'''", true);
				

				
			}
			
			if(args[1].equalsIgnoreCase("onhand")) {
				ArrayList<String> helInv = player.getInventory().get(3);
				String items = "";
				
				for (String item: helInv) {
					items = items + item + "\n";
				}
				
					send(event.getGuild(), event.getChannel(), "``` " + items + "'''", true);

				
			}
			
			if(args[1].equalsIgnoreCase("offhand")) {
				ArrayList<String> helInv = player.getInventory().get(4);
				String items = "";
				
				for (String item: helInv) {
					items = items + item + "\n";
				}
				
					send(event.getGuild(), event.getChannel(), "``` " + items + "'''", true);
				

				
			}
			
			if(args[1].equalsIgnoreCase("cape")) {
				ArrayList<String> helInv = player.getInventory().get(5);
				String items = "";
				
				for (String item: helInv) {
					items = items + item + "\n";
				}
				
					send(event.getGuild(), event.getChannel(), "``` " + items + "'''", true);
				

				
			}
			
			if(args[1].equalsIgnoreCase("necklace")) {
				ArrayList<String> helInv = player.getInventory().get(6);
				String items = "";
				
				for (String item: helInv) {
					items = items + item + "\n";
				}
				
					send(event.getGuild(), event.getChannel(), "``` " + items + "'''", true);
				

			}
			
			if(args[1].equalsIgnoreCase("ring")) {
				ArrayList<String> helInv = player.getInventory().get(7);
				String items = "";
				
				for (String item: helInv) {
					items = items + item + "\n";
				}
				
					send(event.getGuild(), event.getChannel(), "``` " + items + "'''", true);
				

				
			}
			if(args[1].equalsIgnoreCase("boots")) {
				ArrayList<String> helInv = player.getInventory().get(8);
				String items = "";
				
				for (String item: helInv) {
					items = items + item + "\n";
				}
				
					send(event.getGuild(), event.getChannel(), "``` " + items + "'''", true);
				

				
			}
			if(args[1].equalsIgnoreCase("weapons")) {
				ArrayList<String> helInv = player.getInventory().get(9);
				String items = "";
				
				for (String item: helInv) {
					items = items + item + "\n";
				}
				
					send(event.getGuild(), event.getChannel(), "``` " + items + "'''", true);
				

				
			}
			
			if(args[1].equalsIgnoreCase("items")) {
				Map<String, Integer> itemList = player.getItems();
				Map<String, Integer> tempList = itemList.newInstance();
				String items = "ITEM\t\tQUANTITIY\n";
				if(itemList.size() <= 50) {
					while(itemList.size() > 0) {
						Map.Pair<String, Integer> itemPair = itemList.removeAny();
						items = items + itemPair.key() + "\t\t" + itemPair.value() + "\n";
						tempList.add(itemPair.key(), itemPair.value());
					}
				}
				else {
					/*
					 * more than 50 then do multiple messages to avoid the max character limit????/
					 */
					
				}
				

				
				send(event.getGuild(), event.getChannel(), "``` " + items + "'''", true);
				

				
			}
			
			if(args[1].equalsIgnoreCase("")) {
				send(event.getGuild(), event.getChannel(), "Type *inventory [type] to show the inventory\n\n [type] can be helmet, chest, leggings, onHand, offHand, cape, necklace, ring, boots, weapons, items", true);
			}
			//update JSON
			Konosuba.CLIENT_DATA_MANAGER.trySave();
			
		}else {
			send(event.getGuild(), event.getChannel(), "You haven't started yet!", true);
			return;
		}
		
		
		
	}

}