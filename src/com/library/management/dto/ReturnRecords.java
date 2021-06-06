package com.library.management.dto;

import java.io.Serializable;

/**
 * DB Model class for return_records table
 * @author Sandi
 *
 */
public class ReturnRecords implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int bookId;
	private int userId;
	private String returnedDate;
	private String title;
	private String username;
	
	public ReturnRecords() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReturnRecords(int bookId, int userId, String borrowedDate) {
		super();
		this.bookId = bookId;
		this.userId = userId;
		this.returnedDate = borrowedDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(String borrowedDate) {
		this.returnedDate = borrowedDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "ReturnRecords [id=" + id + ", bookId=" + bookId + ", userId=" + userId + ", returnedDate="
				+ returnedDate + "]";
	}

}
