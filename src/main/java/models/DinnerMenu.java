package models;

public class DinnerMenu extends Menu {
	
	public DinnerMenu(long id, long restaurant_id, String type) {
		super(id, restaurant_id, type);
		
		setMenuType("dinner");
	}

}
