package de.muv1n.theRealBot.database;

import lombok.Getter;
import lombok.Setter;

public class TicketChannelUser {
    public TicketChannelUser(){

    }

    @Getter @Setter
    private Long userID;
    @Getter @Setter
    private String channelName;
}
