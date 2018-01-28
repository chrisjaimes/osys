package models;

public abstract class Menu {
	private long id;
	private long restaurant_id;
	private String menu_type;
	
	public Menu(long id, long restaurant_id) {
		this.id = id;
		this.restaurant_id= restaurant_id;
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
	
	public class Category {
		private final String type;
		
		public Category(String type) {
			this.type = type;
		}
		
		public String getCategory() {
			return type;
		}
	}
}
