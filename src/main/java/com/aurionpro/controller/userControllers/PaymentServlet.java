package com.aurionpro.controller.userControllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PaymentServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			HttpSession session = request.getSession(false);

			if (session == null || session.getAttribute("loggedUser") == null) {
				response.sendRedirect("login.jsp?error=sessionExpired");
				return;
			}
			UserService service = new UserService();
			UserModel user = (UserModel) session.getAttribute("loggedUser");
			int userId = user.getUserId();

			List<BeneficiaryDetails> beneficiaries = service.getBeneficiaryDetails(userId);
			
			List<AccountDetails> accounts = service.getUserAccounts(userId);
			List<AccountDetails>  activeAccounts = accounts.stream()
			        .filter(acc -> "ACTIVE".equalsIgnoreCase(acc.getStatus()))
			        .collect(Collectors.toList());
			
			session.setAttribute("beneficiaries", beneficiaries);
			session.setAttribute("accounts", activeAccounts);
			System.out.println("accounts: "+accounts.size());
			response.sendRedirect("payment.jsp");

		} catch (Exception e) {

			response.sendRedirect("CustomerHome.jsp?error=exception");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("loggedUser") == null) {
			response.sendRedirect("login.jsp?error=sessionExpired");
			return;
		}
		System.out.println("Payment");
		UserService service = new UserService();
		UserModel user = (UserModel) session.getAttribute("loggedUser");
		int userId = user.getUserId();
		String type = request.getParameter("accountType");   // ✅ comes from hidden input
		long accountNumber = Long.parseLong(request.getParameter("accountNo")); // ✅ id sent from select
		System.out.println(accountNumber);
		long beneficiaryId = Long.parseLong(request.getParameter("beneficiaryId"));
		String beneficiaryName = request.getParameter("beneficiaryName");
		BigDecimal amount = new BigDecimal(request.getParameter("amount"));
		String remarks = request.getParameter("remark");

		System.out.println(type);
		TransactionModel transaction = new TransactionModel();
		transaction.setAccountNumber(accountNumber);
		transaction.setToAccountId(beneficiaryId);
		transaction.setAmount(amount);
		transaction.setDescription(remarks);
		transaction.setTxnType("DEBIT");
		transaction.setBeneficiaryName(beneficiaryName);
		
		if(service.makePayment(userId, transaction)) {
			response.sendRedirect("customerHome.jsp?msg=paymentDone");
		}
//		doGet(request, response);

	}

}
