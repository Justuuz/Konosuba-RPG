package io.osu.konosuba.commands;

import java.util.List;
import java.util.function.Consumer;

import io.magiccraftmaster.util.Calculator;
import io.osu.konosuba.util.BattleCalculator;
import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.ReactionCommand;
import io.osu.konosuba.data.BattleData;
import io.osu.konosuba.data.LocationData;
import io.osu.konosuba.data.MonsterData;
import io.osu.konosuba.data.UserData;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;

public class Battle extends Command implements ReactionCommand{

	public Battle() {
		super(Konosuba.COLOR, Konosuba.PREFIX, "battle", "all related battle commands", null, 0);
		// TODO Auto-generated constructor stub
	}

	/*
	 * framework
	 */

	MessageReceivedEvent event;



	@SuppressWarnings("unused")
	@Override
	public void run(MessageReceivedEvent event, String[] args) {

		//Getting the players information from here.

			UserData player = new UserData(event.getAuthor().getIdLong());
			if(player.getStartStatus()) {
				if(args.length == 1) {
					if(player.getBattleStatus()) {
						battleMessage(new BattleData(event.getAuthor().getIdLong()),event);
						return;
					}

				}
				if(args[1].equalsIgnoreCase("search")) {

					if(!player.getBattleStatus()) {

						LocationData location = new LocationData(player.getLocation());

						if (!location.getMonsterList().isEmpty()){
							List<Integer> monsterlist = location.getMonsterList();
							int monsterid = monsterlist.get((int) Calculator.randomIn(0, monsterlist.size(), false));
							MonsterData monster = new MonsterData(monsterid);
							player.setBattleStatus(true);
							BattleData battle = new BattleData(event.getAuthor().getIdLong());
							battle.setMonsterId(monsterid);
							battle.setMonsterHealth(monster.getHealth());
							battle.setUserHealth(player.getHealth());
							battleMessage(battle, event);
							//							battle.endSession(event.getAuthor().getIdLong());
							return;
						}else { 
							send(event.getGuild(), event.getChannel(), "You are not in a searchable area!", true);
						}
					}else {
						send(event.getGuild(), event.getChannel(),"You are already in battle!" , true);
					}

				}

			}

		}	

	@Override
	public void run(MessageReactionAddEvent event) {
		String log1 = "";
		String log2 = "";
		try {
			UserData player = new UserData(event.getUser().getIdLong());
			BattleData battle = new BattleData(event.getUser().getIdLong());
			if(player.getBattleStatus()) {
				if(event.getReactionEmote().getName().equalsIgnoreCase("\u2694") && event.getMessageIdLong() == battle.getMessageId()) {
					battleMessage(battle, event, log1, log2);


				}else if(event.getReactionEmote().getEmote().getName().equalsIgnoreCase("wand") && event.getMessageIdLong() == battle.getMessageId()){

				}else if(event.getReactionEmote().getEmote().getName().equalsIgnoreCase("bag") && event.getMessageIdLong() == battle.getMessageId()) {

				}else if(event.getReactionEmote().getEmote().getName().equalsIgnoreCase("exit") && event.getMessageIdLong() == battle.getMessageId()) {
					EmbedBuilder escape = new EmbedBuilder();
					escape.setDescription((event.getMember() != null ? event.getMember().getEffectiveName() : event.getUser().getName()) + " Escaped from " + new MonsterData(battle.getMonsterId()).getName());
					event.getTextChannel().editMessageById(battle.getMessageId(), escape.build()).queue();
					System.out.println("check3");
					battle.endSession(event.getUser().getIdLong());
					player.setBattleStatus(false);

				}
			}
		} catch(Exception e) {

		}

	}

