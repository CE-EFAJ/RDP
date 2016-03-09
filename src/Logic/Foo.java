package Logic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Foo {
	private Regex regex=new Regex();
	private List<Game> games=new LinkedList<Game>();
	private JsonFile gameFile=new JsonFile("games.txt");
	private List<User> users=new ArrayList<User>();
	private JsonFile userFile= new JsonFile("users.txt");
	
	public Foo(){
		LoadGames();
		LoadUsers();
	}
	
	public void NewGame(String creator, String pattern){
		games.add(new Game(creator,pattern));
	}
	
	public void DeleteGame(String number){
		for(int i=0;i<games.size();i++){
			if(number==games.get(i).getNumber()){
				games.remove(i);			
				break;
			}
		}
	}
	
	public void LoadGames(){
		games.clear();
		gameFile.OpenFile("read");
		List<String> lista = gameFile.Read();
		gameFile.CloseFile();
		if(lista.isEmpty()/*|lista.get(0)==""*/){System.out.println("no hay Juegos");}
		else{
			int n=0;
			while(n!=lista.size()){
				games.add(gameFile.getGson().fromJson(lista.get(n), Game.class));
				n=n+1;}
			}
		}
	
	public void SaveGames(){
		gameFile.OpenFile("write");
		if(games.isEmpty()){}
		else{
			int n=0;
			while(n!=games.size()){
				gameFile.WriteClass(games.get(n));
				n=n+1;}
			}
		gameFile.CloseFile();
		}
	
	public void LoadUsers(){
		users.clear();
		userFile.OpenFile("read");
		List<String> lista = userFile.Read();
		userFile.CloseFile();
		if(lista.isEmpty()/*& lista.get(0)!=""*/){System.out.println("no hay usuarios");}
		else{
			int n=0;
			while(n!=lista.size()){
				users.add(userFile.getGson().fromJson(lista.get(n), User.class));
				n=n+1;}
			}
		}
	
	public void SaveUsers(){
		userFile.OpenFile("write");
		if(users.isEmpty()){}
		else{
			int n=0;
			while(n!=users.size()){
				userFile.WriteClass(users.get(n));
				n=n+1;}
			}
		userFile.CloseFile();
		}
	
	public Game getGame(String numGame){
		for(int i=0; i<games.size();i++){
			if(games.get(i).getNumber()==numGame){
				return games.get(i);
			}
		}
		return null;
	}

	public boolean IsCorrectMode1(String numGame, String nameUser, String[] solutions){
		Game gameTested=getGame(numGame);
		if(gameTested!=null){
			for(int i=0;i<5;i++){
				String conca=solutions[i] + gameTested.getExamples().getSoluciones()[i];
				if(regex.Validate(gameTested.getPattern(), conca)){}
				else{
					return false;
				}
				gameTested.plusAttempts();
			}
			DeleteGame(numGame);
			SetNotify(numGame,nameUser,"1",Arrays.toString(solutions));
			return true;
			
		}
		return false;
	}
	
	private void SetNotify(String numGame, String nameUser, String metodo, String solution) {
		String message= "El juego #"+numGame+ "fue solucionado por "+nameUser+"mediante el metodo "+metodo+
				" y la(s) solucion(es): " +solution+".";
		getUser(getGame(numGame).getCreator()).AddMessage(message);
		
		
		
	}

	public boolean IsCorrectMode2(String numGame, String nameUser, String solution){
		Game gameTested=getGame(numGame);
		if(gameTested!=null){
			if(gameTested.validateSolution(solution)==5){
				DeleteGame(numGame);
				SetNotify(numGame,nameUser,"2",solution);
				return true;
			}
			if(gameTested.validateSolution(solution)==4){
				getGame(numGame).addPlayers(nameUser);
				getGame(numGame).plusAttempts();
				return false;
			}
			else{
				getGame(numGame).plusAttempts();
				return false;
			}
		}
		return false;
	}
	
	public User getUser(String userName){
		for(int i=0; i<users.size();i++){
			if(users.get(i).getName()==userName){
				return users.get(i);
			}
		}
		return null;
	}
	
	public void newUser(String userName){
		users.add(new User(userName));
	}
	
	public String connectedUser(String userName){
		User cUser = getUser(userName);
		if(cUser!=null){
			if(cUser.HaveMessages()){
				getUser(userName).setNotify(false);
				return userFile.getGson().toJson(cUser.getMessages());
			}
			return "no message";
		}
		newUser(userName);
		return "";
	}
	
	public Soluciones changeExample(Soluciones Sol){
		getGame(Sol.getIdGame()).changeExample(Sol);
		return getGame(Sol.getIdGame()).getExamples();
		
	}
}
	
	

