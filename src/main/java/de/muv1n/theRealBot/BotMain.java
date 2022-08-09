package de.muv1n.theRealBot;

import de.muv1n.theRealBot.events.GuildMemberJoin;
import de.muv1n.theRealBot.events.GuildMemberRemove;
import de.muv1n.theRealBot.events.GuildReady;
import de.muv1n.theRealBot.events.PrivateMessageReceive;
import de.muv1n.theRealBot.slashCommands.BanCommand;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static net.dv8tion.jda.api.Permission.MODERATE_MEMBERS;

public class BotMain {
    public static JDABuilder builder;
    public static JDA jda;

    public static void main(String[] args) throws IOException, LoginException {
        builder = JDABuilder.createDefault(Token.getToken());
        builder.enableIntents(GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_MESSAGE_TYPING, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_BANS, GatewayIntent.DIRECT_MESSAGE_REACTIONS, GatewayIntent.DIRECT_MESSAGE_TYPING);
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.setStatus(OnlineStatus.IDLE);
        builder.setActivity(Activity.streaming("Schaue meinem Vater auf Twitch vorbei!", "https://www.twitch.tv/therealj0sh"));
        builder.addEventListeners(new GuildReady());
        builder.addEventListeners(new GuildMemberJoin(), new GuildMemberRemove(), new PrivateMessageReceive());
        builder.addEventListeners(new BanCommand());
        jda = builder.build();
        System.out.println("[BOT] Der Bot wurde erfolgreich gestartet!");

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
