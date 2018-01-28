package error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import IO.Response;

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
        return new Response("ERROR", "401", "Unauthorised Request");
    }
	
}
