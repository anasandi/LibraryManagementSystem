package com.library.management.dto;

import java.io.Serializable;

/**
 * DB Class for borrow_records table
 * @author Sandi
 *
 */
public class BorrowRecords implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int bookId;
	private String title;
	private int userId;
	private String username;
	private String borrowedDate;
	private int status;
	
	public BorrowRecords() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public BorrowRecords(int bookId, String title, int userId, String username, String borrowedDate) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.userId = userId;
		this.username = username;
		this.borrowedDate = borrowedDate;
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

	
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getBorrowedDate() {
		return borrowedDate;
	}

	public void setBorrowedDate(String borrowedDate) {
		this.borrowedDate = borrowedDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "BorrowRecords [id=" + id + ", bookId=" + bookId + ", title=" + title + ", userId=" + userId
				+ ", username=" + username + ", borrowedDate=" + borrowedDate + ", status=" + status + "]";
	}
}
