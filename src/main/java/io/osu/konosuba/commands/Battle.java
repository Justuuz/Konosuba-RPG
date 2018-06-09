package io.osu.konosuba.commands;

import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.BattleData;
import io.osu.konosuba.data.LocationData;
import io.osu.konosuba.data.MonsterData;
import io.osu.konosuba.data.UserData;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Battle extends Command {
	
	public Battle() {
		super(Konosuba.COLOR, Konosuba.PREFIX, "battle", "all related battle commands", null, 0);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * framework
	 */
	
	
	@SuppressWarnings("unused")
	@Override
	public void run(MessageReceivedEvent event, String[] args) {
		
		//Getting the players information from here.
		try {
			UserData player = new UserData(event.getChannel().getIdLong());
			
			if(player.getStartStatus()) {
				
				if(args[1].equalsIgnoreCase("search")) {
					if(!player.getBattleStatus()) {
						LocationData location = new LocationData(player.getLocation());
						if (true /*location.hasSearch() */){
							/* List<Integer> monsterlist = location.getMonsterList;
							int monsterid = monsterlist.get(0, monsterlist.size());
							*/
//							MonsterData monster = new MonsterData(monsterid);
							player.setBattleStatus(true);
							BattleData battle = new BattleData(event.getChannel().getIdLong());
							//battle.setMonsterId(monsterid);
//							battle.setMonsterHealth(monster.getHealth());
							battle.setUserHealth(player.getHealth());
							
						}else { 
							send(event.getGuild(), event.getChannel(), "You are in an unsearchable area!", true);
						}
					}else {
						send(event.getGuild(), event.getChannel(),"You are already in battle!" , true);
					}
					
				}

				if(args[1].equalsIgnoreCase("attack")) {

				}

				if(args[1].equalsIgnoreCase("magic")) {

				}

				if(args[1].equalsIgnoreCase("heal")) {

				}

				if(args[1].equalsIgnoreCase("item")) {

				}

				if(args[1].equalsIgnoreCase("run")) {

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	/*
	 * Show UI here
	 */
		
		
	}

}
