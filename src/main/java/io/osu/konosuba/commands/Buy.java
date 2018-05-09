package main.java.io.osu.konosuba.commands;

import io.osu.konosuba.Command;
import io.osu.konosuba.Konosuba;
import io.osu.konosuba.data.ClientData;
import io.osu.konosuba.util.PointsHandler;
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