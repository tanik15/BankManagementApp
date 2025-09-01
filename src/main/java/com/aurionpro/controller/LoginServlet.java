package com.aurionpro.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.model.UserModel;
import com.aurionpro.service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        UserService service = new UserService();
        UserModel user = new UserModel(username,password, role);
        user = service.UserVerifications(user);
        if (user != null) {
            // Login success → store in session
            HttpSession session = request.getSession();
            session.setAttribute("loggedUser", user);

            if ("CUSTOMER".equalsIgnoreCase(user.getRole())) {
                response.sendRedirect("customerHome.jsp");
            } else if ("ADMIN".equalsIgnoreCase(user.getRole())) {
                response.sendRedirect("adminDashboard.jsp");
            } else {
                // Unknown role fallback
                response.sendRedirect("login.jsp?error=invalidrole");
            }
        } else {
            // Login failed
            response.sendRedirect("login.jsp?error=invalid");
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
