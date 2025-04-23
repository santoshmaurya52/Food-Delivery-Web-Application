package dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import dao.RestaurantDAO;
import model.Restaurant;

public class RestaurantDAOImpl implements RestaurantDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/tapfood";
	private static final String USER = "root";
	private static final String PASSWORD = "Kanpur@1";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Failed to load MySQL driver", e);
		}
	}

	@Override
	public void addRestaurant(Restaurant restaurant) {
		String sql = "INSERT INTO restaurants (name, address, phone, cuisineType, deliveryTime, adminUserId, rating, isActive, imagePath) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, restaurant.getName());
			stmt.setString(2, restaurant.getAddress());
			stmt.setString(3, restaurant.getPhone());
			stmt.setString(4, restaurant.getCuisineType());
			stmt.setString(5, restaurant.getDeliveryTime());
			stmt.setInt(6, restaurant.getAdminUserId());
			stmt.setDouble(7, restaurant.getRating());
			stmt.setString(8, restaurant.getIsActive());
			stmt.setString(9, restaurant.getImagePath());

			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error while adding restaurant:");
			e.printStackTrace();
		}
	}

	@Override
	public Restaurant getRestaurant(int restaurantId) {
		String sql = "SELECT * FROM restaurants WHERE restaurantId = ?";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, restaurantId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Restaurant(rs.getInt("restaurantId"), rs.getString("name"), rs.getString("address"),
						rs.getString("phone"), rs.getString("cuisineType"), rs.getString("deliveryTime"),
						rs.getInt("adminUserId"), rs.getDouble("rating"), rs.getString("isActive"),
						rs.getString("imagePath"));
			}
		} catch (SQLException e) {
			System.out.println("Error while fetching single restaurant:");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateRestaurant(Restaurant restaurant) {
		String sql = "UPDATE restaurants SET name=?, address=?, phone=?, cuisineType=?, deliveryTime=?, adminUserId=?, rating=?, isActive=?, imagePath=? WHERE restaurantId=?";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, restaurant.getName());
			stmt.setString(2, restaurant.getAddress());
			stmt.setString(3, restaurant.getPhone());
			stmt.setString(4, restaurant.getCuisineType());
			stmt.setString(5, restaurant.getDeliveryTime());
			stmt.setInt(6, restaurant.getAdminUserId());
			stmt.setDouble(7, restaurant.getRating());
			stmt.setString(8, restaurant.getIsActive());
			stmt.setString(9, restaurant.getImagePath());
			stmt.setInt(10, restaurant.getRestaurantId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error while updating restaurant:");
			e.printStackTrace();
		}
	}

	@Override
	public void deleteRestaurant(int restaurantId) {
		String sql = "DELETE FROM restaurants WHERE restaurantId=?";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, restaurantId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error while deleting restaurant:");
			e.printStackTrace();
		}
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		List<Restaurant> restaurants = new ArrayList<>();
		Restaurant rs = null;
		String sql = "SELECT * FROM restaurants";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet res = stmt.executeQuery()) {

			while (res.next()) {

				rs = new Restaurant();
				rs.setRestaurantId(res.getInt(1));
				rs.setName(res.getString(2));
				rs.setAddress(res.getString(3));
				rs.setPhone(res.getString(4));
				rs.setCuisineType(res.getString(5));
				rs.setDeliveryTime(res.getString(6));
				rs.setAdminUserId(res.getInt(7));
				rs.setRating(res.getDouble(8));
				rs.setIsActive(res.getString(9));
				rs.setImagePath(res.getString(10));
				
				restaurants.add(rs);
//                restaurants.add(new Restaurant(
//                    rs.getInt("restaurantId"),
//                    rs.getString("name"),
//                    rs.getString("address"),
//                    rs.getString("phone"),
//                    rs.getString("cuisineType"),
//                    rs.getString("deliveryTime"),
//                    rs.getInt("adminUserId"),
//                    rs.getDouble("rating"),
//                    rs.getString("isActive"),
//                    rs.getString("imagePath")
//                ));
			}
		} catch (SQLException e) {
			System.out.println("Error while fetching all restaurants:");
			e.printStackTrace();
		}
		return restaurants;
	}
}
