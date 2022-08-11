package de.muv1n.theRealBot.slashCommands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;

public class KickCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent e) {

        TextChannel logs = e.getGuild().getTextChannelsByName("\uD83D\uDCD8┃logs", false).get(0);

        if (!e.getName().equals("kick"))return;
        User user = Objects.requireNonNull(e.getOption("name")).getAsUser();
        if (e.getOption("begründung") != null){
            String reason = Objects.requireNonNull(e.getOption("begründung")).getAsString();
            sendPrivateMessageWithReason(user, e.getGuild(), reason);
            Objects.requireNonNull(e.getGuild()).kick(user, reason).queue();
            e.replyEmbeds(sendEmbedWithReason(user, reason, e).build()).setEphemeral(true).queue();
            logs.sendMessageEmbeds(sendEmbedWithReason(user, reason, e).build()).queue();
        }else{
            logs.sendMessageEmbeds(sendEmbedWithoutReason(user, e).build()).queue();
            sendPrivateMessageWithoutReason(user, e.getGuild());
            Objects.requireNonNull(e.getGuild()).kick(user).queue();
            e.replyEmbeds(sendEmbedWithoutReason(user, e).build()).setEphemeral(true).queue();
        }
    }
    public void sendPrivateMessageWithReason(User user, Guild guild, String reason){
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.RED);
        eb.setTitle(":hammer:Du wurdest gekickt:hammmer:");
        eb.addField("Die Begründung ist:", "", true);
        eb.addField("**" + reason + "**", "", false);
        user.openPrivateChannel().flatMap((privateChannel -> privateChannel.sendMessageEmbeds(eb.build()))).queue();
    }
    public void sendPrivateMessageWithoutReason(User user, Guild guild){
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.RED);
        eb.setTitle(":hammer:Du wurdest gekickt:sob:");
        eb.addField("Es gibt keine Begründung!", "", true);
        user.openPrivateChannel().flatMap((privateChannel -> privateChannel.sendMessageEmbeds(eb.build()))).queue();
    }
    public EmbedBuilder sendEmbedWithReason(User user, String reason, SlashCommandInteractionEvent e){
        EmbedBuilder er = new EmbedBuilder();
        er.setTitle(":hammer:Erfolgreich geckickt:hammer:");
        er.setColor(Color.RED);
        er.addField("Der Nutzer " + user.getName() + " wurde von " + Objects.requireNonNull(e.getMember()).getEffectiveName() + "gekickt!", "", false);
        er.addField("Die Begründung ist:", "", false);
        er.addField("**" + reason + "**", "", false);
        return er;
    }
    public EmbedBuilder sendEmbedWithoutReason(User user, SlashCommandInteractionEvent e){
        EmbedBuilder er = new EmbedBuilder();
        er.setTitle(":hammer:Erfolgreich gebannt:hammer:");
        er.setColor(Color.RED);
        er.addField("Der Nutzer " + user.getName() + " wurde von " + Objects.requireNonNull(e.getMember()).getEffectiveName() + " gekickt!", "", false);
        return er;
    }
}
