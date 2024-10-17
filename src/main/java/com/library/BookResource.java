package com.library;

import jakarta.inject.Inject;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class BookResource {
	@Inject
    BookDAO bookDAO;
	
	@GET
    public List<Book> list() {
        return bookDAO.listAll();
    }
	
	@GET
    @Path("/{id}")
    public Book get(@PathParam("id") Long id) {
        return bookDAO.findById(id);
    }
	
	
	@POST
    @Transactional
    public Response create(Book book) {
        bookDAO.persist(book);
        return Response.status(Response.Status.CREATED).entity(book).build();
    }
	
	@PUT
    @Path("/{id}")
    @Transactional
    public Book update(@PathParam("id") Long id, Book book) {
        book.setId(id);
        return bookDAO.update(book);
    }
	
	
	@DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        Book book = bookDAO.findById(id);
        if (book != null) {
            bookDAO.delete(book);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
	
	

}
