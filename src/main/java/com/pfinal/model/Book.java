package com.pfinal.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Book {

	private String id;
	private String title;
	private int pages;
	private String author;
	private String editura;
	
	public String getEditura() {
		return editura;
	}
	public void setEditura(String editura) {
		this.editura = editura;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	

	
	
	
}
