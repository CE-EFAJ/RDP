package Logic;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Foo {
	private Regex regex=new Regex();
	private List<Game> games=new LinkedList<Game>();
	private JsonFile gameFile=new JsonFile("games.txt");
	private List<User> users=new ArrayList<User>();
	private JsonFile userFile= new JsonFile("users.txt");
	
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
		if(lista.isEmpty()|lista.get(0)==""){}
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
		if(lista.isEmpty()& lista.get(0)!=""){}
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
				String conca=solutions[i] + gameTested.getExamples()[i];
				if(regex.Validate(gameTested.getPattern(), conca)){}
				else{
					return false;
					//add + attempts
				}
				gameTested.plusAttempts();
			}
			return true;
			
		}
		return false;
	}
	
	public boolean IsCorrectMode2(String numGame, String nameUser, String solution){
		Game gameTested=getGame(numGame);
		if(gameTested!=null){
			if(gameTested.validateSolution(solution)==5){
				return true;
			}
			if(gameTested.validateSolution(solution)==4){
				gameTested.addPlayers(nameUser);
				gameTested.plusAttempts();
				return false;
			}
			else{
				gameTested.plusAttempts();
				return false;
			}
		}
		return false;
	}
}
	
	

