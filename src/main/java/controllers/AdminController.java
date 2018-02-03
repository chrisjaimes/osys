package controllers;

import java.util.AbstractMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import IO.Response;
import config.Constants;
import config.SQLConnection;
import connections.AdminConnection;
import error.AppErrorHandler;
import models.Menu;
import models.MenuItem;
import models.Restaurant;
import services.MenuItemService;
import services.MenuService;
import services.RestaurantService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	RestaurantService restaurant_service;
	@Autowired
	MenuService menu_service;
	@Autowired
	MenuItemService menu_item_service;
	
//    private final AtomicLong counter = new AtomicLong();

	@RequestMapping(value="/db", method=RequestMethod.POST)
	public Response connectToDB(@RequestBody(required=true) AdminConnection request) {
		
		AbstractMap.SimpleEntry<String, String> credentials;
		credentials = new AbstractMap.SimpleEntry<>(request.getUsername(), request.getPassword());
	
		int connection = SQLConnection.connect(credentials, request.getHost(), request.getPort());
		
		switch(connection) {
			case 1:
				return new Response("error", "401", Constants.INVALID_CREDENTIALS_MSG);
			case 2:
				return new Response("success", "200", Constants.ADMIN_CONNECTED_TO_DB_MSG);
			case 3:
				return new Response("error", "400", Constants.CONNECTION_TO_DB_FAILED_MSG);
		}
		
		return new Response("succes", "200", request.getUsername() + ":admin connected");
	}
	
	@RequestMapping(value="/disconnect", method=RequestMethod.GET)
	public Response closeConnectionToDB() {
		if(SQLConnection.closeConnection()) {
			return new Response("success", "200", "Connection to " + Constants.DATABASE + " was closed");
		} else {
			return new Response("error", "200", "Error trying to disconnect from " + Constants.DATABASE);
		}
	}
	
    @RequestMapping(value = "/addRestaurant", method = RequestMethod.POST)
    public Response addRestaurant(@RequestBody(required=true) Restaurant request) {
    	if(SQLConnection.connection != null) {
    		
    		int op = restaurant_service.insertRestaurant(request);
    		
    		if(op != Constants.SUCCESSFUL_DB_OPERATION)
    			return new AppErrorHandler().operationError(op);
    		
    		return new Response("success", "200", "restaurant " + request.getName() + " added");
    	}
    	  	
    	return new AppErrorHandler().unauthorized();
    }
    
    @RequestMapping(value="/getRestaurants", method = RequestMethod.GET)
    public Response getRestaurants() {
    	if(SQLConnection.connection != null) {
    		
    		List<Restaurant> restaurants = restaurant_service.findRestaurants();

    		for(int i = 0; i < restaurants.size(); i++)
    			System.out.println(restaurants.get(i).getId());
    		return new Response("succes", "200", restaurants+"");
    	}
    	  	
    	return new AppErrorHandler().unauthorized();
    }
    
    @RequestMapping(value="/deleteRestaurant", method = RequestMethod.DELETE)
    public Response deleteRestaurant(@RequestBody(required=true) Restaurant request) {
    	if(SQLConnection.connection != null) {
    		
    		int op = restaurant_service.deleteRestaurant(request.getId());
    		
    		if(op != Constants.SUCCESSFUL_DB_OPERATION) {
    			return new AppErrorHandler().operationError(op);
    		}
    		
    		return new Response("success", "200", "deleted");
    	}
    	
    	return new AppErrorHandler().unauthorized();
    }
    
    @RequestMapping(value="/{restId}/getMenu", method=RequestMethod.GET)
    public Response getMenu(@PathVariable("restId") long id) {
    	if(SQLConnection.connection != null) {
    		
    		List<Menu> menus = menu_service.findMenusByRestaurantId(id);

    		for(int i = 0; i < menus.size(); i++)
    			System.out.println(menus.get(i).getId());
    		return new Response("succes", "200", menus+"");
    	}
    	
    	return new AppErrorHandler().unauthorized();
    }
    
    @RequestMapping(value="/{restId}/addMenu", method=RequestMethod.POST)
    public Response addMenu(@PathVariable("restId") long rest_id, @RequestBody(required=true) Menu request) {
    	if(SQLConnection.connection != null) {
    		
    		int op = menu_service.insertMenu(rest_id, request);
    		
    		if(op != Constants.SUCCESSFUL_DB_OPERATION)
    			return new AppErrorHandler().operationError(op);
    		
    		return new Response("success", "200", "menu " + request.getType() + " added");
    	}
    	
    	return new AppErrorHandler().unauthorized();
    }
    
    @RequestMapping(value="/{restId}/deleteMenu", method = RequestMethod.DELETE)
    public Response deleteMenu(@PathVariable("restId") long rest_id, @RequestBody(required=true) Menu request) {
    	if(SQLConnection.connection != null) {
    		
    		int op = menu_service.deleteMenu(rest_id, request.getId());
    		
    		if(op != Constants.SUCCESSFUL_DB_OPERATION) {
    			return new AppErrorHandler().operationError(op);
    		}
    		
    		return new Response("success", "200", "deleted menu");
    	}
    	
    	return new AppErrorHandler().unauthorized();
    }
    
    @RequestMapping(value="/{restId}/{menuId}/addItem", method=RequestMethod.POST)
    public Response addMenuItem(@PathVariable("restId") long rest_id, @PathVariable("menuId") long menu_id, @RequestBody(required=true) MenuItem request) {
    	if(SQLConnection.connection != null) {
    		
    		int op = menu_item_service.insertMenuItem(menu_id, request);
    		
    		if(op != Constants.SUCCESSFUL_DB_OPERATION)
    			return new AppErrorHandler().operationError(op);
    		
    		return new Response("success", "200", "item " + request.getName() + " added");
    	}
    	
    	return new AppErrorHandler().unauthorized();
    }
    
    @RequestMapping(value="/{restId}/{menuId}/deleteItem", method = RequestMethod.DELETE)
    public Response deleteMenuItem(@PathVariable("restId") long rest_id, @PathVariable("menuId") long menu_id, @RequestBody(required=true) MenuItem request) {
    	if(SQLConnection.connection != null) {
    		
    		int op = menu_item_service.deleteMenuItem(menu_id, request.getId());
    		
    		if(op != Constants.SUCCESSFUL_DB_OPERATION) {
    			return new AppErrorHandler().operationError(op);
    		}
    		
    		return new Response("success", "200", "deleted menu");
    	}
    	
    	return new AppErrorHandler().unauthorized();
    }
}