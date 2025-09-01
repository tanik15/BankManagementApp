<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Beneficiary - MyBank</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background: linear-gradient(to right, #004e92, #000428);
	font-family: Arial, sans-serif;
}

.card {
	margin-top: 80px;
	border-radius: 15px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
}

.form-label {
	font-weight: bold;
}

h3 {
	color: #004e92;
}
</style>
</head>
<body>
	<div class="container d-flex justify-content-center">
		<div class="col-md-6">
			<div class="card p-4">
				<h3 class="text-center mb-4">Add Beneficiary</h3>
				<form action="BeneficiaryServlet" method="post">

					<div class="mb-3">
						<label for="beneficiaryName" class="form-label">Beneficiary
							Name</label> <input type="text" class="form-control" id="beneficiaryName"
							name="beneficiaryName" required>
					</div>

					<div class="mb-3">
						<label for="beneficiaryAccount" class="form-label">Beneficiary
							Account Number</label> <input type="number" class="form-control"
							id="beneficiaryAccount" name="beneficiaryAccount" required>
					</div>

					<div class="mb-3">
						<label for="bankName" class="form-label">Bank Name</label> <input
							type="text" class="form-control" id="bankName" name="bankName"
							required>
					</div>

					<div class="mb-3">
						<label for="ifscCode" class="form-label">IFSC Code</label> <input
							type="text" class="form-control" id="ifscCode" name="ifscCode"
							pattern="[A-Z]{4}0[0-9]{6}" placeholder="e.g. HDFC0005678"
							required>
						<div class="form-text text-muted">Format: 4 letters + 0 + 6
							digits</div>
					</div>

					<div class="d-flex gap-3 mt-4">
						<button type="submit" class="btn btn-primary flex-grow-1">
							Add Beneficiary</button>
						<a href="customerHome.jsp" class="btn btn-secondary flex-fill"
							style="max-width: 200px;"> Back </a>
					</div>

				</form>
			</div>
		</div>
	</div>
</body>
</html>