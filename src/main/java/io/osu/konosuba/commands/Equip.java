package io.osu.konosuba.commands;

import java.sql.ResultSet;
import java.sql.Statement;


import io.magiccraftmaster.util.StringUtils;
import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;

import io.osu.konosuba.data.UserData;
import io.osu.konosuba.util.PointsHandler;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Equip extends Command {

	public Equip() {

		super(Konosuba.COLOR, Konosuba.PREFIX, "equip", "all related battle commands", null, 0);



	}

	/*
	 * framework
	 */

	private final PointsHandler correctStats = new PointsHandler();

	@Override
	public void run(MessageReceivedEvent event, String[] args) {

		/*
		 * equips an item
		 * 
		 * (important) cannot equip an item if there is already an item in that place
		 * helmet, chest, leggings, onHand, offHand, cape, necklace, ring, boots;
		 */

		try {
			UserData player = new UserData(event.getAuthor().getIdLong());
			if(player.getStartStatus()) {
				if(args.length == 1) {
					send(event.getGuild(), event.getChannel(), "Not enough arguments.", true);
					return;
				}
				if(args[1].equalsIgnoreCase("helmet")) {		
					String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
					Statement statement = Konosuba.CONNECTION2.createStatement();
					ResultSet result = statement.executeQuery("SELECT * FROM 'gear' WHERE name LIKE '" + item + "';");
					if(result.next()) {
						player.setHelmet(result.getInt("gearid"));
						correctStats.recalibratePoints(player);
						String message = "Equipped " + item;
						send(event.getGuild(), event.getChannel(), message , true);
					}
					else {
						send(event.getGuild(), event.getChannel(), "not a valid item" , true);
					}
				}

				if(args[1].equalsIgnoreCase("chest")) {	
					String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
					Statement statement = Konosuba.CONNECTION1.createStatement();
					ResultSet result = statement.executeQuery("SELECT * FROM 'gear' WHERE name LIKE '" + item + "';");
					if(result.next()) {			
						player.setChest(result.getInt("gearid"));
						correctStats.recalibratePoints(player);
						String message = "Equipped " + item;
						send(event.getGuild(), event.getChannel(), message , true);
					}
					else {
						send(event.getGuild(), event.getChannel(), "not a valid item" , true);
					}
				}

				if(args[1].equalsIgnoreCase("leggings")) {	
					String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
					Statement statement = Konosuba.CONNECTION2.createStatement();
					ResultSet result = statement.executeQuery("SELECT * FROM 'gear' WHERE name LIKE '" + item + "';");
					if(result.next()) {
						player.setLegs(result.getInt("gearid"));
						correctStats.recalibratePoints(player);
						String message = "Equipped " + item;
						send(event.getGuild(), event.getChannel(), message , true);
					}
					else {
						send(event.getGuild(), event.getChannel(), "not a valid item" , true);
					}
				}

				if(args[1].equalsIgnoreCase("onhand")) {	
					String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
					Statement statement = Konosuba.CONNECTION2.createStatement();
					ResultSet result = statement.executeQuery("SELECT * FROM 'gear' WHERE name LIKE '" + item + "';");
					if(result.next()) {
						player.setOnhand(result.getInt("gearid"));
						correctStats.recalibratePoints(player);
						String message = "Equipped " + item;
						send(event.getGuild(), event.getChannel(), message , true);

					}
					else {
						send(event.getGuild(), event.getChannel(), "not a valid item" , true);
					}
				}

				if(args[1].equalsIgnoreCase("offhand")) {
					String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
					Statement statement = Konosuba.CONNECTION2.createStatement();
					ResultSet result = statement.executeQuery("SELECT * FROM 'gear' WHERE name LIKE '" + item + "';");
					if(result.next()) {
						player.setOffhand(result.getInt("gearid"));
						correctStats.recalibratePoints(player);
						String message = "Equipped " + item;
						send(event.getGuild(), event.getChannel(), message , true);
					}
					else {
						send(event.getGuild(), event.getChannel(), "not a valid item" , true);
					}
				}

				if(args[1].equalsIgnoreCase("cape")) {
					String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
					Statement statement = Konosuba.CONNECTION2.createStatement();
					ResultSet result = statement.executeQuery("SELECT * FROM 'gear' WHERE name LIKE '" + item + "';");
					if(result.next()) {
						player.setCape(result.getInt("gearid"));
						correctStats.recalibratePoints(player);
						String message = "Equipped " + item;
						send(event.getGuild(), event.getChannel(), message , true);
					}
					else {
						send(event.getGuild(), event.getChannel(), "not a valid item" , true);
					}
				}

				if(args[1].equalsIgnoreCase("necklace")) {
					String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
					Statement statement = Konosuba.CONNECTION2.createStatement();
					ResultSet result = statement.executeQuery("SELECT * FROM 'gear' WHERE name LIKE '" + item + "';");
					if(result.next()) {
						player.setNecklace(result.getInt("gearid"));
						correctStats.recalibratePoints(player);
						String message = "Equipped " + item;
						send(event.getGuild(), event.getChannel(), message , true);
					}
					else {
						send(event.getGuild(), event.getChannel(), "not a valid item" , true);
					}
				}

				if(args[1].equalsIgnoreCase("ring")) {
					String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
					Statement statement = Konosuba.CONNECTION2.createStatement();
					ResultSet result = statement.executeQuery("SELECT * FROM 'gear' WHERE name LIKE '" + item + "';");
					if(result.next()) {
						player.setRing(result.getInt("gearid"));
						correctStats.recalibratePoints(player);
						String message = "Equipped " + item;
						send(event.getGuild(), event.getChannel(), message , true);
					}
					else {
						send(event.getGuild(), event.getChannel(), "not a valid item" , true);
					}
				}

				if(args[1].equalsIgnoreCase("boots")) {
					String item = StringUtils.toString(StringUtils.clip(args, StringUtils.ClipType.LEFT, 2), " ");
					Statement statement = Konosuba.CONNECTION2.createStatement();
					ResultSet result = statement.executeQuery("SELECT * FROM 'gear' WHERE name LIKE '" + item + "';");
					if(result.next()) {
						player.setBoots(result.getInt("gearid"));
						correctStats.recalibratePoints(player);
						String message = "Equipped " + item;
						send(event.getGuild(), event.getChannel(), message , true);
					}
					else {
						send(event.getGuild(), event.getChannel(), "not a valid item" , true);
					}
				}


			}else {
				send(event.getGuild(), event.getChannel(), "You haven't started yet!", true);
				return;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}