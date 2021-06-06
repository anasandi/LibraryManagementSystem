package com.library.management.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.management.database.BookDBAO;
import com.library.management.exception.UpdateFailureException;

/**
 * Servlet implementation class DeleteBookServlet
 * @author hsuwai
 * 
 */
@WebServlet("/DeleteBookServlet")
public class DeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BookDBAO bookDB;
	
	public void init() throws ServletException {
		bookDB = (BookDBAO) getServletContext()
                .getAttribute("bookDB");

		if (bookDB == null) {
			throw new UnavailableException("Couldn't get database.");
		}
    }
	
    public void destroy() {
    	bookDB = null;
    }
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBookServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Delete Book Servlet - " + request.getParameter("id"));
		
		if(request.getParameter("id") == null) {
			System.out.println("Invalid Request Parameter");
			request.getRequestDispatcher("/admin/books").forward(request, response);
			return;
		}
		int bookID = Integer.parseInt(request.getParameter("id"));
		try {
			bookDB.deleteBook(bookID);
			System.out.println("Book deleted successfully");
			request.getRequestDispatcher("/admin/books").forward(request, response);
		} catch (UpdateFailureException e) {
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
