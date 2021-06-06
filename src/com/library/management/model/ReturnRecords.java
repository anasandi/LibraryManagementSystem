package com.library.management.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DB Model class for return_records table
 * @author hsuwai
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
	private Timestamp returnedDate;
	
	public ReturnRecords() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReturnRecords(int bookId, int userId, Timestamp borrowedDate) {
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

	public Timestamp getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(Timestamp borrowedDate) {
		this.returnedDate = borrowedDate;
	}

	@Override
	public String toString() {
		return "ReturnRecords [id=" + id + ", bookId=" + bookId + ", userId=" + userId + ", returnedDate="
				+ returnedDate + "]";
	}

}
