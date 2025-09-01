package com.aurionpro.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.model.CustomerDetails;
import com.aurionpro.model.UserModel;
import com.aurionpro.service.UserService;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fullName = request.getParameter("fullName");
		String dob = request.getParameter("dob");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String aadhar = request.getParameter("aadhar");
		String pan = request.getParameter("pan");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		CustomerDetails customer = new CustomerDetails();
		customer.setFullName(fullName);
		customer.setDob(Date.valueOf(dob));
		customer.setEmail(email);
		customer.setPhone(phone);
		customer.setAddress(address);
		customer.setAadharNo(aadhar);
		customer.setPanNo(pan);
		UserModel user = new UserModel();
		user.setUserId(0);
		user.setUsername(username);
		user.setPassword(password);
		user.setRole("Customer");
		UserService service = new UserService();
		user=service.createUserAccount(user);
		if(user!= null) {
			customer.setUserId(user.getUserId());
			service.addCustomerDetails(customer);
			 response.sendRedirect("login.jsp?msg=registered");
		}
		else {
			response.sendRedirect("register.jsp?error=failed");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
