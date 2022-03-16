package com.rest;

import com.rest.model.PlayerInfo;
import com.rest.services.Requester;

public class RequestThread extends Thread {
    private final Requester requester;
    private final int playerNumber;

    public RequestThread(Requester requester, int playerNumber) {
        this.requester = requester;
        this.playerNumber = playerNumber;
    }

    @Override
    public void run() {
        PlayerInfo playerInfo = this.requester.getPlayerInfo();
        if (playerNumber == 1) {
            StaticData.firstPlayerInfo = playerInfo;
        }
        else {
            StaticData.secondPlayerInfo = playerInfo;
        }
    }
}
