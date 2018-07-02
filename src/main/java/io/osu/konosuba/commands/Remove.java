package io.osu.konosuba.commands;

import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.UserData;
import io.osu.konosuba.util.PointsHandler;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Remove extends Command {

	public Remove() {

		super(Konosuba.COLOR, Konosuba.PREFIX, "remove", "all related battle commands", null, 0);

		// TODO Auto-generated constructor stub
	}

	/*
	 * framework
	 */
	private final PointsHandler correctStats = new PointsHandler();


	@Override
	public void run(MessageReceivedEvent event, String[] args) {

		/*
		 * removes an item
		 * 
		 * can't remove item if there isn't one
		 * helmet, chest, leggings, onHand, offHand, cape, necklace, ring, boots;
		 */
		UserData player = new UserData(event.getAuthor().getIdLong());
		if(player.getStartStatus()) {
			if(args.length == 1) {
				send(event.getGuild(),event.getChannel(), "Not enough arguments" , true);
				return;
			}
			if(args[1].equalsIgnoreCase("helmet")) {

				if(player.getHelmet() == 0) {
					String message = "No item to remove";

					send(event.getGuild(), event.getChannel(), message, true);

				}
				else {
					player.setHelmet(0);
					correctStats.recalibratePoints(player);
					send(event.getGuild(), event.getChannel(), "Successful: item removed.", true);
				}

			}

			if(args[1].equalsIgnoreCase("chest")) {

				if(player.getChest() == 0) {
					String message = "No item to remove";

					send(event.getGuild(), event.getChannel(), message, true);

				}
				else {
					player.setChest(0);
					correctStats.recalibratePoints(player);
					send(event.getGuild(), event.getChannel(), "Successful: item removed.", true);
				}

			}

			if(args[1].equalsIgnoreCase("leggings")) {

				if(player.getLegs() == 0) {
					String message = "No item to remove";

					send(event.getGuild(), event.getChannel(), message, true);

				}
				else {
					player.setLegs(0);
					correctStats.recalibratePoints(player);
					send(event.getGuild(), event.getChannel(), "Successful: item removed.", true);
				}

			}

			if(args[1].equalsIgnoreCase("onhand")) {

				if(player.getOnhand() == 0) {
					String message = "No item to remove";

					send(event.getGuild(), event.getChannel(), message, true);

				}
				else {
					player.setOnhand(0);
					correctStats.recalibratePoints(player);
					send(event.getGuild(), event.getChannel(), "Successful: item removed.", true);
				}

			}

			if(args[1].equalsIgnoreCase("offhand")) {

				if(player.getOffhand() == 0) {
					String message = "No item to remove";

					send(event.getGuild(), event.getChannel(), message, true);
				}
				else {
					player.setOffhand(0);
					correctStats.recalibratePoints(player);
					send(event.getGuild(), event.getChannel(), "Successful: item removed.", true);
				}

			}

			if(args[1].equalsIgnoreCase("cape")) {

				if(player.getCape() == 0) {
					String message = "No item to remove";

					send(event.getGuild(), event.getChannel(), message, true);

				}
				else {
					player.setCape(0);
					correctStats.recalibratePoints(player);
					send(event.getGuild(), event.getChannel(), "Successful: item removed.", true);
				}

			}

			if(args[1].equalsIgnoreCase("necklace")) {

				if(player.getNecklace() == 0) {
					String message = "No item to remove";

					send(event.getGuild(), event.getChannel(), message, true);

				}
				else {
					player.setNecklace(0);
					correctStats.recalibratePoints(player);
					send(event.getGuild(), event.getChannel(), "Successful: item removed.", true);
				}

			}

			if(args[1].equalsIgnoreCase("ring")) {

				if(player.getRing() == 0) {
					String message = "No item to remove";

					send(event.getGuild(), event.getChannel(), message, true);

				}
				else {
					player.setRing(0);
					correctStats.recalibratePoints(player);
					send(event.getGuild(), event.getChannel(), "Successful: item removed.", true);
				}

			}
			if(args[1].equalsIgnoreCase("boots")) {

				if(player.getBoots() == 0) {
					String message = "No item to remove";

					send(event.getGuild(), event.getChannel(), message, true);

				}
				else {
					player.setBoots(0);
					correctStats.recalibratePoints(player);
					send(event.getGuild(), event.getChannel(), "Successful: item removed.", true);
				}

			}

		}else {
			send(event.getGuild(), event.getChannel(), "You haven't started yet!", true);
			return;
		}
	}


}