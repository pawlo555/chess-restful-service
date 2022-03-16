package com.rest.services;

import com.rest.model.PlayerInfo;

public class MockRequester implements Requester {

    @Override
    public PlayerInfo getPlayerInfo() {
        PlayerInfo playerInfo = new PlayerInfo();
        playerInfo.setBlitzRanking(1400);
        playerInfo.setBulletRanking(1500);
        playerInfo.setRapidRanking(1600);
        playerInfo.setUrl("https://lichess.org/@/pawlo555)");
        playerInfo.setNumberOfPlayedGames(4000);
        playerInfo.setCreationDate(1523304606613L);
        playerInfo.setLastOnlineDate(1523404606613L);
        playerInfo.setNickname("pawlo555");
        playerInfo.setOnline(false);
        return playerInfo;
    }
}
