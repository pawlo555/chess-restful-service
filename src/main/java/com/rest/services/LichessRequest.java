package com.rest.services;

import com.rest.model.GameInfo;
import com.rest.model.PlayerInfo;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class LichessRequest implements Requester {
    private final Client client = ClientBuilder.newClient();
    private final WebTarget target = client.target(getBaseURI());

    @Override
    public PlayerInfo getPlayerInfo(String playerNick) {
        PlayerInfo playerInfo = new PlayerInfo(playerNick);
        try {
            JSONObject json = performRequest(playerNick);
            updateUserProfile(json, playerInfo);
            playerInfo.setCompleted();
        }
        catch(Exception e) {
            System.out.println("An error occurred");
            System.out.println(e.getMessage());
        }
        return playerInfo;
    }

    @Override
    public List<GameInfo> getGamesInfo(String playerNick, int numberOfGames) {
        List<GameInfo> gameInfos = new ArrayList<>();
        String[] gamesAsString = performGamesRequest(playerNick, numberOfGames);
        for (String gameAsString: gamesAsString) {
            gameInfos.add(getGameInfoFromString(gameAsString));
        }
        return gameInfos;
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("https://lichess.org/api").build();
    }

    private String[] performGamesRequest(String playerNick, int numberOfGames) {
        return target.path("games").path("user").path(playerNick).queryParam("max", numberOfGames).request()
                .accept("application/x-ndjson").get(String.class).split("\n");
    }

    private GameInfo getGameInfoFromString(String gameInfoString) {
        JSONObject gameJson = new JSONObject(gameInfoString);
        GameInfo gameInfo = new GameInfo();
        gameInfo.setGameURL("https://lichess.org/" + gameJson.getString("id"));
        JSONObject playersInfo = gameJson.getJSONObject("players");
        gameInfo.setWhitePlayerNick(playersInfo.getJSONObject("white").getJSONObject("user").getString("name"));
        gameInfo.setBlackPlayerNick(playersInfo.getJSONObject("black").getJSONObject("user").getString("name"));
        gameInfo.setResult(findGameResult(gameJson));
        return gameInfo;
    }

    private String findGameResult(JSONObject gameJson) {
        if (gameJson.getString("status").equals("draw")) {
            return "0.5 - 0.5";
        }
        try {
            String winner = gameJson.getString("winner");
            if (winner.equals("white")) {
                return "  1 - 0  ";
            }
            else {
                return "  0 - 1  ";
            }
        }
        catch(Exception ignore) {
            return "  0 - 0  ";
        }
    }

    private JSONObject performRequest(String playerNick) {
        return new JSONObject(target.path("user").path(playerNick)
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(String.class));
    }

    private void updateUserProfile(JSONObject json, PlayerInfo playerInfo) {
        playerInfo.setUrl(json.getString("url"));
        playerInfo.setCreationDate(json.getLong("createdAt"));
        playerInfo.setLastOnlineDate(json.getLong("seenAt"));
        playerInfo.setOnline(json.getBoolean("online"));
        playerInfo.setNumberOfPlayedGames(json.getJSONObject("count").getInt("all"));
        JSONObject perfs = json.getJSONObject("perfs");
        playerInfo.setBulletRanking(perfs.getJSONObject("bullet").getInt("rating"));
        playerInfo.setBlitzRanking(perfs.getJSONObject("blitz").getInt("rating"));
        playerInfo.setRapidRanking(perfs.getJSONObject("rapid").getInt("rating"));
    }
}


