<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Swiggy: RegistrationPage</title>
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

.container {
	background-color: #fff;
	padding: 30px;
	border-radius: 12px;
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
	width: 350px;
}

h1.text-center {
	text-align: center;
	margin-bottom: 20px;
	color: #fc8019;
}

h3.text-center {
	text-align: center;
	margin-bottom: 20px;
}
.form-group {
	margin-bottom: 15px;
}

.form-group label {
	display: block;
	margin-bottom: 6px;
	font-weight: bold;
	color: #444;
}

.form-control {
	width: 100%;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 6px;
	box-sizing: border-box;
}

.form-check {
	margin-top: 15px;
	margin-bottom: 20px;
}

.form-check-label {
	color: #333;
}

.btn-primary {
	background-color: #fc8019;
	color: white;
	border: none;
	padding: 10px 20px;
	border-radius: 6px;
	cursor: pointer;
	font-size: 16px;
	transition: background-color 0.3s ease;
	width: 100%;
}

.btn-primary:hover {
	background-color: #e86f10;
}
</style>
</head>
<body>
	<div class="container">
		<h1 class="text-center">Swiggy</h1>
		<h3 class="text-center">Registration Page</h3>
		<form action="register" method="post">
			<div class="form-group">
				<label for="fullName">Enter Full Name</label> <input type="text"
					class="form-control" id="fullName" name="fname" required>
			</div>
			<div class="form-group">
				<label for="fullName">Username</label> <input type="text"
					class="form-control" id="fullName" name="username" required>
			</div>
			<div class="form-group">
				<label for="email">Email address</label> <input type="email"
					class="form-control" id="email" name="email" required>
			</div>
			<div class="form-group">
				<label for="phone">Phone No.</label> <input type="number"
					class="form-control" id="phone" name="phno" required>
			</div>
			<div class="form-group">
				<label for="password">Password</label> <input type="password"
					class="form-control" id="password" name="password" required>
			</div>
			<div class="form-check">
				<input type="checkbox" class="form-check-input" name="check"
					id="exampleCheck1" required> <label
					class="form-check-label" for="exampleCheck1">Agree terms &
					Conditions</label>
			</div>
			<button type="submit" class="btn btn-primary">Register</button>
		</form>
	</div>
</body>
</html>
