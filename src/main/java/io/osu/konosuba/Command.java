/*
 * Copyright (c) 2017. MagicCraftMaster
 */
package io.osu.konosuba;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
//import com.raichu.discord.data.*;

import java.awt.*;

public abstract class Command {
	private final Color embedColor;
	private final String prefix, name, description;
	private final Permission requiredPermission;
	private final long requiredID;

	/**
	 * Creates a generic command
	 * @param embedColor the embed color for decorated messages
	 * @param prefix the prefix
	 * @param name the command name
	 * @param description the command description
	 * @param requiredPermission the permission required (null makes no permissions required)
	 * @param requiredID if this is set only that user ID will be allowed (0 is unset)
	 */
	@SuppressWarnings("SameParameterValue")
	protected Command(Color embedColor, String prefix, String name, String description, Permission requiredPermission, long requiredID) {
		this.embedColor = embedColor == null ? Konosuba.COLOR : embedColor;
		this.prefix = prefix == null ? Konosuba.PREFIX : prefix;
		this.name = name;
		this.description = description;
		this.requiredPermission = requiredPermission;
		this.requiredID = requiredID;
	}

	/**
	 * Returns the command name
	 * @return a name string
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the command description
	 * @return a description string
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the required Discord permission for this command
	 * @return a {@link Permission} or null
	 */
	public Permission getRequiredPermission() {
		return requiredPermission;
	}

	/**
	 * Embeds a message nicely for you
	 * @param jda the JDA instance
	 * @param message the message to embed
	 * @return a {@link MessageEmbed}
	 */
	private MessageEmbed embed(JDA jda, String message) {
		return new EmbedBuilder().setColor(embedColor)
				.setAuthor(jda.getSelfUser().getName(), null, jda.getSelfUser().getEffectiveAvatarUrl())
				.setDescription(message).build();
	}

	/**
	 * Sends a message to the given channel
	 * Won't send an embed the the bot doesn't have permission to use embeds
	 * @param channel the channel to send to
	 * @param message the message to send
	 * @param decorate if the message should be an embed
	 */
	protected void send(Guild guild, MessageChannel channel, String message, boolean decorate) {
		//System.out.println(("Sending \"" + message + "\" - " + (decorate && (guild == null || guild.getSelfMember().hasPermission(Permission.MESSAGE_EMBED_LINKS)))).replace("\n", "\\n").replace("\t", "\\t"));
		if (decorate && (guild == null || guild.getSelfMember().hasPermission(Permission.MESSAGE_EMBED_LINKS))) {
			channel.sendMessage(embed(channel.getJDA(), message)).queue();
		} else {
			channel.sendMessage(message).queue();
		}
	}

	/**
	 * Checks an event for validity and runs the command if it passes
	 * @param event the event to check
	 */
	public void check(MessageReceivedEvent event) {
		String prefix = this.prefix;
//		if(event.isFromType(ChannelType.TEXT) && Relic.GUILD_DATA_MANAGER.hasData(event.getGuild().getIdLong())) {
//			GuildData data = Relic.GUILD_DATA_MANAGER.getData(event.getGuild().getIdLong());
//			if(data.getGuildPrefix() != null) prefix = data.getGuildPrefix();
//		}
		
		String[] args = event.getMessage().getContentRaw().split(" +");
		if (args[0].startsWith(prefix)) args[0] = args[0].substring(prefix.length()); else return;
		if ((requiredID == 0 || event.getAuthor().getIdLong() == requiredID) && requiredPermission != null && event.isFromType(ChannelType.TEXT) && !event.getMember().hasPermission(requiredPermission)) {
			send(event.getGuild(), event.getChannel(), "You don't have permission for that", false);
			return;
		}
			if (args[0].equalsIgnoreCase(name)) run(event, args);
	}

	/**
	 * Simple utility
	 * @param args the command args
	 * @param index the argument index
	 * @return a boolean
	 */
	protected boolean hasArgument(String[] args, int index) {
		return args.length > index;
	}

	protected static EmbedBuilder getEmbedBuilder(MessageReceivedEvent event) {
		return new EmbedBuilder().setFooter(event.getJDA().getSelfUser().getName(), event.getJDA().getSelfUser().getEffectiveAvatarUrl()).setColor(Konosuba.COLOR);
	}

	protected static void send(MessageReceivedEvent event, MessageEmbed embed) {
		if (event.isFromType(ChannelType.TEXT) && !event.getGuild().getSelfMember().hasPermission(event.getTextChannel(), Permission.MESSAGE_EMBED_LINKS)) {
			StringBuilder stringBuilder = new StringBuilder("**").append(embed.getTitle()).append("**\n").append(embed.getDescription());
			for (MessageEmbed.Field field : embed.getFields()) stringBuilder.append("\n\n**").append(field.getName()).append("**\n").append(field.getValue());
			event.getChannel().sendMessage(stringBuilder.toString()).queue();

		} else event.getChannel().sendMessage(embed).queue();
	}

	/**
	 * This is the actual code to run when the command fires
	 * @param event the event that passed
	 * @param args the args
	 */
	protected abstract void run(MessageReceivedEvent event, String[] args);
	
}
