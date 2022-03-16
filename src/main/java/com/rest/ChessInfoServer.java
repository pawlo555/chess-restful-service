package com.rest;

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
    public void processRequest(@Context HttpServletResponse servletResponse) throws IOException {
        System.out.println("Jestem w metodzie");
        servletResponse.sendRedirect("../rest/result");
    }
}
