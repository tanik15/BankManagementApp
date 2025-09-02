<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"%>
<%@ page import="com.aurionpro.model.TransactionModel"%>
<%@ page import="com.aurionpro.model.AccountDetails"%>
<%@ page import="com.aurionpro.model.BeneficiaryDetails"%>
<%
List<TransactionModel> transactions = (List<TransactionModel>) session.getAttribute("transactions");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Passbook - Secure Bank</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">
	<div class="container mt-5">
		<div class="card shadow-lg">
			<div class="card-header bg-primary text-white text-center">
				<h3>My Passbook</h3>
			</div>
			<div class="card-body">

				<!-- 🔹 Filter Form -->
				<form action="allTransactionsServlet" method="get" class="mb-4">
					<div class="p-3 bg-light border-bottom">
						<div class="row g-3 justify-content-center">
							<div class="col-md-3">
								<input type="text" id="accountFilter" name ="accountFilter" class="form-control"
									placeholder="Search by AccountNo">
							</div>
							<div class="col-md-3">
								<input type="text" id="beneficiaryFilter" name = "beneficiaryFilter" class="form-control"
									placeholder="Search by beneficiary">
							</div>
							<!-- Filter by Date -->
							<div class="col-md-3">
								<input type="date" class="form-control" id="dateFilter"
									name="dateFilter">
							</div>
						</div>
						<div class="col-md-1 d-grid">
							<button type="submit" class="btn btn-primary">Filter</button>
						</div>
					</div>
				</form>
				<!-- 🔹 End Filter Form -->

				<%
				if (transactions != null && !transactions.isEmpty()) {
				%>
				<div class="table-responsive">
					<table
						class="table table-bordered table-striped text-center align-middle">
						<thead class="table-dark">
							<tr>
								<th>Transaction ID</th>
								<th>Date & Time</th>
								<th>From Account</th>
								<th>To Account</th>
								<th>Beneficiary Name</th>
								<th>Type</th>
								<th>Amount</th>
								<th>Description</th>
							</tr>
						</thead>
						<tbody>
							<%
							for (TransactionModel txn : transactions) {
							%>
							<tr>
								<td><%=txn.getTransactionId()%></td>
								<td><%=txn.getTxnDate()%></td>
								<td><%=txn.getAccountNumber()%></td>
								<td><%=txn.getToAccountId()%></td>
								<td><%=txn.getBeneficiaryName()%></td>
								<td>
									<%
									if ("DEBIT".equalsIgnoreCase(txn.getTxnType())) {
									%> <span class="badge bg-danger">Debit</span> <%
 } else {
 %> <span class="badge bg-success">Credit</span> <%
 }
 %>
								</td>
								<td><%=txn.getAmount()%></td>
								<td><%=txn.getDescription()%></td>
							</tr>
							<%
							}
							%>
						</tbody>
					</table>
				</div>
				<%
				} else {
				%>
				<div class="alert alert-warning text-center">No transactions
					found in your passbook.</div>
				<%
				}
				%>
			</div>
			<div class="card-footer text-center">
				<a href="adminHome.jsp" class="btn btn-secondary">Back to
					Dashboard</a>
			</div>
		</div>
	</div>

</body>
</html>