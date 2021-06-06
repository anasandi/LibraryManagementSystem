package com.library.management.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.management.database.BookDBAO;
import com.library.management.dto.BookDto;
import com.library.management.exception.BooksNotFoundException;

/**
 * Servlet implementation class BookListServlet
 * @author hsuwai
 * 
 */
@WebServlet("/BookListServlet")
public class BookListServlet extends HttpServlet {
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
    public BookListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Book List Servlet");
		List<BookDto> bookList;
		try {
			bookList = bookDB.getAllBooks();
			request.setAttribute("bookList", bookList);
			System.out.println("Total books list : " + bookList.size());
			
			request.getRequestDispatcher("/pages/admin/books.jsp").forward(request, response);
		} catch (BooksNotFoundException e) {
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
