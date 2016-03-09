package Logic;

import java.util.LinkedList;
import java.util.List;

public class User {
	
	private String name;
	private Boolean notify=false;
	private List<String> messages=new LinkedList<String>();
	
	public User(String name,Boolean notify, List<String> messages){
		this.name=name;
		this.notify=notify;
		this.messages=messages;
		
	}

	public User(String name){
		this.name=name;
	}
	
	public List<String> getMessages() {
		return messages;
	}

	public void AddMessage(String text){
		messages.add(text);
		notify=true;
	}
	public void setNotify(Boolean notify) {
		messages.clear();
		this.notify = notify;
	}

	public Boolean HaveMessages(){
		return notify;
	}
	public String getName() {
		return name;
	}
	
	

}
