package de.muv1n.theRealBot;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import de.muv1n.theRealBot.buttonInteractions.ReactionRoleButon;
import de.muv1n.theRealBot.events.GuildMemberJoin;
import de.muv1n.theRealBot.events.GuildMemberRemove;
import de.muv1n.theRealBot.events.GuildReady;
import de.muv1n.theRealBot.events.PrivateMessageReceive;
import de.muv1n.theRealBot.slashCommands.BanCommand;
import de.muv1n.theRealBot.slashCommands.HelpCommand;
import de.muv1n.theRealBot.slashCommands.KickCommand;
import de.muv1n.theRealBot.slashCommands.ReactionRoleMessage;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BotMain {
    public static JDABuilder builder;
    public static JDA jda;
    /*@Getter
    public static MongoClient mongoClient;
    @Getter
    public static MongoDatabase database;
    @Getter
    public static MongoCollection collection;
*/
    public static void main(String[] args) throws IOException, LoginException {
        builder = JDABuilder.createDefault(Token.getToken());
        builder.enableIntents(GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_MESSAGE_TYPING, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_BANS, GatewayIntent.DIRECT_MESSAGE_REACTIONS, GatewayIntent.DIRECT_MESSAGE_TYPING);
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.setStatus(OnlineStatus.IDLE);
        builder.setActivity(Activity.streaming("Schaue meinem Vater auf Twitch vorbei!", "https://www.twitch.tv/therealj0sh"));
        //SlashCommand Util
        builder.addEventListeners(new GuildReady());
        //Register Events
        builder.addEventListeners(new GuildMemberJoin(), new GuildMemberRemove(), new PrivateMessageReceive());
        //Register SlashCommand
        builder.addEventListeners(new BanCommand(), new KickCommand(), new ReactionRoleMessage(), new HelpCommand());
        //Register ButtonInteraction
        builder.addEventListeners(new ReactionRoleButon());
        jda = builder.build();
        System.out.println("[BOT] Der Bot wurde erfolgreich gestartet!");

        /*mongoClient = new MongoClient(new MongoClientURI("mongodb://45.81.233.187:27017"));
        database = mongoClient.getDatabase("therealbot");
        collection = database.getCollection("channelName");
*/
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
}
