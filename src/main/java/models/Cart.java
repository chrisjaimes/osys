package models;

public class Cart {
	private Order[] orders;
	
	public Order[] getOrder() {
		return orders;
	}
	
	public void setOrders(Order[] orders) {
		this.orders = orders;
	}
}
