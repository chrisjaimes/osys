package controllers;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;

import IO.Response;
import config.Constants;
import error.AppErrorHandler;
import models.Customer;
import services.CustomerService;

@RestController
@RequestMapping("/user")
public class CustomerController {

	@Autowired 
	CustomerService customer_service;

	HttpSession session = null;

	@RequestMapping(value="/home", method=RequestMethod.GET)
	public Response home() {
		if(session != null) {
			return new Response("success", "200", "user homepage");
		} else {
			return new Response("ERROR", "401", "Please login");
		}
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public RedirectView login(@RequestBody(required=true) Customer customer, BindingResult result, WebRequest request, Errors errors) {

		customer.setPassword(BCrypt.hashpw(customer.getPassword(), BCrypt.gensalt(8)));
		
		RedirectView redirectView = new RedirectView();
		
		if(session != null) {
			redirectView.setUrl("/user/login");
			return redirectView;
		} 

		int op = customer_service.insertCustomer(customer);
		
		if(op != Constants.SUCCESSFUL_DB_OPERATION)
			redirectView.setUrl("/user/signup_fail");
		else redirectView.setUrl("/user/signup_success");

		return redirectView;
	}
	
	@RequestMapping(value="/signup_success", method=RequestMethod.GET)
	public String signup_success() {
		return "Registration was succesful! Please login and start your OSYS journey!";
	}
	
	@RequestMapping(value="/signup_fail", method=RequestMethod.GET)
	public String fail() {
		return "We're sorry! Signup was not possible. Username or email could be in use.";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestBody(required=true) Customer request) {
		//if not login, login
		return "HOLA";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public String logout(@RequestBody(required=true) Customer request) {
		//if not login, login
		return "HOLA";
	}
//	
//	@RequestMapping(value="/{customerId}/getOrders", method=RequestMethod.GET)
//	public Response login(@PathVariable("customerId") long customer_id, @RequestBody(required=true) CustomerConnection request) {
//		//if login, ....
//		//look for the cart associated to this user SQLConsultant.getCartByCustomerId();
//	}
//
//	@RequestMapping(value="/{customerId}/addItemToCart", method=RequestMethod.POST)
//	public Response login(@PathVariable("customerId") long customer_id, @RequestBody(required=true) MenuItem request) {
//		//if login...
//		//get cart id associated to this customer... SQLConsultant.getCartByCustomerId(); 
//		//then, add MenuItem request to this cart 
//	}
//	
//	@RequestMapping(value="/{customerId}/removeItem", method=RequestMethod.POST)
//	public Response login(@PathVariable("customerId") long customer_id, @RequestBody(required=true) MenuItem request) {
//		//if login...
//		//get cart id associated to this customer... SQLConsultant.getCartByCustomerId(); 
//		//then, remove MenuItem request to this cart 
//		
//		//REMEMBER TO HASH PASSWORD https://github.com/djmdjm/jBCrypt http://www.devglan.com/spring-mvc/storing-hashed-password-database-java
//	}
//	
//	@RequestMapping(value="/{customerId}/placeOrder", method=RequestMethod.POST)
//	public Response login(@PathVariable("customerId") long customer_id) {
//		//if login...
//		//get cart id associated to this customer... SQLConsultant.getCartByCustomerId(); 
//		//then, place order 
//	}
}
