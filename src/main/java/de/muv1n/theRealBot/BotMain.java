package de.muv1n.theRealBot;

import de.muv1n.theRealBot.events.GuildMemberJoin;
import de.muv1n.theRealBot.events.GuildMemberRemove;
import de.muv1n.theRealBot.util.Token;
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
    public BotMain() throws LoginException, IOException {
        builder = JDABuilder.createDefault(Token.getToken());
        builder.enableIntents(GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_MESSAGE_TYPING, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_BANS, GatewayIntent.DIRECT_MESSAGE_REACTIONS, GatewayIntent.DIRECT_MESSAGE_TYPING);
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.setStatus(OnlineStatus.IDLE);
        builder.setActivity(Activity.streaming("Schaue meinem Vater auf Twitch vorbei!", "https://www.twitch.tv/therealj0sh"));
        builder.addEventListeners(new GuildMemberJoin(), new GuildMemberRemove());
        jda = builder.build();
        System.out.println("[BOT] Der Bot wurde erfolgreich gestartet!");
        stop();
    }

    public static void main(String[] args) {
        try{
            BotMain bot = new BotMain();

        } catch (LoginException | IOException e) {
           e.printStackTrace();
        }
    }
    public static void stop() throws IOException {
        while(true){
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            if (reader.readLine().equals("stop")){
                builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
                reader.close();
                builder.setStatus(OnlineStatus.OFFLINE);
                jda.shutdown();
                System.out.println("[BOT] Der Bot wurde erfolgreich heruntergefahren!");
                System.exit(0);
            }
        }
    }
}
