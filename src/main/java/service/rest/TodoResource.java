package service.rest;

import service.rest.model.Todo;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBElement;

public class TodoResource {
    @Context
    UriInfo uriInfo;

    @Context
    Request request;
    String id;

    public TodoResource(UriInfo uriInfo, Request request, String id) {
        this.id = id;
        this.request = request;
        this.uriInfo = uriInfo;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Todo getTodo() {
        return TodoDao.getModel().get(id);
    }

    @GET
    @Produces(MediaType.TEXT_XML)
    public Todo getTodoHTML() {
        return TodoDao.getModel().get(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response putTodo(JAXBElement<Todo> todo) {
        return putAndGetResponse(todo.getValue());
    }

    @DELETE
    public void deleteTodo() {
        TodoDao.getModel().remove(id);
    }

    private Response putAndGetResponse(Todo todo) {
        Response res;
        if (TodoDao.getModel().containsKey(todo.getId())) {
            res = Response.noContent().build();
        }
        else {
            res = Response.created(uriInfo.getAbsolutePath()).build();
        }
        TodoDao.getModel().put(todo.getId(), todo);
        return res;
    }
}
