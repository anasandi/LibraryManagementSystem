package com.library.management.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.management.database.BookDBAO;
import com.library.management.database.BorrowDBAO;
import com.library.management.dto.BookDto;
import com.library.management.dto.BorrowRecords;
import com.library.management.dto.UserDto;
import com.library.management.exception.BookNotFoundException;
import com.library.management.exception.PersistenceFailureException;
import com.library.management.exception.UpdateFailureException;
import com.library.management.model.BookStatus;
import com.library.management.util.CommonUtil;

/**
 * Servlet implementation class MemberBorrowBookServlet
 * @author Sandi
 */
@WebServlet("/MemberBorrowBookServlet")
public class MemberBorrowBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private BorrowDBAO borrowDB;
	private BookDBAO bookDB;
	
	public void init() throws ServletException {
		borrowDB = (BorrowDBAO) getServletContext()
                .getAttribute("borrowDB");

		bookDB = (BookDBAO) getServletContext()
                .getAttribute("bookDB");

		if (bookDB == null) {
			throw new UnavailableException("Couldn't get database.");
		}
		
		if (borrowDB == null) {
			throw new UnavailableException("Couldn't get database.");
		}
    }
	
    public void destroy() {
    	borrowDB = null;
    }
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberBorrowBookServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id") == null) {
			System.out.println("Invalid Request Parameter");
			request.getRequestDispatcher("/members/userbooks").forward(request, response);
			return;
		}
		
		UserDto loginUser = CommonUtil.validateLoggedInUser(request);
		if(loginUser == null) {
			request.setAttribute("errorMessage", "No Logged In User!!");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		int bookId = Integer.parseInt(request.getParameter("id"));
		BookDto book = null;
		
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		
		try {
			book = bookDB.findBookById(bookId);
			request.setAttribute("book", book);
			
			// Save Borrow Record
			BorrowRecords borrow = new BorrowRecords();
			borrow.setBookId(bookId);
			borrow.setUserId(loginUser.getId());
			borrow.setBorrowedDate(CommonUtil.convertDateString(currentTime));
			borrow.setStatus(0);
			
			borrowDB.saveBorrowRecord(borrow);
			
			// Update book status
			bookDB.updateBookStatus(bookId, BookStatus.INACTIVE.name());
			
			System.out.println("Book Result : " + book);
			request.getRequestDispatcher("/members/userbooks").forward(request, response);
		} catch (BookNotFoundException | UpdateFailureException | PersistenceFailureException e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
