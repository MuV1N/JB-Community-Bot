package de.muv1n.theRealBot.buttonInteractions;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;

public class ReactionRoleButon extends ListenerAdapter {

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent e) {

        if(e.getComponentId().equals("twitch")){
            Role tw = Objects.requireNonNull(e.getGuild()).getRoleById(934533413204029510L);
            String service = "Twitch";
            if (Objects.requireNonNull(e.getMember()).getRoles().contains(tw)){
                e.getGuild().removeRoleFromMember(e.getUser(), tw).queue();
                e.replyEmbeds(replyMessageEmbedsRemovedRole(tw, service).build()).setEphemeral(true).queue();
            }else{
                e.getGuild().addRoleToMember(e.getUser(), tw).queue();
                e.replyEmbeds(replyMessageEmbedsAddedRole(tw, service).build()).setEphemeral(true).queue();
            }
        }
        if (e.getComponentId().equals("youtube")){
            String service = "Youtube";
            Role yt = Objects.requireNonNull(e.getGuild()).getRoleById(936616241358078033L);
            if (Objects.requireNonNull(e.getMember()).getRoles().contains(yt)){
                e.getGuild().removeRoleFromMember(e.getUser(), yt).queue();
                e.replyEmbeds(replyMessageEmbedsRemovedRole(yt, service).build()).setEphemeral(true).queue();
            }else{
                e.getGuild().addRoleToMember(e.getUser(), yt).queue();
                e.replyEmbeds(replyMessageEmbedsAddedRole(yt, service).build()).setEphemeral(true).queue();
            }
        }
        if (e.getComponentId().equals("discord")){
            String service = "Discord";
            Role dc = Objects.requireNonNull(e.getGuild()).getRoleById(936616389811245056L);
            if (Objects.requireNonNull(e.getMember()).getRoles().contains(dc)){
                e.getGuild().removeRoleFromMember(e.getUser(), dc).queue();
                e.replyEmbeds(replyMessageEmbedsRemovedRole(dc, service).build()).setEphemeral(true).queue();
            }else{
                e.getGuild().addRoleToMember(e.getUser(), dc).queue();
                e.replyEmbeds(replyMessageEmbedsAddedRole(dc, service).build()).setEphemeral(true).queue();
            }
        }
    }
    public EmbedBuilder replyMessageEmbedsRemovedRole(Role role, String service){
        EmbedBuilder eb = new EmbedBuilder();

        eb.setColor(Color.RED);
        eb.setTitle("Rolle entfernt");
        eb.addField("Dir wurde die Rolle **<@&" + role.getName() + ">** entzogen!", "", false);
        eb.addField("Das bedeutet, dass du jetzt keine " + service + "-Benachrichtigungen mehr bekommst!", "", false);

        return eb;
    }
    public EmbedBuilder replyMessageEmbedsAddedRole(Role role, String service){
        EmbedBuilder eb = new EmbedBuilder();

        eb.setColor(Color.GREEN);
        eb.setTitle("Rolle hinzugefügt");
        eb.addField("Du hast die Rolle **<@&" + role.getName() + ">** hinzugefügt bekommen!", "", false);
        eb.addField("Das bedeutet, dass du jetzt " + service + "-Benachrichtigungen bekommst!", "", false);

        return eb;
    }
}
