package com.rest.model;

import java.util.Date;

public class PlayerInfo {
    private String nickname;
    private String url;
    private Date creationDate;
    private Date lastOnlineDate;
    private int numberOfPlayedGames;
    private int bulletRanking;
    private int blitzRanking;
    private int rapidRanking;
    private boolean isOnline;

    public PlayerInfo() {}

    public PlayerInfo(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "nickname='" + nickname + '\'' +
                ", url=" + url +
                ", creationDate=" + creationDate +
                ", lastOnlineDate=" + lastOnlineDate +
                ", numberOfPlayedGames=" + numberOfPlayedGames +
                ", bulletRanking=" + bulletRanking +
                ", blitzRanking=" + blitzRanking +
                ", rapidRanking=" + rapidRanking +
                ", isOnline=" + isOnline +
                '}';
    }

    public void setBlitzRanking(int blitzRanking) {
        this.blitzRanking = blitzRanking;
    }

    public void setBulletRanking(int bulletRanking) {
        this.bulletRanking = bulletRanking;
    }

    public void setRapidRanking(int rapidRanking) {
        this.rapidRanking = rapidRanking;
    }

    public void setCreationDate(long creationDateSeconds) {
        this.creationDate = new Date(creationDateSeconds);
    }

    public void setLastOnlineDate(long creationDateSeconds) {
        this.lastOnlineDate = new Date(creationDateSeconds);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setNumberOfPlayedGames(int numberOfPlayedGames) {
        this.numberOfPlayedGames = numberOfPlayedGames;
    }

    public void setOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLastOnlineDate() {
        return lastOnlineDate;
    }

    public int getBlitzRanking() {
        return blitzRanking;
    }

    public int getBulletRanking() {
        return bulletRanking;
    }

    public int getNumberOfPlayedGames() {
        return numberOfPlayedGames;
    }

    public int getRapidRanking() {
        return rapidRanking;
    }

    public String getNickname() {
        return nickname;
    }

    public String getUrl() {
        return url;
    }
}
