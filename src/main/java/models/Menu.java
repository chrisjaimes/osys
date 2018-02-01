package models;

public class Menu {
	private long id;
	private long restaurant_id;
	private String type;
	private MenuItem[] menu_items;
	
	public Menu() {}
	
	public Menu(long id, long restaurant_id, String menu_type) {
		this.id = id;
		this.restaurant_id= restaurant_id;
		this.type = menu_type;
	}
	
	public long getId() {
		return id;
	}
	
	public long getRestaurantId() {
		return restaurant_id;
	}
	
	public String getType() {
		return type.toLowerCase();
	}
	
	public MenuItem[] getItems() {
		return this.menu_items;
	}
	
	public void setType(String menu_type) {
		this.type = menu_type;
	}
	
	public void setMenuItems(MenuItem[] items) {
		this.menu_items = items;
	}
}
