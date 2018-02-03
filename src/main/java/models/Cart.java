package models;

import java.util.List;

public class Cart {
	
	private long customer_id;
	private List<Order> orders;
	
	public long getCustomerId() {
		return customer_id;
	}
	
	public List<Order> getOrder() {
		return orders;
	}
	
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public void setCustomerId(long customer_id) {
		this.customer_id = customer_id;
	}
}
