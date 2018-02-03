package models;

public class Customer{

	private Cart cart;
	private String username, password, email, address;
	
	public Cart getCart() {
		return cart;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}