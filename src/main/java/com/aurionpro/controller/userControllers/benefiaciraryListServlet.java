package com.aurionpro.controller.userControllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.model.BeneficiaryDetails;
import com.aurionpro.model.UserModel;
import com.aurionpro.service.UserService;

/**
 * Servlet implementation class benefiaciraryListServlet
 */
@WebServlet("/benefiaciraryListServlet")
public class benefiaciraryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public benefiaciraryListServlet() {
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

			UserModel user = (UserModel) session.getAttribute("loggedUser");
			int userId = user.getUserId();
			UserService service = new UserService();
			List<BeneficiaryDetails> beneficiaries = service.getBeneficiaryDetails(userId);
			
			session.setAttribute("beneficiaries", beneficiaries);
			RequestDispatcher dispatcher = request.getRequestDispatcher("beneficiariesList.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			response.sendRedirect("CustomerHome.jsp?error=exception");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
