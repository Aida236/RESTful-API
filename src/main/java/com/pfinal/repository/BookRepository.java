package com.pfinal.repository;

import java.util.List;

import com.pfinal.model.Book;

public interface BookRepository {

	List<Book> findAllBooks();

	Book findBook(String bookId);

	void create(Book book);

	Book update(Book book, String bookId);

	void delete(String bookId);


}

