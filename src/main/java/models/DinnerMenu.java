package models;

public class DinnerMenu extends Menu {
	
	public DinnerMenu(long id, long restaurant_id) {
		super(id, restaurant_id);
		
		setMenuType("dinner");
	}

}
