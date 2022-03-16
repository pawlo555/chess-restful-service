package com.rest;

import com.rest.StaticData;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/result")
public class ChessResult {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String createHTML() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html> " + "<title>" + "Hello Jersey" + "</title>"
                + "<body><h1>" + "Hello Jersey" + "</body></h1>" + "<p>")
                .append(StaticData.firstPlayerInfo)
                .append("</p>")
                .append("</html> ");

        return stringBuilder.toString();
    }
}
