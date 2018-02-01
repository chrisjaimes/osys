package models;

public class Order {

	private long id;
	private MenuItem item;
	private int quantity;
	
	public Order(int id, MenuItem item, int quantity) {
		this.id = id;
		this.item = item;
		this.quantity = quantity;
	}
	
	public long getId() {
		return id;
	}
	
	public MenuItem getItem() {
		return item;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setItem(MenuItem item) {
		this.item = item;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
