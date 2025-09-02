package com.aurionpro.controller.adminControllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.model.CustomerDetails;
import com.aurionpro.model.UserModel;
import com.aurionpro.service.AdminService;

/**
 * Servlet implementation class customerDetailServlet
 */
@WebServlet("/customerDetailServlet")
public class customerDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public customerDetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        UserModel user = (UserModel) session.getAttribute("loggedUser");
        AdminService service = new AdminService();
        List<CustomerDetails> customers = service.getAllCustomers();
    	session.setAttribute("customers", customers);
    	request.getRequestDispatcher("allCustomers.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
