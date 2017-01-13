package com.pfinal.client;


import java.util.List;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.pfinal.model.Book;

public class BookClient {

	private Client client;
	
	public BookClient() {
		client = ClientBuilder.newClient();
		
	}
	
	public void delete(String bookId) {
		WebTarget target = client.target("http://localhost:8080/proiect-final/webapi/");
		Response response = target.path("books/" + bookId)
							.request(MediaType.APPLICATION_JSON).delete();
		
		if(response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + ": eroare la server");
			
		}
	}
	
	public Book get(String id) {
		
		WebTarget target = client.target("http://localhost:8080/proiect-final/webapi/");
		
		Response response = target.path("books/"+ id).request(MediaType.APPLICATION_JSON).get(Response.class);
		
		if(response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + ": eroare la server");
			
		}
		return response.readEntity(Book.class);
		
	}
	
	public List<Book> get() {
		WebTarget target = client.target("http://localhost:8080/proiect-final/webapi/");
		
		List<Book> response = target.path("books").request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Book>> () {});
		
		return response;
		
	}

	public Book create(Book book) {
		WebTarget target = client.target("http://localhost:8080/proiect-final/webapi/");
	
Response response = target.path("books/book")
			.request(MediaType.APPLICATION_JSON)
			.post(Entity.entity(book, MediaType.APPLICATION_JSON));
					
		if(response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + ": eroare la server");
			
		}
	
		return response.readEntity(Book.class);
	}

	public Book update(Book book) {

		WebTarget target = client.target("http://localhost:8080/proiect-final/webapi/");
		Response response = target.path("books/" + book.getId())
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(book, MediaType.APPLICATION_JSON));
				
		if(response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + ": eroare la server");
			
		}
		return response.readEntity(Book.class);
	}

	
	
}
