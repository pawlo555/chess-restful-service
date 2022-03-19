package com.rest;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(UriBuilder.fromUri("https://lichess.org/api/games/user/pawlo555")
                .queryParam("max", 2).build().toString());
        String[] reply = target.request()
                .accept("application/x-ndjson")
                .get(String.class).split("\n");
        System.out.println(Arrays.toString(reply));
        JSONObject game = new JSONObject(reply[0]);
        System.out.println(game);
    }
}
