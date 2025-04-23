package dao;

import java.util.List;

import model.OrderItem;

public interface OrderItemDAO {

	void addOrderItem(OrderItem orderItem);
	 OrderItem getOrderItem(int orderItemId);
	 void updateOrderItem(OrderItem orderItem);
	 void deleteOrderItem(int orderItemId);
	 List<OrderItem> getAllOrderItem();
	
}
