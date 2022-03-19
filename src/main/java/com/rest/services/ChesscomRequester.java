package com.rest.services;

import com.rest.model.GameInfo;
import com.rest.model.PlayerInfo;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class ChesscomRequester implements Requester {
    private final static int ONLINE_TIME = 300000;
    private final Client client = ClientBuilder.newClient();
    private final WebTarget target = client.target(getBaseURI());

    @Override
    public PlayerInfo getPlayerInfo(String playerNick) {
        PlayerInfo playerInfo = new PlayerInfo(playerNick);

        JSONObject generalJson = performGeneralRequest(playerNick);
        System.out.println(generalJson);
        updateGeneralProfile(generalJson, playerInfo);
        JSONObject statsJson = performStatsRequest(playerNick);
        System.out.println(statsJson);
        updateStatsProfile(statsJson, playerInfo);

        return playerInfo;
    }

    @Override
    public List<GameInfo> getGamesInfo(String playerNick, int numberOfGames) {
        List<GameInfo> gameInfos = new ArrayList<>();
        JSONArray games = performGamesRequest(playerNick);
        if (games.length() < numberOfGames) {
            System.out.println("To few games from chess.com");
        }
        for (int i=0; i<games.length(); i++) {
            gameInfos.add(getGameInfo(games.getJSONObject(i)));
        }
        return gameInfos;
    }

    public GameInfo getGameInfo(JSONObject gameJson) {
        GameInfo gameInfo = new GameInfo();
        gameInfo.setGameURL(gameJson.getString("url"));
        gameInfo.setWhitePlayerNick(gameJson.getJSONObject("white").getString("username"));
        gameInfo.setBlackPlayerNick(gameJson.getJSONObject("black").getString("username"));
        gameInfo.setResult(getResult(gameJson));
        return gameInfo;
    }

    private String getResult(JSONObject gameJson) {
        if (gameJson.getJSONObject("white").getString("result").equals("win")) {
            return "1 - 0";
        }
        else if (gameJson.getJSONObject("black").getString("result").equals("win")) {
            return "0 - 1";
        }
        else {
            return "0.5 - 0.5";
        }
    }

    private JSONArray performGamesRequest(String playerNick) {
        JSONObject reply = new JSONObject(target.path(playerNick)
                .path("games")
                .path("archives")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(String.class));
        JSONArray jsonArray = reply.getJSONArray("archives");
        String archivesPath = jsonArray.getString(jsonArray.length()-1);
        JSONObject gamesReply = new JSONObject(client.target(archivesPath)
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(String.class));
        return gamesReply.getJSONArray("games");
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
