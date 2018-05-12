package io.osu.konosuba.commands;

import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.UserData;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Remove extends Command {

	public Remove() {

		super(Konosuba.COLOR, Konosuba.PREFIX, "remove", "all related battle commands", null, 0);

		// TODO Auto-generated constructor stub
	}

	/*
	 * framework
	 */



	@Override
	public void run(MessageReceivedEvent event, String[] args) {

		/*
		 * removes an item
		 * 
		 * can't remove item if there isn't one
		 * helmet, chest, leggings, onHand, offHand, cape, necklace, ring, boots;
		 */
		try {
			UserData player = new UserData(event.getAuthor().getIdLong());
			if(player.getStartStatus()) {

				if(args[1].equalsIgnoreCase("helmet")) {

					if(player.getHelmet().equalsIgnoreCase("none")) {
						String message = "No item to remove";

						send(event.getGuild(), event.getChannel(), message, true);

					}
					else {
						player.setHelmet("None");
					}

				}

				if(args[1].equalsIgnoreCase("chest")) {

					if(player.getChest().equalsIgnoreCase("none")) {
						String message = "No item to remove";

						send(event.getGuild(), event.getChannel(), message, true);

					}
					else {
						player.setChest("None");
					}

				}

				if(args[1].equalsIgnoreCase("leggings")) {

					if(player.getLegs().equalsIgnoreCase("none")) {
						String message = "No item to remove";

						send(event.getGuild(), event.getChannel(), message, true);

					}
					else {
						player.setLegs("None");
					}

				}

				if(args[1].equalsIgnoreCase("onhand")) {

					if(player.getOnhand().equalsIgnoreCase("none")) {
						String message = "No item to remove";

						send(event.getGuild(), event.getChannel(), message, true);

					}
					else {
						player.setOnhand("None");
					}

				}

				if(args[1].equalsIgnoreCase("offhand")) {

					if(player.getOffhand().equalsIgnoreCase("none")) {
						String message = "No item to remove";

						send(event.getGuild(), event.getChannel(), message, true);

					}
					else {
						player.setOffhand("None");
					}

				}

				if(args[1].equalsIgnoreCase("cape")) {

					if(player.getCape().equalsIgnoreCase("none")) {
						String message = "No item to remove";

						send(event.getGuild(), event.getChannel(), message, true);

					}
					else {
						player.setCape("None");
					}

				}

				if(args[1].equalsIgnoreCase("necklace")) {

					if(player.getNecklace().equalsIgnoreCase("none")) {
						String message = "No item to remove";

						send(event.getGuild(), event.getChannel(), message, true);

					}
					else {
						player.setNecklace("None");
					}

				}

				if(args[1].equalsIgnoreCase("ring")) {

					if(player.getRing().equalsIgnoreCase("none")) {
						String message = "No item to remove";

						send(event.getGuild(), event.getChannel(), message, true);

					}
					else {
						player.setRing("None");
					}

				}
				if(args[1].equalsIgnoreCase("boots")) {

					if(player.getBoots().equalsIgnoreCase("none")) {
						String message = "No item to remove";

						send(event.getGuild(), event.getChannel(), message, true);

					}
					else {
						player.setBoots("None");
					}

				}

			}else {
				send(event.getGuild(), event.getChannel(), "You haven't started yet!", true);
				return;
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}


}