
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.List, model.Restaurant"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Restaurant Listing - Swiggy Style</title>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	font-family: Arial, sans-serif;
}

.navbar {
	display: flex;
	justify-content: space-between;
	align-items: center;
	background-color: #444;
	color: white;
	padding: 15px 20px;
}

.navbar ul {
	list-style: none;
	display: flex;
}

.navbar ul li {
	padding: 0 15px;
}

.navbar ul li a {
	color: white;
	text-decoration: none;
}

.navbar ul li a:hover {
	text-decoration: none;
}

body {
	font-family: Arial, sans-serif;
	background-color: #fc8019; /* Swiggy-like background color */
	margin: 50px;
	padding: 40px;
}

.container {
	max-width: 1200px;
	margin: auto;
}

.grid {
	display: grid;
	grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
	gap: 20px;
}

.restaurant-card {
	background: white;
	padding: 15px;
	border-radius: 10px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
	text-align: center;
}

.restaurant-card img {
	width: 100%;
	border-radius: 10px;
	height: 70%
}

.restaurant-card h3 {
	margin: 10px 0 5px;
	font-size: 18px;
}

.restaurant-card p {
	color: #777;
	font-size: 14px;
}

.rating {
	color: green;
}

.footer {
	color: white;
	text-align: center;
	padding: 15px 20px;
}
</style>
</head>
<body>


	<div class="navbar">
		<h2>Swiggy</h2>
		<ul>
			<li><a href="Login.jsp">Login</a></li>
			<li><a href="Help.html">Help</a></li>
			<li><a href="Contact.html">Contact</a></li>
		</ul>
	</div>
	<br>
	<br>
	<br>
	<br>

	<div class="container">
		<h1>Restaurant with Online Food delivery</h1>
		<div class="grid">

			<%
			List<Restaurant> allRestaurants = (List<Restaurant>) session.getAttribute("restaurants");
			if (allRestaurants != null) {
				for (Restaurant r : allRestaurants) {
			%>
			<div class="restaurant-card">
				<a href="menu?restaurantId=<%=r.getRestaurantId()%>"> <img
					src="<%=r.getImagePath()%>" alt="">
				</a>
				<h3><%=r.getName()%></h3>
				<h4 class="rating">
					★
					<%=r.getRating()%>
					|
					<%=r.getDeliveryTime()%></h4>
				<p><%=r.getCuisineType()%></p>
				<p><%=r.getAddress()%></p>
			</div>
			<%
			}
			} else {
			%>
			<p style="color: white;">No restaurants available.</p>
			<%
			}
			%>

		</div>
	</div>

	<footer>
		<div class="footer">Copywrite &copy;
			www.mauryaanand168@gmail.com | All rights reserved</div>
	</footer>
</body>
</html>
