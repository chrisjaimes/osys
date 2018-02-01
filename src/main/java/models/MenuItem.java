package models;

public class MenuItem implements Comparable<MenuItem> {
	
	private long id;
	private long menu_id;
	private String name, description, category;
	private double price;
	
	public MenuItem(long id, long menu_id, String name, String description, String category, double price) {
		this.id = id;
		this.menu_id = menu_id;
		this.name = name;
		this.description = description;
		this.category = category;
		this.price = price;
	}
	
	public MenuItem() {}

	public int compareTo(MenuItem item) {
		return this.name.compareTo(item.name);
	}
	
	public long getId() {
		return id;
	}
	
	public long getMenuIdOfItem() {
		return menu_id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getCategory() {
		return category.toLowerCase();
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setMenuIdOfItem(long menu_id) {
		this.menu_id = menu_id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
}

