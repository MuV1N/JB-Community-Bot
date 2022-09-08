package de.muv1n.theRealBot.buttonInteractions.tickettool;

import de.muv1n.theRealBot.BotMain;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class TicketCreateButtons extends ListenerAdapter {

    private String channelID;

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent e) {
        if (!e.getComponentId().equals("ticket")) return;

        Guild guild = e.getGuild();
        Role everyoneTestServer = guild.getRoleById(1007005808476815542L);
        Role joshServer = guild.getRoleById(934200357532336230L);
        String logChannelIDTestServer = "1007005810733355111";
        String logChannelIDJoshServer = "1006731591302774874";

        Category category = guild.getCategoriesByName("Support", true).get(0);

        Collection<Permission> allowPerms = new ArrayList<>();
        allowPerms.add(Permission.MESSAGE_SEND);
        allowPerms.add(Permission.MESSAGE_HISTORY);
        allowPerms.add(Permission.MESSAGE_MENTION_EVERYONE);
        Collection<Permission> denyPerms = new ArrayList<>();
        Collection<Permission> allowEveryonePerms = new ArrayList<>();
        Collection<Permission> denyEveryonePerms = new ArrayList<>();

        String name = e.getUser().getName();
        guild.createTextChannel("ticket-" + name, category).queue(channel ->{
            channelID = channel.getId();

            BotMain main = new BotMain();

            //BotMain.getInstance().getBackendManager().createChannel(e.getMember(), name, dbUser -> System.out.println("sd"));

            channel.getManager().sync().queue();
            channel.getManager().putMemberPermissionOverride(e.getMember().getIdLong(), allowPerms, denyPerms).queueAfter(25, TimeUnit.MILLISECONDS);
            channel.sendMessage(e.getMember().getAsMention()).setEmbeds(sendChannelCreateEmbeds().build()).setActionRow(
                    Button.danger("close", "Ticket schließen").withEmoji(Emoji.fromUnicode("\uD83D\uDD12"))
            ).queueAfter(2, TimeUnit.SECONDS);
            e.reply("").setEphemeral(true).queue(r ->
                    r.editOriginal("Dein Ticket Channel wurde erstellt es ist " + guild.getTextChannelById(channelID).getAsMention() + "!").queueAfter(2, TimeUnit.SECONDS));

            guild.getTextChannelById(logChannelIDJoshServer).sendMessageEmbeds(logChannelCreated(channelID, guild, e, name).build()).queueAfter(2, TimeUnit.SECONDS);

        });



        }
    public EmbedBuilder logChannelCreated(String channelID, Guild guild, ButtonInteractionEvent e, String name){
        EmbedBuilder eb = new EmbedBuilder();

        eb.setColor(Color.green);
        eb.setTitle(e.getMember().getEffectiveName());
        eb.addField("Ticket Channel", "Name: ticket-" + name, false);
        eb.addField("Action", "Created", false);

        return eb;
    }
    public EmbedBuilder sendChannelCreateEmbeds(){

        EmbedBuilder eb = new EmbedBuilder();

        eb.setColor(Color.GREEN);
        eb.setTitle("Wilkommen im Support!");
        eb.setDescription("Schildere uns doch bitte dein Problem, ein Moderator wird dein Ticket in kürze bearbeiten!");
        eb.addField("Wie kannst du dass Ticket schließen", "Um das Ticket zu schließen, Reagiere mit :lock:", false);

        return eb;
    }
}
