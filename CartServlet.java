package servlet;

import java.io.IOException;
import java.sql.SQLException;

import dao.MenuDAO;
import dao.impl.MenuDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.CartItem;
import model.Menu;


@WebServlet("/cart")
public class CartServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Cart Servlet Called");
		
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		
		
		
		Integer currentRestaurantId = (Integer)session.getAttribute("restaurantId");
		int newRestaurantId = Integer.parseInt(request.getParameter("restaurantId"));
	
		if(cart==null || newRestaurantId !=currentRestaurantId)
		{
			cart = new Cart();
			session.setAttribute("cart", cart);
			session.setAttribute("restaurantId", newRestaurantId);
		}
		
		String action = request.getParameter("action");
		try {
			if("add".equals(action)) {
				addItemToCart(request, cart);
			}else if("update".equals(action)) {
				updateToCartItem(request, cart);
			}else if("remove".equals(action)) {
				removeItemFromCart(request,cart);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		response.sendRedirect("Cart.jsp");
	}

//	private void addItemToCart(HttpServletRequest request, Cart cart) {
//	    int itemId = Integer.parseInt(request.getParameter("itemId"));
//	    int quantity = Integer.parseInt(request.getParameter("quantity"));
//
//	    MenuDAO menuDAO = new MenuDAOImpl();
//	    Menu menuItem = menuDAO.getMenu(itemId);
//	    
//	    System.out.println("The menu in cart Servlet "+ menuItem);
//
//	    if (menuItem != null) {
//	        CartItem item = new CartItem(
//	            menuItem.getMenuId(),
//	            menuItem.getRestaurantId(),
//	            menuItem.getItemName(),
//	            quantity,
//	            menuItem.getPrice()
//	        );
//	        cart.addCartItem(item);
//	    }
//	}
	
	private void addItemToCart(HttpServletRequest request, Cart cart) {
	    int itemId = Integer.parseInt(request.getParameter("itemId"));
	    int quantity = Integer.parseInt(request.getParameter("quantity"));

	    MenuDAO menuDAO = new MenuDAOImpl();
	    Menu menuItem = menuDAO.getMenu(itemId);

	    if (menuItem != null) {
	        System.out.println("Menu Item Found: " + menuItem);
	        CartItem item = new CartItem(
	            menuItem.getMenuId(),
	            menuItem.getRestaurantId(),
	            menuItem.getItemName(),
	            quantity,
	            menuItem.getPrice()
	        );
	        cart.addCartItem(item);
	    } else {
	        System.out.println("Menu Item Not Found for ID: " + itemId);
	    }
	}


	private void updateToCartItem(HttpServletRequest request, Cart cart)throws ClassNotFoundException, SQLException {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        
        cart.updateCartItem(itemId, quantity);
    }

    private void removeItemFromCart(HttpServletRequest request, Cart cart) {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        
        cart.removeCartItem(itemId);

    }
   

	
	
	
}



