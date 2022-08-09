package de.muv1n.theRealBot.events;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GuildReady extends ListenerAdapter {

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent e) {
        List<CommandData> commandData = new ArrayList<>();

        commandData.add(Commands.slash("ban", "Banne Nutzer von diesem Server")
                .addOption(OptionType.USER, "name", "Den zu bannenden Nutzer", true, false)
                .addOption(OptionType.STRING, "begründung", "Wieso dieser Spieler gebannt wird", false, false)
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.BAN_MEMBERS, Permission.ADMINISTRATOR)));

        e.getGuild().updateCommands().addCommands(commandData).queue();
    }
}
