package com.rest;

import com.rest.model.GameInfo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/result")
public class ChessResult {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String createHTML() {
        return "<html> <title> Results: </title>"
                + "<body>" +
                "<h1>" + "Results" + "</h1>" +
                errorsOccurred() +
                "<table>" +
                "<tr> <th></th>" +
                "<th scope=\"col\"> Lichess.com </th>" +
                "<th scope=\"col\"> Chess.com </th> </tr>" +
                getIdLine() +
                getUrl() +
                getBulletRanking() +
                getBlitzRanking() +
                getRapidRanking() +
                getNumberOfPlayedGames() +
                getCreationDate() + getLastOnline() +
                getOnlineStatus() +
                "</table>" +
                "<h2> Games: </h2> <br> <table>" +
                loadGames() +
                "</table>" +
                "<p>" +
                StaticData.firstPlayerInfo +
                "</p>" +
                StaticData.secondPlayerInfo +
                "</body>" +
                "</html> ";
    }

    private String errorsOccurred() {
        String errors = "";
        if (!StaticData.firstPlayerInfo.isCompleted()) {
            errors = errors + "An error occurred and data for user " +
                    StaticData.firstPlayerInfo.getNickname() + " may be incomplete.</br>";
        }
        if (!StaticData.secondPlayerInfo.isCompleted()) {
            errors = errors + "An error occurred and data for user " +
                    StaticData.secondPlayerInfo.getNickname() + " may be incomplete.</br>";
        }
        return errors;
    }

    private String getIdLine() {
        return "<tr>" +
                "<th scope=\"row\"> Nick: </th>" +
                "<td>" + StaticData.firstPlayerInfo.getNickname() + "</td>" +
                "<td>" + StaticData.secondPlayerInfo.getNickname() + "</td>" +
                "</tr>";
    }

    private String getUrl() {
        return "<tr>" +
                "<th scope=\"row\"> Link: </th>" +
                "<td>" + "<a href=\""+ StaticData.firstPlayerInfo.getUrl() + "\">click</a></td>" +
                "<td>" + "<a href=\""+ StaticData.secondPlayerInfo.getUrl() + "\">click</a></td>" +
                "</tr>";
    }

    private String getBulletRanking() {
        return "<tr>" +
                "<th scope=\"row\"> Bullet ranking: </th>" +
                "<td>" + StaticData.firstPlayerInfo.getBulletRanking() + "</td>" +
                "<td>" + StaticData.secondPlayerInfo.getBulletRanking() + "</td>" +
                "</tr>";
    }

    private String getRapidRanking() {
        return "<tr>" +
                "<th scope=\"row\"> Rapid ranking: </th>" +
                "<td>" + StaticData.firstPlayerInfo.getRapidRanking() + "</td>" +
                "<td>" + StaticData.secondPlayerInfo.getRapidRanking() + "</td>" +
                "</tr>";
    }

    private String getBlitzRanking() {
        return "<tr>" +
                "<th scope=\"row\"> Blitz ranking: </th>" +
                "<td>" + StaticData.firstPlayerInfo.getBlitzRanking() + "</td>" +
                "<td>" + StaticData.secondPlayerInfo.getBlitzRanking() + "</td>" +
                "</tr>";
    }

    private String getNumberOfPlayedGames() {
        return "<tr>" +
                "<th scope=\"row\"> Played games: </th>" +
                "<td>" + StaticData.firstPlayerInfo.getNumberOfPlayedGames() + "</td>" +
                "<td>" + StaticData.secondPlayerInfo.getNumberOfPlayedGames() + "</td>" +
                "</tr>";
    }

    private String getCreationDate() {
        return "<tr>" +
                "<th scope=\"row\"> Account creation time: </th>" +
                "<td>" + StaticData.firstPlayerInfo.getCreationDate() + "</td>" +
                "<td>" + StaticData.secondPlayerInfo.getCreationDate() + "</td>" +
                "</tr>";
    }

    private String getLastOnline() {
        return "<tr>" +
                "<th scope=\"row\"> Last online: </th>" +
                "<td>" + StaticData.firstPlayerInfo.getLastOnlineDate() + "</td>" +
                "<td>" + StaticData.secondPlayerInfo.getLastOnlineDate() + "</td>" +
                "</tr>";
    }

    private String getOnlineStatus() {
        return "<tr>" +
                "<th scope=\"row\"> Online status: </th>" +
                "<td>" + StaticData.firstPlayerInfo.isOnline() + "</td>" +
                "<td>" + StaticData.secondPlayerInfo.isOnline() + "</td>" +
                "</tr>";
    }

    private String loadGames() {
        StringBuilder builder = new StringBuilder();
        builder.append("<tr>" +
                "<th scope=\"col\"> White </th>" +
                "<th scope=\"col\"> Result </th>" +
                "<th scope=\"col\"> Black </th>" +
                "<th scope=\"col\"> Link </th>" +
                "</tr>");
        for (GameInfo gameInfo: StaticData.firstGamesInfo) {
            builder.append(loadGame(gameInfo));
        }
        for (GameInfo gameInfo: StaticData.secondGamesInfo) {
            builder.append(loadGame(gameInfo));
        }
        return builder.toString();
    }

    private String loadGame(GameInfo gameInfo) {
        return "<tr>" +
                "<td>" + gameInfo.getWhitePlayerNick() + "</td>" +
                "<td>" + gameInfo.getResult() + "</td>" +
                "<td>" + gameInfo.getBlackPlayerNick() + "</td>" +
                "<td> <a href=\"" + gameInfo.getGameURL() + "\">" +" link </a></td>" +
                "<tr>";
    }
}
