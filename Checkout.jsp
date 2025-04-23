<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Restaurant Checkout</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 20px;
	background-color: #f8f9fa;
}

.container {
	max-width: 500px;
	margin: auto;
	padding: 20px;
}

.card {
	background: white;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

label {
	display: block;
	margin: 10px 0 5px;
}

input, select, button {
	width: 100%;
	padding: 10px;
	margin-bottom: 15px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

button {
	background-color: #28a745;
	color: white;
	border: none;
	cursor: pointer;
}

button:hover {
	background-color: #218838;
}

.payment-details {
	display: none;
}
</style>
<script>
	function togglePaymentDetails() {
		var paymentMethod = document.getElementById("payment").value;
		var upiInput = document.getElementById("upi-details");
		var cardInput = document.getElementById("card-details");

		upiInput.style.display = "none";
		cardInput.style.display = "none";

		if (paymentMethod === "UPI") {
			upiInput.style.display = "block";
		} else if (paymentMethod === "Credit Card"
				|| paymentMethod === "Debit Card") {
			cardInput.style.display = "block";
		}
	}
</script>
</head>
<body>
	<div class="container">
		<div class="card">
			<h2>Checkout</h2>
			<form action="checkout" method="post">
				<label for="address">Delivery Address:</label> <input type="text"
					id="address" name="address" required> <label for="payment">Payment
					Method:</label> <select id="payment" name="payment" required
					onchange="togglePaymentDetails()">
					<option value="Credit Card">Credit Card</option>
					<option value="Debit Card">Debit Card</option>
					<option value="UPI">UPI</option>
					<option value="Cash on Delivery">Cash on Delivery</option>
				</select>

				<div id="upi-details" class="payment-details">
					<label for="upi-id">Enter UPI ID:</label> <input type="text"
						id="upi-id" name="upi-id" placeholder="example@upi">
				</div>

				<div id="card-details" class="payment-details">
					<label for="card-number">Card Number:</label> <input type="text"
						id="card-number" name="card-number"
						placeholder="1234 5678 9012 3456"> <label for="expiry">Expiry
						Date:</label> <input type="text" id="expiry" name="expiry"
						placeholder="MM/YY"> <label for="cvv">CVV:</label> <input
						type="text" id="cvv" name="cvv" placeholder="123">
				</div>
			</form>
			
			<form action="Confirmation.jsp" method="post">
				<input type="submit" value="Proceed to Checkout"
				class="proceed-to-checkout-btn">
				</form>	
			
		</div>
	</div>
</body>
</html>
