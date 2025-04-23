package dao;

import java.util.List;

import model.Order;

public interface OrderDAO {

	int addOrder(Order order);
	Order getOrder(int orderId);
	void updateOrder(Order order);
	void deleteOrder(int orderId);
	List<Order> getAllOrder();
	
}
