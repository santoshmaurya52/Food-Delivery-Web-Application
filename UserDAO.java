package dao;

import java.util.List;

import model.User;

public interface UserDAO {
	
	
    public boolean addUser(User user);
    public  User loginUser(String email, String password);
    void updateUser(User user);
    void deleteUser(int userId);
    List<User> getAllUsers();
   
}
