package JsonFacade;
import com.google.gson.Gson;

import Beans.GamePetition;
import Beans.Mode;
import Beans.Pattern;
import Logic.Foo;
import Logic.Game;
import Logic.Soluciones;
import Server.Server;

public class Controller {
	Gson gson;
	Server servidor;
	Foo logic;
	
	public Controller(){
		gson= new Gson();
		logic= new Foo();
		//processMessage();
		
		
	}

	public String processMessage(String json) {
		
		Package paquete = gson.fromJson(json, Package.class);
		int msgCode=paquete.getMessageCode();
		String msg=paquete.getMessage();
		System.out.println(msg);
		Package sendPack;
		String sendMsg;
		Soluciones sol;
		boolean solution;
		
		
		switch (msgCode) {
		case 101: //Conexion establecida App: User Server: boolean
			logic.connectedUser(msg);
			sendPack= new Package(101, "true");
			sendMsg =  gson.toJson(sendPack);
			return sendMsg;
			
		case 102:// Create Game App: (pattern, User) Server: Soluciones
			Pattern bean= gson.fromJson(msg, Pattern.class);
			sol=logic.NewGame(bean._user, bean._patron);
			if(sol==null){
				sendPack=new Package(102,"Regex no valido");		
			}
			else{
				sendPack=new Package(102,gson.toJson(sol));
			}
			
			sendMsg= gson.toJson(sendPack);
			return sendMsg;
			
		case 103: //GameList MoreGames App:Index Server:ArrayGameList
			Game[] listGame =logic.getGameList(Integer.parseInt(msg));
			sendPack= new Package(103, gson.toJson(listGame));
			sendMsg= gson.toJson(sendPack);
			return sendMsg;
			
		case 104: //Solutiones a escoger App:Soluciones Server:Soluciones
			sol= logic.changeExample(gson.fromJson(msg, Soluciones.class));
			
			sendPack= new Package(104, gson.toJson(sol));
			sendMsg= gson.toJson(sendPack);
			return sendMsg;
			
		case 105: //GameList Seleccionar Juego App: (IdGame,user) Server: Juego
			GamePetition gp= gson.fromJson(msg, GamePetition.class);
			Game game= logic.askGame(gp._idGame, gp._username);
			
			sendPack = new Package(105, gson.toJson(game));
			sendMsg= gson.toJson(sendPack);
			return sendMsg;
			
		case 106: //Mode1 Complete App:(id, user, soluciones[]) Server: boolean
			Mode mode1= gson.fromJson(msg, Mode.class);
			solution= logic.IsCorrectMode1(mode1._idGame, mode1._username, mode1._soluciones);
			sendPack= new Package(106, Boolean.toString(solution));
			sendMsg= gson.toJson(sendPack);
			return sendMsg;
			
		case 107://Mode2 Guess App:(id, user, soluciones[]) Server: boolean
			Mode mode2= gson.fromJson(msg, Mode.class);
			solution= logic.IsCorrectMode2(mode2._idGame, mode2._username, mode2._soluciones[0]);
			sendPack= new Package(107, Boolean.toString(solution));
			sendMsg= gson.toJson(sendPack);
			return sendMsg;
			
		case 108: //Notifications App: user Server: notifications[]
			String notify =logic.UserNotifications(msg);
			sendPack= new Package(108, notify);
			sendMsg= gson.toJson(sendPack);
			return sendMsg;
			
			
		}
		return "";
		
	}
}
