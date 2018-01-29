package models;

public class LunchMenu extends Menu {
	
	public LunchMenu(long id, long restaurant_id, String type) {
		super(id, restaurant_id, type);
		
		setMenuType("lunch");
	}

}
