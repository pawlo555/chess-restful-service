package com.rest;

import com.rest.model.GameInfo;
import com.rest.model.PlayerInfo;

import java.util.ArrayList;
import java.util.List;

public class StaticData {
    public static PlayerInfo firstPlayerInfo = new PlayerInfo();
    public static PlayerInfo secondPlayerInfo = new PlayerInfo();
    public static List<GameInfo> firstGamesInfo = new ArrayList<>();
    public static List<GameInfo> secondGamesInfo = new ArrayList<>();

    public static boolean firstPlayerFinish = false;
    public static boolean secondPlayerFinish = false;
    public static boolean firstGamesFinish = false;
    public static boolean secondGamesFinish = false;
}
