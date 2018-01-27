package models;

import java.util.ArrayList;

public class DinnerMenu extends Menu {
	
	public DinnerMenu(long id, long restaurant_id, ArrayList<Category> categories) {
		super(id, restaurant_id, categories);
		
		setMenuType("dinner");
	}

}
