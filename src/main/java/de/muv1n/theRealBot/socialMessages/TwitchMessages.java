package de.muv1n.theRealBot.socialMessages;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.events.ChannelGoLiveEvent;
import lombok.Getter;
import net.dv8tion.jda.api.entities.Guild;

import java.util.Objects;

public class TwitchMessages {

    @Getter
    public static TwitchMessages instance;

    public static void twitchMessage(Guild guild, TwitchClient twitchClient){

        twitchClient.getEventManager().onEvent(ChannelGoLiveEvent.class, e ->{
           if (e.getChannel().getName().equals("MuV1N")){
               Objects.requireNonNull(guild.getTextChannelById(1007005809756082270L)).sendMessage("test").queue();
           }
        });

    }

}
