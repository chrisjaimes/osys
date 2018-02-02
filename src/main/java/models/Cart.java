package models;

public class Cart {
	
	private long customer_id;
	private Order[] orders;
	
	public long getCustomerId() {
		return customer_id;
	}
	
	public Order[] getOrder() {
		return orders;
	}
	
	public void setOrders(Order[] orders) {
		this.orders = orders;
	}
	
	public void setCustomerId(long customer_id) {
		this.customer_id = customer_id;
	}
}
