package com.library.management.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DB Model Class for book table
 * @author hsuwai
 *
 */
public class Book implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private String author;
	private String ISBN;
	private Timestamp publishedDate;
	private Timestamp createdDate;
	private Timestamp updatedDate;
	private BookStatus status;
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(String title, String author, String iSBN, Timestamp publishedDate, Timestamp createdDate,
			Timestamp updatedDate, BookStatus status) {
		super();
		this.title = title;
		this.author = author;
		ISBN = iSBN;
		this.publishedDate = publishedDate;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public Timestamp getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Timestamp publishedDate) {
		this.publishedDate = publishedDate;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public BookStatus getStatus() {
		return status;
	}

	public void setStatus(BookStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", ISBN=" + ISBN + ", publishedDate="
				+ publishedDate + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", status=" + status
				+ "]";
	}
}
