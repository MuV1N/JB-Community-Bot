package de.muv1n.theRealBot.slashCommands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReactionRoleMessage extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent e) {
        if (!e.getName().equals("sendreactionmessage")) return;

        if (e.getOption("type").getAsString().equals("roles")){
            e.replyEmbeds(sendMessageEmbedsRole(e).build()).addActionRow(
                    Button.success("twitch", "Twitch-Benachrichtigungen"),
                    Button.success("youtube", "YouTube-Benachrichtigungen"),
                    Button.success("discord", "Discord-Benachrichtigungen")
            ).queue();
        }
        if (e.getOption("type").getAsString().equals("ticket")){
            e.replyEmbeds(sendMessageEmbedsTickets(e).build()).addActionRow(
                    Button.success("ticket", "Erstelle ein Ticket")
            ).queue();
        }

    }
    private String[] words = new String[]{"roles", "ticket"};

    @Override
    public void onCommandAutoCompleteInteraction(@NotNull CommandAutoCompleteInteractionEvent e) {
        if (!e.getName().equals("sendreactionmessage") || !e.getFocusedOption().getName().equals("type")) return;
        List<Command.Choice> options = Stream.of(words)
                .filter(word -> word.startsWith(e.getFocusedOption().getValue()))
                .map(word -> new Command.Choice(word, word))
                .collect(Collectors.toList());
        e.replyChoices(options).queue();

    }

    public EmbedBuilder sendMessageEmbedsRole(SlashCommandInteractionEvent e) {
        Member memmber = Objects.requireNonNull(e.getGuild()).getMemberById(566521375842631686L);
        Role dc = Objects.requireNonNull(e.getGuild()).getRoleById(936616389811245056L);
        Role yt = Objects.requireNonNull(e.getGuild()).getRoleById(936616241358078033L);
        Role tw = Objects.requireNonNull(e.getGuild()).getRoleById(934533413204029510L);
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.cyan);
        eb.setTitle("Rollen Auswahl");
        eb.setDescription("Du kannst auswählen, welche Benachrichtigungs Rollen du haben möchtest!");
        assert memmber != null;
        eb.addField("Twitch-Benachrichtigungen","Du möchtest Benachrichtigungen bekommen, wenn **<@" + memmber.getId() + ">** auf Twitch live geht, dann klicke auf den Knopf mit dem Namen Twitch-Benachrichtigungen!", false);
        eb.addField("YouTube-Benachrichtigungen","Du möchtest Benachrichtigungen bekommen, wenn **<@" + memmber.getId() + ">** ein neues YouTube video hochgeladen hat, dann klicke auf den Knopf mit dem Namen YouTube-Benachrichtigungen!", false);
        eb.addField("Discord-Benachrichtigungen","Du möchtest Benachrichtigungen bekommen, wenn etwas auf diesem Discord Server geändert wird, dann klicke auf den Knopf mit dem Namen Discord-Benachrichtigungen!", false);


        return eb;
    }
    public EmbedBuilder sendMessageEmbedsTickets(SlashCommandInteractionEvent e){
    EmbedBuilder eb = new EmbedBuilder();

    eb.setTitle("Support Ticket");
    eb.setColor(Color.GREEN);
    eb.setDescription("Du benötigst Hilfe, dann erstell ein Ticket!");
    eb.addField("Wie erstelle ich ein Ticket?", "Du kannst ein Ticket erstellen, indem du unter dieser Nachricht auf den Knopf mit dem Namen 'Erstelle ein Ticket'", false);


    return eb;
    }

}
