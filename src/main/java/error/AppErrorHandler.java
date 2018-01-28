package error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import IO.Response;
import config.Constants;

@RestController
@RequestMapping("/error")
public class AppErrorHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
    @RequestMapping("/404")
    public Response notFound() {
        return new Response("ERROR", "404", "Resource Not Found");
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @RequestMapping("/401")
    public Response unauthorized() {
        return new Response("ERROR", "401", "Unauthorized Request");
    }
    
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @RequestMapping("/500")
    public Response operationError(int code) {
    	if(code == Constants.DB_ERROR)
    		return new Response("ERROR", "500", "Error related to DB. Resource could be a duplicate in the database.");
    	else if(code == Constants.INTERNAL_ERROR)
    		return new Response("ERROR", "500", "Internal error. Error during operation");
    	else if(code == Constants.DB_COUNT_ERROR)
    		return new Response("ERROR", "500", "Internal error. Error before adding resource to DB.");
    	else if(code == Constants.DB_INSERT_OPERATION_INTERRUPTED)
    		return new Response("ERROR", "500", "Operation cannot be completed. Make sure you provide all parameters");
    	
    	return new Response("ERROR", "500", "Internal error. Error during operation");
    }
	
}
