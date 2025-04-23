<%@page import="java.awt.MenuItem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "java.util.List,model.Menu" %>    

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restaurant Menu</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #fc8019;
            text-align: center;
        }
        .menu {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 20px;
            max-width: 900px;
            margin: 0 auto;
          
        }
        .menu-item {
            background: white;
            padding: 15px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            height: 85%;
        }
        
        .menu-item img{
        	
        	width: 100%;
        	height: 50%;
        }
        
        .menu-item h3 {
            margin: 0;
            font-size: 1.2em;
        }
        .menu-item p {
            font-size: 0.9em;
            color: gray;
        }
        .add-to-cart {
            background-color: #ff6600;
            color: white;
            border: none;
            padding: 10px;
            cursor: pointer;
            border-radius: 5px;
            margin-top: 10px;
        }
        .add-to-cart:hover {
            background-color: #e65c00;
        }
        
        .menu-item .rating{
        	
        	color: green;
        }
        
    </style>
</head>
<body>
    <h1>Restaurant Menu</h1>
    <div class="menu">
    <%
    	List<Menu> menulist = (List<Menu>)request.getAttribute("menus");
    	for(Menu m : menulist )
    	{
    %>
        <div class="menu-item">
        <img alt="" src=<%= m.getImagePath() %>>
            <h3><%= m.getItemName() %></h3>
            <p><%= m.getDescription() %></p>
            <h4 class="rating">★ <%= m.getRating() %> |  ₹ <%= m.getPrice() %></h4>
            
            <form action="cart" method="post">
            	<input type="hidden" name="restaurantId" value="<%= request.getParameter("restaurantId")%>">
            	<input type="hidden" name="itemId" value="<%= m.getMenuId() %>">
            	<input type="hidden" name="quantity" value="1" min="1">
            	<input type="hidden" name="action" value="add">
            	<button class="add-to-cart">Add to Cart</button>
            </form>
            
        </div>
        <%
    		}
        %>
        
    </div>
</body>
</html>
