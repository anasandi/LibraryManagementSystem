package com.library.management.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.management.database.BookDBAO;
import com.library.management.database.BorrowDBAO;
import com.library.management.database.ReturnDBAO;
import com.library.management.dto.BorrowRecords;
import com.library.management.dto.ReturnRecords;
import com.library.management.dto.UserDto;
import com.library.management.exception.BookNotFoundException;
import com.library.management.exception.PersistenceFailureException;
import com.library.management.exception.UpdateFailureException;
import com.library.management.model.BookStatus;
import com.library.management.util.CommonUtil;

/**
 * Servlet class to return book
 * @author Sandi
 *
 */
@WebServlet("/MemberReturnBookServlet")
public class MemberReturnBookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private BorrowDBAO borrowDB;
	private BookDBAO bookDB;
	private ReturnDBAO returnDB;

	public void init() throws ServletException {
		borrowDB = (BorrowDBAO) getServletContext().getAttribute("borrowDB");

		bookDB = (BookDBAO) getServletContext().getAttribute("bookDB");

		returnDB = (ReturnDBAO) getServletContext().getAttribute("returnDB");

		if (bookDB == null) {
			throw new UnavailableException("Couldn't get database.");
		}

		if (borrowDB == null) {
			throw new UnavailableException("Couldn't get database.");
		}

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
	public MemberReturnBookServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("id") == null) {
			System.out.println("Invalid Request Parameter");
			request.getRequestDispatcher("/members/returnList").forward(request, response);
			return;
		}

		UserDto loginUser = CommonUtil.validateLoggedInUser(request);
		if (loginUser == null) {
			request.setAttribute("errorMessage", "No Logged In User!!");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		int borrowRecordId = Integer.parseInt(request.getParameter("id"));

		BorrowRecords borrowRecords = null;

		Timestamp currentTime = new Timestamp(System.currentTimeMillis());

		try {
			borrowRecords = borrowDB.findBorrowRecordByUserIdandBookId(borrowRecordId);

			// Save Return Record
			ReturnRecords returnRecord = new ReturnRecords();
			returnRecord.setUserId(loginUser.getId());
			returnRecord.setBookId(borrowRecords.getBookId());
			returnRecord.setReturnedDate(CommonUtil.convertDateString(currentTime));

			returnDB.saveReturnRecords(returnRecord);

			// Update Borrow Record Status
			borrowDB.returnBookStatus(1, borrowRecords.getId());

			// Update book status
			bookDB.updateBookStatus(borrowRecords.getBookId(), BookStatus.ACTIVE.name());
		} catch (UpdateFailureException | PersistenceFailureException | BookNotFoundException e) {
			throw new ServletException(e);
		}

		// System.out.println("Book Result : " + book);
		request.getRequestDispatcher("/members/borrowList").forward(request, response);

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
