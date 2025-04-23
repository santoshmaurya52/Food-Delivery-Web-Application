<%@page import="model.CartItem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.CartItem, model.Cart"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Swiggy Cart UI</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f5f5f5;
}

.cart-container {
	width: 350px;
	background: white;
	margin: 50px auto;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.cart-item {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 10px 0;
	border-bottom: 1px solid #ddd;
}

.quantity-controls {
	display: flex;
	align-items: center;
}

.quantity-btn, .remove-btn, .proceed-to-checkout-btn {
	background: #ff6600;
	color: white;
	border: none;
	padding: 5px 10px;
	cursor: pointer;
	border-radius: 5px;
}

.quantity-btn:disabled {
	background: #ccc;
	cursor: not-allowed;
}

.total {
	font-weight: bold;
	padding: 10px 0;
	text-align: right;
}

.add-more-items a {
	display: inline-block;
	margin-top: 10px;
	color: white;
	background: #ff6600;
	padding: 8px 12px;
	text-decoration: none;
	border-radius: 5px;
}
</style>
</head>
<body>
	<div class="cart-container">
		<h2>Cart</h2>

		<%
		Cart cart = (Cart) session.getAttribute("cart");
		Integer restaurantId = (Integer) session.getAttribute("restaurantId");

		if (cart != null || !cart.getItems().isEmpty()) {
			for (CartItem item : cart.getItems().values()) {
		%>
		<div class="cart-item">
			<div>
				<h3><%=item.getName()%></h3>
				<p>
					Price: ₹
					<%=item.getPrice()%></p>
				<p>
					Total: ₹
					<%=item.getTotalPrice()%></p>

				<div class="quantity-controls">
					<form action="cart" method="post" style="display: inline;">
						<input type="hidden" name="itemId" value="<%=item.getItemId()%>">
						<input type="hidden" name="action" value="update"> <input
							type="hidden" name="quantity"
							value="<%=item.getQuantity() + 1%>">
						<button class="quantity-btn">+</button>
					</form>

					<p><%=item.getQuantity()%></p>

					<form action="cart" method="post" style="display: inline;">
						<input type="hidden" name="itemId" value="<%=item.getItemId()%>">
						<input type="hidden" name="action" value="update"> <input
							type="hidden" name="quantity"
							value="<%=item.getQuantity() - 1%>">
						<button class="quantity-btn" <%if (item.getQuantity() == 0) {%>
							disabled <%}%>>-</button>
					</form>
				</div>
			</div>

			<form action="cart" method="post">
				<input type="hidden" name="itemId" value="<%=item.getItemId()%>">
				<input type="hidden" name="action" value="remove">
				<button  class="remove-btn">Remove</button>
			</form>
		</div>
		<%
		}
		%>

		<div class="total">
			Grand Total:
			<%=cart.getTotalPrice()%></div>



		<div class="add-more-items">
			<a
				href="menu?restaurantId=<%=session.getAttribute("restaurantId")%>"
				class="btn add-more-items"> Add More Items</a><br>
			<br>
		</div>



		<%
		} else {
		%>
		<p style="text-align: center; color: #757575">Your Cart is Empty</p>
		<div class="add-more-items">
			<a
				href="menu?restaurantId=<%=session.getAttribute("restaurantId")%>">Add
				Items</a>
		</div>
		<%
		}
		%>
		<%
		if (session.getAttribute("cart") != null) {
		%>
		<form action="Checkout.jsp" method="post">
			<input type="submit" value="Proceed to Checkout"
				class="proceed-to-checkout-btn">
		</form>
		<%
		}
		%>
	</div>
</body>
</html>
