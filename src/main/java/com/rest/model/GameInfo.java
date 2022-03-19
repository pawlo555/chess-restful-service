package com.rest.model;

import java.net.URL;


public class GameInfo {
    private String whitePlayerNick;
    private String blackPlayerNick;
    private String result;
    private String gameURL;

    public GameInfo() {}

    @Override
    public String toString() {
        return "White: " +
                whitePlayerNick +
                " " +
                result +
                " " +
                "Black: " +
                blackPlayerNick +
                " " +
                gameURL;
    }

    public String getBlackPlayerNick() {
        return blackPlayerNick;
    }

    public String getGameURL() {
        return gameURL;
    }

    public String getResult() {
        return result;
    }

    public String getWhitePlayerNick() {
        return whitePlayerNick;
    }

    public void setBlackPlayerNick(String blackPlayerNick) {
        this.blackPlayerNick = blackPlayerNick;
    }

    public void setGameURL(String gameURL) {
        this.gameURL = gameURL;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setWhitePlayerNick(String whitePlayerNick) {
        this.whitePlayerNick = whitePlayerNick;
    }
}
