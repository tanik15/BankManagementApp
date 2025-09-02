package com.aurionpro.controller.userControllers;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.model.Loan;
import com.aurionpro.model.UserModel;
import com.aurionpro.service.UserService;

/**
 * Servlet implementation class LoanApplicationServlet
 */
@WebServlet("/LoanApplicationServlet")
@MultipartConfig
public class LoanApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoanApplicationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        UserModel user = (UserModel) session.getAttribute("loggedUser");
		int userId = user.getUserId();
        String loanType = request.getParameter("loantype");
        System.out.println(loanType);
        double loanAmount = Double.parseDouble(request.getParameter("loanAmount"));
        BigDecimal amount= BigDecimal.valueOf(loanAmount);
        int tenure = Integer.parseInt(request.getParameter("tenure"));
        // Upload multiple docs
        
        Loan loan = new Loan();
        loan.setLoanType(loanType);
        loan.setLoanAmount(amount);
        loan.setTenure(tenure);
        loan.setAccountNo(50233123);
        
        UserService service = new UserService();
        service.applyForLoan(request, loan, userId);
        response.sendRedirect("customerHome.jsp?msg=Loan");
	}
	

}
