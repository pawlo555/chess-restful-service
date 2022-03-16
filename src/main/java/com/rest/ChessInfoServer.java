package com.rest;

import com.rest.services.MockRequester;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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



    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void processRequest(@Context HttpServletResponse servletResponse) throws IOException, InterruptedException {
        MockRequester firstRequester = new MockRequester();
        MockRequester secondRequester = new MockRequester();
        RequestThread firstThread = new RequestThread(firstRequester, 1);
        RequestThread secondThread = new RequestThread(secondRequester, 2);
        firstThread.start();
        secondThread.start();
        firstThread.join();
        secondThread.join();
        servletResponse.sendRedirect("../rest/result");
    }
}
