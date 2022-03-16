package com.rest.services;

import com.rest.model.GameInfo;
import com.rest.model.PlayerInfo;

import java.util.List;

public interface Requester {

    PlayerInfo getPlayerInfo();
    //List<GameInfo> getGamesInfo();
}
