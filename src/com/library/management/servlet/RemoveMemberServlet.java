package com.library.management.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.management.database.UserDBAO;
import com.library.management.exception.UpdateFailureException;

/**
 * Servlet implementation class RemoveMemberServlet
 * @author hsuwai
 * 
 */
@WebServlet("/RemoveMemberServlet")
public class RemoveMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserDBAO userDB;
	
	public void init() throws ServletException {
		userDB = (UserDBAO) getServletContext()
                .getAttribute("userDB");

		if (userDB == null) {
			throw new UnavailableException("Couldn't get database.");
		}
    }
	
    public void destroy() {
    	userDB = null;
    }
    
	/**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveMemberServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Remove Member Servlet " + request.getParameter("id"));
		int userID = Integer.parseInt(request.getParameter("id"));
		try {
			userDB.deleteUser(userID);
			System.out.println("Successfully removed user");
			request.getRequestDispatcher("/admin/members").forward(request, response);
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
