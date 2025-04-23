<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.User"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Swiggy - Login Page</title>
<style>
body {
	margin: 0;
	padding: 0;
	font-family: Arial, sans-serif;
	background-color: #ffb74d;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.login-container {
	background-color: #ffffff;
	padding: 30px;
	border-radius: 10px;
	box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.2);
	width: 300px;
}

.login-container h5 {
	text-align: center;
	margin-bottom: 25px;
	color: #333;
	font-size: 24px;
}

.form-group {
	margin-bottom: 20px;
}

label {
	display: block;
	margin-bottom: 5px;
	font-weight: bold;
}

input[type="email"], input[type="password"] {
	width: 100%;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 6px;
	font-size: 16px;
}

.form-check {
	margin-bottom: 20px;
	display: flex;
	align-items: center;
}

.form-check-input {
	margin-right: 10px;
}

.btn {
	width: 100%;
	padding: 10px;
	background-color: #fc8019;
	border: none;
	border-radius: 6px;
	color: #fff;
	font-size: 16px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.btn:hover {
	background-color: #e46d00;
}

.text-center a {
	display: block;
	text-align: center;
	margin-top: 15px;
	color: #fc8019;
	text-decoration: none;
}

.text-center a:hover {
	text-decoration: underline;
}

.main {
	text-align: center;
	margin-bottom: 18px;
	color: #fc8019;
}
</style>
</head>
<body>

	<div class="login-container">
		<h1 class="main">Swiggy</h1>
		<h5>Login Page</h5>

		<form action="login" method="post">
			<div class="form-group">
				<label for="exampleInputEmail1">Email address</label> <input
					type="email" class="form-control" id="exampleInputEmail1"
					placeholder="Enter email" required="required" name="email">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">Password</label> <input
					type="password" class="form-control" id="exampleInputPassword1"
					placeholder="Password" required="required" name="password">
			</div>
			<div class="text-center">
				<button type="submit" class="btn">Login</button>
				<a href="Register.jsp">Create Account</a>
			</div>
		</form>
	</div>

</body>
</html>
