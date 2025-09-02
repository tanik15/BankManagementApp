package com.aurionpro.controller.userControllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.model.CustomerDetails;
import com.aurionpro.model.UserModel;
import com.aurionpro.service.UserService;

/**
 * Servlet implementation class MyProfileServlet
 */
@WebServlet("/MyProfileServlet")
public class MyProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MyProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("servlet");
			HttpSession session = request.getSession(false);
			if (session == null || session.getAttribute("loggedUser") == null) {
				response.sendRedirect("login.jsp?error=sessionExpired");
				return;
			}

			UserModel user = (UserModel) session.getAttribute("loggedUser");
			int userId = user.getUserId();
			UserService service = new UserService();
			CustomerDetails customer = service.getCustomerDetails(userId);
			session.setAttribute("customer", customer);
			System.out.println(customer.getFullName());
			response.sendRedirect("myProfile.jsp");
		} catch (Exception e) {

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
