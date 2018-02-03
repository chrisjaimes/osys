package dao;

import java.util.List;

import models.Menu;

public interface MenuDao {
	List<Menu> findMenusByRestaurantId(long restaurant_id);
	int insertMenu(long restaurant_id, Menu menu);
	int deleteMenu(long restaurant_id, long id);
}
