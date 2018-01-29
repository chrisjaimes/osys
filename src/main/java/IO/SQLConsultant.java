package IO;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.Constants;
import config.SQLConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.BreakfastMenu;
import models.DinnerMenu;
import models.LunchMenu;
import models.Menu;
import models.MenuItem;
import models.Restaurant;

public class SQLConsultant {

	//SQL Queries for Restaurants
	
	public static ObservableList<Restaurant> getRestaurants() {

		ObservableList<Restaurant> restaurant_list = FXCollections.observableArrayList();
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
	
	//SQL Queries for Menus
	
	public static ObservableList<Menu> getMenusByRestaurant(long restaurant_id) {

		ObservableList<Menu> menu_list = FXCollections.observableArrayList();
		BreakfastMenu bmenu;
		LunchMenu lmenu;
		DinnerMenu dmenu;
		
		String query = "SELECT * FROM menu WHERE restaurant_id = ?;";

		try {
				PreparedStatement statement = SQLConnection.connection.prepareStatement
				(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				
				statement.setLong(1, restaurant_id);
				
				ResultSet result = statement.executeQuery();
				
				while(result.next()) {
					
					if(result.getString("type").trim().equals("breakfast")) {
						bmenu = new BreakfastMenu(result.getLong("id"), result.getLong("restaurant_id"), result.getString("type"));
						menu_list.add(bmenu);
						bmenu.setMenuItems(getItemsByMenu(bmenu.getId()));
					} else if(result.getString("type").trim().equals("lunch")) {
						lmenu = new LunchMenu(result.getLong("id"), result.getLong("restaurant_id"), result.getString("type"));
						menu_list.add(lmenu);
						lmenu.setMenuItems(getItemsByMenu(lmenu.getId()));
					} else {
						dmenu = new DinnerMenu(result.getLong("id"), result.getLong("restaurant_id"), result.getString("type"));
						menu_list.add(dmenu);
						dmenu.setMenuItems(getItemsByMenu(dmenu.getId()));
					}
				}
			
				return menu_list;
			
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public static MenuItem[] getItemsByMenu(long menu_id) {

		MenuItem[] item_list;
		MenuItem item;
		int total_items = 0, index = 0;
		
		String query = "SELECT * FROM menu_item WHERE menu_id = ?;";

		try {
				PreparedStatement statement = SQLConnection.connection.prepareStatement
				(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				
				statement.setLong(1, menu_id);
				
				ResultSet result = statement.executeQuery();
				
				result.last();
				total_items = result.getRow();
				
				item_list = new MenuItem[total_items];
				
				while(result.next()) {
					
					item = new MenuItem(result.getLong("id"), result.getLong("menu_id"), result.getString("name"), 
							result.getString("description"), result.getString("category"));
					
					item_list[index++] = item;
				}
			
				return item_list;
			
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
}
