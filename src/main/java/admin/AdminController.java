package admin;

import java.util.AbstractMap;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import IO.Response;
import config.Constants;
import config.SQLConnection;
import error.AppErrorHandler;
import hello.Greeting;
import models.Restaurant;

@RestController
@RequestMapping("/admin")
public class AdminController {

//    private final AtomicLong counter = new AtomicLong();

	@RequestMapping(value="/db", method=RequestMethod.POST)
	public Response connectToDB(@RequestBody(required=true) Admin request) {
		
		AbstractMap.SimpleEntry<String, String> credentials;
		credentials = new AbstractMap.SimpleEntry<>(request.getUsername(), request.getPassword());
	
		int connection = SQLConnection.connect(credentials, request.getHost());
		
		switch(connection) {
			case 1:
				return new Response("error", "401", Constants.INVALID_CREDENTIALS_MSG);
			case 2:
				return new Response("error", "200", Constants.ADMIN_CONNECTED_TO_DB_MSG);
			case 3:
				return new Response("error", "400", Constants.CONNECTION_TO_DB_FAILED_MSG);
		}
		
		return new Response("succes", "200", request.getUsername() + ":admin connected");
	}
	
	public void closeConnectionToDB() {
		
	}
	
	@RequestMapping(value="/greeting", method=RequestMethod.POST)
    public Response greeting(@RequestBody(required=true) Greeting request) {
		if(request.equals(null)) System.out.println("nullito");
		System.out.println("aja " + request.toString());
    	return new Response("succces", "200", "request: " + request.getContent());
    }
	
    @RequestMapping(value = "/addRestaurant", method = RequestMethod.POST)
    public Response addRestaurant(@RequestBody(required=true) Restaurant request) {
    	if(SQLConnection.connection != null) {
    		new AppErrorHandler().unauthorized();
    	}
    	
    	return new Response("success", "200", "restaurant " + request + " added");
    }
    
    @RequestMapping(value="/getRestaurants", method = RequestMethod.GET)
    public Response getRestaurants() {
    	if(SQLConnection.connection != null) {
    		new AppErrorHandler().unauthorized();
    	}

    	
    	
    	return new Response("success", "200", "restaurants");
    }
    
    @RequestMapping(value="/deleteRestaurant", method = RequestMethod.DELETE)
    public Response deleteRestaurant(@RequestBody(required=true) Restaurant request) {
    	if(SQLConnection.connection != null) {
    		new AppErrorHandler().unauthorized();
    	}
    	
    	return new Response("success", "200", "deleted");
    }
}