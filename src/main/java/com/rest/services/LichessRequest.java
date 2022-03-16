package com.rest.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.rest.model.PlayerInfo;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import org.json.JSONObject;

public class LichessRequest implements Requester {
    private final Client client = ClientBuilder.newClient();
    private final WebTarget target = client.target(getBaseURI());

    @Override
    public PlayerInfo getPlayerInfo(String playerNick) {
        PlayerInfo playerInfo = new PlayerInfo(playerNick);
        JSONObject json = new JSONObject(target.path("user").path(playerNick).request()
                .accept(MediaType.APPLICATION_JSON).get(String.class));
        playerInfo.setUrl(json.getString("url"));
        playerInfo.setCreationDate(json.getLong("createdAt"));
        playerInfo.setLastOnlineDate(json.getLong("seenAt"));
        return playerInfo;
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("https://lichess.org/api").build();
    }
}
