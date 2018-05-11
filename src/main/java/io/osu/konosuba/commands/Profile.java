package io.osu.konosuba.commands;

import java.awt.Color;

import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.ClientData;
import io.osu.konosuba.data.UserData;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Profile extends Command {

	public Profile() {
		super(Konosuba.COLOR, Konosuba.PREFIX, "profile", "commands that deal with profile", null, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run(MessageReceivedEvent event, String[] args) {
		// TODO Auto-generated method stub
		try {
		UserData player = new UserData(event.getAuthor().getIdLong());
		if(player.getStartStatus()) {
			if(args.length == 1) {
				EmbedBuilder profile = new EmbedBuilder();
				profile.setTitle((event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName()) + "'s Profile");
				profile.addField("__Class__", player.getClasses(), true);
				profile.addField("__Balance__","" +player.getBalance(), true);
				profile.addField("__Stats__", "**Strength:** " + player.getStrength()
				+ "\n**Physical Defense:** " + player.getPhysicalDefense()
				+ "\n**Magical Defense:** " + player.getMagicalDefense()
				+ "\n**Magic:** " + player.getMagic()
				+ "\n**Dexterity**: " + player.getDexterity()
				+ "\n**Luck**: " + player.getLuck(), false);
				profile.addField("__Equipment__", "**Helmet:** " + player.getHelmet()
				+ "\n**Chestplate:** " + player.getChest()
				+"\n**Leggings:** " + player.getLegs()
				+"\n**Boots:** " + player.getBoots()
				+"\n**Cape:** " + player.getCape()
				+"\n**OnHand:** " + player.getOnhand()
				+"\n**OffHand:** " + player.getOffhand()
				+ "\n**Ring:** " + player.getRing()
				+ "\n**Necklace:** " + player.getNecklace(), true);	
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
