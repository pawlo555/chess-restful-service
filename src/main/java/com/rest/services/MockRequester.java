package com.rest.services;

import com.rest.model.PlayerInfo;

public class MockRequester implements Requester {

    @Override
    public PlayerInfo getPlayerInfo(String playerNick) {
        PlayerInfo playerInfo = new PlayerInfo(playerNick);
        playerInfo.setBlitzRanking(1400);
        playerInfo.setBulletRanking(1500);
        playerInfo.setRapidRanking(1600);
        playerInfo.setUrl("https://lichess.org/@/"+playerNick);
        playerInfo.setNumberOfPlayedGames(4000);
        playerInfo.setCreationDate(1523304606613L);
        playerInfo.setLastOnlineDate(1523404606613L);

        playerInfo.setOnline(false);
        return playerInfo;
    }
}
