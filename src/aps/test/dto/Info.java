package aps.test.dto;

public class Info {
	private String message;
	private boolean successful;
	
	public Info() {
		this.message = "";
		this.successful = true;
	}
	
	public Info(String message, boolean successful) {
		this.message = message;
		this.successful = successful;
	}
	
	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
