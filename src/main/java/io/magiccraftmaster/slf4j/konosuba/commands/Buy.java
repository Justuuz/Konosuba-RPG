package io.magiccraftmaster.slf4j.konosuba.commands;

import io.magiccraftmaster.slf4j.konosuba.Command;
import io.magiccraftmaster.slf4j.konosuba.Konosuba;
import io.magiccraftmaster.slf4j.konosuba.data.ClientData;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Buy extends Command {

    public Buy() {
        super(Konosuba.COLOR, Konosuba.PREFIX, "talk", "all buying stuff", null, 0);
        // TODO Auto-generated constructor stub
    }

    /*
     * framework
     */


    @Override
    public void run(MessageReceivedEvent event, String[] args) {


        if (player.getStartStatus()) {






        } else {
            send(event.getGuild(), event.getChannel(), "You haven't started yet!", true);
            return;
        }

    }
}