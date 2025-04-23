package dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.OrderItemDAO;
import model.OrderItem;

public class OrderItemDAOImpl implements OrderItemDAO{

	 	private static final String URL = "jdbc:mysql://localhost:3306/tapfood";
	    private static final String USER = "root";
	    private static final String PASSWORD = "Kanpur@1";
	
	    static {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL driver
	        } catch (ClassNotFoundException e) {
	            throw new RuntimeException("Failed to load MySQL driver", e);
	        }
	    }
	    
	    
	@Override
	public void addOrderItem(OrderItem orderItem) {

		 String sql = "INSERT INTO order_items (orderId, menuId, quantity, totalAmount) VALUES (?, ?, ?, ?)";
	        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, orderItem.getOrderId());
	            pstmt.setInt(2, orderItem.getMenuId());
	            pstmt.setInt(3, orderItem.getQuantity());
	            pstmt.setDouble(4, orderItem.getTotalAmount());
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}

	@Override
	public OrderItem getOrderItem(int orderItemId) {
		
        String sql = "SELECT * FROM order_items WHERE orderItemId = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderItemId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new OrderItem(
                        rs.getInt("orderId"),
                        rs.getInt("menuId"),
                        rs.getInt("quantity"),
                        rs.getDouble("totalAmount")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public void updateOrderItem(OrderItem orderItem) {
		
		 String sql = "UPDATE order_items SET orderId = ?, menuId = ?, quantity = ?, totalAmount = ? WHERE orderItemId = ?";
	        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, orderItem.getOrderId());
	            pstmt.setInt(2, orderItem.getMenuId());
	            pstmt.setInt(3, orderItem.getQuantity());
	            pstmt.setDouble(4, orderItem.getTotalAmount());
	            pstmt.setInt(5, orderItem.getOrderItemId());
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}

	@Override
	public void deleteOrderItem(int orderItemId) {
		
		String sql = "DELETE FROM order_items WHERE orderItemId = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderItemId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public List<OrderItem> getAllOrderItem() {

		 List<OrderItem> orderItem = new ArrayList<>();
	        String sql = "SELECT * FROM order_items";
	        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {
	            while (rs.next()) {
	                orderItem.add(new OrderItem(
	                        rs.getInt("orderId"),
	                        rs.getInt("menuId"),
	                        rs.getInt("quantity"),
	                        rs.getDouble("totalAmount")
	                ));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return orderItem;
		
	}

}
