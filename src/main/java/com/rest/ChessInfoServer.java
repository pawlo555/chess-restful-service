package com.rest;

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
            @Context HttpServletResponse servletResponse) throws IOException, InterruptedException {
        Requester firstRequester = new LichessRequest();
        Requester secondRequester = new MockRequester();
        RequestThread firstThread = new RequestThread(firstRequester, 1, firstId);
        RequestThread secondThread = new RequestThread(secondRequester, 2, secondId);
        firstThread.start();
        secondThread.start();
        firstThread.join(TIMEOUT_MILLIS);
        secondThread.join(1);
        servletResponse.sendRedirect("../rest/result");

    }
}
