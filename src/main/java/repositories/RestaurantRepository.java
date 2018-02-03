package repositories;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import config.Constants;
import config.SQLConnection;
import dao.RestaurantDAO;
import models.Restaurant;

@Repository
public class RestaurantRepository implements RestaurantDAO {
 
    @Override
    public List<Restaurant> findRestaurants() {

		List<Restaurant> restaurant_list = new ArrayList<Restaurant>();
		Restaurant restaurant;
		
		String query = "SELECT * FROM restaurant;";

		try {
				PreparedStatement statement = SQLConnection.connection.prepareStatement
				(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				
	
				ResultSet result = statement.executeQuery();
				
				while(result.next()) {
					
					Array array = result.getArray("keywords");
					String[] keywords = (String[])array.getArray();
					
					restaurant = new Restaurant(result.getLong("id"), result.getString("name"), result.getString("address"), 
							result.getString("description"), keywords, result.getLong("bmenu_id"), 
							result.getLong("lmenu_id"), result.getLong("dmenu_id"));
					
					restaurant_list.add(restaurant);
				}
			
				Collections.sort(restaurant_list);
			
				return restaurant_list;
			
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	
    @Override
	public int insertRestaurant(Restaurant restaurant) {

		String sql = "";
	
		sql = "INSERT INTO restaurant (name, address, description, keywords) "
				+ "VALUES (?, ?, ?, ?); ";
		
		try {
			
			PreparedStatement statement = SQLConnection.connection.prepareStatement
			(sql,  ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			if(restaurant.getName() == null        || restaurant.getName().isEmpty() ||
			   restaurant.getAddress() == null     || restaurant.getAddress().isEmpty() ||
			   restaurant.getDescription() == null || restaurant.getDescription().isEmpty() ||
			   restaurant.getKeywords() == null    || restaurant.getKeywords().length == 0)
				return Constants.DB_INSERT_OPERATION_INTERRUPTED;
			
			statement.setString(1, restaurant.getName().trim());
			statement.setString(2, restaurant.getAddress().trim());
			statement.setString(3, restaurant.getDescription().trim());

			Array keywords = SQLConnection.connection.createArrayOf("text", restaurant.getKeywords());
			statement.setArray(4, keywords);
				
			statement.execute();
			return Constants.SUCCESSFUL_DB_OPERATION;
			
		} catch (SQLException e) {
			return Constants.DB_ERROR;
		}
	}
	
    @Override
	public int deleteRestaurant(long id) {
		
		String sql = "DELETE FROM restaurant WHERE id = ? ";
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
}