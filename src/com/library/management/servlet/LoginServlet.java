package com.library.management.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.management.database.UserDBAO;
import com.library.management.dto.UserDto;
import com.library.management.exception.UserNotFoundException;

/**
 * Servlet implementation class LoginServlet
 * @author hsuwai
 * 
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
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
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserDto authorizedUser;
		try {
			authorizedUser = userDB.findUserByUserNameAndPassword(username, password);
			
			HttpSession session = request.getSession();
			if(authorizedUser == null) {
				request.setAttribute("errorMessage", "Invalid Username or Password");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else {
				System.out.println("Authorized User " + authorizedUser.getUsername());
				session.setAttribute("loginUser", authorizedUser);
				
				if(authorizedUser.getRole().equals("admin")) {
					request.getRequestDispatcher("/admin/members").forward(request, response);
				}else {
					request.getRequestDispatcher("/members/userbooks").forward(request, response);
				}
			}
		} catch (UserNotFoundException e) {
			throw new ServletException(e);
		}

		
		
	}

}
