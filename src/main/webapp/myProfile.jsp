<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.aurionpro.model.CustomerDetails"%>
<%
CustomerDetails customer = (CustomerDetails) session.getAttribute("customer");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Profile - SmartBank</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">

	<div class="container mt-5">
		<div class="card shadow-lg border-0 rounded-4">
			<div
				class="card-header bg-primary text-white text-center fs-4 fw-bold rounded-top-4">
				My Profile</div>
			<div class="card-body p-4">
				<table class="table table-striped table-hover align-middle">
					<tbody class="table-group-divider">
						<tr>
							<th scope="row" class="text-secondary">Full Name</th>
							<td class="fw-semibold"><%=customer.getFullName()%></td>
						</tr>
						<tr>
							<th scope="row" class="text-secondary">Date of Birth</th>
							<td class="fw-semibold"><%=customer.getDob()%></td>
						</tr>
						<tr>
							<th scope="row" class="text-secondary">Email</th>
							<td class="fw-semibold"><%=customer.getEmail()%></td>
						</tr>
						<tr>
							<th scope="row" class="text-secondary">Phone</th>
							<td class="fw-semibold"><%=customer.getPhone()%></td>
						</tr>
						<tr>
							<th scope="row" class="text-secondary">Address</th>
							<td class="fw-semibold"><%=customer.getAddress()%></td>
						</tr>
						<tr>
							<th scope="row" class="text-secondary">Aadhar No</th>
							<td class="fw-semibold"><%=customer.getAadharNo()%></td>
						</tr>
						<tr>
							<th scope="row" class="text-secondary">PAN Card No</th>
							<td class="fw-semibold"><%=customer.getPanNo()%></td>
						</tr>
					</tbody>
				</table>

				<div class="d-flex justify-content-center gap-3 mt-4">
					<a href="customerHome.jsp" class="btn btn-secondary px-4">Back</a>
				</div>
			</div>
		</div>
	</div>


</body>
</html>