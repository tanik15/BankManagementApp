<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"%>
<%@ page import="com.aurionpro.model.AccountDetails"%>
<%
List<AccountDetails> accounts = (List<AccountDetails>) session.getAttribute("accounts");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Apply for Loan - SmartBank</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">

	<div class="container mt-5">
		<div class="card shadow-lg">
			<div class="card-header bg-primary text-white text-center">
				<h3>Apply for Loan</h3>
			</div>
			<div class="card-body">
				<form action="LoanApplicationServlet" method="post"
					enctype="multipart/form-data">
					<!-- Loan Details -->
					<div class="mb-3">
						<label class="form-label">Loan Type</label> <select
							name="loantype" class="form-select" required>
							<option value="Home Loan">Home Loan</option>
							<option value="Car Loan">Car Loan</option>
							<option value="Personal Loan">Personal Loan</option>
							<option value="Education Loan">Education Loan</option>
						</select>
					</div>

					<div class="mb-3">
						<label class="form-label">Loan Amount</label> <input type="number"
							name="loanAmount" class="form-control" required>
					</div>

					<div class="mb-3">
						<label class="form-label">Tenure (in months)</label> <input
							type="number" name="tenure" class="form-control" required>
					</div>

					<!-- Document Upload -->
					<h5 class="mt-4">Upload Documents</h5>
					<div class="mb-3">
						<label class="form-label">Identity Proof</label> <input
							type="file" name="identity_proof" class="form-control" required>
					</div>

					<div class="mb-3">
						<label class="form-label">Address Proof</label> <input type="file"
							name="address_proof" class="form-control" required>
					</div>

					<div class="mb-3">
						<label class="form-label">Income Proof</label> <input type="file"
							name="income_proof" class="form-control" required>
					</div>

					<!-- Buttons -->
					<div class="text-center">
						<button type="submit" class="btn btn-success">Apply</button>
						<a href="customerHome.jsp" class="btn btn-secondary">Cancel</a>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>