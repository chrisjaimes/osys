package models;

import java.util.ArrayList;

public class BreakfastMenu extends Menu {
	
	public BreakfastMenu(long id, long restaurant_id, ArrayList<Category> categories) {
		super(id, restaurant_id, categories);
		
		setMenuType("breakfast");
	}

}
