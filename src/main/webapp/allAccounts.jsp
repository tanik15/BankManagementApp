<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.aurionpro.model.AccountDetails"%>
<%
List<AccountDetails> Accounts = (List<AccountDetails>) session.getAttribute("accounts");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pending Accounts - Smart Bank</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">
	<div class="container-fluid mt-5">
		<div class="card shadow-lg">
			<div class="card-header bg-primary text-white text-center">
				<h3>All Accounts</h3>
			</div>
			<div class="card-body">
				<%
				if (Accounts != null && !Accounts.isEmpty()) {
				%>
				<div class="table-responsive">
					<table
						class="table table-bordered table-striped text-center align-middle w-100">
						<thead class="table-dark">
							<tr>
								<th>ID</th>
								<th>Name</th>
								<th>AccountNo</th>
								<th>IFSC</th>
								<th>Balance</th>
								<th>Branch</th>
								<th>Opened On</th>
								<th>Account Type</th>
								<th>Business Type</th>
								<th>Business PAN</th>
								<th>Nominee</th>
							</tr>
						</thead>
						<tbody>
							<%
							int id = 1;
							for (AccountDetails acc : Accounts) {
							%>
							<tr>
								<td><%=id%></td>
								<td><%=acc.getFullName()%></td>
								<td><%=acc.getAccountNo()%></td>
								<td><%=acc.getIfsc()%></td>
								<td><%=acc.getBalance()%></td>
								<td><%=acc.getBranch()%></td>
								<td><%=acc.getOpenedOn()%></td>
								<td><%=acc.getAccountType()%></td>
								<td>
									<%
									if (acc.getBussinessType() == null) {
									%> - <%
									} else {
									%> <%=acc.getBussinessType()%>
									<%
									}
									%>
								</td>
								<td>
									<%
									if (acc.getBussinessPan() == 0) {
									%> - <%
									} else {
									%> <%=acc.getBussinessPan()%>
									<%
									}
									%>
								</td>

								<td><%=acc.getNominee()%></td>
							</tr>
							<%
							id++;
							}
							%>
						</tbody>
					</table>
				</div>
				<%
				} else {
				%>
				<div class="alert alert-warning text-center">No 
					Accounts found.</div>
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
