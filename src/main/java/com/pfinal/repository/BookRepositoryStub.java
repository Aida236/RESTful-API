package com.pfinal.repository;
	
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.pfinal.model.Book;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;



/* C:\mongodb\bin>mongod.exe --dbpath "c:\mongodb\data" 
C:\mongodb\bin>mongo.exe

*/

	public class BookRepositoryStub implements BookRepository {

		MongoClient mongo;
		
		@Override
		public void create(Book Book) {
			
			try {
				mongo = new MongoClient("localhost" , 27017);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			DB db = mongo.getDB("proiect");
			DBCollection table = db.getCollection("carti");
			
			BasicDBObject document = new BasicDBObject();
			document.put("id", Book.getId());
			document.put("author", Book.getAuthor());
			document.put("title", Book.getTitle());
			document.put("pages", Book.getPages());
			document.put("editura", Book.getEditura());
		
			table.insert(document);
		
		}
		
		@Override
		public void delete(String BookId) {
		
			try {
				mongo = new MongoClient("localhost" , 27017);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}

			DB db = mongo.getDB("proiect");
			DBCollection table = db.getCollection("carti");

			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("id", BookId);

			table.remove(searchQuery);
		}
		
		@Override
		public Book update(Book Book, String BookId) {

			try {
				mongo = new MongoClient("localhost" , 27017);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}

			DB db = mongo.getDB("proiect");
			DBCollection table = db.getCollection("carti");
			
			
			BasicDBObject query = new BasicDBObject();
			query.put("id", BookId);

			BasicDBObject newDocument = new BasicDBObject();
			
			if(Book.getPages() != 0)
				newDocument.put("pages", Book.getPages());
			if(Book.getId() != null)
				newDocument.put("id", Book.getId());
			if(Book.getTitle() != null)
				newDocument.put("title", Book.getTitle());
			if(Book.getAuthor() != null)
				newDocument.put("author", Book.getAuthor());
			if(Book.getEditura() != null)
				newDocument.put("editura", Book.getEditura());
			
						
			BasicDBObject updateObj = new BasicDBObject();
			updateObj.put("$set", newDocument);

			table.update(query, updateObj);
					
			return Book;
		}
		
		public List<Book> findAllBooks () {
			
			try {
				mongo = new MongoClient("localhost" , 27017);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}

			DB db = mongo.getDB("proiect");
			DBCollection table = db.getCollection("carti");
				
			List<Book> activities = new ArrayList<Book>();
			
			DBCursor cursor = table.find();
			while(cursor.hasNext()){
				DBObject obj = cursor.next();
				Book Book = new Book();
			
				Book.setId( (String) obj.get("id"));
				Book.setTitle((String) obj.get("title"));
				Book.setPages( (Integer) obj.get("pages"));
				Book.setAuthor((String) obj.get("author"));
				Book.setEditura((String) obj.get("editura")) ;
				
				activities.add(Book);
				Book = null;
			}

			return activities;
			
			
		}
		
		@Override
		public Book findBook(String BookId) {
			
			try {
				mongo = new MongoClient("localhost" , 27017);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}

			DB db = mongo.getDB("proiect");
			DBCollection table = db.getCollection("carti");
			
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("id" , BookId);
			
			DBCursor cursor = table.find(searchQuery);
			
			DBObject obj = cursor.next();		
			Book Book = new Book();	
			Book.setId( (String) obj.get("id"));
			Book.setTitle((String) obj.get("title"));
			Book.setPages( (Integer) obj.get("pages"));
			Book.setAuthor((String) obj.get("author"));
			Book.setEditura((String) obj.get("editura"));
			
			return Book;
			
			
		}

	
	
		
	}
