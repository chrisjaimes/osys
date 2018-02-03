package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.Restaurant;
import repositories.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	RestaurantRepository restaurant_repo;
	
	public List<Restaurant> findRestaurants() {
		return restaurant_repo.findRestaurants();
	}
	public int insertRestaurant(Restaurant restaurant) {
		return restaurant_repo.insertRestaurant(restaurant);
	}
	public int deleteRestaurant(long id) {
		return restaurant_repo.deleteRestaurant(id);
	}
	
}
