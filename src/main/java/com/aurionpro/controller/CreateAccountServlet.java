package com.aurionpro.controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.model.AccountDetails;
import com.aurionpro.model.UserModel;
import com.aurionpro.service.UserService;

/**
 * Servlet implementation class CreateAccountServlet
 */
@WebServlet("/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateAccountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if (session == null || session.getAttribute("loggedUser") == null) {
				response.sendRedirect("login.jsp?error=sessionExpired");
				return;
			}

			UserModel user = (UserModel) session.getAttribute("loggedUser");
			int userId = user.getUserId();
			System.out.println(userId);

			String accountType = request.getParameter("accountType");
			double dep = Double.parseDouble(request.getParameter("deposit"));
			BigDecimal deposit = BigDecimal.valueOf(dep);
			String nominee = request.getParameter("nominee");

			// ✅ Validation of minimum deposit
			if ("Current".equalsIgnoreCase(accountType) && dep < 5000) {
				response.sendRedirect("createAccount.jsp?error=minBalanceCurrent");
				return;
			}
			if ("Savings".equalsIgnoreCase(accountType) && dep < 1000) {
				response.sendRedirect("createAccount.jsp?error=minBalanceSavings");
				return;
			}

			// ✅ Build Account object
			AccountDetails account = new AccountDetails();
			account.setUserId(userId);
			account.setAccountType(accountType);
			account.setBalance(deposit);
			account.setNominee(nominee);

			if ("Current".equalsIgnoreCase(accountType)) {
				String businessType = request.getParameter("businessType");
				long businessPan = (long) Long.parseLong(request.getParameter("businessPan"));
				account.setBussinessType(businessType);
				account.setBussinessPan(businessPan);
			}
			UserService service = new UserService();
			// ✅ Call service to save
			boolean isCreated = service.createUserBankAccount(account);

			if (isCreated) {
				response.sendRedirect("customerHome.jsp?msg=accountCreated");
			} else {
				response.sendRedirect("createAccount.jsp?error=failed");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("createAccount.jsp?error=exception");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
