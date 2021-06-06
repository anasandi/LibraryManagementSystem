package com.library.management.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.library.management.database.ReturnDBAO;
import com.library.management.dto.ReturnRecords;
import com.library.management.exception.BooksNotFoundException;

/**
 * Servlet implementation class ReturnRecordsServlet
 * @author hsuwai
 */
@WebServlet("/ReturnRecordsServlet")
public class ReturnRecordsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private ReturnDBAO returnDB;
	
	public void init() throws ServletException {
		returnDB = (ReturnDBAO) getServletContext()
                .getAttribute("returnDB");

		if (returnDB == null) {
			throw new UnavailableException("Couldn't get database.");
		}
    }
	
    public void destroy() {
    	returnDB = null;
    }
    
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnRecordsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<ReturnRecords> returnList;
		try {
			returnList = returnDB.getAllReturnRecords();
			System.out.println("Return History List - "+ returnList.size());
			request.setAttribute("returnList", returnList);
			
			request.getRequestDispatcher("/pages/admin/returnHistory.jsp").forward(request, response);
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
