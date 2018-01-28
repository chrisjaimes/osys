package IO;

public class Response {

	private String status;
	private String code;
	private String message;
	
	public Response(String status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getResponse() {
		return status;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
}
