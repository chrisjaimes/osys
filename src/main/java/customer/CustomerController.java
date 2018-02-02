package customer;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import IO.Response;
import models.MenuItem;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public Response login(@RequestBody(required=true) CustomerConnection request) {
		
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public Response login(@RequestBody(required=true) CustomerConnection request) {
		//if not login, login
	}
	
	@RequestMapping(value="/{customerId}/getOrders", method=RequestMethod.GET)
	public Response login(@PathVariable("customerId") long customer_id, @RequestBody(required=true) CustomerConnection request) {
		//if login, ....
		//look for the cart associated to this user SQLConsultant.getCartByCustomerId();
	}

	@RequestMapping(value="/{customerId}/addItemToCart", method=RequestMethod.POST)
	public Response login(@PathVariable("customerId") long customer_id, @RequestBody(required=true) MenuItem request) {
		//if login...
		//get cart id associated to this customer... SQLConsultant.getCartByCustomerId(); 
		//then, add MenuItem request to this cart 
	}
	
	@RequestMapping(value="/{customerId}/removeItem", method=RequestMethod.POST)
	public Response login(@PathVariable("customerId") long customer_id, @RequestBody(required=true) MenuItem request) {
		//if login...
		//get cart id associated to this customer... SQLConsultant.getCartByCustomerId(); 
		//then, remove MenuItem request to this cart 
		
		//REMEMBER TO HASH PASSWORD https://github.com/djmdjm/jBCrypt http://www.devglan.com/spring-mvc/storing-hashed-password-database-java
	}
	
	@RequestMapping(value="/{customerId}/placeOrder", method=RequestMethod.POST)
	public Response login(@PathVariable("customerId") long customer_id) {
		//if login...
		//get cart id associated to this customer... SQLConsultant.getCartByCustomerId(); 
		//then, place order 
	}
}
