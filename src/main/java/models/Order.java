package models;

public class Order {

	private MenuItem item;
	private int quantity;
	
	public Order(MenuItem item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}
	
	public MenuItem getItem() {
		return item;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setItem(MenuItem item) {
		this.item = item;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}	
