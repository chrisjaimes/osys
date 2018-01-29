package models;

public abstract class Menu {
	private long id;
	private long restaurant_id;
	private String menu_type;
	private MenuItem[] menu_items;
	
	public Menu(long id, long restaurant_id, String menu_type) {
		this.id = id;
		this.restaurant_id= restaurant_id;
		this.menu_type = menu_type;
	}
	
	public Menu() {}
	
	public long getId() {
		return id;
	}
	
	public long getRestaurantId() {
		return restaurant_id;
	}
	
	public String getMenuType() {
		return menu_type;
	}
	
	public void setMenuType(String menu_type) {
		this.menu_type = menu_type;
	}
	
	public MenuItem[] getItems() {
		return this.menu_items;
	}
	
	public void setMenuItems(MenuItem[] items) {
		this.menu_items = items;
	}
}
