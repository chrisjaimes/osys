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
import dao.MenuDao;
import models.BreakfastMenu;
import models.DinnerMenu;
import models.LunchMenu;
import models.Menu;

@Repository
public class MenuRepository implements MenuDao {

	@Override
	public List<Menu> findMenusByRestaurantId(long restaurant_id) {

		MenuItemRepository mir = new MenuItemRepository();
		List<Menu> menu_list = new ArrayList<Menu>();
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
						bmenu.setMenuItems(mir.findItemsByMenu(bmenu.getId()));
					} else if(result.getString("type").trim().equals("lunch")) {
						lmenu = new LunchMenu(result.getLong("id"), result.getLong("restaurant_id"), result.getString("type"));
						menu_list.add(lmenu);
						lmenu.setMenuItems(mir.findItemsByMenu(lmenu.getId()));
					} else {
						dmenu = new DinnerMenu(result.getLong("id"), result.getLong("restaurant_id"), result.getString("type"));
						menu_list.add(dmenu);
						dmenu.setMenuItems(mir.findItemsByMenu(dmenu.getId()));
					}
				}
			
				return menu_list;
			
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public int insertMenu(long restaurant_id, Menu menu) {

		String sql = "";
	
		sql = "INSERT INTO menu (type, restaurant_id) "
				+ "VALUES (?, ?); ";
		
		try {
			
			PreparedStatement statement = SQLConnection.connection.prepareStatement
			(sql,  ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			if(menu.getType() == null || menu.getType().isEmpty())
				return Constants.DB_INSERT_OPERATION_INTERRUPTED;
			
			statement.setString(1,  menu.getType().trim());
			statement.setLong(2, restaurant_id);

			statement.execute();
			return Constants.SUCCESSFUL_DB_OPERATION;
			
		} catch (SQLException e) {
			return Constants.DB_ERROR;
		}
	}
	
	@Override
	public int deleteMenu(long restaurant_id, long id) {
		
		String sql = "DELETE FROM menu WHERE id = ? AND restaurant_id = ?";
		try {
			
			PreparedStatement statement = SQLConnection.connection.prepareStatement
			(sql,  ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			statement.setLong(1, id);
			statement.setLong(2,  restaurant_id);
			
			statement.execute();
			return Constants.SUCCESSFUL_DB_OPERATION;
			
		} catch (SQLException e) {
			System.out.println(e);
			return Constants.DB_DELETE_OPERATION_INTERRUPTED;
		}
	}
}
