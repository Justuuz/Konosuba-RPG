package io.osu.konosuba.commands;

import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.ClassData;
import io.osu.konosuba.data.GearData;
import io.osu.konosuba.data.UserData;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Profile extends Command {

	public Profile() {
		super(Konosuba.COLOR, Konosuba.PREFIX, "profile", "commands that deal with profile", null, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run(MessageReceivedEvent event, String[] args) {
		
		try {
		UserData player = new UserData(event.getAuthor().getIdLong());
		if(player.getStartStatus()) {
			if(args.length == 1) {
				EmbedBuilder profile = new EmbedBuilder();
				profile.setTitle((event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName()) + "'s Profile");
				profile.addField("__Class__", "" + new ClassData(player.getClasses()).getName(), true);
				profile.addField("__Balance__","" +player.getBalance(), true);
				profile.addField("__Stats__", "**Strength:** " + player.getStrength()
				+ "\n**Physical Defense:** " + player.getPhysicalDefense()
				+ "\n**Magical Defense:** " + player.getMagicalDefense()
				+ "\n**Magic:** " + player.getMagic()
				+ "\n**Dexterity**: " + player.getDexterity()
				+ "\n**Luck**: " + player.getLuck(), false);
				profile.addField("__Equipment__", "**Helmet:** " + new GearData(player.getHelmet()).getName()
				+ "\n**Chestplate:** " + new GearData(player.getChest()).getName()
				+"\n**Leggings:** " + new GearData(player.getLegs()).getName()
				+"\n**Boots:** " + new GearData(player.getBoots()).getName()
				+"\n**Cape:** " + new GearData(player.getCape()).getName()
				+"\n**OnHand:** " + new GearData(player.getOnhand()).getName()
				+"\n**OffHand:** " + new GearData(player.getOffhand()).getName()
				+ "\n**Ring:** " + new GearData(player.getRing()).getName()
				+ "\n**Necklace:** " + new GearData(player.getNecklace()).getName(), true);	
				profile.setThumbnail(event.getAuthor().getAvatarUrl());
				event.getChannel().sendMessage(profile.build()).queue();
				
			}
		}else {
			send(event.getGuild(), event.getChannel(), "You haven't started yet!", true);
			return;
		}
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		
		
	}

}
