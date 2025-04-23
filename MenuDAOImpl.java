package dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.MenuDAO;
import model.Menu;

public class MenuDAOImpl implements MenuDAO {
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
    public void addMenu(Menu menu) {
        String sql = "INSERT INTO menu (menuId, restaurantId, itemName, description, price, "
        		+ "isAvailable, rating, imagePath) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, menu.getMenuId());
            stmt.setInt(2, menu.getRestaurantId());
            stmt.setString(3, menu.getItemName());
            stmt.setString(4, menu.getDescription());
            stmt.setDouble(5, menu.getPrice());
            stmt.setString(6, menu.getIsAvailable());
            stmt.setDouble(7, menu.getRating());
            stmt.setString(8, menu.getImagePath());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Menu getMenu(int menuId) {
        Menu menuItem = null;
        String sql = "SELECT * FROM menu WHERE menuId = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, menuId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                menuItem = new Menu();
                menuItem.setMenuId(rs.getInt("menuId"));
                menuItem.setRestaurantId(rs.getInt("restaurantId"));
                menuItem.setItemName(rs.getString("itemName"));
                menuItem.setPrice(rs.getDouble("price"));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving menu item: " + e.getMessage()); // More user-friendly error message
            e.printStackTrace(); // Log full error in development
        }

        if (menuItem == null) {
            throw new RuntimeException("Menu item with ID " + menuId + " not found."); // Optional: Throw exception instead of returning null
        }

        return menuItem;
    }


    @Override
    public void updateMenu(Menu menu) {
        String sql = "UPDATE menu SET itemName=?, description=?, price=?, isAvailable=?, rating=?, "
        		+ "imagePath=? WHERE menuId=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, menu.getItemName());
            stmt.setString(2, menu.getDescription());
            stmt.setDouble(3, menu.getPrice());
            stmt.setString(4, menu.getIsAvailable());
            stmt.setDouble(5, menu.getRating());
            stmt.setString(6, menu.getImagePath());
            stmt.setInt(7, menu.getMenuId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMenu(int menuId) {
        String sql = "DELETE FROM menu WHERE menuId=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, menuId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Menu> getAllMenus() {
        List<Menu> menuList = new ArrayList<>();
        String sql = "SELECT * FROM menu";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                menuList.add(new Menu(
                        rs.getInt("menuId"),
                        rs.getInt("restaurantId"),
                        rs.getString("itemName"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getString("isAvailable"),
                        rs.getInt("rating"),
                        rs.getString("imagePath")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }
    
    @Override
    public List<Menu> getAllMenusByRestaurant(int restaurantId) {
        List<Menu> menuList = new ArrayList<>();
        String sql = "SELECT * FROM menu WHERE restaurantId = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, restaurantId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                menuList.add(new Menu(
                        rs.getInt("menuId"),
                        rs.getInt("restaurantId"),
                        rs.getString("itemName"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getString("isAvailable"),
                        rs.getDouble("rating"),
                        rs.getString("imagePath")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }
}

