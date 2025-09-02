package com.aurionpro.controller.userControllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.model.BeneficiaryDetails;
import com.aurionpro.model.UserModel;
import com.aurionpro.service.UserService;

/**
 * Servlet implementation class BeneficiaryServlet
 */
@WebServlet("/BeneficiaryServlet")
public class BeneficiaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BeneficiaryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        UserModel user = (UserModel) session.getAttribute("loggedUser");

        // Read form fields from addBeneficiary.jsp
        String name = request.getParameter("beneficiaryName");
        String account = request.getParameter("beneficiaryAccount");
        String bank = request.getParameter("bankName");
        String ifsc = request.getParameter("ifscCode");

        // Create Beneficiary object
        BeneficiaryDetails beneficiary = new BeneficiaryDetails();
        beneficiary.setuserId(user.getUserId());
        beneficiary.setBeneficiaryName(name);
        beneficiary.setBeneficiaryAccount(Long.parseLong(account));
        beneficiary.setBankName(bank);
        beneficiary.setIfscCode(ifsc);
        
        UserService service = new UserService();
        service.addBeneficiaryDetails(beneficiary);

        // Redirect to list page
        response.sendRedirect("benefiaciraryListServlet");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
