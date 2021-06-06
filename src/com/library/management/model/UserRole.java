package com.library.management.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DB Model Class for user_role table
 * user_roles will be inserted by initialization script - role_create.sql
 * @author hsuwai
 *
 */
public class UserRole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String roleName;
	private String description;
	private Timestamp createdDate;
	private Timestamp updatedDate;
	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserRole(String roleName, String description, Timestamp createdDate, Timestamp updatedDate) {
		super();
		this.roleName = roleName;
		this.description = description;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Override
	public String toString() {
		return "UserRole [id=" + id + ", roleName=" + roleName + ", description=" + description + ", createdDate="
				+ createdDate + ", updatedDate=" + updatedDate + "]";
	}

}
