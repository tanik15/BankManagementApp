package com.aurionpro.controller.adminControllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.model.Loan;
import com.aurionpro.service.AdminService;

/**
 * Servlet implementation class getUsersPendingLoans
 */
@WebServlet("/getUsersPendingLoans")
public class getUsersPendingLoans extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getUsersPendingLoans() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedUser") == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		AdminService service = new AdminService();
		if (request.getParameter("loanId") != null) {
			int loanId = Integer.parseInt(request.getParameter("loanId"));
			String action = request.getParameter("action");
			service.updateLoanRequest(loanId, action);
		}
		List<Loan> loans = service.getUsersPendingLoans();
		session.setAttribute("loans", loans);
		request.getRequestDispatcher("PendingLoans.jsp").forward(request, response);
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
