package com.aurionpro.controller;

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

import com.aurionpro.model.AccountDetails;
import com.aurionpro.model.BeneficiaryDetails;
import com.aurionpro.model.TransactionModel;
import com.aurionpro.model.UserModel;
import com.aurionpro.service.UserService;

/**
 * Servlet implementation class PassbookServlet
 */
@WebServlet("/PassbookServlet")
public class PassbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PassbookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			HttpSession session = request.getSession(false);

			if (session == null || session.getAttribute("loggedUser") == null) {
				response.sendRedirect("login.jsp?error=sessionExpired");
				return;
			}
			UserService service = new UserService();
			UserModel user = (UserModel) session.getAttribute("loggedUser");
			int userId = user.getUserId();

			List<TransactionModel> transactions = service.getUserTransactions(userId);
			List<AccountDetails> accounts = service.getUserAccounts(userId);
			List<BeneficiaryDetails> beneficiaries = service.getBeneficiaryDetails(userId);
			
			if(request.getParameter("accountFilter")!=null) {
				String accountFilter = request.getParameter("accountFilter");
		        String beneficiaryFilter = request.getParameter("beneficiaryFilter");
		        String dateFilter = request.getParameter("dateFilter"); // yyyy-MM-dd

		        List<TransactionModel> filtered = new ArrayList<>(transactions);

		        // ✅ Apply filters one by one
		        if (accountFilter != null && !accountFilter.isEmpty()) {
		            filtered.removeIf(txn -> txn.getAccountNumber() != Long.parseLong(accountFilter));
		        }

		        if (beneficiaryFilter != null && !beneficiaryFilter.isEmpty()) {
		            filtered.removeIf(txn -> txn.getBeneficiaryName() == null || 
		                                     !txn.getBeneficiaryName().equalsIgnoreCase(beneficiaryFilter));
		        }

		        if (dateFilter != null && !dateFilter.isEmpty()) {
		            filtered.removeIf(txn -> !txn.getTxnDate().toString().equals(dateFilter));
		            // adjust depending on how txn.getTxnDate() is stored (LocalDate, String, Timestamp, etc.)
		        }

		        // ✅ Put filtered list back in session
		        session.setAttribute("transactions", filtered);
			}else {
				session.setAttribute("transactions", transactions);
			}
			session.setAttribute("accounts", accounts);
			session.setAttribute("beneficiaries", beneficiaries);
			RequestDispatcher dispatcher =  request.getRequestDispatcher("passbook.jsp");
			dispatcher.forward(request, response);
		}catch(Exception e) {
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
