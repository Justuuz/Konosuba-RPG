package io.osu.konosuba.commands;

import java.awt.Color;

import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.ClientData;
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
		
		ClientData player = Konosuba.CLIENT_DATA_MANAGER.getData(event.getAuthor().getIdLong());
		if(player.getStartStatus()) {
			if(args.length == 1) {
				EmbedBuilder profile = new EmbedBuilder();
				profile.setTitle((event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName()) + "'s Profile");
				profile.addField("Class", player.getClassType(), true);
				profile.addField("Balance","" +player.getBalance(), true);
				profile.addField("Stats", "Strength: " + player.getStrength()
				+ "\nPhyiscal Defense: " + player.getPhysicalDefense()
				+ "\nMagical Defense: " + player.getMagicalDefense()
				+ "\nMagic: " + player.getMagic()
				+ "\nDexterity " + player.getDexterity()
				+ "\nLuck " + player.getLuck(), false);
				profile.addField("Equipment", "Helmet: " + player.getHelmet()
				+ "\nChestplate: " + player.getChest()
				+"\nLeggings: " + player.getLeggings()
				+"\nBoots: " + player.getBoots()
				+"\nCape: " + player.getLeggings()
				+"\nOnHand: " + player.getOnHand()
				+"\nOffHand: " + player.getOffHand()
				+ "\nRing: " + player.getRing()
				+ "\nNecklace: " + player.getNecklace(), true);
				profile.setThumbnail(event.getAuthor().getAvatarUrl());
				event.getChannel().sendMessage(profile.build()).queue();
				
			}
		}else {
			send(event.getGuild(), event.getChannel(), "You haven't started yet!", true);
			return;
		}
		
		
		
	}

}
