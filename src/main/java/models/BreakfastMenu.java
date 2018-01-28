package models;

public class BreakfastMenu extends Menu {
	
	public BreakfastMenu(long id, long restaurant_id) {
		super(id, restaurant_id);
		
		setMenuType("breakfast");
	}

}
