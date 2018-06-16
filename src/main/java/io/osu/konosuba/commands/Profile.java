package io.osu.konosuba.commands;

import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.ClassData;
import io.osu.konosuba.data.ItemData;
import io.osu.konosuba.data.LocationData;
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

		UserData player = new UserData(event.getAuthor().getIdLong());
		if(player.getStartStatus()) {
			if(args.length == 1) {
				EmbedBuilder profile = new EmbedBuilder();
				profile.setTitle((event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName()) + "'s Profile");
				profile.addField("__Class__", "" + new ClassData(player.getClasses()).getName(), true);
				profile.addField("__Balance__","" +player.getBalance(), true);
				profile.addField("__Location__", new LocationData(player.getLocation()).getMainLocation()  +": "+ new LocationData(player.getLocation()).getSubLocation(), true);
				profile.addField("__Stats__",
						"\n**Health:** " + player.getHealth()
						+ "\n**Strength:** " + player.getStrength()
						+ "\n**Physical Defense:** " + player.getPhysicalDefense()
						+ "\n**Magical Defense:** " + player.getMagicalDefense()
						+ "\n**Magic:** " + player.getMagic()
						+ "\n**Dexterity**: " + player.getDexterity()
						+ "\n**Luck**: " + player.getLuck(), false);
				profile.addField("__Equipment__", "**Helmet:** " + new ItemData(player.getHelmet()).getName()
						+ "\n**Chestplate:** " + new ItemData(player.getChest()).getName()
						+"\n**Leggings:** " + new ItemData(player.getLegs()).getName()
						+"\n**Boots:** " + new ItemData(player.getBoots()).getName()
						+"\n**Cape:** " + new ItemData(player.getCape()).getName()
						+"\n**OnHand:** " + new ItemData(player.getOnhand()).getName()
						+"\n**OffHand:** " + new ItemData(player.getOffhand()).getName()
						+ "\n**Ring:** " + new ItemData(player.getRing()).getName()
						+ "\n**Necklace:** " + new ItemData(player.getNecklace()).getName(), false);	
				profile.setThumbnail(event.getAuthor().getAvatarUrl());
				event.getChannel().sendMessage(profile.build()).queue();

			}
		}else {
			send(event.getGuild(), event.getChannel(), "You haven't started yet!", true);
			return;
		}
	}

}
