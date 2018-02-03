package dao;

import models.Cart;
import models.Customer;
import models.MenuItem;

public interface CustomerDAO {
	int insertCustomer(Customer restaurant);
	int deleteCustomer(long id);
	Cart getCart(Customer customer);
	Cart addItemToCart(Cart cart, MenuItem item);
	Cart removeItemFromCart(Cart cart, MenuItem item);
}
