package io.osu.konosuba.Listeners;

import java.util.function.Consumer;

import io.magiccraftmaster.util.Calculator;
import io.osu.konosuba.data.BattleData;
import io.osu.konosuba.data.MonsterData;
import io.osu.konosuba.data.UserData;
import io.osu.konosuba.util.BattleCalculator;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ReactionListener extends ListenerAdapter{
	
	
	
	@Override
	public void onMessageReactionAdd(MessageReactionAddEvent event) {
		String log1 = "";
		String log2 = "";
		try {
			UserData player = new UserData(event.getUser().getIdLong());
			System.out.println("check1");
			if(player.getBattleStatus()) {
				System.out.println("check2");
				if(event.getReactionEmote().getEmote().getName().equalsIgnoreCase("crossed_swords")) {
					System.out.println("check3");
					if(event.getReaction().getCount() > 1) {
						System.out.println("check4");
						BattleData battle = new BattleData(event.getUser().getIdLong());
						MonsterData monster = new MonsterData(battle.getMonsterId());
						if(player.getDexterity() > monster.getDexterity() || player.getDexterity() == monster.getDexterity()) { // Player going First
							int damage = BattleCalculator.physicalCalculation(player.getStrength(), monster.getPhysicalDefense());
							double evade = BattleCalculator.evadeChance(monster.getDexterity(), player.getDexterity());
							double hitChance = BattleCalculator.physicalHitChance(player.getStrength(), monster.getDexterity());
							if(Calculator.chance(hitChance)) {
								if(!Calculator.chance(evade)) {
									battle.setMonsterHealth(battle.getMonsterHealth() - damage);
								}else {
									log1 = monster.getName() + " Dodged!";
								}
							}else {
								log1 =  (event.getMember() != null ? event.getMember().getEffectiveName() : event.getUser().getName()) + "Missed!";
							}
							//Monster Turn
							damage = BattleCalculator.physicalCalculation(monster.getStrength(), player.getPhysicalDefense());
							evade = BattleCalculator.evadeChance(player.getDexterity(), monster.getDexterity());
							hitChance = BattleCalculator.physicalHitChance(monster.getStrength(), player.getDexterity());
							if(Calculator.chance(hitChance)) {
								if(!Calculator.chance(evade)) {
									battle.setUserHealth(battle.getUserHealth() - damage);
								}else {
									log2 = (event.getMember() != null ? event.getMember().getEffectiveName() : event.getUser().getName()) + " Dodged!";
								}
							}else {
								log2 = monster.getName() + " Missed!";
							}
							if(battle.getUserHealth() <= 0) {
								EmbedBuilder message = new EmbedBuilder();
								message.setDescription("You have been defeated by a " + monster.getName());
								event.getTextChannel().editMessageById(battle.getMessageId(), message.build()).queue();
								player.setBattleStatus(false);
								battle.endSession(event.getUser().getIdLong());
								return;
							}else if(battle.getMonsterHealth() <= 0) {
								int gold  = (int) Calculator.randomIn((int) monster.getMinCash(), (int) monster.getMaxCash(), false);
								player.setBalance(player.getBalance() + gold);
								EmbedBuilder message = new EmbedBuilder();
								message.setDescription("You killed a " + monster.getName() +"! You earned:" + gold + " Gold.");
								event.getTextChannel().editMessageById(battle.getMessageId(), message.build()).queue();
								player.setBattleStatus(false);
								battle.endSession(event.getUser().getIdLong());
								return;
							}
						}else { //Monster going First
							int damage = BattleCalculator.physicalCalculation(monster.getStrength(), player.getPhysicalDefense());
							double evade = BattleCalculator.evadeChance(player.getDexterity(), monster.getDexterity());
							double hitChance = BattleCalculator.physicalHitChance(monster.getStrength(), player.getDexterity());
							if(Calculator.chance(hitChance)) {
								if(!Calculator.chance(evade)) {
									battle.setMonsterHealth(battle.getMonsterHealth() - damage);
								}else {
									log2 = (event.getMember() != null ? event.getMember().getEffectiveName() : event.getUser().getName()) + " Dodged!";
								}
							}else {
								log2 = monster.getName() + " Missed!";
							}
							
							//Player Turn
							damage = BattleCalculator.physicalCalculation(player.getStrength(), monster.getPhysicalDefense());
							evade = BattleCalculator.evadeChance(monster.getDexterity(), player.getDexterity());
							hitChance = BattleCalculator.physicalHitChance(player.getStrength(), monster.getDexterity());
							if(Calculator.chance(hitChance)) {
								if(!Calculator.chance(evade)) {
									battle.setMonsterHealth(battle.getMonsterHealth() - damage);
								}else {
									log1 = monster.getName() + " Dodged!";
								}
							}else {
								 log1 = "You Missed!";
							}
							if(battle.getUserHealth() <= 0) {
								EmbedBuilder message = new EmbedBuilder();
								message.setDescription("You have been defeated by a " + monster.getName());
								event.getTextChannel().editMessageById(battle.getMessageId(), message.build()).queue();
								player.setBattleStatus(false);
								battle.endSession(event.getUser().getIdLong());
								return;
							}else if(battle.getMonsterHealth() <= 0) {
								int gold  = (int) Calculator.randomIn((int) monster.getMinCash(), (int) monster.getMaxCash(), false);
								player.setBalance(player.getBalance() + gold);
								EmbedBuilder message = new EmbedBuilder();
								message.setDescription("You killed a " + monster.getName() +"! You earned:" + gold + " Gold.");
								event.getTextChannel().editMessageById(battle.getMessageId(), message.build()).queue();
								player.setBattleStatus(false);
								battle.endSession(event.getUser().getIdLong());
								return;
							}
						}
						battleMessage(battle, event, log1, log2);


					}
				}else if(event.getReactionEmote().getEmote().getName().equalsIgnoreCase("wand")){
					
				}else if(event.getReactionEmote().getEmote().getName().equalsIgnoreCase("bag")) {
					
				}else if(event.getReactionEmote().getEmote().getName().equalsIgnoreCase("exit")) {
					
				}
			}
		} catch(Exception e) {

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
		battleMessage.setTitle("** " + event.getMember() != null ? event.getMember().getEffectiveName() : event.getUser().getName() + " ** vs ** " + monster.getName() + " **");
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
