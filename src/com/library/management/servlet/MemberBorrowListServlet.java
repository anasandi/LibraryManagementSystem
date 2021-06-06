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

import com.library.management.database.BorrowDBAO;
import com.library.management.dto.BorrowRecords;
import com.library.management.dto.UserDto;
import com.library.management.exception.BooksNotFoundException;
import com.library.management.exception.UnauthorizedException;
import com.library.management.util.CommonUtil;

/* *
 * Servlet to get borrow records for logged in user
 * @author Sandi
 */
@WebServlet("/MemberBorrowListServlet")
public class MemberBorrowListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BorrowDBAO borrowDB;

	public void init() throws ServletException {
		borrowDB = (BorrowDBAO) getServletContext().getAttribute("borrowDB");

		if (borrowDB == null) {
			throw new UnavailableException("Couldn't get database.");
		}
	}

	public void destroy() {
		borrowDB = null;
	}

	public MemberBorrowListServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Borrow Book List Servlet");
		// Validate Logged In User
		UserDto loginUser = CommonUtil.validateLoggedInUser(request);
		if (loginUser == null) {
			request.setAttribute("errorMessage", "No Logged In User!!");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		try {
			if (CommonUtil.validateMemberAccess(loginUser)) {

				List<BorrowRecords> borrowList = borrowDB.getAllBorrowRecordsByUserId(loginUser.getId());
				request.setAttribute("borrowList", borrowList);
				System.out.println("Total borrowList  : " + borrowList.size());
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/members/borrowList.jsp");
				requestDispatcher.forward(request, response);
			}
		} catch (UnauthorizedException | BooksNotFoundException e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
