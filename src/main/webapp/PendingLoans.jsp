<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.aurionpro.model.Loan"%>
<%
    List<Loan> pendingLoans = (List<Loan>) session.getAttribute("loans");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pending Loans - Smart Bank</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container-fluid mt-5">
        <div class="card shadow-lg">
            <div class="card-header bg-warning text-dark text-center">
                <h3>Pending Loans Requests</h3>
            </div>
            <div class="card-body">
                <% if (pendingLoans != null && !pendingLoans.isEmpty()) { %>
                <div class="table-responsive">
                    <table class="table table-bordered table-striped text-center align-middle w-100">
                        <thead class="table-dark">
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Account No</th>
                                <th>Loan Amount</th>
                                <th>Loan Type</th>
                                <th>Tenure (months)</th>
                                <th>Applied Date</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                            int id = 1;
                            for (Loan loan : pendingLoans) {
                            %>
                            <tr>
                                <td><%= id %></td>
                                <td><%= loan.getName() %></td>
                                <td><%= loan.getAccountNo() %></td>
                                <td><%= loan.getLoanAmount() %></td>
                                <td><%= loan.getLoanType() %></td>
                                <td><%= loan.getTenure() %></td>
                                <td><%= loan.getApplicationDate()%></td>
                                <td>
                                    <form action="getUsersPendingLoans" method="post" class="d-inline">
                                        <input type="hidden" name="loanId" value="<%= loan.getLoanId() %>">
                                        <input type="hidden" name="action" value="APPROVED">
                                        <button type="submit" class="btn btn-success btn-sm">Approve</button>
                                    </form>
                                    <form action="getUsersPendingLoans" method="post" class="d-inline">
                                        <input type="hidden" name="loanId" value="<%= loan.getLoanId() %>">
                                        <input type="hidden" name="action" value="REJECTED">
                                        <button type="submit" class="btn btn-danger btn-sm">Reject</button>
                                    </form>
                                </td>
                            </tr>
                            <%
                            id++;
                            }
                            %>
                        </tbody>
                    </table>
                </div>
                <% } else { %>
                <div class="alert alert-warning text-center">
                    No pending Loans found.
                </div>
                <% } %>
            </div>
            <div class="card-footer text-center">
                <a href="adminHome.jsp" class="btn btn-secondary">Back to Dashboard</a>
            </div>
        </div>
    </div>
</body>
</html>
