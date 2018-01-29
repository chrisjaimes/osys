package admin;

import java.util.AbstractMap;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import IO.Response;
import IO.SQLConsultant;
import config.Constants;
import config.SQLConnection;
import error.AppErrorHandler;
import javafx.collections.ObservableList;
import models.Menu;
import models.Restaurant;

@RestController
@RequestMapping("/admin")
public class AdminController {

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
    		
    		int op = SQLConsultant.addRestaurant(request);
    		
    		if(op != Constants.SUCCESSFUL_DB_OPERATION)
    			return new AppErrorHandler().operationError(op);
    		
    		return new Response("success", "200", "restaurant " + request.getName() + " added");
    	}
    	  	
    	return new AppErrorHandler().unauthorized();
    }
    
    @RequestMapping(value="/getRestaurants", method = RequestMethod.GET)
    public Response getRestaurants() {
    	if(SQLConnection.connection != null) {
    		
    		ObservableList<Restaurant> restaurants = SQLConsultant.getRestaurants();

    		for(int i = 0; i < restaurants.size(); i++)
    			System.out.println(restaurants.get(i).getId());
    		return new Response("succes", "200", restaurants+"");
    	}
    	  	
    	return new AppErrorHandler().unauthorized();
    }
    
    @RequestMapping(value="/deleteRestaurant", method = RequestMethod.DELETE)
    public Response deleteRestaurant(@RequestBody(required=true) Restaurant request) {
    	if(SQLConnection.connection != null) {
    		
    		int op = SQLConsultant.deleteRestaurant(request.getId());
    		
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
    		
    		System.out.println("get menu of " + id);
    		
    		ObservableList<Menu> menus = SQLConsultant.getMenusByRestaurant(id);

    		for(int i = 0; i < menus.size(); i++)
    			System.out.println(menus.get(i).getId());
    		return new Response("succes", "200", menus+"");
    	}
    	
    	return new AppErrorHandler().unauthorized();
    }
}