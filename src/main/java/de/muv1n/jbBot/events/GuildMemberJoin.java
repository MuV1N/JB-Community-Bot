package de.muv1n.jbBot.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Random;

public class GuildMemberJoin extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent e) {

        TextChannel channel = e.getGuild().getTextChannelsByName("\uD83D\uDC4B┃willkommen", true).get(0);

        Random i = new Random();
        int upperbound = 13;
        int x = i.nextInt(upperbound);
        Color[] colors = {Color.GREEN, Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK,
                Color.RED, Color.WHITE, Color.YELLOW};
        e.getGuild().addRoleToMember(e.getUser(), e.getGuild().getRoleById(1017423926604877865L)).queue();
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(colors[x]);
        eb.setDescription(":wave: " + e.getMember().getAsMention() + " ist erfolgreich auf dem Discord Server der JB Community gelandet :partying_face:");
        eb.setThumbnail(e.getUser().getEffectiveAvatarUrl());
        eb.setTitle(":heart:-wilkommen :wave:");
        eb.addField("Er ist der " + e.getGuild().getMemberCount() + " :busts_in_silhouette: Nutzer auf diesem Raumschiff!", "", false);
        channel.sendMessageEmbeds(eb.build()).queue();
        sendPrivateMessage(e.getUser(), e.getGuild());
        }
        public void sendPrivateMessage(User user, Guild guild){
            Random i = new Random();
            int upperbound = 13;
            int x = i.nextInt(upperbound);
            Color[] colors = {Color.GREEN, Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK,
                    Color.RED, Color.WHITE, Color.YELLOW};
            EmbedBuilder eb = new EmbedBuilder();
            eb.setColor(colors[x]);
            eb.setTitle(":heart:-wilkommen auf " + guild.getName() + ":wave:");
            eb.setDescription("**Wenn du fragen :question: hast, schreibe mir einfach eine Nachricht!**");
            eb.addField("Das Team wünscht dir viel spaß auf dem Server :partying_face:","", false);
            user.openPrivateChannel().flatMap((privateChannel -> privateChannel.sendMessageEmbeds(eb.build()))).queue();
        }
}
