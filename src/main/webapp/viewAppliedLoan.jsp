<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.aurionpro.model.Loan"%>
<%@ page import="java.util.List"%>
<%
    List<Loan> loans = (List<Loan>) request.getAttribute("loans");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Loans - Secure Bank</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">
	<div class="container mt-5">
		<div class="card shadow-lg">
			<div class="card-header bg-primary text-white text-center">
				<h3>My Loan Applications</h3>
			</div>
			<div class="card-body">
				<%
                    if (loans != null && !loans.isEmpty()) {
                %>
				<div class="table-responsive">
					<table
						class="table table-bordered table-striped text-center align-middle">
						<thead class="table-dark">
							<tr>
								<th>Loan ID</th>
								<th>Loan Type</th>
								<th>Amount</th>
								<th>Tenure</th>
								<th>Interest Rate</th>
								<th>Application Date</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody>
							<%
                                for (Loan loan : loans) {
                            %>
							<tr>
								<td><%= loan.getLoanId() %></td>
								<td><%= loan.getLoanType() %></td>
								<td><%= loan.getLoanAmount()%></td>
								<td><%= loan.getTenure() %> months</td>
								<td><%= loan.getInterestRate() %> %</td>
								<td><%= loan.getApplicationDate() %></td>
								<td>
									<% if ("APPROVED".equalsIgnoreCase(loan.getStatus())) { %> 
									<span class="badge bg-success">Approved</span>
									 <% } else if ("PENDING".equalsIgnoreCase(loan.getStatus())) { %>
									<span class="badge bg-warning">Pending</span> <% } else { %> <span
									class="badge bg-danger">Rejected</span> <% } %>
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
					You have not applied for any loans yet. <a href="applyLoan.jsp"
						class="alert-link">Apply Now</a>.
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