package com.library.management.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.library.management.dto.BorrowRecords;
import com.library.management.exception.BookNotFoundException;
import com.library.management.exception.BooksNotFoundException;
import com.library.management.exception.PersistenceFailureException;
import com.library.management.exception.UpdateFailureException;
import com.library.management.util.CommonUtil;
import com.library.management.util.Constants;

public class BorrowDBAO {
	
	Connection con;
    private boolean conFree = true;
    
    public BorrowDBAO() throws Exception{
    	 try {
             
             Class.forName(Constants.DRIVER_NAME);
             con = DriverManager.getConnection(Constants.URL, Constants.USERNAME, Constants.PASSWORD);
             
         } catch (Exception ex) {
             System.out.println("Exception in BorrowDBAO: " + ex);
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
    
    public void saveBorrowRecord(BorrowRecords borrowrecord) throws PersistenceFailureException {
		try {
            getConnection();
			
            String insertStatement = "INSERT INTO borrow_records (book_id, user_id, borrowed_date, status)"
                    + " VALUES (?, ?, ?, ?)";
            
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setInt(1, borrowrecord.getBookId());
            prepStmt.setInt(2, borrowrecord.getUserId());
            prepStmt.setTimestamp(3, CommonUtil.convertTimestamp(borrowrecord.getBorrowedDate()));
            prepStmt.setInt(4, borrowrecord.getStatus());
            prepStmt.execute();
            prepStmt.close();
        } catch (Exception e) {
			throw new PersistenceFailureException("Not able to save borrow record");
		}finally {
			releaseConnection();
		}
	}
    
   
	
    public List<BorrowRecords> getAllBorrowRecords() throws BooksNotFoundException {
		List<BorrowRecords> borrowrecordList = new ArrayList<BorrowRecords>();
		
		try {
            getConnection();
			
			String selectStatement = "SELECT br.id, b.id, b.title, u.id, u.user_name,br.borrowed_date FROM borrow_records br, book b, user u WHERE br.book_id = b.id AND br.user_id = u.id";
					
			PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            
            while (rs.next()) {
                BorrowRecords borrow = new BorrowRecords();
                borrow.setId(rs.getInt(1));
                borrow.setBookId(rs.getInt(2));
                borrow.setTitle(rs.getString(3));
                borrow.setUserId(rs.getInt(4));
                borrow.setUsername(rs.getString(5));
                borrow.setBorrowedDate(CommonUtil.convertDateString(rs.getTimestamp(6)));
                borrowrecordList.add(borrow);
            }
            
            prepStmt.close();
        }catch (Exception e) {
			throw new BooksNotFoundException("Not able to find borrow records");
		}finally {
			releaseConnection();
		}
		return borrowrecordList;
	}
	
    public List<BorrowRecords> getAllBorrowRecordsByUserId(int userId) throws BooksNotFoundException {
		List<BorrowRecords> borrowrecordList = new ArrayList<BorrowRecords>();
		
		try {
            getConnection();
			
			String selectStatement = "SELECT br.id, b.id, b.title, u.id, u.user_name,br.borrowed_date, br.status FROM borrow_records br, book b, user u WHERE br.book_id = b.id AND br.user_id = u.id AND u.id = ?";
					
			PreparedStatement prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setInt(1, userId);
            ResultSet rs = prepStmt.executeQuery();
            
            while (rs.next()) {
                BorrowRecords borrow = new BorrowRecords();
                borrow.setId(rs.getInt(1));
                borrow.setBookId(rs.getInt(2));
                borrow.setTitle(rs.getString(3));
                borrow.setUserId(rs.getInt(4));
                borrow.setUsername(rs.getString(5));
                borrow.setBorrowedDate(CommonUtil.convertDateString(rs.getTimestamp(6)));
                borrow.setStatus(rs.getInt(7));
                borrowrecordList.add(borrow);
            }
            
            prepStmt.close();
        }catch (Exception e) {
        	throw new BooksNotFoundException("Not able to find borrow records");
		}finally {
			releaseConnection();
		}
		return borrowrecordList;
	}
	
    public BorrowRecords findBorrowRecordByUserIdandBookId(int recordId) throws BookNotFoundException {

    	BorrowRecords borrow = null;
		try {
            getConnection();
			
			String selectStatement = "SELECT br.id, b.id, b.title, u.id, u.user_name,br.borrowed_date, br.status FROM borrow_records br, book b, user u WHERE br.book_id = b.id AND br.user_id = u.id AND br.id = ?";
			PreparedStatement prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setInt(1, recordId);
            ResultSet rs = prepStmt.executeQuery();
            
            if (rs.next()) {
                borrow = new BorrowRecords();
                borrow.setId(rs.getInt(1));
                borrow.setBookId(rs.getInt(2));
                borrow.setTitle(rs.getString(3));
                borrow.setUserId(rs.getInt(4));
                borrow.setUsername(rs.getString(5));
                borrow.setBorrowedDate(CommonUtil.convertDateString(rs.getTimestamp(6)));
                borrow.setStatus(rs.getInt(7));
            }
            
            prepStmt.close();
        }catch (Exception e) {
			throw new BookNotFoundException("Not able to find borrow record : " + recordId);
		}finally {
			releaseConnection();
		}
		return borrow;
	}
	

    public void returnBookStatus(int status, int recordId) throws UpdateFailureException{
			try{
		        getConnection();
		        String updateStatement="UPDATE borrow_records SET status=? where id=?";
		        PreparedStatement prepStmt = con.prepareStatement(updateStatement);
		        prepStmt.setInt(1,status);
		        prepStmt.setInt(2,recordId);
		        
		        prepStmt.execute();
	            prepStmt.close();
				
			}catch(Exception e){
				throw new UpdateFailureException("Not able to update status: " + recordId);
				}finally {
					releaseConnection();
				}
			
	}
   
}
