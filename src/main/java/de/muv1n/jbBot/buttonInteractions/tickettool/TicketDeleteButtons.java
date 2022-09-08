package de.muv1n.jbBot.buttonInteractions.tickettool;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class TicketDeleteButtons extends ListenerAdapter {

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent e) {
        if (!e.getComponentId().equals("delete")) return;
        Long testServerID = 1007005808476815542L;
        Long logChannelIDJoshServer = 1006731591302774874L;
        Long JBServerID = 934200357532336230L;




        if (e.getGuild() == e.getJDA().getGuildById(testServerID)) {
            //TEST-Mod
            Role testModTestServer = (Role) e.getGuild().getRoleById(1007005808598458485L);
            //Mod
            Role modTestServer = (Role) e.getGuild().getRoleById(1007005808598458486L);
            //Mod-Leitung
            Role modLeitungTestServer = (Role) e.getGuild().getRoleById(1007005808598458487L);
            //Devs
            Role devsTestServer = (Role) e.getGuild().getRoleById(1007005808598458488L);
            //Leitung
            Role leitungTestServer = (Role) e.getGuild().getRoleById(1007005808598458489L);
            //Josh
            Role joshTestServer = (Role) e.getGuild().getRoleById(1007005808632025109L);
            if (e.getMember().getRoles().contains(testModTestServer) || e.getMember().getRoles().contains(modTestServer) || e.getMember().getRoles().contains(modLeitungTestServer) ||
                    e.getMember().getRoles().contains(devsTestServer) || e.getMember().getRoles().contains(leitungTestServer) || e.getMember().getRoles().contains(joshTestServer)) {
                e.reply("Der Channel wird in 5 Sekundene gelöscht").setEphemeral(true).queue(m ->{
                    m.editOriginal("Der Channel wird in 4 Sekundene gelöscht").queueAfter(1, TimeUnit.SECONDS);
                    m.editOriginal("Der Channel wird in 3 Sekundene gelöscht").queueAfter(2, TimeUnit.SECONDS);
                    m.editOriginal("Der Channel wird in 2 Sekundene gelöscht").queueAfter(3, TimeUnit.SECONDS);
                    m.editOriginal("Der Channel wird in 1 Sekundene gelöscht").queueAfter(4, TimeUnit.SECONDS);
                });
                e.getChannel().delete().queueAfter(5, TimeUnit.SECONDS);
                String name = e.getChannel().getName();
                e.getGuild().getTextChannelById(1007005810733355111L).sendMessageEmbeds(sendCloseTicketActionsEmbeds(e, name).build()).queue();
            }else {
                e.reply("Du kannst das Ticket nicht löschen!").setEphemeral(true).queue();
            }
        } else {
            //TEST-Mod
            Role testMod = (Role) e.getGuild().getRoleById(934782939907125338L);
            //Mod
            Role mod = (Role) e.getGuild().getRoleById(934209113846591568L);
            //Mod-Leitung
            Role modLeitung = (Role) e.getGuild().getRoleById(1006176317026340916L);
            //Devs
            Role devs = (Role) e.getGuild().getRoleById(934211068430008380L);
            //Leitung
            Role leitung = (Role) e.getGuild().getRoleById(934208411778838589L);
            //Josh
            Role owner = (Role) e.getGuild().getRoleById(934208723658870816L);
            if (e.getMember().getRoles().contains(testMod) || e.getMember().getRoles().contains(mod) || e.getMember().getRoles().contains(modLeitung) ||
                    e.getMember().getRoles().contains(devs) || e.getMember().getRoles().contains(leitung) || e.getMember().getRoles().contains(owner)) {
                e.reply("Der Channel wird in 5 Sekundene gelöscht").setEphemeral(true).queue();
                e.getChannel().delete().queueAfter(5, TimeUnit.SECONDS);
                String name = e.getChannel().getName();
                e.getGuild().getTextChannelById(1006735597727121558L).sendMessageEmbeds(sendCloseTicketActionsEmbeds(e, name).build()).queue();
            }else{
                e.reply("Du kannst das Ticket nicht löschen!").setEphemeral(true).queue();
            }
        }
    }

    public EmbedBuilder sendCloseTicketActionsEmbeds(ButtonInteractionEvent e, String name) {
        EmbedBuilder eb = new EmbedBuilder();

        eb.setColor(Color.RED);
        eb.setTitle(e.getMember().getEffectiveName());
        eb.addField("Ticket Channel name new:", "closed-" + name, false);
        eb.addField("Action", "Deleted", false);

        return eb;

    }
}
