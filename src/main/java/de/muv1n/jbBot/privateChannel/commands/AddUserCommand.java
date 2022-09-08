package de.muv1n.jbBot.privateChannel.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AddUserCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent e) {

    }

    @Override
    public void onCommandAutoCompleteInteraction(@NotNull CommandAutoCompleteInteractionEvent e) {

    }

    //REGISTER COMMAND
    public void onGuildReady(@NotNull GuildReadyEvent e) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("c-adduser", "Füge einen Nutzer zu deinem Private Channel hinzu")
                .addOption(OptionType.USER, "nutzer", "Name des Nutzers", true, false)
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.KICK_MEMBERS)));



        e.getGuild().updateCommands().addCommands(commandData).queue();
    }
}
