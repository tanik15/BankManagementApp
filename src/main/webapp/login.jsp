<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SmartBank - Secure Login</title>
<style>
body {
	font-family: Arial, sans-serif;
	background: linear-gradient(to right, #005AA7, #FFFDE4);
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

.login-container {
	background-color: #ffffff;
	padding: 30px 40px;
	border-radius: 12px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
	width: 350px;
	text-align: center;
}

.login-container h2 {
	margin-bottom: 20px;
	color: #005AA7;
}

.form-group {
	margin-bottom: 15px;
	text-align: left;
}

.form-group label {
	display: block;
	font-size: 14px;
	color: #333;
	margin-bottom: 5px;
}

.form-group input, .form-group select {
	width: 100%;
	padding: 10px;
	border: 1px solid #aaa;
	border-radius: 6px;
	font-size: 14px;
}

.form-group input:focus, .form-group select:focus {
	border-color: #005AA7;
	outline: none;
}

.btn-login {
	background-color: #005AA7;
	color: white;
	border: none;
	padding: 12px;
	width: 100%;
	border-radius: 6px;
	font-size: 16px;
	cursor: pointer;
}

.btn-login:hover {
	background-color: #004080;
}

.btn-register {
	background-color: #28a745;
	color: white;
	border: none;
	padding: 12px;
	width: 100%;
	border-radius: 6px;
	font-size: 16px;
	cursor: pointer;
}

.btn-register:hover {
	background-color: #1e7e34;
}

.note {
	font-size: 12px;
	color: #666;
	margin-top: 15px;
}
</style>
</head>
<body>
	<%
	String error = request.getParameter("error");
	if ("invalidrole".equals(error)) {
	%>
	<script>
		alert("Invalid role selected. Please try again.");
	</script>
	<%
	} else if ("invalid".equals(error)) {
	%>
	<script>
		alert("Invalid username or password. Please try again.");
	</script>
	<%
	}
	%>
	
	<%
    String msg = request.getParameter("msg");
    if ("registered".equals(msg)) {
%>
    <script>
        alert("Registration successful! Please login.");
    </script>
<%
    }
%>
	

	<div class="login-container">
		<h2>SmartBank Login</h2>
		<form action="LoginServlet" method="post">

			<div class="form-group">
				<label for="username">Username</label> <input type="text"
					name="username" id="username" required>
			</div>
		
			<div class="form-group">
				<label for="password">Password</label> <input type="password"
					name="password" id="password" required>
			</div>

			<div class="form-group">
				<label for="role">Role</label> <select name="role" id="role"
					required>
					<option value="">-- Select Role --</option>
					<option value="CUSTOMER">Customer</option>
					<option value="ADMIN">Admin</option>
				</select>
			</div>

			<button type="submit" class="btn-login">Login</button>
		</form>
		<br>
		<form action="register.jsp" method="get">
			<button type="submit" class="btn-register">New User?
				Register Here</button>
		</form>

		<p class="note">For your security, never share your login
			credentials.</p>

	</div>
</body>
</html>