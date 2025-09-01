<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.aurionpro.model.AccountDetails"%>
<%@ page import="com.aurionpro.model.BeneficiaryDetails"%>
<%
List<AccountDetails> accounts = (List<AccountDetails>) session.getAttribute("accounts");
List<BeneficiaryDetails> beneficiaries = (List<BeneficiaryDetails>) session.getAttribute("beneficiaries");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Make Payment - SmartBank</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script>
	function updateAccountType() {
		const select = document.getElementById("accountNo");
		const type = select.options[select.selectedIndex]
				.getAttribute("data-type");
		document.getElementById("accountType").value = type;
	}
</script>
</head>

<body class="bg-light">

	<div class="container mt-5">
		<div class="card shadow-lg border-0 rounded-4">
			<div
				class="card-header bg-primary text-white text-center fs-4 fw-bold rounded-top-4">
				Make a Payment</div>
			<div class="card-body p-4">

				<form action="PaymentServlet" method="post">
					<input type="hidden" id="accountType" name="accountType">
					<!-- Select Account -->
					<div class="mb-3">
						<label for="accountNo" class="form-label fw-semibold">Select
							Your Account</label> <select class="form-select" id="accountNo"
							name="accountNo" required onchange="updateAccountType()">
							<option value="" disabled selected>--Choose Account--</option>
							<%
							if (accounts != null) {
								for (AccountDetails acc : accounts) {
							%>
							<option value="<%=acc.getAccountNo()%>"
								data-type="<%=acc.getAccountType()%>">
								<%=acc.getAccountNo()%> -
								<%=acc.getAccountType()%> (Balance:
								<%=acc.getBalance()%>)
							</option>
							<%
							}
							}
							%>
						</select>

					</div>


					<!-- Select Beneficiary -->
					<select class="form-select" id="beneficiaryId" name="beneficiaryId"
						required onchange="updateBeneficiaryName()">
						<option value="" disabled selected>-- Choose Beneficiary
							--</option>
						<%
						if (beneficiaries != null) {
							for (BeneficiaryDetails b : beneficiaries) {
						%>
						<option value="<%=b.getBeneficiaryAccount()%>"
							data-name="<%=b.getBeneficiaryName()%>">
							<%=b.getBeneficiaryName()%> -
							<%=b.getBeneficiaryAccount()%> (<%=b.getBankName()%>)
						</option>
						<%
						}
						}
						%>
					</select> <input type="hidden" id="beneficiaryName" name="beneficiaryName" />

					<script>
						function updateBeneficiaryName() {
							var select = document
									.getElementById("beneficiaryId");
							var selectedOption = select.options[select.selectedIndex];
							document.getElementById("beneficiaryName").value = selectedOption
									.getAttribute("data-name");
						}
					</script>


					<!-- Amount -->
					<div class="mb-3">
						<label for="amount" class="form-label fw-semibold">Amount</label>
						<input type="number" class="form-control" id="amount"
							name="amount" min="1" placeholder="Enter amount" required>
					</div>

					<!-- Description -->
					<div class="mb-3">
						<label for="remark" class="form-label fw-semibold">Remark
							/ Purpose</label> <input type="text" class="form-control" id="remark"
							name="remark" placeholder="E.g. Rent, Bill Payment" required>
					</div>

					<!-- Submit -->
					<div class="d-flex justify-content-center gap-3 mt-4">
						<button type="submit" class="btn btn-success px-4">Pay
							Now</button>
						<a href="customerHome.jsp" class="btn btn-secondary"> Back</a>
					</div>

				</form>

			</div>
		</div>
	</div>

</body>
</html>