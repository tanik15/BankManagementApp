package com.aurionpro.controller.userControllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.model.AccountDetails;
import com.aurionpro.model.UserModel;
import com.aurionpro.service.UserService;


@WebServlet("/showAccountServlet")
public class showAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public showAccountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedUser") == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		UserModel user = (UserModel) session.getAttribute("loggedUser");
		int userId = user.getUserId();
		UserService service = new UserService();
		if (request.getParameter("action")!=null) {
			String status = (String) request.getParameter("action");
			System.out.println(status);
			long accountId = Long.parseLong(request.getParameter("accountNo"));
			service.updateBankAccountStatus(userId, status, accountId);
			
		}
		List<AccountDetails> accounts = service.getUserAccounts(userId);
		session.setAttribute("accounts", accounts);
		response.sendRedirect("showAccounts.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
