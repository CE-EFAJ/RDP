package JsonFacade;
import com.google.gson.Gson;

import Server.Connection;
import Server.Server;

public class Controller {
	Gson gson;
	Server servidor;
	Connection conec;
	
	public Controller(Connection connection){
		gson= new Gson();
		conec=connection;
		processMessage();
		
		
	}

	private void processMessage() {
		Package paquete = gson.fromJson(conec.getMsg(), Package.class);
		int msgCode=paquete.getMessageCode();
		String msg=paquete.getMessage();
		
		switch (msgCode) {
		case 101:
			
			break;
		case 102:

		default:
			break;
		}
		
	}
}
