package com.rest.model;

import java.net.URL;
import java.util.Date;

public class PlayerInfo {
    private String nickname;
    private URL url;
    private Date creationDate;
    private Date lastOnlineDate;
    private int numberOfPlayedGames;
    private int bulletRanking;
    private int blitzRanking;
    private int rapidRanking;

    public PlayerInfo() {}
}
