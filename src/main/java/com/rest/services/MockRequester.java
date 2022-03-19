package com.rest.services;

import com.rest.model.GameInfo;
import com.rest.model.PlayerInfo;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<GameInfo> getGamesInfo(String playerNick, int numberOfGames) {
        ArrayList<GameInfo> games = new ArrayList<>();
        for(int i =0; i<numberOfGames; ++i) {
            games.add(getGameInfo());
        }
        return games;
    }

    private GameInfo getGameInfo() {
        GameInfo gameInfo = new GameInfo();
        gameInfo.setGameURL("https://lichess.org/4vsQmN35");
        gameInfo.setBlackPlayerNick("Piotrek");
        gameInfo.setWhitePlayerNick("Tomasz");
        gameInfo.setResult("1-0");
        return gameInfo;
    }
}
