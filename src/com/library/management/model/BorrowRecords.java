package com.library.management.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DB Class for borrow_records table
 * @author hsuwai
 *
 */
public class BorrowRecords implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int bookId;
	private int userId;
	private int status;
	private Timestamp borrowedDate;
	
	public BorrowRecords() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BorrowRecords(int bookId, int userId, Timestamp borrowedDate) {
		super();
		this.bookId = bookId;
		this.userId = userId;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Timestamp getBorrowedDate() {
		return borrowedDate;
	}

	public void setBorrowedDate(Timestamp borrowedDate) {
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
		return "BorrowRecords [id=" + id + ", bookId=" + bookId + ", userId=" + userId + ", borrowedDate="
				+ borrowedDate + "]";
	}

}
