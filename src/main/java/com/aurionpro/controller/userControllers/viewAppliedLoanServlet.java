package com.aurionpro.controller.userControllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.model.Loan;
import com.aurionpro.model.UserModel;
import com.aurionpro.service.UserService;


@WebServlet("/viewAppliedLoanServlet")
public class viewAppliedLoanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public viewAppliedLoanServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession(false);
	        if (session == null || session.getAttribute("loggedUser") == null) {
	            response.sendRedirect("login.jsp");
	            return;
	        }

	        // Get logged in user
	        UserModel user = (UserModel) session.getAttribute("loggedUser");
	        int userId = user.getUserId();
	        UserService service = new UserService();
	        // Fetch loan applications of this user
	        List<Loan> loans = service.getAppliedLoans(userId);

	        // Pass list to JSP
	        request.setAttribute("loans", loans);
	        request.getRequestDispatcher("viewAppliedLoan.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
