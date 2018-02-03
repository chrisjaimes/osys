package dao;

import java.util.List;

import models.MenuItem;

public interface MenuItemDao {
	List<MenuItem> findItemsByMenu(long menu_id);
	int insertMenuItem(long menu_id, MenuItem item);
	int deleteMenuItem(long menu_id, long id);
}
