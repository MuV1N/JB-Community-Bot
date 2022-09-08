package de.muv1n.jbBot;

import de.muv1n.jbBot.buttonInteractions.ReactionRoleButon;
import de.muv1n.jbBot.buttonInteractions.tickettool.TicketCloseButtons;
import de.muv1n.jbBot.buttonInteractions.tickettool.TicketCloseVerify;
import de.muv1n.jbBot.buttonInteractions.tickettool.TicketCreateButtons;
import de.muv1n.jbBot.buttonInteractions.tickettool.TicketDeleteButtons;
import de.muv1n.jbBot.events.*;
import de.muv1n.jbBot.slashCommands.BanCommand;
import de.muv1n.jbBot.slashCommands.HelpCommand;
import de.muv1n.jbBot.slashCommands.KickCommand;
import de.muv1n.jbBot.slashCommands.ReactionRoleMessage;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;
import java.io.*;

public class BotMain {

    @Getter
    private static BotMain instance;
    /*@Getter
    private MongoManager mongoManager;
    @Getter
    private TicketChannelUser ticketChannelUser;
    @Getter
    private BackendManager backendManager;*/
    public static JDABuilder builder;
    public static JDA jda;

    public static void main(String[] args) throws IOException, LoginException {

        instance = new BotMain();

        //init();

        //TwitchClient twitchClient = TwitchClientBuilder.builder().withEnableHelix(true).build();

        builder = JDABuilder.createDefault(Token.getToken());
        builder.enableIntents(GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_MESSAGE_TYPING, GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_BANS, GatewayIntent.DIRECT_MESSAGE_REACTIONS, GatewayIntent.DIRECT_MESSAGE_TYPING);
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.setStatus(OnlineStatus.ONLINE);

        //SlashCommand Util
        builder.addEventListeners(new GuildReady());
        //Register Events
        builder.addEventListeners(new GuildMemberJoin(), new GuildMemberRemove(), new PrivateMessageReceive());
        //Register SlashCommand
        builder.addEventListeners(new BanCommand(), new KickCommand(), new ReactionRoleMessage(), new HelpCommand());
        //Register ButtonInteraction
        builder.addEventListeners(new ReactionRoleButon(), new TicketCreateButtons(), new TicketCloseButtons(), new TicketCloseVerify(), new TicketDeleteButtons());

        //private channel

        builder.addEventListeners();

        //end of Private Channel

        jda = builder.build();
        System.out.println("[BOT] Der Bot wurde erfolgreich gestartet!");

        //TwitchMessages.twitchMessage(jda.getGuildById(1007005808476815542L), twitchClient);

        stop();

    }
    public static void stop() throws IOException {
        while(true){
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            if (reader.readLine().equals("stop")){
                reader.close();
                builder.setStatus(OnlineStatus.OFFLINE);
                jda.shutdown();
                System.out.println("[BOT] Der Bot wurde erfolgreich heruntergefahren!");
                System.exit(0);
            }
        }
    }
    /*private static void init(){
        instance.mongoManager = new MongoManager(instance, "-mongodb-clevercloud-customers.services.clever-cloud.com", 2017);
        instance.mongoManager.connect("uhesdtv2uknkd3sqkh3j", "JGkNJClBini9kkjmLdUZ", "bwbvvumxzran3lw");
        instance.backendManager = new BackendManager(instance);
    }*/
}
