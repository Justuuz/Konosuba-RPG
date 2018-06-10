package io.osu.konosuba.commands;

import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.ItemData;
import io.osu.konosuba.data.UserData;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Inventory extends Command {

	public Inventory() {
		super(Konosuba.COLOR, Konosuba.PREFIX, "inventory", "commands that deal with inventory", null, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run(MessageReceivedEvent event, String[] args) {
		// TODO Auto-generated method stub


		UserData player = null;
		try {
			player = new UserData(event.getAuthor().getIdLong());
		} catch (Exception e) {
			e.printStackTrace();
		}

		Objects.requireNonNull(player);

		if(player.getStartStatus()) {
			

			if(args.length == 1) {
				send(event.getGuild(), event.getChannel(), "Not enough arguments.", true);
				return;
			}
			
			List<List<Integer>> inv = player.getInventory();
			
			
			
			
			
			if(args[1].equalsIgnoreCase("helmet")) {
				
				/*
				 * get all the current helmet items into the 
				 */
				EmbedBuilder helmet = new EmbedBuilder();
				helmet.setTitle((event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName()) + "'s Helmets");
				helmet.setAuthor("", event.getAuthor().getAvatarUrl());
				helmet.setThumbnail("https://cdn130.picsart.com/250247865001212.png");
				helmet.setFooter("To equip, do *equip (gear type) (piece)", event.getJDA().getSelfUser().getAvatarUrl());
				List<Integer> helInv = inv.get(0);
				StringBuilder items = new StringBuilder();
				 
				for (Integer item: helInv) {
					try {
						ItemData name = new ItemData(item);
						items.append(name.getName()).append("\n");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				items.toString().replace('_', ' ');
				helmet.setDescription(items.toString());
					event.getChannel().sendMessage(helmet.build()).queue();
			}
			
			if(args[1].equalsIgnoreCase("chest")) {
				EmbedBuilder chest = new EmbedBuilder();
				chest.setTitle((event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName()) + "'s Chestplates");
				chest.setAuthor("", event.getAuthor().getAvatarUrl());
				chest.setThumbnail("https://i.imgur.com/Q88IkdH.png");
				chest.setFooter("To equip, do *equip (gear type) (piece)", event.getJDA().getSelfUser().getAvatarUrl());

				
				List<Integer> cheInv = inv.get(1);
				StringBuilder items = new StringBuilder();
				
				for (Integer item: cheInv) {
					try {
						ItemData name = new ItemData(item);
						items.append(name.getName()).append("\n");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				items.toString().replaceAll("_", " ");
				chest.setDescription(items.toString());
					event.getChannel().sendMessage(chest.build()).queue();
				

				
			}
			
			if(args[1].equalsIgnoreCase("leggings")) {
				EmbedBuilder legs = new EmbedBuilder();
				legs.setTitle((event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName()) + "'s Leggings");
				legs.setAuthor("", event.getAuthor().getAvatarUrl());
				legs.setThumbnail("https://i.imgur.com/BTMazbj.png");
				legs.setFooter("To equip, do *equip (gear type) (piece)", event.getJDA().getSelfUser().getAvatarUrl());
				
				List<Integer> legInv = inv.get(2);
				StringBuilder items = new StringBuilder();
				
				for (Integer item: legInv) {
					try {
						ItemData name = new ItemData(item);
						items.append(name.getName()).append("\n");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				items.toString().replaceAll("_", " ");
				
				legs.setDescription(items.toString());
				event.getChannel().sendMessage(legs.build()).queue();
				

				
			}
			
			if(args[1].equalsIgnoreCase("onhand")) {
				EmbedBuilder onhand = new EmbedBuilder();
				onhand.setTitle((event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName()) + "'s On-Hands");
				onhand.setAuthor("", event.getAuthor().getAvatarUrl());
				onhand.setThumbnail("https://i.imgur.com/kD7hIBU.png");
				onhand.setFooter("To equip, do *equip (gear type) (piece)", event.getJDA().getSelfUser().getAvatarUrl());
				List<Integer> onInv = inv.get(3);
				StringBuilder items = new StringBuilder();
				
				for (Integer item: onInv) {
					try {
						ItemData name = new ItemData(item);
						items.append(name.getName()).append("\n");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
				}
				
				items.toString().replaceAll("_", " ");
				onhand.setDescription(items.toString());
				event.getChannel().sendMessage(onhand.build()).queue();

			}
			
			if(args[1].equalsIgnoreCase("offhand")) {
				EmbedBuilder offhand = new EmbedBuilder();
				offhand.setTitle((event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName()) + "'s Leggings");
				offhand.setAuthor("", event.getAuthor().getAvatarUrl());
				offhand.setThumbnail("https://pre00.deviantart.net/0509/th/pre/i/2016/048/5/8/basic_pixel_sword_by_dr_morgan47-d9s5a12.png");
				offhand.setFooter("To equip, do *equip (gear type) (piece)", event.getJDA().getSelfUser().getAvatarUrl());
				
				List<Integer> offInv = inv.get(4);
				StringBuilder items = new StringBuilder();
				
				for (Integer item: offInv) {
					try {
						ItemData name = new ItemData(item);
						items.append(name.getName()).append("\n");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				items.toString().replaceAll("_", " ");
				offhand.setDescription(items.toString());
				event.getChannel().sendMessage(offhand.build()).queue();
				
			}
			
			if(args[1].equalsIgnoreCase("cape")) {
				EmbedBuilder cape = new EmbedBuilder();
				cape.setTitle((event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName()) + "'s Capes");
				cape.setAuthor("", event.getAuthor().getAvatarUrl());
				cape.setThumbnail("https://pre00.deviantart.net/0509/th/pre/i/2016/048/5/8/basic_pixel_sword_by_dr_morgan47-d9s5a12.png");
				cape.setFooter("To equip, do *equip (gear type) (piece)", event.getJDA().getSelfUser().getAvatarUrl());
				
				List<Integer> capInv = inv.get(5);
				StringBuilder items = new StringBuilder();
				
				for (Integer item: capInv) {
					try {
						ItemData name = new ItemData(item);
						items.append(name.getName()).append("\n");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				items.toString().replaceAll("_", " ");
				cape.setDescription(items.toString());
				event.getChannel().sendMessage(cape.build()).queue();
				
			}
			
			if(args[1].equalsIgnoreCase("necklace")) {
				EmbedBuilder cape = new EmbedBuilder();
				cape.setTitle((event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName()) + "'s Necklaces");
				cape.setAuthor("", event.getAuthor().getAvatarUrl());
				cape.setThumbnail("https://pre00.deviantart.net/0509/th/pre/i/2016/048/5/8/basic_pixel_sword_by_dr_morgan47-d9s5a12.png");
				cape.setFooter("To equip, do *equip (gear type) (piece)", event.getJDA().getSelfUser().getAvatarUrl());
				
				List<Integer> necInv = inv.get(6);
				StringBuilder items = new StringBuilder();
				
				for (Integer item: necInv) {
					try {
						ItemData name = new ItemData(item);
						items.append(name.getName()).append("\n");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				items.toString().replaceAll("_", " ");
				cape.setDescription(items.toString());
				event.getChannel().sendMessage(cape.build()).queue();

			}
			
			if(args[1].equalsIgnoreCase("ring")) {
				EmbedBuilder cape = new EmbedBuilder();
				cape.setTitle((event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName()) + "'s Rings");
				cape.setAuthor("", event.getAuthor().getAvatarUrl());
				cape.setThumbnail("https://pre00.deviantart.net/0509/th/pre/i/2016/048/5/8/basic_pixel_sword_by_dr_morgan47-d9s5a12.png");
				cape.setFooter("To equip, do *equip (gear type) (piece)", event.getJDA().getSelfUser().getAvatarUrl());
				
				List<Integer> rinInv = inv.get(7);
				StringBuilder items = new StringBuilder();
				
				for (Integer item: rinInv) {
					try {
						ItemData name = new ItemData(item);
						items.append(name.getName()).append("\n");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				items.toString().replaceAll("_", " ");
				cape.setDescription(items.toString());
				event.getChannel().sendMessage(cape.build()).queue();

				
			}
			if(args[1].equalsIgnoreCase("boots")) {
				EmbedBuilder cape = new EmbedBuilder();
				cape.setTitle((event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName()) + "'s Boots");
				cape.setAuthor("", event.getAuthor().getAvatarUrl());
				cape.setThumbnail("https://pre00.deviantart.net/0509/th/pre/i/2016/048/5/8/basic_pixel_sword_by_dr_morgan47-d9s5a12.png");
				cape.setFooter("To equip, do *equip (gear type) (piece)", event.getJDA().getSelfUser().getAvatarUrl());
				
				List<Integer> booInv = inv.get(8);
				StringBuilder items = new StringBuilder();
				
				for (Integer item: booInv) {
					try {
						ItemData name = new ItemData(item);
						items.append(name.getName()).append("\n");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				items.toString().replaceAll("_", " ");
				cape.setDescription(items.toString());
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
//				List<Integer> weaInv = inv.get(9);
//				Integer items = "";
//				
//				for (Integer item: weaInv) {
//					items = items + item + "\n";
//				}
//				
//				items.replaceAll("_", " ");
//				event.getChannel().sendMessage(cape.build()).queue();
//				
//			}
			
			if(args[1].equalsIgnoreCase("items")) {
				EmbedBuilder item = new EmbedBuilder();
				boolean noItems = false;
				HashMap<Integer, Integer> itemList = player.getItems();
				if(itemList.size() == 0) {
					item.setTitle((event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName()) + "'s items");
					item.setAuthor("", event.getAuthor().getAvatarUrl());
					//item.setThumbnail();
					item.setFooter("", event.getJDA().getSelfUser().getAvatarUrl());
					item.setDescription("No items");
					event.getChannel().sendMessage(item.build()).queue();
					noItems = true;
				}
				HashMap<Integer, Integer> tempList = player.getItems();
				tempList.clear();
				StringBuilder items = new StringBuilder("ITEM\t\tQUANTITIY\n");
				if(itemList.size() <= 50 && !noItems) {

					// TODO FIX THESE!

					/*while(itemList.size() > 0) {
						Map.Pair<Integer, Integer> itemPair = itemList.remove;
						items.append(itemPair.key()).append("\t\t").append(itemPair.value()).append("\n");
						tempList.add(itemPair.key(), itemPair.value());
					}
					itemList.transferFrom(tempList);
					item.setTitle((event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName()) + "'s items");
					item.setAuthor("", event.getAuthor().getAvatarUrl());
					//item.setThumbnail();
					item.setFooter("", event.getJDA().getSelfUser().getAvatarUrl());
					item.setDescription(items.toString());
					event.getChannel().sendMessage(item.build()).queue();*/
				}
				else {
					/*
					 * more than 50 then do multiple messages to avoid the max character limit????/
					 */
					
				}
				

			
				

				
			}
			
			if(args[1].equalsIgnoreCase("")) {
				send(event.getGuild(), event.getChannel(), "Type *inventory [type] to show the inventory\n\n [type] can be helmet, chest, leggings, onHand, offHand, cape, necklace, ring, boots, weapons, items", true);
			}
			
		}else {
			send(event.getGuild(), event.getChannel(), "You haven't started yet!", true);
		}
		
		
		
	}

}