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
		 * helmet, chest, leggings, boots, onHand, offHand, cape, necklace, ring;
		 */
		
		ClientData player = new ClientData(event.getChannel().getIdLong());
		
		if(args[1].equalsIgnoreCase("helmet")) {
			
			if(!player.getHelmet().equalsIgnoreCase("none")) {
				String message = "Please remove the current item first";

				send(event.getGuild(), event.getChannel(), message, true);
			}
			else {
				
				String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
				
				player.setHelmet(item);
				String message = "Equipped" + item;
				send(event.getGuild(), event.getChannel(), message , true);
			}
			
		}
		
		if(args[1].equalsIgnoreCase("chest")) {
			
			if(!player.getChest().equalsIgnoreCase("none")) {
				String message = "Please remove the current item first";

				send(event.getGuild(), event.getChannel(), message, true);
			}
			else {
				
				String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
				
				player.setChest(item);
				String message = "Equipped" + item;
				send(event.getGuild(), event.getChannel(), message , true);
			}
			
		}
		
		if(args[1].equalsIgnoreCase("leggings")) {
			
			if(!player.getLeggings().equalsIgnoreCase("none")) {
				String message = "Please remove the current item first";

				send(event.getGuild(), event.getChannel(), message, true);
			}
			else {
				
				String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
				
				player.setLeggings(item);
				String message = "Equipped" + item;
				send(event.getGuild(), event.getChannel(), message , true);
			}
			
		}
		
		if(args[1].equalsIgnoreCase("onhand")) {
			
			if(!player.getOnHand().equalsIgnoreCase("none")) {
				String message = "Please remove the current item first";

				send(event.getGuild(), event.getChannel(), message, true);
			}
			else {
				
				String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
				
				player.setOnHand(item);
				String message = "Equipped" + item;
				send(event.getGuild(), event.getChannel(), message , true);
			}
			
		}
		
		if(args[1].equalsIgnoreCase("offhand")) {
			
			
			if(!player.getOffHand().equalsIgnoreCase("none")) {
				String message = "Please remove the current item first";

				send(event.getGuild(), event.getChannel(), message, true);
			}
			else {
				
				String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
				
				player.setOffHand(item);
				String message = "Equipped" + item;
				send(event.getGuild(), event.getChannel(), message , true);
			}
			
		}
		
		if(args[1].equalsIgnoreCase("cape")) {
			
			if(!player.getCape().equalsIgnoreCase("none")) {
				String message = "Please remove the current item first";

				send(event.getGuild(), event.getChannel(), message, true);
			}
			else {
				
				String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
				
				player.setCape(item);
				String message = "Equipped" + item;
				send(event.getGuild(), event.getChannel(), message , true);
			}
			
		}
		
		if(args[1].equalsIgnoreCase("necklace")) {
			
			if(!player.getNecklace().equalsIgnoreCase("none")) {
				String message = "Please remove the current item first";

				send(event.getGuild(), event.getChannel(), message, true);
			}
			else {
				
				String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
				
				player.setNecklace(item);
				String message = "Equipped" + item;
				send(event.getGuild(), event.getChannel(), message , true);
			}
			
		}
		
		if(args[1].equalsIgnoreCase("ring")) {
			
			if(!player.getRing().equalsIgnoreCase("none")) {
				String message = "Please remove the current item first";

				send(event.getGuild(), event.getChannel(), message, true);
			}
			else {
				
				String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
				
				player.setRing(item);
				String message = "Equipped" + item;
				send(event.getGuild(), event.getChannel(), message , true);
			}
			
		}
		
		//update client data
		Konosuba.CLIENT_DATA_MANAGER.trySave();
		
	}
}