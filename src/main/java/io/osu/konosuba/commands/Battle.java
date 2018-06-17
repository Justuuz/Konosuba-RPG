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
			if(args.length == 1 && !hasArgument(args,2)) {
				if(player.getBattleStatus()) {
					battleMessage(new BattleData(event.getAuthor().getIdLong()),event);
					return;
				}

			}
			if(hasArgument(args, 1) && args[1].equalsIgnoreCase("search") && !hasArgument(args, 2)) {

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
		UserData player = new UserData(event.getUser().getIdLong());
		BattleData battle = new BattleData(event.getUser().getIdLong());
		MonsterData monster = new MonsterData(battle.getMonsterId());
		if(player.getBattleStatus()) {
			if(event.getReactionEmote().getName().equalsIgnoreCase("\u2694") && event.getMessageIdLong() == battle.getMessageId()) {
				if(player.getDexterity() >= monster.getDexterity()) {
					// Player goes first
					log1 = battlePhase("player", "attack",battle, player, monster, event);
					if(battle.getMonsterHealth() <= 0) {
						EmbedBuilder end = new EmbedBuilder();
						int earned = (int) Calculator.randomIn(monster.getMinCash(), monster.getMaxCash(), false);
						player.setBalance(player.getBalance() + earned);
						end.setDescription((event.getMember() != null ? event.getMember().getEffectiveName() : event.getUser().getName()) + "killed the " + monster.getName()
						+ " and earned " + earned + " Gold!");
						event.getTextChannel().editMessageById(battle.getMessageId(), end.build()).queue();
						player.setBattleStatus(false);
						battle.endSession(event.getUser().getIdLong());	
						return;
					}

					//Monster Turn
					String style = "";
					if(Calculator.randomIn(0, 50, true) > 50.0) {
						style = "attack";
					}else {
						style = "magic";
					}
					log2 = battlePhase("monster", style, battle, player, monster, event);
					if(battle.getUserHealth() <= 0) {
						EmbedBuilder end = new EmbedBuilder();
						end.setDescription((event.getMember() != null ? event.getMember().getEffectiveName() : event.getUser().getName()) + " has been slained by a " + monster.getName());
						event.getTextChannel().editMessageById(battle.getMessageId(), end.build()).queue();
						player.setBattleStatus(false);
						battle.endSession(event.getUser().getIdLong());
						return;
					}

				}else { 
					//Monster goes first
					String style = "";
					if(Calculator.randomIn(0, 50, true) > 50.0) {
						style = "attack";
					}else {
						style = "magic";
					}
					log2 = battlePhase("monster", style, battle, player, monster, event);
					if(battle.getUserHealth() <= 0) {
						EmbedBuilder end = new EmbedBuilder();
						end.setDescription((event.getMember() != null ? event.getMember().getEffectiveName() : event.getUser().getName()) + " has been slained by a " + monster.getName());
						event.getTextChannel().editMessageById(battle.getMessageId(), end.build()).queue();
						player.setBattleStatus(false);
						battle.endSession(event.getUser().getIdLong());
						return;
					}

					//Player Turn
					log1 = battlePhase("player" ,"attack", battle, player, monster, event);
					if(battle.getMonsterHealth() <= 0) {
						EmbedBuilder end = new EmbedBuilder();
						int earned = (int) Calculator.randomIn(monster.getMinCash(), monster.getMaxCash(), false);
						player.setBalance(player.getBalance() + earned);
						end.setDescription((event.getMember() != null ? event.getMember().getEffectiveName() : event.getUser().getName()) + "killed the " + monster.getName()
						+ " and earned " + earned + " Gold!");
						event.getTextChannel().editMessageById(battle.getMessageId(), end.build()).queue();
						player.setBattleStatus(false);
						battle.endSession(event.getUser().getIdLong());	
						return;
					}
				}
				battleMessage(battle, event, log1, log2);
			}else if(event.getReactionEmote().getEmote().getName().equalsIgnoreCase("wand") && event.getMessageIdLong() == battle.getMessageId()){
				if(player.getDexterity() >= monster.getDexterity()) {
					// Player goes first
					log1 = battlePhase("player", "magic",battle, player, monster, event);
					if(battle.getMonsterHealth() <= 0) {
						EmbedBuilder end = new EmbedBuilder();
						int earned = (int) Calculator.randomIn(monster.getMinCash(), monster.getMaxCash(), false);
						player.setBalance(player.getBalance() + earned);
						end.setDescription((event.getMember() != null ? event.getMember().getEffectiveName() : event.getUser().getName()) + " killed the " + monster.getName()
						+ " and earned " + earned + " Gold!");
						event.getTextChannel().editMessageById(battle.getMessageId(), end.build()).queue();
						player.setBattleStatus(false);
						battle.endSession(event.getUser().getIdLong());	
						return;
					}

					//Monster Turn
					String style = "";
					if(Calculator.randomIn(0, 50, true) > 50.0) {
						style = "attack";
					}else {
						style = "magic";
					}
					log2 = battlePhase("monster", style, battle, player, monster, event);
					if(battle.getUserHealth() <= 0) {
						EmbedBuilder end = new EmbedBuilder();
						end.setDescription((event.getMember() != null ? event.getMember().getEffectiveName() : event.getUser().getName()) + " has been slained by a " + monster.getName());
						event.getTextChannel().editMessageById(battle.getMessageId(), end.build()).queue();
						player.setBattleStatus(false);
						battle.endSession(event.getUser().getIdLong());
						return;
					}

				}else { 
					//Monster goes first
					String style = "";
					if(Calculator.randomIn(0, 50, true) > 50.0) {
						style = "attack";
					}else {
						style = "magic";
					}
					log2 = battlePhase("monster", style, battle, player, monster, event);
					if(battle.getUserHealth() <= 0) {
						EmbedBuilder end = new EmbedBuilder();
						end.setDescription((event.getMember() != null ? event.getMember().getEffectiveName() : event.getUser().getName()) + " has been slained by a " + monster.getName());
						event.getTextChannel().editMessageById(battle.getMessageId(), end.build()).queue();
						player.setBattleStatus(false);
						battle.endSession(event.getUser().getIdLong());
						return;
					}

					//Player Turn
					log1 = battlePhase("player" ,"magic", battle, player, monster, event);
					if(battle.getMonsterHealth() <= 0) {
						EmbedBuilder end = new EmbedBuilder();
						int earned = (int) Calculator.randomIn(monster.getMinCash(), monster.getMaxCash(), false);
						player.setBalance(player.getBalance() + earned);
						end.setDescription((event.getMember() != null ? event.getMember().getEffectiveName() : event.getUser().getName()) + " killed the " + monster.getName()
						+ " and earned " + earned + " Gold!");
						event.getTextChannel().editMessageById(battle.getMessageId(), end.build()).queue();
						player.setBattleStatus(false);
						battle.endSession(event.getUser().getIdLong());	
						return;
					}
				}
				battleMessage(battle, event, log1, log2);

			}else if(event.getReactionEmote().getEmote().getName().equalsIgnoreCase("bag") && event.getMessageIdLong() == battle.getMessageId()) {

			}else if(event.getReactionEmote().getEmote().getName().equalsIgnoreCase("exit") && event.getMessageIdLong() == battle.getMessageId()) {
				EmbedBuilder escape = new EmbedBuilder();
				escape.setDescription((event.getMember() != null ? event.getMember().getEffectiveName() : event.getUser().getName()) + " Escaped from " + new MonsterData(battle.getMonsterId()).getName());
				event.getTextChannel().editMessageById(battle.getMessageId(), escape.build()).queue();
				battle.endSession(event.getUser().getIdLong());
				player.setBattleStatus(false);

			}
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
		battleMessage.addField("__" +  (event.getMember() != null ? event.getMember().getEffectiveName() : event.getAuthor().getName()) +"'s Stats__" , "**Health**: "+battle.getUserHealth(), true);
		battleMessage.addField("__" + monster.getName() + "'s Stats__", "**Health**: "+battle.getMonsterHealth(), true);
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
			event.getReaction().removeReaction(event.getUser()).queue();
		};

		EmbedBuilder battleMessage = new EmbedBuilder();
		MonsterData monster = new MonsterData(battle.getMonsterId());
		battleMessage.setTitle("** " + (event.getMember() != null ? event.getMember().getEffectiveName() : event.getUser().getName()) + " ** vs ** " + monster.getName() + " **");
		battleMessage.addField("__" +  (event.getMember() != null ? event.getMember().getEffectiveName() : event.getUser().getName()) +"'s Stats__" , "**Health**: "+battle.getUserHealth() , true);
		battleMessage.addField("__" + monster.getName() + "'s Stats__", "**Health**: "+battle.getMonsterHealth(), true);
		if(!log1.isEmpty()) {
			battleMessage.addField("**__Logs__**:", log1 + "\n" + log2, false);
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

	private String battlePhase(String choose, String style, BattleData battle, UserData player, MonsterData monster, MessageReactionAddEvent event) {
		String log1 = "";
		if(choose.equalsIgnoreCase("player")) {
			if(style.equalsIgnoreCase("attack")) {
				if(Calculator.randomIn(0, 100, false) <= BattleCalculator.physicalHitChance(player.getStrength(), player.getDexterity())) { // Player Hit Chance hits
					if(Calculator.randomIn(0, 100, false) <= BattleCalculator.evadeChance(monster.getDexterity(), player.getDexterity())) { // Monster Evades
						log1 = monster.getName() + " was able to dodge " + (event.getMember() != null ? event.getMember().getEffectiveName() : event.getUser().getName()) + " Attack!"; 
					}else { // Monster Doesn't Evade
						double damage = BattleCalculator.physicalCalculation(player.getStrength(), monster.getPhysicalDefense());
						if(Calculator.randomIn(0, 100, false) <= BattleCalculator.Luck(player.getLuck(), monster.getLuck())) { // Double Damage
							damage *= 2;
						}
						battle.setMonsterHealth(battle.getMonsterHealth() - (int)damage);
						log1 = (event.getMember() != null ? event.getMember().getEffectiveName() : event.getUser().getName()) + " dealt " + (int)damage + " damage!";  
					}
				}else { // Player missed. 
					log1 = (event.getMember() != null ? event.getMember().getEffectiveName() : event.getUser().getName()) + " Missed!";
				}
			}else if(style.equalsIgnoreCase("magic")){
				if(Calculator.randomIn(0, 100, false) <= BattleCalculator.magicalHitChance(player.getMagic(), player.getDexterity())) { // Player Hit Chance hits
					if(Calculator.randomIn(0, 100, false) <= BattleCalculator.evadeChance(monster.getDexterity(), player.getDexterity())) { // Monster Evades
						log1 = monster.getName() + " was able to dodge " + (event.getMember() != null ? event.getMember().getEffectiveName() : event.getUser().getName()) + " Attack!"; 
					}else { // Monster Doesn't Evade
						double damage = BattleCalculator.magicalCalculation(player.getMagic(), monster.getMagicalDefense());
						if(Calculator.randomIn(0, 100, false) <= BattleCalculator.Luck(player.getLuck(), monster.getLuck())) { // Double Damage
							damage *= 2;
						}
						battle.setMonsterHealth(battle.getMonsterHealth() - (int)damage);
						log1 = (event.getMember() != null ? event.getMember().getEffectiveName() : event.getUser().getName()) + " dealt " + (int)damage + " damage!";  
					}
				}else { // Player missed. 
					log1 = (event.getMember() != null ? event.getMember().getEffectiveName() : event.getUser().getName()) + " Missed!";
				}	
			}
		}else if(choose.equalsIgnoreCase("monster")) {
			if(style.equalsIgnoreCase("attack")) {
				if(Calculator.randomIn(0, 100, false) <= BattleCalculator.physicalHitChance(monster.getStrength(), player.getDexterity())) { // Monster Hit Chance hits
					if(Calculator.randomIn(0, 100, false) <= BattleCalculator.evadeChance(player.getDexterity(), monster.getDexterity())) { // Player Evades
						log1 =  (event.getMember() != null ? event.getMember().getEffectiveName() : event.getUser().getName()) + " was able to dodge " + monster.getName() + " Attack!"; 
					}else { // Player Doesn't Evade
						double damage = BattleCalculator.physicalCalculation(monster.getStrength(), player.getPhysicalDefense());
						if(Calculator.randomIn(0, 100, false) <= BattleCalculator.Luck(monster.getLuck(), player.getLuck())) { // Double Damage
							damage *= 2;
						}
						battle.setUserHealth(battle.getUserHealth() - (int)damage);
						log1 = monster.getName() + " dealt " + (int)damage + " damage!";  
					}
				}else { // Player missed. 
					log1 = monster.getName() + " Missed!";
				}
			}else if(style.equalsIgnoreCase("magic")) {
				if(Calculator.randomIn(0, 100, false) <= BattleCalculator.physicalHitChance(monster.getStrength(), player.getDexterity())) { // Monster Hit Chance hits
					if(Calculator.randomIn(0, 100, false) <= BattleCalculator.evadeChance(player.getDexterity(), monster.getDexterity())) { // Player Evades
						log1 =  (event.getMember() != null ? event.getMember().getEffectiveName() : event.getUser().getName()) + " was able to dodge " + monster.getName() + " Attack!"; 
					}else { // Player Doesn't Evade
						double damage = BattleCalculator.physicalCalculation(monster.getStrength(), player.getPhysicalDefense());
						if(Calculator.randomIn(0, 100, false) <= BattleCalculator.Luck(monster.getLuck(), player.getLuck())) { // Double Damage
							damage *= 2;
						}
						battle.setUserHealth(battle.getUserHealth() - (int)damage);
						log1 = monster.getName() + " dealt " + (int)damage + " damage!";  
					}
				}else { // Player missed. 
					log1 = monster.getName() + " Missed!";
				}
			}
		}

		return log1;
	}


}
