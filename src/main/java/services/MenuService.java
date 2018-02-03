package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.Menu;
import repositories.MenuRepository;

@Service
public class MenuService {

	@Autowired
	MenuRepository menu_repo;
	
	public List<Menu> findMenusByRestaurantId(long restaurant_id) {
		return menu_repo.findMenusByRestaurantId(restaurant_id);
	}
	public int insertMenu(long restaurant_id, Menu menu) {
		return menu_repo.insertMenu(restaurant_id, menu);
	}
	public int deleteMenu(long restaurant_id, long id) {
		return menu_repo.deleteMenu(restaurant_id, id);
	}
	
}
