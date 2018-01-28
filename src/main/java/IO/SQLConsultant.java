package IO;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import config.Constants;
import config.SQLConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Restaurant;

public class SQLConsultant {

	public static ObservableList<Restaurant> getRestaurants() {

		ObservableList<Restaurant> restaurant_list = FXCollections.observableArrayList();
		Restaurant restaurant;
		
		String query = "SELECT * FROM restaurant;";

		try {
				PreparedStatement statement = SQLConnection.connection.prepareStatement
				(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				
	
				ResultSet result = statement.executeQuery();
				
				restaurant_list = FXCollections.observableArrayList();
				
				while(result.next()) {
					
					Array array = result.getArray("keywords");
					String[] keywords = (String[])array.getArray();
					
					restaurant = new Restaurant(result.getLong("id"), result.getString("name"), result.getString("address"), 
							result.getString("description"), keywords, result.getLong("bmenu_id"), 
							result.getLong("lmenu_id"), result.getLong("dmenu_id"));
					
					restaurant_list.add(restaurant);
				}
			
				FXCollections.sort(restaurant_list);
			
				return restaurant_list;
			
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public static int addRestaurant(Restaurant restaurant) {

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
	
	public static int deleteRestaurant(long id) {
		
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
