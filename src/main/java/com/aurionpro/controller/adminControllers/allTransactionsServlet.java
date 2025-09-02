package com.aurionpro.controller.adminControllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.model.TransactionModel;
import com.aurionpro.service.AdminService;

/**
 * Servlet implementation class allTransactionsServlet
 */
@WebServlet("/allTransactionsServlet")
public class allTransactionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public allTransactionsServlet() {
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
			response.sendRedirect("login.jsp?error=sessionExpired");
			return;
		}
		AdminService service = new AdminService();

		List<TransactionModel> transactions = service.getAllTransactions();

		if (request.getParameter("accountFilter") != null) {
			System.out.println("accountfilter");
			String accountFilter = request.getParameter("accountFilter");
			String beneficiaryFilter = request.getParameter("beneficiaryFilter");
			String dateFilter = request.getParameter("dateFilter"); // yyyy-MM-dd

			List<TransactionModel> filtered = new ArrayList<>(transactions);

			if (accountFilter != null && !accountFilter.isEmpty()) {
			    long filterValue = Long.parseLong(accountFilter.trim());
			    filtered.removeIf(txn -> txn.getAccountNumber() != filterValue);
			}


			if (beneficiaryFilter != null && !beneficiaryFilter.isEmpty()) {
				filtered.removeIf(txn -> txn.getBeneficiaryName() == null
						|| !txn.getBeneficiaryName().equalsIgnoreCase(beneficiaryFilter));
			}

			if (dateFilter != null && !dateFilter.isEmpty()) {
				filtered.removeIf(txn -> !txn.getTxnDate().toString().equals(dateFilter));
				// adjust depending on how txn.getTxnDate() is stored (LocalDate, String,
				// Timestamp, etc.)
			}
			System.out.println(filtered.size());
			// ✅ Put filtered list back in session
			session.setAttribute("transactions", filtered);
		} else {
			session.setAttribute("transactions", transactions);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("allTransactions.jsp");
		dispatcher.forward(request, response);
		
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
