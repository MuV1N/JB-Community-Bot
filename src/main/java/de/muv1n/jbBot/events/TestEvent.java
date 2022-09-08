package de.muv1n.jbBot.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.HyperlinkEvent;
import java.awt.*;

public class TestEvent extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent e) {
        if (!e.getMessage().getContentStripped().equals("!test")) return;
        e.getChannel().sendMessageEmbeds(embedBuilder().build()).queue();
    }
    public EmbedBuilder embedBuilder(){
        EmbedBuilder embedBuilder = new EmbedBuilder();

        HyperlinkEvent hyperlinkEvent = null;
        if (hyperlinkEvent.getEventType() == HyperlinkEvent.EventType.ACTIVATED)


        embedBuilder.setTitle("[SDAD](https://Twitch.tv/muv1n)").setColor(Color.GREEN).setDescription("sD");


        return embedBuilder;
    }
}
