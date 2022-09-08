package de.muv1n.jbBot.buttonInteractions.tickettool;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class TicketCloseButtons extends ListenerAdapter {
    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent e) {
        if (!e.getComponentId().equals("close")) return;
        Long testServerID = 1007005808476815542L;

        e.getChannel().sendMessage(e.getMember().getAsMention()).setEmbeds(sendRealy().build()).setActionRow(
                Button.success("vc", "Ticket Schließen").withEmoji(Emoji.fromUnicode("✅")),
                Button.danger("stopclose","Abbrechen").withEmoji(Emoji.fromUnicode("❎"))
        ).queueAfter(3, TimeUnit.SECONDS);
        e.reply("").setEphemeral(true).queue(reply-> {
            reply.editOriginal("Nachricht wurde gesendet!").queueAfter(3, TimeUnit.SECONDS);
        });

    }
    public EmbedBuilder sendRealy(){
        EmbedBuilder eb = new EmbedBuilder();

        eb.setColor(Color.ORANGE);
        eb.setTitle("Bist du sicher?");
        eb.addField("Sicher?", "Wenn du das Ticket schließt kannst du es nicht mehr einsehen!", false);
        eb.addField("Unsicher?", "Dann warte lieber bis jemand aus dem Team das Ticket schließt!", false);

        return eb;
    }
}
