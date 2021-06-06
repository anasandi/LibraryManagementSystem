package com.library.management.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.library.management.dto.UserDto;
import com.library.management.exception.UnauthorizedException;

import java.sql.Date;

/**
 * Util class for common functions
 * @author hsuwai
 *
 */
public class CommonUtil {

	public static String convertDateTimeString(Timestamp ts) {
		if (ts == null) return null;
		SimpleDateFormat df = new SimpleDateFormat(Constants.DATE_TIME);
		return df.format(new Date(ts.getTime()));
	}
	
	public static String convertDateString(Timestamp ts) {
		if (ts == null) return null;
		SimpleDateFormat df = new SimpleDateFormat(Constants.DATE_ONLY);
		return df.format(new Date(ts.getTime()));
	}
	
	
	public static Timestamp convertTimestamp(String date) throws ParseException {
		if (date == null ) return null;
		SimpleDateFormat df = new SimpleDateFormat(Constants.DATE_ONLY);
		return new Timestamp(df.parse(date).getTime());		
	}
	
	public static UserDto validateLoggedInUser(HttpServletRequest request) {
		UserDto loginUser = null;
		HttpSession session = request.getSession(false);
		if(session != null) {
			loginUser = (UserDto) session.getAttribute("loginUser");
			System.out.println("Logged In User - " + loginUser );
		}
		return loginUser;
	}
	
	public static boolean validateAdminAccess(UserDto loginUser) throws UnauthorizedException {
		if(loginUser.getRole().equals("member")) {
			System.out.println("Unauthorized User");
			throw new UnauthorizedException("Unauthorized User");
		}
		return true;
	}
	
	public static boolean validateMemberAccess(UserDto loginUser) throws UnauthorizedException {
		if(loginUser.getRole().equals("admin")) {
			System.out.println("Unauthorized User");
			throw new UnauthorizedException("Unauthorized User");
		}
		return true;
	}
}
