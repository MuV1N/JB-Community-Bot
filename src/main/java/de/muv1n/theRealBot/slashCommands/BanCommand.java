package de.muv1n.theRealBot.slashCommands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class BanCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent e) {
        if (!e.getName().equals("ban"))return;
        User user = Objects.requireNonNull(e.getOption("name")).getAsUser();
        EmbedBuilder eor = new EmbedBuilder();
        eor.setColor(Color.RED);
        eor.setTitle(":hammer:Erfolgreich gebannt:hammer:");
        eor.addField("Der Nutzer " + user.getName() + " wurde gebannt!", "", false);
        if (e.getOption("begründung") != null){
        String reason = Objects.requireNonNull(e.getOption("begründung")).getAsString();
        EmbedBuilder er = new EmbedBuilder();
        er.setTitle(":hammer:Erfolgreich gebannt:hammer:");
        er.setColor(Color.RED);
        er.addField("Der Nutzer " + user.getName() + " wurde gebannt!", "", false);
        er.addField("Die Begründung ist:", "", false);
        er.addField("**" + reason + "**", "", false);
        sendPrivateMessageWithReason(user, e.getGuild(), reason);
        Objects.requireNonNull(e.getGuild()).ban(user, 1, reason).queue();
        e.replyEmbeds(er.build()).setEphemeral(true).queue();
            e.getGuild().getTextChannelsByName("\uD83D\uDCD8logs", false).get(0).sendMessageEmbeds(er.build()).queue();
        }else{
            e.getGuild().getTextChannelsByName("\uD83D\uDCD8logs", false).get(0).sendMessageEmbeds(eor.build()).queue();
            sendPrivateMessageWithoutReason(user, e.getGuild());
            Objects.requireNonNull(e.getGuild()).ban(user, 1).queue();
            e.replyEmbeds(eor.build()).setEphemeral(true).queue();
        }
    }
    public void sendPrivateMessageWithReason(User user, Guild guild, String reason){
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.RED);
        eb.setTitle(":hammer:Du wurdest gebannt:sob:");
        eb.addField("Die Begründung ist:", "", true);
        eb.addField("**" + reason + "**", "", false);
        user.openPrivateChannel().flatMap((privateChannel -> privateChannel.sendMessageEmbeds(eb.build()))).queue();
    }
    public void sendPrivateMessageWithoutReason(User user, Guild guild){
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.RED);
        eb.setTitle(":hammer:Du wurdest gebannt:sob:");
        eb.addField("Es gibt keine Begründung!", "", true);
        user.openPrivateChannel().flatMap((privateChannel -> privateChannel.sendMessageEmbeds(eb.build()))).queue();
    }
}
