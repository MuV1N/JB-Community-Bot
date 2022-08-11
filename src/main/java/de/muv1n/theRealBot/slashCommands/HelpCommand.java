package de.muv1n.theRealBot.slashCommands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class HelpCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent e) {
        if (!e.getName().equals("help"))return;
        e.replyEmbeds(sendHelpMessageEmbeds().build()).setEphemeral(true).queue();


    }

    public EmbedBuilder sendHelpMessageEmbeds(){
        EmbedBuilder eb = new EmbedBuilder();

        eb.setColor(Color.cyan);
        eb.setTitle("HIlfe Nachricht");
        eb.setDescription("Du benötigst hilfe zu allen Komandos, dann schau in die folgende Liste:");
        eb.addField("/ban", "Mit **/ban** kannst du einen Nutzer von dem Discord Server bannen. Du kannst auswählen, ob du eine **Begründung** angibst oder nicht!", false);
        eb.addField("/kick", "Mit **/kick** kannst du einen Nutzer von dem Discord Server kicken. Du kannst auswählen, ob du eine **Begründung** angibst oder nicht!", false);
        eb.addField("/sendreactionmessage", "Mit **/sendreactionmessage** kannst du eine **Öffentliche** Nachricht senden, welche vorgefertigt sind wie, z.B. die **Reaction roles** Nachricht. " +
                "In naher zukunft werden mit sicherheit weiter Reaction Nachrichten kommen, dass wirst du aber erfahren es ist immer im autocomplete!", false);

        return eb;
    }

}
