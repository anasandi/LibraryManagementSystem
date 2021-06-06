package com.library.management.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.management.dto.ReturnRecords;
import com.library.management.exception.BooksNotFoundException;
import com.library.management.exception.PersistenceFailureException;
import com.library.management.util.CommonUtil;
import com.library.management.util.Constants;

public class ReturnDBAO {
	Connection con;
    private boolean conFree = true;
    
    public ReturnDBAO() throws Exception {
        try {
            
            Class.forName(Constants.DRIVER_NAME);
            con = DriverManager.getConnection(Constants.URL, Constants.USERNAME, Constants.PASSWORD);
            
        } catch (Exception ex) {
            System.out.println("Exception in ReturnDBAO: " + ex);
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
     * Save record into DB
     * @param returnrecords
     * @throws PersistenceFailureException
     */
    public void saveReturnRecords(ReturnRecords returnrecords) throws PersistenceFailureException {
		try {
            getConnection();
			
            String insertStatement = "INSERT INTO return_records (book_id, user_id, returned_date)"
                    + " VALUES (?, ?, ?)";
            
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setInt(1, returnrecords.getBookId());
            prepStmt.setInt(2, returnrecords.getUserId());
            prepStmt.setTimestamp(3, CommonUtil.convertTimestamp(returnrecords.getReturnedDate()));
 
            
            prepStmt.execute();
            prepStmt.close();
        } catch (Exception e) {
			throw new PersistenceFailureException("Not able to save return_record : " + returnrecords.getBookId());
		}finally {
			releaseConnection();
		}
	}
    
    /**
     * Get all return records
     * @return
     * @throws BooksNotFoundException
     */
    public List<ReturnRecords> getAllReturnRecords() throws BooksNotFoundException {
		List<ReturnRecords> returnrecordList = new ArrayList<ReturnRecords>();
		
		try {
            getConnection();
			
            String selectStatement = "SELECT rr.id, b.id, b.title, u.id, u.user_name,rr.returned_date FROM return_records rr, book b, user u WHERE rr.book_id = b.id AND rr.user_id = u.id";
			
			PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            
            while (rs.next()) {
                ReturnRecords returnrecords = new ReturnRecords();
                returnrecords.setId(rs.getInt(1));
                returnrecords.setBookId(rs.getInt(2));
                returnrecords.setTitle(rs.getString(3));
                returnrecords.setUserId(rs.getInt(4));
                returnrecords.setUsername(rs.getString(5));
                returnrecords.setReturnedDate(CommonUtil.convertDateString(rs.getTimestamp(6)));
                returnrecordList.add(returnrecords);
            }
            
            prepStmt.close();
        }catch (SQLException e) {
			throw new BooksNotFoundException("Not able to find return records");
		}finally {
			releaseConnection();
		}
		return returnrecordList;
	}
	
}
