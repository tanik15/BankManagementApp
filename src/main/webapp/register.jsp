<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <title>SmartBank - Register</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #005AA7, #FFFDE4);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .register-container {
            background-color: #ffffff;
            padding: 30px 40px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.2);
            width: 420px;
            text-align: center;
            max-height: 90vh;
            overflow-y: auto;
        }

        .register-container h2 {
            margin-bottom: 20px;
            color: #005AA7;
        }

        .form-group {
            margin-bottom: 15px;
            text-align: left;
        }

        .form-group label {
            display: block;
            font-size: 14px;
            color: #333;
            margin-bottom: 5px;
        }

        .form-group input, 
        .form-group textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #aaa;
            border-radius: 6px;
            font-size: 14px;
        }

        .form-group input:focus, 
        .form-group textarea:focus {
            border-color: #005AA7;
            outline: none;
        }

        .btn-register {
            background-color: #28a745;
            color: white;
            border: none;
            padding: 12px;
            width: 100%;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
            margin-top: 10px;
        }

        .btn-register:hover {
            background-color: #1e7e34;
        }

        .note {
            font-size: 12px;
            color: #666;
            margin-top: 15px;
        }
    </style>
</head>
<body>
    <div class="register-container">
        <h2>SmartBank - New User Registration</h2>
        <form action="RegisterServlet" method="post">
            
            <div class="form-group">
                <label for="fullName">Full Name</label>
                <input type="text" name="fullName" id="fullName" required>
            </div>

            <div class="form-group">
                <label for="dob">Date of Birth</label>
                <input type="date" name="dob" id="dob" required>
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" name="email" id="email" required>
            </div>

            <div class="form-group">
                <label for="phone">Phone</label>
                <input type="tel" name="phone" id="phone" 
                       pattern="[0-9]{10}" maxlength="10"
                       title="Enter 10 digit phone number" required>
            </div>

            <div class="form-group">
                <label for="address">Address</label>
                <textarea name="address" id="address" rows="3" required></textarea>
            </div>

            <div class="form-group">
                <label for="aadhar">Aadhar Number</label>
                <input type="text" name="aadhar" id="aadhar"
                       pattern="[0-9]{12}" maxlength="12"
                       title="Enter 12 digit Aadhar number" required>
            </div>

            <div class="form-group">
                <label for="pan">PAN Card Number</label>
                <input type="text" name="pan" id="pan"
                       pattern="[A-Z]{5}[0-9]{4}[A-Z]{1}" maxlength="10"
                       title="Enter valid PAN format (ABCDE1234F)" required>
            </div>

            <div class="form-group">
                <label for="username">Choose Username</label>
                <input type="text" name="username" id="username" required>
            </div>

            <div class="form-group">
                <label for="password">Choose Password</label>
                <input type="password" name="password" id="password"
                       minlength="6" required>
            </div>
            
            <button type="submit" class="btn-register">Register</button>
        </form>
        <p class="note">Already have an account? <a href="login.jsp">Login here</a></p>
    </div>
</body>
</html>