package com.library.management.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.management.dto.BookDto;
import com.library.management.exception.BookNotFoundException;
import com.library.management.exception.BooksNotFoundException;
import com.library.management.exception.UpdateFailureException;
import com.library.management.util.CommonUtil;
import com.library.management.util.Constants;

/**
 * Connection object to database - book table
 * @author hsuwai
 *
 */
public class BookDBAO {

	Connection con;
    private boolean conFree = true;
    
    public BookDBAO() throws Exception {
        try {
            
            Class.forName(Constants.DRIVER_NAME);
            con = DriverManager.getConnection(Constants.URL, Constants.USERNAME, Constants.PASSWORD);
            
        } catch (Exception ex) {
            System.out.println("Exception in BookDBAO: " + ex);
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
	 * Find all books
	 * @return List<Book>
	 * @throws BooksNotFoundException 
	 */
	public List<BookDto> getAllBooks() throws BooksNotFoundException {
		List<BookDto> bookList = new ArrayList<BookDto>();
		
		try {
            getConnection();
			
			String selectStatement = "SELECT * FROM book";
			PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            
            while (rs.next()) {
                BookDto book = new BookDto();
                book.setId(rs.getInt(1));
                book.setTitle(rs.getString(2));
                book.setAuthor(rs.getString(3));
                book.setISBN(rs.getString(4));
                book.setPublishedDate(CommonUtil.convertDateString(rs.getTimestamp(5)));
                book.setStatus(rs.getString(6));
                book.setCreatedDate(CommonUtil.convertDateTimeString(rs.getTimestamp(7)));
                book.setUpdatedDate(CommonUtil.convertDateTimeString(rs.getTimestamp(8)));
                
                bookList.add(book);
            }
            
            prepStmt.close();
        }catch (SQLException e) {
			throw new BooksNotFoundException("Something wrong file fetching books.");
		}finally {
			releaseConnection();
		}
		return bookList;
	}
	
	/**
	 * Find Book by ID
	 * @param bookId
	 * @return
	 * @throws BookNotFoundException 
	 */
	public BookDto findBookById(int bookId) throws BookNotFoundException {
		BookDto book = null;

		try {
            getConnection();
			
			String selectStatement = "SELECT * FROM book WHERE id = ?";
			PreparedStatement prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setInt(1, bookId);
            ResultSet rs = prepStmt.executeQuery();
            
            if (rs.next()) {
            	book = new BookDto();
                book.setId(rs.getInt(1));
                book.setTitle(rs.getString(2));
                book.setAuthor(rs.getString(3));
                book.setISBN(rs.getString(4));
                book.setPublishedDate(CommonUtil.convertDateString(rs.getTimestamp(5)));
                book.setStatus(rs.getString(6));
                book.setCreatedDate(CommonUtil.convertDateTimeString(rs.getTimestamp(7)));
                book.setUpdatedDate(CommonUtil.convertDateTimeString(rs.getTimestamp(8)));
            }else {
            	throw new BookNotFoundException("Not able to find book: " +
            			bookId);
            }
            
            prepStmt.close();
        }catch (SQLException e) {
        	throw new BookNotFoundException("Not able to find book: " +
        			bookId);
		}finally {
			releaseConnection();
		}
		return book;
	}
	
	/**
	 * Save Book Information
	 * @param book
	 * @throws UpdateFailureException 
	 */
	public void saveBook(BookDto book) throws UpdateFailureException {
		try {
            getConnection();
			
            String insertStatement = "INSERT INTO book (title, author, ISBN, published_date, status)"
                    + " VALUES (?, ?, ?, ?, ?)";
            
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, book.getTitle());
            prepStmt.setString(2, book.getAuthor());
            prepStmt.setString(3, book.getISBN());
            prepStmt.setTimestamp(4, CommonUtil.convertTimestamp(book.getPublishedDate()));
            prepStmt.setString(5, book.getStatus());
            
            prepStmt.execute();
            prepStmt.close();
        } catch (Exception e) {
			throw new UpdateFailureException("Not able to save new book : " + book.getTitle());
		}finally {
			releaseConnection();
		}
	}
	
	/**
	 * Update Book information
	 * @param book
	 * @throws UpdateFailureException 
	 */
	public void updateBook(BookDto book) throws UpdateFailureException {
		try {
            getConnection();
			
            String updateStatement = "UPDATE book SET title = ?, author = ?, ISBN = ?, published_date = ?, status = ?"
                    + " WHERE id = ?";
            
            PreparedStatement prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1, book.getTitle());
            prepStmt.setString(2, book.getAuthor());
            prepStmt.setString(3, book.getISBN());
            prepStmt.setTimestamp(4, CommonUtil.convertTimestamp(book.getPublishedDate()));
            prepStmt.setString(5, book.getStatus());
            
            prepStmt.setInt(6, book.getId());
            
            prepStmt.execute();
            prepStmt.close();
        } catch (Exception e) {
        	throw new UpdateFailureException("Not able to update book information: " + book.getId());
		}finally {
			releaseConnection();
		}
	}
	
	/**
	 * Delete Book
	 * @param id
	 * @throws UpdateFailureException 
	 */
	public void deleteBook(int id) throws UpdateFailureException {
		try {
            getConnection();
			
            String deleteStatement = "DELETE FROM book WHERE id = ?";
            
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setInt(1, id);
            
            prepStmt.execute();
            prepStmt.close();
        } catch (SQLException e) {
        	throw new UpdateFailureException("Not able to delete book: " + id);
		}finally {
			releaseConnection();
		}
	}
	
	public void updateBookStatus(int id, String status) throws UpdateFailureException {
		try {
            getConnection();
			
            String updateStatement = "UPDATE book SET status = ? WHERE id = ?";
            
            PreparedStatement prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1, status);
            prepStmt.setInt(2, id);
            
            prepStmt.execute();
            prepStmt.close();
        } catch (SQLException e) {
        	throw new UpdateFailureException("Not able to update book status: " + id);
		}finally {
			releaseConnection();
		}
	}
	
}
