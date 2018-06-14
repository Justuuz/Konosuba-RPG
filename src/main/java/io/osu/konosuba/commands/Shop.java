package io.osu.konosuba.commands;

import java.util.ArrayList;

import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.ItemData;
import io.osu.konosuba.data.LocationData;
import io.osu.konosuba.data.UserData;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Shop extends Command{

	protected Shop() {
		super(Konosuba.COLOR, Konosuba.PREFIX, "shop", "depending on place, will show a list of shop", null, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run(MessageReceivedEvent event, String[] args) {

			UserData player = new UserData(event.getAuthor().getIdLong());
			if(player.getStartStatus()) {
				LocationData shops = new LocationData(player.getLocation());
				if(args[1].equalsIgnoreCase("shop") && args[2].equalsIgnoreCase("1")) {
					if(!shops.getItemShopName().isEmpty()) {
						
					}
				}
			}
	}
	
	private void shopHelper(int shop,LocationData data) {
		EmbedBuilder shopBuild = new EmbedBuilder();
		switch(shop) {
		case 1:
			shopBuild.setTitle(data.getItemShopName());
			ArrayList<Integer> list = new ArrayList<Integer>(data.getItemShop());
			for(int items : list) {
				ItemData itemData = new ItemData(items);
				shopBuild.addField("**" +itemData.getName() +"**", "**ID**: " + items, true);
			}
		}
	}

}
