<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Bank Account - MyBank</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<style>
body {
            background: linear-gradient(to right, #00416a, #e4e5e6);
            font-family: Arial, sans-serif;
        }
        .account-container {
            max-width: 500px;
            margin: 80px auto;
            background: #fff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 8px 16px rgba(0,0,0,0.25);
        }
        .account-container h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #00416a;
        }
        .form-label {
            font-weight: bold;
        }
        .btn-primary {
            background-color: #00416a;
            border: none;
        }
        .btn-primary:hover {
            background-color: #003255;
        }
        .hidden {
            display: none;
        }	
</style>
<script>
	function toggleBusinessFields() {
		let accountType = document.getElementById("accountType").value;
		let businessFields = document.getElementById("businessFields");
		let depositField = document.getElementById("deposit");

		if (accountType === "Current") {
			businessFields.classList.remove("hidden");
			document.getElementById("businessType").required = true;
			document.getElementById("businessPan").required = true;
			depositField.min = 5000; // set min deposit for Current
		} else {
			businessFields.classList.add("hidden");
			document.getElementById("businessType").required = false;
			document.getElementById("businessPan").required = false;
			depositField.min = 1000; // reset min deposit for Savings
		}
	}
</script>
</head>

<body>
	<div class="account-container">
		<h2>Create Account</h2>
		<form action="CreateAccountServlet" method="post" >

			<!-- Account Type -->
			<div class="mb-3">
				<label for="accountType" class="form-label">Account Type</label> <select
					class="form-select" id="accountType" name="accountType" required onchange="toggleBusinessFields()">
					<option value="" disabled selected>Select Account Type</option>
					<option value="Savings">Savings Account</option>
					<option value="Current">Current Account</option>
				</select>
			</div>

			<!-- Initial Deposit -->
			<div class="mb-3">
				<label for="deposit" class="form-label">Initial Deposit</label> <input
					type="number" class="form-control" id="deposit" name="deposit"
					placeholder="Enter amount" required>

			</div>

			<div id="businessFields" class="hidden">
				<div class="mb-3">
					<label for="businessType" class="form-label">Business Type</label>
					<input type="text" class="form-control" id="businessType"
						name="businessType" placeholder="e.g. Retail, IT Services">
				</div>
				<div class="mb-3">
					<label for="businessPan" class="form-label">Business PAN</label> <input
						type="text" class="form-control" id="businessPan"
						name="businessPan" placeholder="Enter Business PAN">
				</div>
			</div>

			<!-- Nominee -->
			<div class="mb-3">
				<label for="nominee" class="form-label">Nominee Name</label> <input
					type="text" class="form-control" id="nominee" name="nominee"
					placeholder="Enter nominee name" required>
			</div>



			<button type="submit" class="btn btn-primary w-100">Request To Create
				Account</button>
		</form>
	</div>
</body>
</html>