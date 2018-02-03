package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import config.Constants;
import config.CustomerConnection;
import config.SQLConnection;
import dao.CustomerDAO;
import models.Cart;
import models.Customer;
import models.MenuItem;
import models.Order;

@Repository
public class CustomerRepository implements CustomerDAO {
	
	@Override
	public int insertCustomer(Customer customer) {
		
		CustomerConnection cc = new CustomerConnection();
		
		if(cc.connection == null)
			cc.connect();
		
		String sql = "";
		
		sql = "INSERT INTO customer (username, password, email, address) "
				+ "VALUES (?, ?, ?, ?); ";
		
		try {
			
			PreparedStatement statement = cc.connection.prepareStatement
			(sql,  ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			if(customer.getUsername() == null        || customer.getUsername().isEmpty() ||
			   customer.getAddress() == null     || customer.getAddress().isEmpty() ||
			   customer.getEmail() == null || customer.getEmail().isEmpty() ||
			   customer.getPassword() == null    || customer.getPassword().isEmpty())
				return Constants.DB_INSERT_OPERATION_INTERRUPTED;
			
			statement.setString(1, customer.getUsername().trim());
			statement.setString(2, customer.getPassword().trim());
			statement.setString(3, customer.getEmail().trim());
			statement.setString(4, customer.getAddress().trim());

			statement.execute();
			return Constants.SUCCESSFUL_DB_OPERATION;
			
		} catch (SQLException e) {
			System.out.println(e);
			return Constants.DB_ERROR;
		}

	}

	@Override
	public int deleteCustomer(long id) {
		String sql = "DELETE FROM customer WHERE id = ? ";
		try {
			
			PreparedStatement statement = SQLConnection.connection.prepareStatement
			(sql,  ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			statement.setLong(1, id);

			statement.execute();
			return Constants.SUCCESSFUL_DB_OPERATION;
			
		} catch (SQLException e) {
			System.out.println(e);
			return Constants.DB_DELETE_OPERATION_INTERRUPTED;
		}
	}

	@Override
	public Cart getCart(Customer customer) {
		return customer.getCart();
	}

	@Override
	public Cart addItemToCart(Cart cart, MenuItem item) {
		List<Order> orders;
		orders = cart.getOrder();
		
		boolean added = false;
		
		for(int i = 0; i < orders.size(); i++) {
			if(orders.get(i).getItem() == item) {
				orders.get(i).setQuantity(orders.get(i).getQuantity() + 1);
				added = true;
			}
		}
		
		if(!added)
			orders.add(new Order(item, 1));
		
		cart.setOrders(orders);
		return cart;
	}

	@Override
	public Cart removeItemFromCart(Cart cart, MenuItem item) {
		List<Order> orders;
		orders = cart.getOrder();
		
		for(int i = 0; i < orders.size(); i++) {
			if(orders.get(i).getItem() == item) {
				orders.get(i).setQuantity(orders.get(i).getQuantity() - 1);
				
				if(orders.get(i).getQuantity() == 0) {
					orders.remove(i);
				}
			}
		}
		
		cart.setOrders(orders);
		return cart;
	}

}
