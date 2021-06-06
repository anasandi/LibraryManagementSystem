package com.library.management.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.management.database.BorrowDBAO;
import com.library.management.dto.BorrowRecords;
import com.library.management.exception.BooksNotFoundException;

/**
 * Servlet implementation class BorrowRecordsServlet
 * @author hsuwai
 */
@WebServlet("/BorrowRecordsServlet")
public class BorrowRecordsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private BorrowDBAO borrowDB;
	
	public void init() throws ServletException {
		borrowDB = (BorrowDBAO) getServletContext()
                .getAttribute("borrowDB");

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
    public BorrowRecordsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<BorrowRecords> borrowList;
		try {
			borrowList = borrowDB.getAllBorrowRecords();
			System.out.println("Borrow History List - "+ borrowList.size());
			request.setAttribute("borrowList", borrowList);
			
			request.getRequestDispatcher("/pages/admin/borrowHistory.jsp").forward(request, response);
		} catch (BooksNotFoundException e) {
			throw new ServletException(e);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
