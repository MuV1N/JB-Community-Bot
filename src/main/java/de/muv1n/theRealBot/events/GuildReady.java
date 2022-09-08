package de.muv1n.theRealBot.events;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
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

        commandData.add(Commands.slash("help", "Zeige eine Liste mit allen Slash-Commands")
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.KICK_MEMBERS)));

        commandData.add(Commands.slash("ban", "Banne einen Nutzer von diesem Server")
                .addOption(OptionType.USER, "name", "Den zu bannenden Nutzer", true, false)
                .addOption(OptionType.STRING, "begründung", "Wieso dieser Spieler gebannt wird", false, false)
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.BAN_MEMBERS)));
        commandData.add(Commands.slash("kick", "Kicke einen Nutzer von diesem Server")
                .addOption(OptionType.USER, "name", "Den Nutzer, welcher gekickt werden soll", true, false)
                .addOption(OptionType.STRING, "begründung", "Wieso dieser Spieler gekickt wird", false, false)
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.BAN_MEMBERS)));
        /*commandData.add(Commands.slash("", "")
                .addOption(OptionType.USER, "user", "Entscheide welcher User getimeoutet wird")
                .addOption()
                .addOption(OptionType.STRING, "reason", "Begründung"));*/

        commandData.add(Commands.slash("sendreactionmessage", "Sende eine Nachrich mit Reactions Buttons als Anhang!")
                .addOption(OptionType.STRING, "type", "Der Nachrichtentyp", true, true)
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.MANAGE_SERVER)));

        e.getGuild().updateCommands().addCommands(commandData).queue();
    }
}