	private void battleMessage(BattleData battle, MessageReceivedEvent event) {

		Consumer<Message> call = (response) -> {
			battle.setMessageId(response.getIdLong());
			event.getTextChannel().addReactionById(battle.getMessageId(), "\u2694").queue();
			event.getTextChannel().addReactionById(battle.getMessageId(), event.getJDA().getEmoteById(455895183393947648L)).queue();
			event.getTextChannel().addReactionById(battle.getMessageId(), event.getJDA().getEmoteById(455895942357319680L)).queue();
			event.getTextChannel().addReactionById(battle.getMessageId(), event.getJDA().getEmoteById(455896284986081303L)).queue();		
		};

		EmbedBuilder battleMessage = new EmbedBuilder();
		MonsterData monster = new MonsterData(battle.getMonsterId());
		battleMessage.setTitle("** " + (event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName()) + " ** vs ** " + monster.getName() + " **");
		battleMessage.addField("__" +  (event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName()) +"'s Stats__" , "**Health**: "+battle.getUserHealth() + "\n**Mana**: " + battle.getUserMana(), true);
		battleMessage.addField("__" + monster.getName() + "'s Stats__", "**Health**: "+battle.getMonsterHealth() + "\n**Mana**: " + battle.getMonsterMana(), true);
		if(battle.getGuildId() == event.getGuild().getIdLong()) {
			if(battle.getChannelId() == event.getChannel().getIdLong()) {
				if(battle.getMessageId() != 0) {
					event.getTextChannel().editMessageById(battle.getMessageId(), battleMessage.build()).queue(call);
				}else {
					event.getChannel().sendMessage(battleMessage.build()).queue(call);
				}
			}else {
				battle.setChannelId(event.getChannel().getIdLong());
				event.getChannel().sendMessage(battleMessage.build()).queue(call);
			}	
		}else {
			battle.setGuildId(event.getGuild().getIdLong());
			battle.setChannelId(event.getChannel().getIdLong());
			event.getChannel().sendMessage(battleMessage.build()).queue(call);
		}	
	}

	private void battleMessage(BattleData battle, MessageReactionAddEvent event,String log1, String log2) {

		Consumer<Message> call = (response) -> {
			battle.setMessageId(response.getIdLong());
			event.getTextChannel().addReactionById(battle.getMessageId(), "\u2694").queue();
			event.getTextChannel().addReactionById(battle.getMessageId(), event.getJDA().getEmoteById(455895183393947648L)).queue();
			event.getTextChannel().addReactionById(battle.getMessageId(), event.getJDA().getEmoteById(455895942357319680L)).queue();
			event.getTextChannel().addReactionById(battle.getMessageId(), event.getJDA().getEmoteById(455896284986081303L)).queue();		
		};

		EmbedBuilder battleMessage = new EmbedBuilder();
		MonsterData monster = new MonsterData(battle.getMonsterId());
		battleMessage.setTitle("** " + (event.getMember() != null ? event.getMember().getEffectiveName() : event.getUser().getName()) + " ** vs ** " + monster.getName() + " **");
		battleMessage.addField("__" +  event.getMember() != null ? event.getMember().getEffectiveName() : event.getUser().getName() +"'s Stats__" , "**Health**: "+battle.getUserHealth() + "\n**Mana**: " + battle.getUserMana(), true);
		battleMessage.addField("__" + monster.getName() + "'s Stats__", "**Health**: "+battle.getMonsterHealth() + "\n**Mana**: " + battle.getMonsterMana(), true);
		if(!log1.isEmpty()) {
			battleMessage.addField("**Logs**:", log1 + "\n" + log2, true);
		}
		if(battle.getGuildId() == event.getGuild().getIdLong()) {
			if(battle.getChannelId() == event.getChannel().getIdLong()) {
				if(battle.getMessageId() != 0) {
					event.getTextChannel().editMessageById(battle.getMessageId(), battleMessage.build()).queue(call);
				}else {
					event.getChannel().sendMessage(battleMessage.build()).queue(call);
				}
			}else {
				battle.setChannelId(event.getChannel().getIdLong());
				event.getChannel().sendMessage(battleMessage.build()).queue(call);
			}	
		}else {
			battle.setGuildId(event.getGuild().getIdLong());
			battle.setChannelId(event.getChannel().getIdLong());
			event.getChannel().sendMessage(battleMessage.build()).queue(call);
		}	
	}

	

}
