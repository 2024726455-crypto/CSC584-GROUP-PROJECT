<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Food Bank System - Login</title>
</head>
<body>

    <h1>Food Bank & Social Voucher System</h1>
    <hr>

    <h3>Please login to your account</h3>
    <form action="FoodBankController" method="POST">
        <input type="hidden" name="action" value="login">
        
        <label>Email / Username:</label><br>
        <input type="text" name="username" required><br><br>
        
        <label>Password:</label><br>
        <input type="password" name="password" required><br><br>
        
        <label>Select Your Mock Access Role:</label><br>
        <select name="role">
            <option value="hep_staff">HEP Staff</option>
            <option value="donor">Donor</option>
            <option value="student">Student</option>
        </select><br><br>
        
        <input type="submit" value="Login">
    </form>

    <p>👉 <em>Don't have an account? <a href="register.html">Create An Account</a></em></p>
    <hr>

</body>
</html>