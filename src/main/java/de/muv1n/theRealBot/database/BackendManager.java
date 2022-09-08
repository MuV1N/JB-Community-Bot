package de.muv1n.theRealBot.database;

import com.mongodb.client.model.Filters;
import de.muv1n.theRealBot.BotMain;
import net.dv8tion.jda.api.entities.Member;

import javax.swing.text.Document;
import java.util.function.Consumer;

public class BackendManager {
/*
    private final BotMain main;

    public BackendManager(BotMain main) {
        this.main = main;
    }

    public void createChannel(Member member, String name, Consumer<TicketChannelUser> consumer){

        Long memberID = member.getIdLong();
        name = "ticket-" + member.getEffectiveName();


        String finalName = name;
        main.getMongoManager().getTicketChannel().find(Filters.eq("userID", memberID)).first((Document t, Throwable thrwbl) ->{
           if (t == null){
               TicketChannelUser dbUser = new TicketChannelUser();
               dbUser.setUserID(memberID);

               main.getMongoManager().getTicketChannel().insertOne(t, (Void t1, Throwable thrwl1)->{
                   consumer.accept(dbUser);
               });
               return;
           }
        });

        main.getMongoManager().getTicketChannel().find(Filters.eq("channelName", finalName)).first((Document t, Throwable throwable) ->{
            if (t == null){
                TicketChannelUser dbUser = new TicketChannelUser();
                dbUser.setChannelName(finalName);

                main.getMongoManager().getTicketChannel().insertOne(t, (Void t1, Throwable thrwl1)->{
                    consumer.accept(dbUser);
                });
                return;
            }
        });

    }
    public void updateChannel(Member member, String name, Consumer<TicketChannelUser> consumer){

        name = "ticket-" + member.getEffectiveName();
        String finalName = "closed-" + name;
        Long memberID = member.getIdLong();

        Document document = null;

        main.getMongoManager().getTicketChannel().replaceOne(Filters.eq("channelName", name), document, (t, throwable) -> {
            TicketChannelUser dbUser = new TicketChannelUser();
            dbUser.setChannelName(finalName);

            consumer.accept(dbUser);
        });

    }
*/}
