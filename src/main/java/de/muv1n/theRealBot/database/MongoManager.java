package de.muv1n.theRealBot.database;

import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoClients;
import com.mongodb.async.client.MongoCollection;
import com.mongodb.async.client.MongoDatabase;
import de.muv1n.theRealBot.BotMain;
import lombok.Getter;

import javax.swing.text.Document;

public class MongoManager {

    private final BotMain main;
    private final String hostname;
    private final int port;

    private MongoClient client;
    private MongoDatabase database;

    @Getter
    private MongoCollection<Document> ticketChannel;

    public MongoManager(BotMain main, String hostname, int port){
        this.main = main;
        this.hostname = hostname;
        this.port = port;
    }

    public void connect(String username, String password, String database){
        this.client = MongoClients.create("mongodb://" + username + ":" + password + "@" + database + this.hostname + port);
        System.out.println("§4DB Connected");
    }

}
