package com.rest;

import com.rest.model.GameInfo;
import com.rest.model.PlayerInfo;

import java.util.ArrayList;
import java.util.List;

public class StaticData {
    public static PlayerInfo firstPlayerInfo = new PlayerInfo();
    public static PlayerInfo secondPlayerInfo = new PlayerInfo();
    public static List<GameInfo> gamesInfo = new ArrayList<>();
}
