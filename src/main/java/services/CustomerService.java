package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.Cart;
import models.Customer;
import models.MenuItem;
import repositories.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customer_repo;
	
	public int insertCustomer(Customer customer) {
		return customer_repo.insertCustomer(customer);
	}
	public int deleteCustomer(long id) {
		return customer_repo.deleteCustomer(id);
	}
	public Cart getCart(Customer customer) {
		return customer_repo.getCart(customer);
	}
	public Cart addItemToCart(Cart cart, MenuItem item) {
		return customer_repo.addItemToCart(cart, item);
	}
	public Cart removeItemFromCart(Cart cart, MenuItem item) {
		return customer_repo.removeItemFromCart(cart, item);
	}
}
