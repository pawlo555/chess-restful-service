package com.rest.services;

import com.rest.model.PlayerInfo;
import org.json.JSONObject;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class ChesscomRequester implements Requester {
    private final static int ONLINE_TIME = 300000;
    private final Client client = ClientBuilder.newClient();
    private final WebTarget target = client.target(getBaseURI());

    @Override
    public PlayerInfo getPlayerInfo(String playerNick) {
        PlayerInfo playerInfo = new PlayerInfo(playerNick);
        try {
            JSONObject generalJson = performGeneralRequest(playerNick);
            System.out.println(generalJson);
            updateGeneralProfile(generalJson, playerInfo);
            JSONObject statsJson = performStatsRequest(playerNick);
            System.out.println(statsJson);
            updateStatsProfile(statsJson, playerInfo);
            playerInfo.setCompleted();
        }
        catch(Exception e) {
            System.out.println("An error occurred");
            System.out.println(e.getMessage());
        }
        return playerInfo;
    }

    private JSONObject performGeneralRequest(String playerNick) {
        return new JSONObject(target.path(playerNick)
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(String.class));
    }

    private void updateGeneralProfile(JSONObject json, PlayerInfo playerInfo) {
        playerInfo.setUrl(json.getString("url"));
        playerInfo.setCreationDate(json.getLong("joined") * 1000);
        long lastOnline = json.getLong("last_online") * 1000;
        playerInfo.setLastOnlineDate(lastOnline);
        playerInfo.setOnline(System.currentTimeMillis() - lastOnline < ONLINE_TIME);
    }

    private JSONObject performStatsRequest(String playerNick) {
        return new JSONObject(target.path(playerNick).path("stats")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(String.class));
    }

    private void updateStatsProfile(JSONObject statsJson, PlayerInfo playerInfo) {
        JSONObject rapidChess = statsJson.getJSONObject("chess_rapid");
        playerInfo.setRapidRanking(rapidChess.getJSONObject("last").getInt("rating"));
        int rapidGames = collectGames(rapidChess);

        JSONObject blitzChess = statsJson.getJSONObject("chess_blitz");
        playerInfo.setBlitzRanking(blitzChess.getJSONObject("last").getInt("rating"));
        int blitzGames = collectGames(blitzChess);

        JSONObject bulletChess = statsJson.getJSONObject("chess_blitz");
        playerInfo.setBulletRanking(bulletChess.getJSONObject("last").getInt("rating"));
        int bulletGames = collectGames(bulletChess);

        playerInfo.setNumberOfPlayedGames(rapidGames + blitzGames + bulletGames);
    }


    private int collectGames(JSONObject games) {
        JSONObject record = games.getJSONObject("record");
        return record.getInt("win") + record.getInt("loss") + record.getInt("draw");
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("https://api.chess.com/pub/player").build();
    }
}
