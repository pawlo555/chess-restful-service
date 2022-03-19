package com.rest;

import com.rest.services.ChesscomRequester;
import com.rest.services.LichessRequest;
import com.rest.services.MockRequester;
import com.rest.services.Requester;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;

@Path("/chess_request")
public class ChessInfoServer {
    @Context
    UriInfo uriInfo;

    @Context
    Request request;

    private final static int TIMEOUT_MILLIS = 2000;

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void processRequest(
            @FormParam("firstId") String firstId,
            @FormParam("secondId") String secondId,
            @FormParam("firstGames") String firstGames,
            @FormParam("secondGames") String secondGames,
            @Context HttpServletResponse servletResponse) throws IOException, InterruptedException {
        Requester firstRequester = new LichessRequest();
        Requester secondRequester = new MockRequester();
        System.out.println("Games");
        System.out.println(firstGames);
        System.out.println(secondGames);
        RequestThread firstThread = new RequestThread(firstRequester, 1, firstId, firstGames);
        RequestThread secondThread = new RequestThread(secondRequester, 2, secondId, secondGames);
        firstThread.start();
        secondThread.start();
        long currentTime = System.currentTimeMillis();
        firstThread.join(TIMEOUT_MILLIS);
        long afterFirstJoin = System.currentTimeMillis();
        long toWait = TIMEOUT_MILLIS + currentTime - afterFirstJoin;
        if (toWait <= 0) {
            toWait = 1;
        }
        secondThread.join(toWait);
        servletResponse.sendRedirect("../rest/result");

    }
}
