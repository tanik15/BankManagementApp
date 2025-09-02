<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Dashboard - Secure Bank</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
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

.card h4 {
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
<body class="bg-light">
	<div class="navbar">
		<h2>SmartBank</h2>
		<div>
			<a href="LogoutServlet">Logout</a>
		</div>
	</div>

	<div class="dashboard">
		<div class="welcome">
			<h2>Welcome,Admin</h2>
			<p>Your one-stop dashboard to manage all your banking needs.</p>
		</div>

		<div class="card-container">

			<!-- Create Bank Account -->
			<div class="card">
				<h4>View Customers</h4>
				<p>View all customers details.</p>
				<a href="customerDetailServlet">View</a>
			</div>

			<div class="card">
				<h4>Manage Pending Accounts</h4>
				<p>Manage pending accounts requests.</p>
				<a href="getPendingAccounts">Manage</a>
			</div>

			<div class="card">
				<h4>View All Accounts</h4>
				<p>View All accounts here.</p>
				<a href="getAllAccountsServlet">View</a>
			</div>

			<!-- Add Beneficiary -->
			<div class="card">
				<h4>Loan Management</h4>
				<p>Manage user's accounts at one place.</p>
				<a href="getUsersPendingLoans">Manage</a>
			</div>

			<div class="card">
				<h4>Show Transactions</h4>
				<p>Show all transactions of users.</p>
				<a href="allTransactionsServlet">Show</a>
			</div>



		</div>
	</div>

	<!-- Footer -->
	<div class="footer">&copy; 2025 SmartBank. All Rights Reserved.</div>
	
</body>
</html>