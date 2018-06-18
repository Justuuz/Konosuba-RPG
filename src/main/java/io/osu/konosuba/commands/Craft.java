package io.osu.konosuba.commands;

import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.ReactionCommand;
import io.osu.konosuba.data.ItemData;
import io.osu.konosuba.data.LocationData;
import io.osu.konosuba.data.UserData;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;

import java.util.function.Consumer;

public class Craft extends Command{

    public Craft() {
        super(Konosuba.COLOR, Konosuba.PREFIX, "craft", "all related crafting commands", null, 0);
        // TODO Auto-generated constructor stub
    }

    /*
     * framework
     */
    private long GlobalId;
    MessageReceivedEvent event;
    private LocationData globalShop;



    @Override
    public void run(MessageReceivedEvent event, String[] args) {

        //ItemData itemData = new ItemData(items);
        UserData player = new UserData(event.getAuthor().getIdLong());
        if (player.getStartStatus()) {

            LocationData crafts = new LocationData(player.getLocation());
            Consumer<Message> call = (m) -> {
                GlobalId = m.getIdLong();
                if(!crafts.getItemShopName().isEmpty() && event.getAuthor() != event.getJDA().getSelfUser()) event.getTextChannel().addReactionById(GlobalId, event.getJDA().getEmoteById(456959750836584459L)).queue();
                if(!crafts.getWeaponShop().isEmpty()&& event.getAuthor() != event.getJDA().getSelfUser()) event.getTextChannel().addReactionById(GlobalId, event.getJDA().getEmoteById(456959760877486090L)).queue();
            };


            if (args.length == 1 && !hasArgument(args, 1)) {

                if(!crafts.getBlacksmithName().isEmpty()) {
                    event.getChannel().sendMessage(craftHelper(crafts.getBlacksmithName(),crafts, player).build()).queue(call);
                }else {
                    send(event.getGuild(), event.getChannel(), "Can't craft here", true);
                }

            }

            /*
            if (hasArgument(args, 1) && args[1].equalsIgnoreCase("make")) {
                if(!args[2].matches("\\d+")) {
                    send(event.getGuild(), event.getChannel(), "Remember! : *craft make [ID] [Amount]", true);
                    return;
                }
                int id = Integer.parseInt(args[2]);
                ItemData item = new ItemData(id);
                if(globalShop.getBlackSmith().contains(id)) {
                    if(!args[3].matches("\\d+")) {
                        send(event.getGuild(), event.getChannel(), "Remember! : *shop buy/sell [ID] [Amount]", true);
                        return;
                    }
                    int amount = Integer.parseInt(args[3]);
                    ItemData craftingItem = new ItemData(player.getInventory().get(9).get(id));
                    //check if enough materials

                    if() {
                        send(event.getGuild(), event.getChannel(), "Not enough pylons", true);
                        return;

                    }
                    send(event.getGuild(), event.getChannel(), "You crafted " + amount + item.getName(), true);
                }else {
                    send(event.getGuild(), event.getChannel(), "The item you are looking for cannot be crafted!", true);
                }

            }

            */

        }
    }

    private EmbedBuilder craftHelper(String blacksmith,LocationData data, UserData player) {
        EmbedBuilder craftBuild = new EmbedBuilder();
        if(blacksmith.equalsIgnoreCase("blacksmith")) {
            craftBuild.setTitle(data.getBlacksmithName());

            for(int items : data.getBlackSmith()) {
                StringBuilder Cost = new StringBuilder("Cost:\n");

                ItemData itemData = new ItemData(items);
                ItemData itemDataCraft1 = new ItemData(itemData.getCraft1().get(0));
                		
                Cost.append(itemData.getCraft1().get(1) + " ");
                Cost.append(itemDataCraft1.getName());
                Cost.append("\n");

                if(!itemData.getCraft2().isEmpty()){

                	ItemData itemDataCraft2 = new ItemData(itemData.getCraft2().get(0));
                    Cost.append(itemData.getCraft2().get(1) + " ");
                    Cost.append(itemDataCraft2.getName());
                    Cost.append("\n");
                    if(!itemData.getCraft3().isEmpty()){

                    	ItemData itemDataCraft3 = new ItemData(itemData.getCraft3().get(0));
                        Cost.append(itemData.getCraft3().get(1) + " ");
                        Cost.append(itemDataCraft3.getName());

                    }

                }
                
                craftBuild.addField("**" + itemData.getName() +" (ID**: "  + items + ")", Cost.toString() , true);

            }
        }


        craftBuild.setFooter("to craft: *craft make [ID] [Amount]" , "https://i.imgur.com/9yE07dd.png");
        return(craftBuild);
    }


}