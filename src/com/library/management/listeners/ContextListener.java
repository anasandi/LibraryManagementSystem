package com.library.management.listeners;

import javax.servlet.*;

import com.library.management.database.BookDBAO;
import com.library.management.database.BorrowDBAO;
import com.library.management.database.ReturnDBAO;
import com.library.management.database.UserDBAO;
import com.library.management.dto.UserDto;
import com.library.management.exception.RegisterFailureException;

/**
 * This is the handler class to run when the applicaton starts
 * @author hsuwai
 *
 */
public final class ContextListener implements ServletContextListener {
    private ServletContext context = null;

    // This method gets called when the application is deployed
    public void contextInitialized(ServletContextEvent event) {
        context = event.getServletContext();

        UserDBAO userDB = null ;
        BookDBAO bookDB = null ;
        BorrowDBAO borrowDB = null;
        ReturnDBAO returnDB=null;
        try {
            userDB = new UserDBAO();
            bookDB = new BookDBAO();
            borrowDB=new BorrowDBAO();
            returnDB=new ReturnDBAO();
            context.setAttribute("userDB", userDB);
            context.setAttribute("bookDB", bookDB);
            context.setAttribute("borrowDB", borrowDB);
            context.setAttribute("returnDB", returnDB);
            
           
        } catch (Exception ex) {
            System.out.println("Couldn't create database bean: " +
                ex.getMessage());
        }
        
        if(userDB.getAdminUsers().size() == 0) {
        	System.out.println("Initializing Admin");
        	
        	UserDto user = new UserDto();
        	user.setUsername("LibraryAdmin");
        	user.setPassword("password@123");
        	user.setRole("admin");
        	try {
				userDB.saveUser(user);
			} catch (RegisterFailureException e) {
				e.printStackTrace();
			}
        }
    }

    // This method gets called when the application is undeployed
    public void contextDestroyed(ServletContextEvent event) {
        context = event.getServletContext();

        UserDBAO userDB = (UserDBAO) context.getAttribute("userDB");
        BookDBAO bookDB = (BookDBAO) context.getAttribute("bookDB");
        BorrowDBAO borrowDB= (BorrowDBAO)context.getAttribute("borrowDB");
        ReturnDBAO returnDB=(ReturnDBAO)context.getAttribute("returnDB");
        if (userDB != null) {
        	userDB.remove();
        }
        
        if(bookDB != null) {
        	bookDB.remove();
        }
        if(borrowDB!=null) {
        	borrowDB.remove();
        }
        if(returnDB!=null) {
        	returnDB.remove();
        }


        context.removeAttribute("userDB");
        context.removeAttribute("bookDB");
        context.removeAttribute("borrowDB");
        context.removeAttribute("returnDB");
    }
}
