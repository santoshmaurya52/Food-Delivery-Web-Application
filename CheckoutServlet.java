package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import dao.impl.OrderDAOImpl;
import dao.impl.OrderItemDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.CartItem;
import model.Order;
import model.OrderItem;
import model.User;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

	private OrderDAOImpl orderDAOImpl;
	OrderItemDAOImpl orderItemDAOImpl;

	@Override
	public void init() {
		try {
			orderDAOImpl = new OrderDAOImpl();
			OrderItemDAOImpl orderItemDAOImpl = new OrderItemDAOImpl();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to initialize OrderDAO", e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		User user = (User) session.getAttribute("loggedInuser");

		if (cart != null && user != null && !cart.getItems().isEmpty()) {

			String paymentMode = request.getParameter("paymentMode");
			String address = request.getParameter("address");
			Integer restaurantId = (Integer) session.getAttribute("restaurantId");

			Order order = new Order();
			order.setUserId(user.getUserId());
			order.setRestaurantId(restaurantId);
			order.setOrderDate(new Timestamp(new Date().getTime()));
			order.setPaymentMode(paymentMode);
			order.setAddress(address);
			order.setStatus("Pending");

			double totalAmount = 0;
			for (CartItem item : cart.getItems().values()) {

				totalAmount += item.getPrice() * item.getQuantity();
			}
			order.setTotalAmount(totalAmount);

			int orderId = orderDAOImpl.addOrder(order);

			for (CartItem item : cart.getItems().values()) {
				int itemId = item.getItemId();
				//String name = item.getName();
				double price = item.getPrice();
				int quantity = item.getQuantity();
				
				double totalPrice = price*quantity;
				
				OrderItem orderItem = new OrderItem(orderId, itemId, quantity, totalAmount);
				orderItemDAOImpl.addOrderItem(orderItem);
				
			}

			session.removeAttribute("cart");
			session.setAttribute("order", order);

			response.sendRedirect("Confirmation.jsp");
		} else {
			response.sendRedirect("cart.jsp");
		}
	}
}
