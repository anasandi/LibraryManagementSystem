package com.library.management.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DB Model class for user table
 * @author hsuwai
 *
 */
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String password;
	private Timestamp birthDate;
	private String gender;
	private Timestamp createdDate;
	private Timestamp updatedDate;
	private int userRoleId;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, Timestamp birthDate, String gender, Timestamp createdDate, Timestamp updatedDate,
			int userRoleId) {
		super();
		this.username = username;
		this.birthDate = birthDate;
		this.gender = gender;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.userRoleId = userRoleId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Timestamp getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Timestamp birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", birthDate=" + birthDate + ", gender=" + gender
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", userRoleId=" + userRoleId + "]";
	}

}
