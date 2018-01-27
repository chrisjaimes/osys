package models;

import java.util.ArrayList;

public class LunchMenu extends Menu {
	
	public LunchMenu(long id, long restaurant_id, ArrayList<Category> categories) {
		super(id, restaurant_id, categories);
		
		setMenuType("lunch");
	}

}
