package models;

public class DinnerMenu extends Menu {
	
	public DinnerMenu(long id, long restaurant_id, String type) {
		super(id, restaurant_id, type);
		
		setType("dinner");
	}
	
	public DinnerMenu(long restaurant_id, String type) {
		setType("dinner");
	}

}
