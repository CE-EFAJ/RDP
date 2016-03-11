package JsonFacade;

public class Package {
	private int messageCode;
	private String message;
	
	public Package (int messageCode, String message){
		this.messageCode=messageCode;
		this.message=message;
	}

	public int getMessageCode() {
		return messageCode;
	}

	public String getMessage() {
		return message;
	}
	

}
