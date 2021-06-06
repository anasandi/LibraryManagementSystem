package com.library.management.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.management.database.BookDBAO;
import com.library.management.dto.BookDto;
import com.library.management.exception.UpdateFailureException;
import com.library.management.model.BookStatus;

/**
 * Servlet implementation class AddBookServlet
 * @author hsuwai
 *
 */
@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
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
    public AddBookServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Adding Book Servlet");
		BookDto book = new BookDto();
		book.setTitle(request.getParameter("title"));
		book.setAuthor(request.getParameter("author"));
		book.setISBN(request.getParameter("isbn"));
		book.setPublishedDate(request.getParameter("publishedDate"));
		book.setStatus(BookStatus.ACTIVE.toString());
		
		System.out.println("Before saving book - " + book);
		try {
			bookDB.saveBook(book);
			System.out.println("Successfully Saved Book");
			request.getRequestDispatcher("/admin/books").forward(request, response);
		} catch (UpdateFailureException e) {
			throw new ServletException(e);
		}
		
		
	}

}
