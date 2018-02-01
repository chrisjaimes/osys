package models;

public class BreakfastMenu extends Menu {
	
	public BreakfastMenu(long id, long restaurant_id, String type) {
		super(id, restaurant_id, type);
		
		setType("breakfast");
	}
	
	public BreakfastMenu(long restaurant_id, String type) {
		setType("breakfast");
	}

}
