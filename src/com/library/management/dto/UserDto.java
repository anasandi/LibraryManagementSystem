package com.library.management.dto;

import java.io.Serializable;

/**
 * DTO class for User Details
 * @author hsuwai
 *
 */
public class UserDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String password;
	private String birthDate;
	private String gender;
	private String createdDate;
	private String updatedDate;
	private String role;
	
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDto(String username, String birthDate, String gender, String createdDate, String updatedDate) {
		super();
		this.username = username;
		this.birthDate = birthDate;
		this.gender = gender;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
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

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", username=" + username + ", birthDate=" + birthDate + ", gender=" + gender
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}
}
