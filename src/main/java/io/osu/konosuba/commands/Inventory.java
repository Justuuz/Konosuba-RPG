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
		
		

		
		if(Konosuba.CLIENT_DATA_MANAGER.hasData(event.getAuthor().getIdLong())) {
			ClientData player = Konosuba.CLIENT_DATA_MANAGER.getData(event.getAuthor().getIdLong());

			
			
			ArrayList<ArrayList<String>> inv = player.getInventory();
			
			
			
			
			
			if(args[1].equalsIgnoreCase("helmet")) {
				
				/*
				 * get all the current helmet items into the array
				 */
				EmbedBuilder helmet = new EmbedBuilder();
				helmet.setTitle((event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName()) + "'s Helmets");
				helmet.setAuthor("", event.getAuthor().getAvatarUrl());
				helmet.setThumbnail("https://cdn130.picsart.com/250247865001212.png");
				helmet.setFooter("To equip, do *equip (gear type) (piece)", event.getJDA().getSelfUser().getAvatarUrl());
				ArrayList<String> helInv = inv.get(0);
				String items = "";
				 
				for (String item: helInv) {
					items = items + item + "\n";
				}
				
				items.replace('_', ' ');
				helmet.setDescription(items);
					event.getChannel().sendMessage(helmet.build()).queue();
			}
			
			if(args[1].equalsIgnoreCase("chest")) {
				EmbedBuilder chest = new EmbedBuilder();
				chest.setTitle((event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName()) + "'s Chestplates");
				chest.setAuthor("", event.getAuthor().getAvatarUrl());
				chest.setThumbnail("https://i.imgur.com/Q88IkdH.png");
				chest.setFooter("To equip, do *equip (gear type) (piece)", event.getJDA().getSelfUser().getAvatarUrl());

				
				ArrayList<String> cheInv = inv.get(1);
				String items = "";
				
				for (String item: cheInv) {
					items = items + item + "\n";
				}
				items.replaceAll("_", " ");
					event.getChannel().sendMessage(chest.build()).queue();
				

				
			}
			
			if(args[1].equalsIgnoreCase("leggings")) {
				EmbedBuilder legs = new EmbedBuilder();
				legs.setTitle((event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName()) + "'s Leggings");
				legs.setAuthor("", event.getAuthor().getAvatarUrl());
				legs.setThumbnail("https://i.imgur.com/BTMazbj.png");
				legs.setFooter("To equip, do *equip (gear type) (piece)", event.getJDA().getSelfUser().getAvatarUrl());
				
				ArrayList<String> legInv = inv.get(2);
				String items = "";
				
				for (String item: legInv) {
					items = items + item + "\n";
				}
				
				items.replaceAll("_", " ");
				event.getChannel().sendMessage(legs.build()).queue();
				

				
			}
			
			if(args[1].equalsIgnoreCase("onhand")) {
				EmbedBuilder onhand = new EmbedBuilder();
				onhand.setTitle((event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName()) + "'s On-Hands");
				onhand.setAuthor("", event.getAuthor().getAvatarUrl());
				onhand.setThumbnail("https://i.imgur.com/kD7hIBU.png");
				onhand.setFooter("To equip, do *equip (gear type) (piece)", event.getJDA().getSelfUser().getAvatarUrl());
				ArrayList<String> onInv = inv.get(3);
				String items = "";
				
				for (String item: onInv) {
					items = items + item + "\n";
				}
				
				items.replaceAll("_", " ");
				event.getChannel().sendMessage(onhand.build()).queue();

			}
			
			if(args[1].equalsIgnoreCase("offhand")) {
				EmbedBuilder offhand = new EmbedBuilder();
				offhand.setTitle((event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName()) + "'s Leggings");
				offhand.setAuthor("", event.getAuthor().getAvatarUrl());
				offhand.setThumbnail("https://pre00.deviantart.net/0509/th/pre/i/2016/048/5/8/basic_pixel_sword_by_dr_morgan47-d9s5a12.png");
				offhand.setFooter("To equip, do *equip (gear type) (piece)", event.getJDA().getSelfUser().getAvatarUrl());
				
				ArrayList<String> offInv = inv.get(4);
				String items = "";
				
				for (String item: offInv) {
					items = items + item + "\n";
				}
				
				items.replaceAll("_", " ");
				event.getChannel().sendMessage(offhand.build()).queue();
				
			}
			
			if(args[1].equalsIgnoreCase("cape")) {
				EmbedBuilder cape = new EmbedBuilder();
				cape.setTitle((event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName()) + "'s Capes");
				cape.setAuthor("", event.getAuthor().getAvatarUrl());
				cape.setThumbnail("https://pre00.deviantart.net/0509/th/pre/i/2016/048/5/8/basic_pixel_sword_by_dr_morgan47-d9s5a12.png");
				cape.setFooter("To equip, do *equip (gear type) (piece)", event.getJDA().getSelfUser().getAvatarUrl());
				
				ArrayList<String> capInv = inv.get(5);
				String items = "";
				
				for (String item: capInv) {
					items = items + item + "\n";
				}
				
				items.replaceAll("_", " ");
				event.getChannel().sendMessage(cape.build()).queue();
				
			}
			
			if(args[1].equalsIgnoreCase("necklace")) {
				EmbedBuilder cape = new EmbedBuilder();
				cape.setTitle((event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName()) + "'s Necklaces");
				cape.setAuthor("", event.getAuthor().getAvatarUrl());
				cape.setThumbnail("https://pre00.deviantart.net/0509/th/pre/i/2016/048/5/8/basic_pixel_sword_by_dr_morgan47-d9s5a12.png");
				cape.setFooter("To equip, do *equip (gear type) (piece)", event.getJDA().getSelfUser().getAvatarUrl());
				
				ArrayList<String> necInv = inv.get(6);
				String items = "";
				
				for (String item: necInv) {
					items = items + item + "\n";
				}
				
				items.replaceAll("_", " ");
				event.getChannel().sendMessage(cape.build()).queue();

			}
			
			if(args[1].equalsIgnoreCase("ring")) {
				EmbedBuilder cape = new EmbedBuilder();
				cape.setTitle((event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName()) + "'s Rings");
				cape.setAuthor("", event.getAuthor().getAvatarUrl());
				cape.setThumbnail("https://pre00.deviantart.net/0509/th/pre/i/2016/048/5/8/basic_pixel_sword_by_dr_morgan47-d9s5a12.png");
				cape.setFooter("To equip, do *equip (gear type) (piece)", event.getJDA().getSelfUser().getAvatarUrl());
				
				ArrayList<String> rinInv = inv.get(7);
				String items = "";
				
				for (String item: rinInv) {
					items = items + item + "\n";
				}
				
				items.replaceAll("_", " ");
				event.getChannel().sendMessage(cape.build()).queue();

				
			}
			if(args[1].equalsIgnoreCase("boots")) {
				EmbedBuilder cape = new EmbedBuilder();
				cape.setTitle((event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName()) + "'s Boots");
				cape.setAuthor("", event.getAuthor().getAvatarUrl());
				cape.setThumbnail("https://pre00.deviantart.net/0509/th/pre/i/2016/048/5/8/basic_pixel_sword_by_dr_morgan47-d9s5a12.png");
				cape.setFooter("To equip, do *equip (gear type) (piece)", event.getJDA().getSelfUser().getAvatarUrl());
				
				ArrayList<String> booInv = inv.get(8);
				String items = "";
				
				for (String item: booInv) {
					items = items + item + "\n";
				}
				
				items.replaceAll("_", " ");
				event.getChannel().sendMessage(cape.build()).queue();
				

				
			}
			
//			if(args[1].equalsIgnoreCase("weapons")) {
//				
//				EmbedBuilder cape = new EmbedBuilder();
//				cape.setTitle((event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName()) + "'s Leggings");
//				cape.setAuthor("", event.getAuthor().getAvatarUrl());
//				cape.setThumbnail("https://pre00.deviantart.net/0509/th/pre/i/2016/048/5/8/basic_pixel_sword_by_dr_morgan47-d9s5a12.png");
//				cape.setFooter("To equip, do *equip (gear type) (piece)", event.getJDA().getSelfUser().getAvatarUrl());
//				
//				ArrayList<String> weaInv = inv.get(9);
//				String items = "";
//				
//				for (String item: weaInv) {
//					items = items + item + "\n";
//				}
//				
//				items.replaceAll("_", " ");
//				event.getChannel().sendMessage(cape.build()).queue();
//				
//			}
			
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
					itemList.combineWith(tempList);
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