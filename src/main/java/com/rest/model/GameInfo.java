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
        StringBuilder builder = new StringBuilder();
        builder.append("White: ")
                .append(whitePlayerNick)
                .append(" ")
                .append(result)
                .append(" ")
                .append("Black: ")
                .append(blackPlayerNick)
                .append(" ")
                .append(gameURL);
        return builder.toString();
    }

}
