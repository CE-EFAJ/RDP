package Logic;

public class User {
	
	private String name;
	private boolean notify=false;
	private String[] messages;//=new String[20];
	
	public User(String name,Boolean notify, String[] messages){
		this.name=name;
		this.notify=notify;
		System.out.println(messages.toString());
		//MakeAListMessage(messages);
		this.messages=messages;
		
	}

	public User(String name){
		this.name=name;
	}
	
	public String[] getMessages() {
		String[] retorno=messages;
		messages= new String[0];
		return retorno;
	}

	public void AddMessage(String text){
		
			String[] cambio = new String[messages.length+1];
			for(int i=0;i<messages.length;i++){
				cambio[i]=messages[i];
			}
			messages=new String[messages.length+1];
			messages=cambio;
			messages[messages.length-1]=text;
		
		notify=true;
	}
	public void setNotify(Boolean notify) {
		 //new String[messages.length];
		this.notify = notify;
	}

	public boolean HaveMessages(){
		return notify;
	}
	public String getName() {
		return name;
	}
	
	

}
