package io.osu.konosuba.test;

import io.magiccraftmaster.util.StringUtils;
import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.UserData;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Item_admin extends Command {
	
	public Item_admin() {
		super(Konosuba.COLOR, Konosuba.PREFIX, "item_admin", "all related battle commands", null, 0);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * framework
	 */
	
	
	@Override
	public void run(MessageReceivedEvent event, String[] args) {
		try {
		UserData player = new UserData(event.getAuthor().getIdLong());
	/*
	 * Show UI here
	 */
		
		if(args[1].equalsIgnoreCase("stick")) {
			String number = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
			int amount = Integer.parseInt(number);
			player.addItems("stick", amount);
			
		}
		
		if(args[1].equalsIgnoreCase("stones")) {
			String number = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
			int amount = Integer.parseInt(number);
			player.addItems("stones", amount);
		}
		
		
		if(args[1].equalsIgnoreCase("goo")) {
			String number = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
			int amount = Integer.parseInt(number);
			player.addItems("goo", amount);
			 
		}
		
		if(args[1].equalsIgnoreCase("bones")) {
			String number = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
			int amount = Integer.parseInt(number);
			player.addItems("bones", amount);
			
		}
		}catch (Exception e){
			e.printStackTrace();
			System.exit(0);
		}
		
	}
}