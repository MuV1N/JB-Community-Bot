package de.muv1n.theRealBot.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class GuildMemberRemove extends ListenerAdapter {

    @Override
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent e) {

        Random i = new Random();
        int upperbound = 13;
        int x = i.nextInt(upperbound);
        Color[] colors = {Color.GREEN, Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK,
                Color.RED, Color.WHITE, Color.YELLOW};
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(colors[x]);
        eb.setTitle(":sob: Tschüss :wave:");
        eb.setDescription(":wave: Der :busts_in_silhouette: user " + e.getUser().getAsMention() + " hat das Raumschiff wieder verlassen :sob:!");
        eb.setThumbnail(Objects.requireNonNull(e.getUser()).getEffectiveAvatarUrl());

        e.getGuild().getTextChannelsByName("\uD83D\uDC4B-bye", true).get(0).sendMessageEmbeds(eb.build()).queue();
    }
}
