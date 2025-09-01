<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"%>
<%@ page import="com.aurionpro.model.TransactionModel"%>
<%@ page import="com.aurionpro.model.AccountDetails"%>
<%@ page import="com.aurionpro.model.BeneficiaryDetails"%>
<%
    List<TransactionModel> transactions = (List<TransactionModel>) session.getAttribute("transactions");
    List<AccountDetails> accounts = (List<AccountDetails>) session.getAttribute("accounts");
    List<BeneficiaryDetails> beneficiaries = (List<BeneficiaryDetails>) session.getAttribute("beneficiaries");
	

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Passbook - Secure Bank</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
	   <div class="container mt-5">
    <div class="card shadow-lg">
        <div class="card-header bg-primary text-white text-center">
            <h3>My Passbook</h3>
        </div>
        <div class="card-body">

            <!-- 🔹 Filter Form -->
            <form action="PassbookServlet" method="get" class="mb-4">
                <div class="row g-3 align-items-end">

                    <!-- Filter by My Accounts -->
                    <div class="col-md-4">
                        <label for="accountFilter" class="form-label fw-semibold">My Accounts</label>
                        <select class="form-select" id="accountFilter" name="accountFilter">
                            <option value="" selected>All Accounts</option>
                            <% if (accounts != null) {
                                   for (AccountDetails acc : accounts) {
                            %>
                                <option value="<%=acc.getAccountNo()%>">
                                    <%=acc.getAccountNo()%>
                                </option>
                            <% }} %>
                        </select>
                    </div>

                    <!-- Filter by Beneficiary -->
                    <div class="col-md-4">
                        <label for="beneficiaryFilter" class="form-label fw-semibold">Beneficiary</label>
                        <select class="form-select" id="beneficiaryFilter" name="beneficiaryFilter">
                            <option value="" selected>All Beneficiaries</option>
                            <% if (beneficiaries != null) {
                                   for (BeneficiaryDetails b : beneficiaries) {
                            %>
                                <option value="<%=b.getBeneficiaryName()%>">
                                    <%=b.getBeneficiaryName()%>
                                </option>
                            <% }} %>
                        </select>
                    </div>

                    <!-- Filter by Date -->
                    <div class="col-md-3">
                        <label for="dateFilter" class="form-label fw-semibold">Date</label>
                        <input type="date" class="form-control" id="dateFilter" name="dateFilter">
                    </div>

                    <!-- Filter Button -->
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
                <table class="table table-bordered table-striped text-center align-middle">
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
                            <td><%= txn.getTransactionId() %></td>
                            <td><%= txn.getTxnDate() %></td>
                            <td><%= txn.getAccountNumber() %></td>
                            <td><%= txn.getToAccountId() %></td>
                            <td><%= txn.getBeneficiaryName() %></td>
                            <td>
                                <% if ("DEBIT".equalsIgnoreCase(txn.getTxnType())) { %>
                                    <span class="badge bg-danger">Debit</span>
                                <% } else { %>
                                    <span class="badge bg-success">Credit</span>
                                <% } %>
                            </td>
                            <td><%= txn.getAmount() %></td>
                            <td><%= txn.getDescription() %></td>
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
                No transactions found in your passbook.
            </div>
            <%
                }
            %>
        </div>
        <div class="card-footer text-center">
            <a href="customerHome.jsp" class="btn btn-secondary">Back to Dashboard</a>
        </div>
    </div>
</div>

</body>
</html>