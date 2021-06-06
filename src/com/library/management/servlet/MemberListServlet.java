package com.library.management.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.management.database.UserDBAO;
import com.library.management.dto.UserDto;
import com.library.management.exception.UnauthorizedException;
import com.library.management.exception.UserNotFoundException;
import com.library.management.util.CommonUtil;

/**
 * Servlet implementation class MemberListServlet
 * @author hsuwai
 * 
 */
@WebServlet("/MemberListServlet")
public class MemberListServlet extends HttpServlet {
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
    public MemberListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("User List Servlet");
		
		//Validate Logged In User 
		UserDto loginUser = CommonUtil.validateLoggedInUser(request);
		if(loginUser == null) {
			request.setAttribute("errorMessage", "No Logged In User!!");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		//Validate Admin User
		try {
			if(CommonUtil.validateAdminAccess(loginUser)) {
				System.out.println("DB Connection : " + userDB);
				List<UserDto> userList = userDB.getAllUsers();
				request.setAttribute("userList", userList);
				
				System.out.println("Total user list : " + userList.size());
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/admin/members.jsp");
				requestDispatcher.forward(request, response);
			}
		} catch (UnauthorizedException | UserNotFoundException e) {
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
