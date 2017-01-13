package com.pfinal.client;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.pfinal.model.Book;

public class BookClientTest {
	
	
	@Test
	public void testDelete() {
		BookClient client = new BookClient();
		client.delete("1234");
		
	}
	
	
	@Test 
	public void testPut() {
		Book book = new Book();
		book.setId("3456");
		book.setTitle("Ion");
		book.setPages(300);
		
		BookClient client = new BookClient();
		
		book = client.update(book);
		
		assertNotNull(book);
		
		
	}
	@Test
	public void testCreate() {
		BookClient client = new BookClient();
		
		Book book = new Book();
		book.setTitle("Enigma Otiliei");
		book.setPages(500);
		
		book = client.create(book);
		assertNotNull(book);
	}

	@Test
	public void testGet() {
		BookClient client = new BookClient();
		Book book = client.get("1234");
		
		System.out.println(book);
		
		assertNotNull(book);
		
				
		
	}
	
	@Test
	public void testGetList() {
		BookClient client = new BookClient();
		
		List<Book> books = client.get();
		
		System.out.println(books);
		
		assertNotNull(books);
	}
	
	@Test(expected=RuntimeException.class)
	public void testGetWithBadRequest() {
		BookClient client = new BookClient();
		
		client.get("123");
	}
	
	@Test(expected=RuntimeException.class)
	public void testGetWithNotFound() {
		BookClient client = new BookClient();
		
		client.get("7777");
	}


}
