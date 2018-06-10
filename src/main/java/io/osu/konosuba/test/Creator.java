package io.osu.konosuba.test;

import io.magiccraftmaster.util.StringUtils;
import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.ItemData;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Creator extends Command {

	//Replace each formal parameter
	public Creator() {
		super(Konosuba.COLOR, Konosuba.PREFIX, "creator", "describe", null, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run(MessageReceivedEvent event, String[] args) {
		try {
		if(args[1].equalsIgnoreCase("create")) {
			String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
			ItemData gear = new ItemData(item);
			gear.setDexterity(0); //example
			gear.setStrength(0); //example
			
			event.getChannel().sendMessage("test").queue();
		}
		
		if(args[1].equalsIgnoreCase("strength")) {
			String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
			ItemData gear = new ItemData(item);
			gear.setStrength(Integer.parseInt(args[3]));
		}
		
		
	}catch(Exception e) {
		e.printStackTrace();
		System.exit(0);
	}
		


}
}
