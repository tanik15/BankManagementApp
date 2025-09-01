<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.aurionpro.model.*"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SmartBank - Customer Dashboard</title>
<style>
body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	margin: 0;
	padding: 0;
	background: #f5f6fa;
}

/* Navbar */
.navbar {
	background: #005AA7;
	color: white;
	padding: 15px 40px;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.navbar h2 {
	margin: 0;
}

.navbar a {
	color: white;
	text-decoration: none;
	margin-left: 20px;
	font-size: 14px;
}

.navbar a:hover {
	text-decoration: underline;
}

/* Dashboard Layout */
.dashboard {
	padding: 30px 50px;
}

.welcome {
	margin-bottom: 30px;
}

.card-container {
	display: grid;
	grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
	gap: 20px;
}

.card {
	background: white;
	border-radius: 12px;
	padding: 25px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	text-align: center;
	transition: transform 0.2s ease;
}

.card:hover {
	transform: translateY(-5px);
}

.card h3 {
	margin-bottom: 15px;
	color: #005AA7;
}

.card p {
	font-size: 14px;
	color: #555;
	margin-bottom: 15px;
}

.card a {
	display: inline-block;
	background: #005AA7;
	color: white;
	padding: 10px 15px;
	border-radius: 6px;
	text-decoration: none;
	font-size: 14px;
}

.card a:hover {
	background: #004080;
}

/* Footer */
.footer {
	text-align: center;
	padding: 15px;
	background: #f1f1f1;
	margin-top: 30px;
	font-size: 12px;
	color: #666;
}
</style>
</head>
<body>
	<%
	String msg = request.getParameter("msg");
	if ("paymentDone".equals(msg)) {
	%>
	<script>
		alert("Payment successful!");
	</script>
	<%
	}
	if ("Loan".equals(msg)) {
		%>
	<script>
		alert("Loan Applied successful!");
	</script>


	<%
	UserModel loggedUser = (UserModel) session.getAttribute("loggedUser");
	if (loggedUser == null) {
		response.sendRedirect("login.jsp?error=sessionExpired");
		return;
	}
	%>

	<!-- Navbar -->
	<div class="navbar">
		<h2>SmartBank</h2>
		<div>
			<a href="MyProfileServlet">My Profile</a> <a href="LogoutServlet">Logout</a>
		</div>
	</div>

	<div class="dashboard">
		<div class="welcome">
			<h2>
				Welcome,
				<%=loggedUser.getFullName()%>
			</h2>
			<p>Your one-stop dashboard to manage all your banking needs.</p>
		</div>

		<div class="card-container">

			<!-- Create Bank Account -->
			<div class="card">
				<h3>Create Bank Account</h3>
				<p>Open a new Savings or Current account instantly.</p>
				<a href="createAccount.jsp">Create</a>
			</div>

			<div class="card">
				<h3>Show My Account</h3>
				<p>Show all my created accounts</p>
				<a href="showAccountServlet">Show</a>
			</div>

			<!-- Add Beneficiary -->
			<div class="card">
				<h3>Add Beneficiary</h3>
				<p>Add people you want to transfer money to.</p>
				<a href="Beneficiaries.jsp">Add</a>
			</div>

			<div class="card">
				<h3>Show Beneficiary List</h3>
				<p>People you have added to beneficiary list.</p>
				<a href="benefiaciraryListServlet">Show</a>
			</div>

			<!-- Make Payments -->
			<div class="card">
				<h3>Make Payments</h3>
				<p>Transfer funds to your accounts or beneficiaries securely.</p>
				<a href="PaymentServlet">Pay</a>
			</div>

			<!-- Passbook -->
			<div class="card">
				<h3>View Passbook</h3>
				<p>Track your account transactions and history.</p>
				<a href="PassbookServlet">View</a>
			</div>

			<!-- Profile -->
			<div class="card">
				<h3>My Profile</h3>
				<p>Update your personal details anytime .</p>
				<a href="MyProfileServlet">Profile</a>
			</div>

			<div class="card">
				<h3>Apply For Loan</h3>
				<p>Apply for any type of loans here.</p>
				<a href="applyLoan.jsp">Loan</a>
			</div>

		</div>
	</div>

	<!-- Footer -->
	<div class="footer">&copy; 2025 SmartBank. All Rights Reserved.</div>

</body>
</html>