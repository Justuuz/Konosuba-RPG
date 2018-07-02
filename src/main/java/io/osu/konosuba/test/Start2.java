package io.osu.konosuba.test;

import io.magiccraftmaster.util.StringUtils;
import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.UserData;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Start2 extends Command {
	private static final List<String> classes = Collections.unmodifiableList(Arrays.asList("adventurer,merchant,crusader,warrior,wizard,rogue,cleric,thief,mage".toLowerCase().split(",")));

	public Start2() {
		super(Konosuba.COLOR, Konosuba.PREFIX, "start2", "the journey begins", null, 0);
	}

	@Override
	protected void run(MessageReceivedEvent event, String[] args) {
		try {
			UserData userData = new UserData(event.getAuthor().getIdLong());

			if (!hasArgument(args, 1) && !userData.getStartStatus()) {
				EmbedBuilder embedBuilder = getEmbedBuilder(event);
				embedBuilder.setDescription("Welcome " + (event.isFromType(ChannelType.TEXT) ? event.getMember().getEffectiveName() : event.getAuthor().getName()) + "!  Today is the day you start your adventure! Before we can start, I must ask traveler, what class are? Do `*start class <class>` to begin!");
				embedBuilder.addField("Classes", StringUtils.toListString(caseClasses()), false);
				send(event, embedBuilder.build());

			}if (!hasArgument(args, 1) && userData.getStartStatus()) {
				event.getChannel().sendMessage("You're a " + userData.getClasses()).queue();

			} else if (hasArgument(args, 1) && args[1].equalsIgnoreCase("class") && !hasArgument(args, 2)) {
				send(event, getEmbedBuilder(event).setTitle("Classes").setDescription(StringUtils.toListString(caseClasses())).build());

			} else if (hasArgument(args, 1) && args[1].equalsIgnoreCase("class") && hasArgument(args, 2) && userData.getStartStatus()) {
				event.getChannel().sendMessage("You're already a " + userData.getClasses() + "!").queue();

			} else if (hasArgument(args, 1) && args[1].equalsIgnoreCase("class") && hasArgument(args, 2) && !userData.getStartStatus()) {
				if (classes.contains(args[2].toLowerCase())) {
					event.getChannel().sendMessage("You choose to be a " + args[2]+ "! Good choice! You are now set to go on your first adventure!").queue();
					userData.setClasses(classes.get(classes.indexOf(args[2].toLowerCase())));
					userData.setStartStatus(true);
				} else {
					event.getChannel().sendMessage("Invalid class").queue();
				}
			}

		} catch (Exception e) {
			Konosuba.LOGGER.error("Exception occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static String[] caseClasses() {
		List<String> classNames = new ArrayList<>();
		for (String className : classes) classNames.add(StringUtils.fixCase(className));
		return classNames.toArray(new String[classNames.size()]);
	}
}
