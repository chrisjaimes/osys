package models;

public class LunchMenu extends Menu {
	
	public LunchMenu(long id, long restaurant_id) {
		super(id, restaurant_id);
		
		setMenuType("lunch");
	}

}
