package google.parsejson;

public class LoginResponse 
{

	private String token;
	private String userId;
	private String  message;
	
	
	public String gettoken() {
		return token;
	}
	public void settoken(String token) {
		this.token = token;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
