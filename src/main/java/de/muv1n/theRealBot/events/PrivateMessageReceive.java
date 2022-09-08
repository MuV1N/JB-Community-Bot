package de.muv1n.theRealBot.events;

import de.muv1n.theRealBot.BotMain;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.GuildManager;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class PrivateMessageReceive extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent e) {
        if (e.getAuthor().isBot()) return;
        if (e.isFromType(ChannelType.PRIVATE)){
            String message = e.getMessage().getContentStripped();
            sendMessage(message, e.getAuthor(), e.getJDA());
        }
    }
    public void sendMessage(String message, User user, JDA jda){

        Guild theRealJ0sh = jda.getGuildById(934200357532336230L);

        TextChannel channel = theRealJ0sh.getTextChannelById(1007036471028498532L);
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(":exclamation: PRIVATE MESSAGE :exclamation:");
        eb.setDescription("Der Nutzer " + user.getAsMention() + " hat eine Private Message gesendet!");
        eb.addField(message , "", false);

        channel.sendMessageEmbeds(eb.build()).queue();



    }
}
