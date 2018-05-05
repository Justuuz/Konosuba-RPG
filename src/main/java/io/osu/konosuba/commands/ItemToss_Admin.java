package io.osu.konosuba.commands;

import io.magiccraftmaster.util.StringUtils;
import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.ClientData;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class ItemToss_Admin extends Command {
	
	public ItemToss_Admin() {
		super(Konosuba.COLOR, Konosuba.PREFIX, "itemtoss_admin", "all related battle commands", null, 0);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * framework
	 */
	
	
	@Override
	public void run(MessageReceivedEvent event, String[] args) {
		
		ClientData player = Konosuba.CLIENT_DATA_MANAGER.getData(event.getAuthor().getIdLong());
	/*
	 * Show UI here
	 */
		
		if(args[1].equalsIgnoreCase("stick")) {
			String number = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
			int amount = Integer.parseInt(number);
			if(player.getItems().hasKey("stick")) {
				if(player.getItems().value("stick") - amount >= 0) {
					player.removeItems("stick", amount);
				}
				else {
					send(event.getGuild(), event.getChannel(), "You can't remove more than what you have",true);
				}
			}
			else {
				send(event.getGuild(), event.getChannel(), "You don't even have that item!",true);
			}
			
			
		}
		
		if(args[1].equalsIgnoreCase("stones")) {
			String number = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
			int amount = Integer.parseInt(number);
			if(player.getItems().hasKey("stones")) {
				if(player.getItems().value("stones") - amount >= 0) {
					player.removeItems("stones", amount);
				}
				else {
					send(event.getGuild(), event.getChannel(), "You can't remove more than what you have",true);
				}
			}
			else {
				send(event.getGuild(), event.getChannel(), "You don't even have that item!",true);
			}
		}
		
		
		if(args[1].equalsIgnoreCase("goo")) {
			String number = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
			int amount = Integer.parseInt(number);
			if(player.getItems().hasKey("goo")) {
				if(player.getItems().value("goo") - amount >= 0) {
					player.removeItems("goo", amount);
				}
				else {
					send(event.getGuild(), event.getChannel(), "You can't remove more than what you have",true);
				}
			}
			else {
				send(event.getGuild(), event.getChannel(), "You don't even have that item!",true);
			}
			 
		}
		
		if(args[1].equalsIgnoreCase("bones")) {
			String number = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
			int amount = Integer.parseInt(number);
			if(player.getItems().hasKey("bones")) {
				if(player.getItems().value("bones") - amount >= 0) {
					player.removeItems("bones", amount);
				}
				else {
					send(event.getGuild(), event.getChannel(), "You can't remove more than what you have",true);
				}
			}
			else {
				send(event.getGuild(), event.getChannel(), "You don't even have that item!",true);
			}
			
		}
		Konosuba.CLIENT_DATA_MANAGER.trySave();
		
	}
}