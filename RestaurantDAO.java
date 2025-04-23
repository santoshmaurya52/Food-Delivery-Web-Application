package dao;

import java.util.List;

import model.Restaurant;

public interface RestaurantDAO {

	
	void addRestaurant(Restaurant restaurant);
	Restaurant getRestaurant(int restaurantId);
	void updateRestaurant(Restaurant restaurant);
	void deleteRestaurant(int restaurantId);
	public List<Restaurant> getAllRestaurants();	

}
