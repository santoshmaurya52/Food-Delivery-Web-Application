package dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.UserDAO;
import model.User;

public class UserDAOImpl implements UserDAO {
	private final String url = "jdbc:mysql://localhost:3306/tapfood";
	private final String user = "root";
	private final String password = "Kanpur@1";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL driver
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Failed to load MySQL driver", e);
		}
	}

	// Get DB Connection
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	@Override
	public boolean addUser(User user) {

		boolean f = false;
		String query = "INSERT INTO users (name, username, password, email, phone) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getUsername());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getEmail());
			stmt.setString(5, user.getPhone());

			int i = stmt.executeUpdate();
			if (i == 1) {
				f = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return f;
	}

	@Override
	public User loginUser(String email, String password) {
		User user = null;
		String query = "SELECT * FROM users WHERE email=? AND password=?";

		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setUsername(rs.getString(3));
				user.setPassword(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setPhone(rs.getString(6));
				user.setAddress(rs.getString(7));
				user.setRole(rs.getString(8));
				user.setCreateDate(rs.getTimestamp(9));
				user.setLastLoginDate(rs.getTimestamp(10));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void updateUser(User user) {
		String query = "UPDATE users SET name=?, username=?, password=?, email=?, phone=?, address=?, role=?, lastLoginDate=? WHERE userId=?";

		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getUsername());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getEmail());
			stmt.setString(5, user.getPhone());
			stmt.setString(6, user.getAddress());
			stmt.setString(7, user.getRole());
			stmt.setTimestamp(8, user.getLastLoginDate());
			stmt.setInt(9, user.getUserId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(int userId) {
		String query = "DELETE FROM users WHERE userId = ?";

		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, userId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> getAllUsers() {
		String query = "SELECT * FROM users";
		List<User> users = new ArrayList<>();

		try (Connection conn = getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("userId"));
				user.setName(rs.getString("name"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setAddress(rs.getString("address"));
				user.setRole(rs.getString("role"));
				user.setCreateDate(rs.getTimestamp("createDate"));
				user.setLastLoginDate(rs.getTimestamp("lastLoginDate"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	
}
