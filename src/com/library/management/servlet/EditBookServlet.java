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
import com.library.management.exception.BookNotFoundException;
import com.library.management.exception.UpdateFailureException;
import com.library.management.model.BookStatus;

/**
 * Servlet implementation class EditBookServlet
 * @author hsuwai
 * 
 */
@WebServlet("/EditBookServlet")
public class EditBookServlet extends HttpServlet {
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
    public EditBookServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Before Edit Book Servlet - " + request.getParameter("id"));
		
		if(request.getParameter("id") == null) {
			//TODO:Error Handle
			System.out.println("Invalid Request Parameter");
			request.getRequestDispatcher("/admin/books").forward(request, response);
			return;
		}
		
		int bookID = Integer.parseInt(request.getParameter("id"));
		BookDto book = null;
		try {
			book = bookDB.findBookById(bookID);
			request.setAttribute("book", book);
			
			System.out.println("Book Result : " + book);
			request.getRequestDispatcher("/pages/admin/bookForm.jsp").forward(request, response);
		} catch (BookNotFoundException e) {
			throw new ServletException(e);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Edit Book Servlet POST Method");
		if(request.getParameter("id") == null) {
			System.out.println("Invalid Request Parameter");
			request.getRequestDispatcher("/admin/books").forward(request, response);
			return;
		}
		
		int bookID = Integer.parseInt(request.getParameter("id"));
		BookDto book = new BookDto();
		book.setId(bookID);
		book.setTitle(request.getParameter("title"));
		book.setAuthor(request.getParameter("author"));
		book.setISBN(request.getParameter("isbn"));
		book.setPublishedDate(request.getParameter("publishedDate"));
		book.setStatus(BookStatus.ACTIVE.name());
		System.out.println("Before updating book - " + book);
		try {
			bookDB.updateBook(book);
			request.getRequestDispatcher("/admin/books").forward(request, response);
		} catch (UpdateFailureException e) {
			throw new ServletException(e);
		}
		
	}

}
