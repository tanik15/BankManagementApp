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
<title>My Accounts - Secure Bank</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">
	<div class="container mt-5">
		<div class="card shadow-lg">
			<div class="card-header bg-primary text-white text-center">
				<h3>My Bank Accounts</h3>
			</div>
			<div class="card-body">
				<%
				if (accounts != null && !accounts.isEmpty()) {
				%>
				<div class="table-responsive">
					<table
						class="table table-bordered table-striped text-center align-middle">
						<thead class="table-dark">
							<tr>
								<th>Account No.</th>
								<th>Account Type</th>
								<th>Branch</th>
								<th>IFSC</th>
								<th>Balance</th>
								<th>Nominee Name</th>
								<th>Status</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<%
							for (AccountDetails acc : accounts) {
							%>
							<tr>
								<td><%=acc.getAccountNo()%></td>
								<td><%=acc.getAccountType()%></td>
								<td><%=acc.getBranch()%></td>
								<td><%=acc.getIfsc()%></td>
								<td><%=acc.getBalance()%></td>
								<td><%=acc.getNominee()%></td>
								<td>
									<%
									if ("ACTIVE".equalsIgnoreCase(acc.getStatus())) {
									%> <span
									class="badge bg-success">Active</span> <%
 } else {
 %> <span
									class="badge bg-danger">Inactive</span> <%
 }
 %>
								</td>
								<td>
									<form action="showAccountServlet" method="post"
										class="d-inline">
										<input type="hidden" name="accountNo"
											value="<%=acc.getAccountNo()%>">
										<%
										if ("ACTIVE".equalsIgnoreCase(acc.getStatus())) {
										%>
										<button type="submit" name="action" value="INACTIVE"
											class="btn btn-danger btn-sm">Inactivate</button>
										<%
										} else {
										%>
										<button type="submit" name="action" value="ACTIVE"
											class="btn btn-success btn-sm">Activate</button>
										<%
										}
										%>
									</form>
								</td>
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
				<div class="alert alert-warning text-center">
					You don't have any accounts yet. <a href="createAccount.jsp"
						class="alert-link">Create one now</a>.
				</div>
				<%
				}
				%>
			</div>
			<div class="card-footer text-center">
				<a href="customerHome.jsp" class="btn btn-secondary">Back to
					Dashboard</a>
			</div>
		</div>
	</div>
</body>
</html>