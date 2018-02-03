package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.MenuItem;
import repositories.MenuItemRepository;

@Service
public class MenuItemService {

	@Autowired
	MenuItemRepository menu_item_repo;
	
	public List<MenuItem> findItemsByMenu(long menu_id) {
		return menu_item_repo.findItemsByMenu(menu_id);
	}
	public int insertMenuItem(long menu_id, MenuItem item) {
		return menu_item_repo.insertMenuItem(menu_id, item);
	}
	public int deleteMenuItem(long menu_id, long id) {
		return menu_item_repo.deleteMenuItem(menu_id, id);
	}
	
}
