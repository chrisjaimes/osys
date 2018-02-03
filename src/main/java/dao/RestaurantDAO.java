package dao;

import java.util.List;

import models.Restaurant;

public interface RestaurantDAO {
	List<Restaurant> findRestaurants();
	int insertRestaurant(Restaurant restaurant);
	int deleteRestaurant(long id);
}
