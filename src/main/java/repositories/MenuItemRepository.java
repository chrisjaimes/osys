package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import config.Constants;
import config.SQLConnection;
import dao.MenuItemDao;
import models.MenuItem;

@Repository
public class MenuItemRepository implements MenuItemDao{
	
	@Override
//	this function is called from MenuRepository.getMenusByRestaurant to retrieve all items in certain menu.
	public List<MenuItem> findItemsByMenu(long menu_id) {

			List<MenuItem> item_list;
			MenuItem item;
//			int total_items = 0;
			
			String query = "SELECT * FROM menu_item WHERE menu_id = ?;";

			try {
					PreparedStatement statement = SQLConnection.connection.prepareStatement
					(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					
					statement.setLong(1, menu_id);
					
					ResultSet result = statement.executeQuery();
					
					result.last();
//					total_items = result.getRow();
					
					item_list = new ArrayList<MenuItem>();
					
					while(result.next()) {
						
						item = new MenuItem(result.getLong("id"), result.getLong("menu_id"), result.getString("name"), 
								result.getString("description"), result.getString("category"), result.getDouble("price"));
						
						item_list.add(item);
					}
				
					return item_list;
				
			} catch (SQLException e) {
				System.out.println(e);
				return null;
			}
		}
	
	@Override
	public int insertMenuItem(long menu_id, MenuItem item) {

		String sql = "";
		
		sql = "INSERT INTO menu_item (menu_id, category, name, description, price) "
				+ "VALUES (?, ?, ?, ?, ?); ";
		
		try {
			
			PreparedStatement statement = SQLConnection.connection.prepareStatement
			(sql,  ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			if(item.getName() == null        || item.getName().isEmpty() ||
			   item.getDescription() == null || item.getDescription().isEmpty() ||
			   item.getCategory() == null    || item.getCategory().isEmpty())
				return Constants.DB_INSERT_OPERATION_INTERRUPTED;
			
			statement.setLong(1, menu_id);
			statement.setString(2, item.getCategory().trim());
			statement.setString(3, item.getName().trim());
			statement.setString(4, item.getDescription().trim());
			statement.setDouble(5, item.getPrice());

			statement.execute();
			return Constants.SUCCESSFUL_DB_OPERATION;
			
		} catch (SQLException e) {
			System.out.println(e);
			return Constants.DB_ERROR;
		}

	}
	
	@Override
	public int deleteMenuItem(long menu_id, long id) {
		
		String sql = "DELETE FROM menu_item WHERE id = ? AND menu_id = ?";
		try {
			
			PreparedStatement statement = SQLConnection.connection.prepareStatement
			(sql,  ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			statement.setLong(1, id);
			statement.setLong(2,  menu_id);
			
			statement.execute();
			return Constants.SUCCESSFUL_DB_OPERATION;
			
		} catch (SQLException e) {
			System.out.println(e);
			return Constants.DB_DELETE_OPERATION_INTERRUPTED;
		}
	}
 
}

