package com.rest;

import com.rest.model.PlayerInfo;
import com.rest.services.Requester;

public class RequestThread extends Thread {
    private final Requester requester;
    private final int playerNumber;
    private final String playerNick;

    public RequestThread(Requester requester, int playerNumber, String playerNick) {
        this.requester = requester;
        this.playerNumber = playerNumber;
        this.playerNick = playerNick;
    }

    @Override
    public void run() {
        PlayerInfo playerInfo = this.requester.getPlayerInfo(playerNick);
        if (playerNumber == 1) {
            StaticData.firstPlayerInfo = playerInfo;
        }
        else {
            StaticData.secondPlayerInfo = playerInfo;
        }
    }
}
