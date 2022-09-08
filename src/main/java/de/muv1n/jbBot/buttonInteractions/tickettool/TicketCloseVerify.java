package de.muv1n.jbBot.buttonInteractions.tickettool;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class TicketCloseVerify extends ListenerAdapter {
    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent e) {
        TextChannel textChannel = e.getChannel().asTextChannel();
        String channelName = e.getChannel().getName();
        Long IDTestServerTicketAction = 1007005810733355111L;
        Long logChannelIDJoshServer = 1006731591302774874L;


        if (e.getComponentId().equals("vc")){
            e.getGuild().getTextChannelById(logChannelIDJoshServer).sendMessageEmbeds(sendCloseTicketActionsEmbeds(e, channelName).build()).queue();
            textChannel.getManager().setName("closed-" + channelName).queue();
            textChannel.sendMessageEmbeds(sendCloseEmbeds().build()).setActionRow(
                    Button.danger("delete", "Löschen").withEmoji(Emoji.fromUnicode("\uD83D\uDD12"))
            ).queue();
            /*Collection<Permission> allowPerms = new ArrayList<>();

            Collection<Permission> denyPerms = new ArrayList<>();
            denyPerms.add(Permission.MESSAGE_SEND);
            denyPerms.add(Permission.MESSAGE_HISTORY);
            denyPerms.add(Permission.MESSAGE_MENTION_EVERYONE);
            denyPerms.add(Permission.VIEW_CHANNEL);

            textChannel.getManager().putMemberPermissionOverride(e.getMember().getIdLong(), allowPerms, denyPerms).queue();*/

            e.getMessage().delete().queue();

        }else if(e.getComponentId().equals("stopclose")){
            e.getMessage().delete().queue();
        }
    }

    public EmbedBuilder sendCloseEmbeds(){
        EmbedBuilder eb = new EmbedBuilder();

        eb.setColor(Color.RED);
        eb.setTitle("\uD83D\uDD12Ticket Geschlossen:lock:");
        eb.setDescription("Das Ticket wurde geschlossen");

        return eb;
    }
    public EmbedBuilder sendCloseTicketActionsEmbeds(ButtonInteractionEvent e, String name){
        EmbedBuilder eb = new EmbedBuilder();

        eb.setColor(Color.RED);
        eb.setTitle(e.getMember().getEffectiveName());
        eb.addField("Ticket Channel name old:", name, false);
        eb.addField("Ticket Channel name new:", "closed-" + name, false);
        eb.addField("Action", "Closed", false);

        return eb;
    }

}
