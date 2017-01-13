package com.pfinal;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.pfinal.model.Book;
import com.pfinal.repository.BookRepository;
import com.pfinal.repository.BookRepositoryStub;

@Path("books") //http:localhost:8080/proiect-final/webapi/books

public class BookResource {
	
	private BookRepository bookRepository = new BookRepositoryStub();
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Book> getAllBooks() {
		return bookRepository.findAllBooks();
		
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("{bookId}") //http:localhost:8080/proiect-final/webapi/books/1234
	public Response getbook(@PathParam ("bookId") String bookId)  {
		if(bookId == null || bookId.length() < 4) {
			return Response.status(Status.BAD_REQUEST).build();
		}
			
		
	Book book = bookRepository.findBook(bookId);
		if(book == null ){
			return Response.status(Status.NOT_FOUND).build();
	
		}
		return Response.ok().entity(book).build();
	}
	
	

	
	@POST
	@Path("book")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Book createbook(Book book) {
		
		System.out.println(book.getTitle());
		System.out.println(book.getPages());
		
		 bookRepository.create(book);
		return book;
		
	}
	
	
	
	@POST
	@Path("book")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Book createbookParams(MultivaluedMap<String, String> formParams) {
		
		System.out.println(formParams.getFirst("title"));
		System.out.println(formParams.getFirst("pages"));
		Book book = new Book();
		book.setTitle(formParams.getFirst("title"));
		book.setPages(Integer.parseInt(formParams.getFirst("pages")));
		
		bookRepository.create(book);
		return book;
	}
	

	@PUT
	@Path("{bookId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response update(Book book, @PathParam("bookId") String bookId) {
		
		System.out.println(book.getId());
		book = bookRepository.update(book, bookId);
		return Response.ok().entity(book).build();
		
	}
	
	
	@DELETE
	@Path("{bookId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response delete(@PathParam ("bookId") String bookId) {
		System.out.println(bookId);

		bookRepository.delete(bookId);
		
		return Response.ok().build();
	}
	
	
}
