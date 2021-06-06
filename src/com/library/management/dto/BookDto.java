package com.library.management.dto;

import java.io.Serializable;

/**
 * DTO class for Book Details
 * @author hsuwai
 *
 */
public class BookDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private String author;
	private String ISBN;
	private String publishedDate;
	private String createdDate;
	private String updatedDate;
	private String status;
	
	public BookDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookDto(String title, String author, String iSBN, String publishedDate, String createdDate,
			String updatedDate, String status) {
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

	public String getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", ISBN=" + ISBN + ", publishedDate="
				+ publishedDate + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", status=" + status
				+ "]";
	}
}
