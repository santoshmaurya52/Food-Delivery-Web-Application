package dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.OrderDAO;
import model.Order;

public class OrderDAOImpl implements OrderDAO {

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
    public int addOrder(Order order) {
        String sql = "INSERT INTO orders (restaurantId, userId, orderDate, totalAmount, status, paymentMode, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int orderId = 0;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, order.getRestaurantId());
            stmt.setInt(2, order.getUserId());
            stmt.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            stmt.setDouble(4, order.getTotalAmount());
            stmt.setString(5, order.getStatus());
            stmt.setString(6, order.getPaymentMode());
            stmt.setString(7, order.getAddress());

            stmt.executeUpdate();

            ResultSet res = stmt.getGeneratedKeys();
            if (res.next()) {
                orderId = res.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderId;
    }

    @Override
    public Order getOrder(int orderId) {
        String sql = "SELECT * FROM orders WHERE orderId = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Order(
                        rs.getInt("orderId"),
                        rs.getInt("restaurantId"),
                        rs.getInt("userId"),
                        rs.getDate("orderDate"),
                        rs.getDouble("totalAmount"),
                        rs.getString("status"),
                        rs.getString("paymentMode"),
                        rs.getString("address")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void updateOrder(Order order) {
        String sql = "UPDATE orders SET restaurantId=?, userId=?, orderDate=?, totalAmount=?, status=?, paymentMode=?, address=? WHERE orderId=?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, order.getRestaurantId());
            stmt.setInt(2, order.getUserId());
            stmt.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            stmt.setDouble(4, order.getTotalAmount());
            stmt.setString(5, order.getStatus());
            stmt.setString(6, order.getPaymentMode());
            stmt.setString(7, order.getAddress());
            stmt.setInt(8, order.getOrderId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        String sql = "DELETE FROM orders WHERE orderId=?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getAllOrder() {
        String sql = "SELECT * FROM orders";

        List<Order> orders = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("orderId"),
                        rs.getInt("restaurantId"),
                        rs.getInt("userId"),
                        rs.getDate("orderDate"),
                        rs.getDouble("totalAmount"),
                        rs.getString("status"),
                        rs.getString("paymentMode"),
                        rs.getString("address")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }
}
