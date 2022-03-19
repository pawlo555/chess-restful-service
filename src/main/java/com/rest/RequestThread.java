package com.rest;

import com.rest.model.GameInfo;
import com.rest.model.PlayerInfo;
import com.rest.services.Requester;

import java.util.List;

public class RequestThread extends Thread {
    private final Requester requester;
    private final int playerNumber;
    private final String playerNick;
    private final int gamesNumber;

    public RequestThread(Requester requester, int playerNumber, String playerNick, String gamesNumber) {
        this.requester = requester;
        this.playerNumber = playerNumber;
        this.playerNick = playerNick;
        this.gamesNumber = Integer.parseInt(gamesNumber);
    }

    @Override
    public void run() {
        try {
            PlayerInfo playerInfo = this.requester.getPlayerInfo(playerNick);
            if (playerNumber == 1) {
                StaticData.firstPlayerInfo = playerInfo;
                StaticData.firstPlayerFinish = true;
            } else {
                StaticData.secondPlayerInfo = playerInfo;
                StaticData.secondPlayerFinish = true;
            }
            List<GameInfo> gamesInfo = this.requester.getGamesInfo(playerNick, gamesNumber);
            if (playerNumber == 1) {
                StaticData.firstGamesInfo = gamesInfo;
                StaticData.firstGamesFinish = true;
            } else {
                StaticData.secondGamesInfo = gamesInfo;
                StaticData.secondGamesFinish = true;
            }
        }
        catch (Exception ignored) {}
    }
}
