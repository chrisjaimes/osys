package models;

import java.util.ArrayList;

public abstract class Menu {
	private final long id;
	private final long restaurant_id;
	private String menu_type;
	private ArrayList<Category> categories;
	
	Menu(long id, long restaurant_id, ArrayList<Category> categories) {
		this.id = id;
		this.restaurant_id= restaurant_id;
		this.categories = categories;
	}
	
	public long getId() {
		return id;
	}
	
	public long getRestaurantId() {
		return restaurant_id;
	}
	
	public ArrayList<Category> getCategories() {
		return categories;
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
