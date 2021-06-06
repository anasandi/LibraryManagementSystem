package com.library.management.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.management.database.UserDBAO;
import com.library.management.dto.UserDto;
import com.library.management.exception.RegisterFailureException;
import com.library.management.util.CommonUtil;

/**
 * Servlet implementation class RegisterMemberServlet
 * @author hsuwai
 * 
 */
@WebServlet("/RegisterMemberServlet")
public class RegisterMemberServlet extends HttpServlet {
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
    public RegisterMemberServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Validate Logged In User
		UserDto loginUser = CommonUtil.validateLoggedInUser(request);
		if(loginUser == null) {
			request.setAttribute("errorMessage", "No Logged In User!!");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/admin/members").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Member Register Servlet ");
		//Validate Logged In User 
		UserDto loginUser = CommonUtil.validateLoggedInUser(request);
		/*
		 * if(loginUser == null) { request.setAttribute("errorMessage",
		 * "No Logged In User!!");
		 * request.getRequestDispatcher("/index.jsp").forward(request, response);
		 * return; }
		 */

		UserDto user = new UserDto();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setGender(request.getParameter("gender"));
		user.setBirthDate(request.getParameter("birthdate"));
		user.setRole("member");
		
		System.out.println("Before Saving : " + user);
		
		try {
			userDB.saveUser(user);
		} catch (RegisterFailureException e) {
			throw new ServletException(e);
		}
		
		if(loginUser != null && loginUser.getRole() == "admin") {
			request.getRequestDispatcher("/admin/members").forward(request, response);
			
		}else {
			request.setAttribute("errorMessage", "Registered Successfully!");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
