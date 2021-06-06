package com.library.management.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.library.management.dto.UserDto;
import com.library.management.exception.RegisterFailureException;
import com.library.management.exception.UpdateFailureException;
import com.library.management.exception.UserNotFoundException;
import com.library.management.exception.UserRoleNotFoundException;
import com.library.management.util.CommonUtil;
import com.library.management.util.Constants;

/**
 * Connection object to the database - user table
 * @author hsuwai
 *
 */
public class UserDBAO {

	Connection con;
    private boolean conFree = true;
    
    public UserDBAO() throws Exception {
        try {
            
            Class.forName(Constants.DRIVER_NAME);
            con = DriverManager.getConnection(Constants.URL, Constants.USERNAME, Constants.PASSWORD);
            
        } catch (Exception ex) {
            System.out.println("Exception in UserDBAO: " + ex);
            throw new Exception("Couldn't open connection to database: " +
                    ex.getMessage());
        }
    }
    
    public void remove() {
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    protected synchronized Connection getConnection() {

        while (conFree == false) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        
        conFree = false;
        notify();
        
        return con;
    }
    
    protected synchronized void releaseConnection() {
        while (conFree == true) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        
        conFree = true;
        notify();
    }
    
    /**
     * Find admin users for initialisation
     * @return
     */
    public List<UserDto>  getAdminUsers() {
    	List<UserDto> userlist = new ArrayList<UserDto>();
		
		try {
            getConnection();
			
			String selectStatement = "SELECT * FROM user WHERE user_role_id IN (SELECT id FROM user_role WHERE role_name = 'admin')";
			PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            
            while (rs.next()) {
                UserDto user = new UserDto();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setBirthDate(CommonUtil.convertDateString(rs.getTimestamp(4)));
                user.setGender(rs.getString(5));
                user.setCreatedDate(CommonUtil.convertDateTimeString(rs.getTimestamp(6)));
                user.setUpdatedDate(CommonUtil.convertDateTimeString(rs.getTimestamp(7)));
                userlist.add(user);
            }
            
            prepStmt.close();
        }catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			releaseConnection();
		}
		return userlist;
    }
    
