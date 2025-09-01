<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.aurionpro.model.BeneficiaryDetails"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Beneficiaries - MyBank</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background: linear-gradient(to right, #004e92, #000428);
	font-family: Arial, sans-serif;
}

.card {
	margin-top: 60px;
	border-radius: 15px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
}

h3 {
	color: #004e92;
}

table {
	border-radius: 10px;
	overflow: hidden;
}
</style>
</head>
<body>
	<div class="container">
		<div class="card p-4">
			<h3 class="text-center mb-4">My Beneficiaries</h3>

			<%
			if (session == null || session.getAttribute("loggedUser") == null) {
				response.sendRedirect("login.jsp");
				return;
			}
			List<BeneficiaryDetails> beneficiaries = (List<BeneficiaryDetails>) session.getAttribute("beneficiaries");
			if (beneficiaries != null && !beneficiaries.isEmpty()) {
			%>

			<table class="table table-bordered table-striped text-center">
				<thead class="table-dark">
					<tr>
						<th>#</th>
						<th>Beneficiary Name</th>
						<th>Account Number</th>
						<th>Bank Name</th>
						<th>IFSC Code</th>
					</tr>
				</thead>
				<tbody>
					<%
					int i = 1;
					for (BeneficiaryDetails b : beneficiaries) {
					%>
					<tr>
						<td><%=i++%></td>
						<td><%=b.getBeneficiaryName()%></td>
						<td><%=b.getBeneficiaryAccount()%></td>
						<td><%=b.getBankName()%></td>
						<td><%=b.getIfscCode()%></td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>

			<%
			} else {
			%>
			<p class="text-center text-muted">No beneficiaries added yet.</p>
			<%
			}
			%>

			<div class="text-center mt-3">
				<a href="Beneficiaries.jsp" class="btn btn-primary">+ Add New
					Beneficiary</a>
					<a href="customerHome.jsp" class="btn btn-secondary">
					Back </a>
			</div>
		</div>
	</div>
</body>

</body>
</html>