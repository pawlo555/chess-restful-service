package com.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Date;

public class ResourceSimple {
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(getBaseURI());
// Fluent interfaces
        System.out.println(target.path("rest").path("todosimple").request()
                .accept(MediaType.TEXT_XML).get(Response.class).toString());
// Get XML
        System.out.println(target.path("rest").path("todosimple").request()
                .accept(MediaType.TEXT_XML).get(String.class));
// Get XML for application
        System.out.println(target.path("rest").path("todosimple").request()
                .accept(MediaType.APPLICATION_XML).get(String.class));
// Get JSON for application
        System.out.println(target.path("rest").path("todosimple").request()
                .accept(MediaType.APPLICATION_JSON).get(String.class));
        long big = (long)1624304397 * 1000;
        System.out.println(new Date(big));
        System.out.println(new Date().getTime());
    }
    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/chess-restful-service").build();
    }

}