	/**
	 * Find all users except admin user
	 * @return
	 * @throws UserNotFoundException 
	 */
	public List<UserDto> getAllUsers() throws UserNotFoundException {
		List<UserDto> userlist = new ArrayList<UserDto>();
		
		try {
			
            getConnection();

			String selectStatement = "SELECT * FROM user WHERE user_role_id IN (SELECT id FROM user_role WHERE role_name = 'member')";
			PreparedStatement prepStmt = con.prepareStatement(selectStatement);

            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                UserDto user = new UserDto();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setBirthDate(CommonUtil.convertDateString(rs.getTimestamp(4)));
                user.setGender(rs.getString(5));
                user.setCreatedDate(CommonUtil.convertDateTimeString(rs.getTimestamp(6)));
                user.setUpdatedDate(CommonUtil.convertDateTimeString(rs.getTimestamp(7)));
                userlist.add(user);
            }
            
            prepStmt.close();
        }catch (SQLException e) {
        	throw new UserNotFoundException("Something wrong while fetching users ");
		}finally {
			releaseConnection();
		}
		return userlist;
	}
	
	public UserDto findUserById(int userId) throws UserNotFoundException {
		UserDto user = null;

		try {
            getConnection();
			
			String selectStatement = "SELECT * FROM user WHERE id = ?";
			PreparedStatement prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setInt(1, userId);
            ResultSet rs = prepStmt.executeQuery();
            
            if (rs.next()) {
            	user = new UserDto();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setBirthDate(CommonUtil.convertDateString(rs.getTimestamp(4)));
                user.setGender(rs.getString(5));
                user.setCreatedDate(CommonUtil.convertDateTimeString(rs.getTimestamp(6)));
                user.setUpdatedDate(CommonUtil.convertDateTimeString(rs.getTimestamp(7)));
                prepStmt.close();
            }else {
            	prepStmt.close();
            	throw new UserNotFoundException("Not able to find user: " +
            			userId);
            }
 
        }catch (SQLException e) {
			throw new UserNotFoundException("Not able to find user: " +
        			userId);
		}finally {
			releaseConnection();
		}
		return user;
	}
	
	public UserDto findUserByUserNameAndPassword(String username, String password) throws UserNotFoundException {
		UserDto user = null;

		try {
            getConnection();
            
			String selectStatement = "SELECT * FROM user WHERE user_name = ? AND password = ?";
			PreparedStatement prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setString(1, username);
			prepStmt.setString(2, password);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
            	user = new UserDto();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setBirthDate(CommonUtil.convertDateString(rs.getTimestamp(4)));
                user.setGender(rs.getString(5));
                user.setCreatedDate(CommonUtil.convertDateTimeString(rs.getTimestamp(6)));
                user.setUpdatedDate(CommonUtil.convertDateTimeString(rs.getTimestamp(7)));
                user.setRole(Integer.parseInt(rs.getString("user_role_id")) == 2 ? "member": "admin");
                prepStmt.close();
            }else {
            	prepStmt.close();
            	throw new UserNotFoundException("Not able to find user with given username and password : " +
            			username);
            }
        }catch (SQLException e) {
        	throw new UserNotFoundException("Not able to find user with given name: " + username);
		}finally {
			releaseConnection();
		}
		return user;
	}
	
	public void saveUser(UserDto user) throws RegisterFailureException {
		int role_id = 0;
		try {
            getConnection();
			
            String selectStatement = "SELECT id FROM user_role WHERE role_name = 'member'";
			PreparedStatement prepSelectStmt = con.prepareStatement(selectStatement);
			ResultSet rs = prepSelectStmt.executeQuery();
            
            if (rs.next()) {
            	role_id = rs.getInt(1);
            }else {
            	throw new UserRoleNotFoundException("Not able to find valid user role" );
            }
            System.out.println("User Role ID : " + role_id);
            
            String insertStatement = "INSERT INTO user (user_name, password, birth_date, gender, user_role_id)"
                    + " VALUES (?, ?, ?, ?, ?)";
            
            if(user.getRole() != null && user.getRole().equals("admin")) {
            	role_id = user.getRole().equals("admin") ? 1: 2;
            }
            
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, user.getUsername());
            prepStmt.setString(2, user.getPassword());
            prepStmt.setTimestamp(3, CommonUtil.convertTimestamp(user.getBirthDate()));
            prepStmt.setString(4, user.getGender());
            prepStmt.setInt(5, role_id);
            
            prepStmt.execute();
            prepStmt.close();
            
            System.out.println("User saved successfully!");
            
        }catch (Exception e) {
			throw new RegisterFailureException("Not able to register new user : " + e.getMessage());
		}finally {
			releaseConnection();
		}
		
	}
	
	public void updateUser(UserDto user) throws UpdateFailureException {
		int role_id = 0;
		try {
            getConnection();
			
            String selectStatement = "SELECT id FROM user_role WHERE role_name = 'member'";
			PreparedStatement prepSelectStmt = con.prepareStatement(selectStatement);
			ResultSet rs = prepSelectStmt.executeQuery();
            
            if (rs.next()) {
            	role_id = rs.getInt(1);
            }else {
            	throw new UserRoleNotFoundException("Not able to find valid user role" );
            }
            System.out.println("User Role ID : " + role_id);
            
            String updateStatement = "UPDATE user SET user_name = ?, birth_date = ?, gender = ?, updated_date = ?"
                    + " WHERE id = ?";
            
            PreparedStatement prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1, user.getUsername());
            prepStmt.setTimestamp(2, CommonUtil.convertTimestamp(user.getBirthDate()));
            prepStmt.setString(3, user.getGender());
            prepStmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            prepStmt.setInt(5, user.getId());
            
            int updatedCount = prepStmt.executeUpdate();
            if(updatedCount == 0) {
            	throw new UpdateFailureException("Not able to update user information: " + user.getId());
            }else System.out.println("User updated successfully!");
            
            prepStmt.close();
        }catch (Exception e) {
        	throw new UpdateFailureException("Not able to update user information: " + user.getId());
		}finally {
			releaseConnection();
		}
	}
	
	public void deleteUser(int id) throws UpdateFailureException {
		try {
            getConnection();
			
            String deleteStatement = "DELETE FROM user WHERE id = ?";
            
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setInt(1, id);
            
            prepStmt.execute();
            prepStmt.close();
        }catch (Exception e) {
        	throw new UpdateFailureException("Not able to delete user " + id);
		}finally {
			releaseConnection();
		}
	}
	
}
