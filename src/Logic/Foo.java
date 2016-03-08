package Logic;
import java.util.ArrayList;
import java.util.List;

public class Foo {
	private List<Game> games=new ArrayList<Game>();
	private JsonFile file=new JsonFile();
	
	public void LoadGames(){
		file.OpenFile("read");
		List<String> lista = file.Read();
		file.CloseFile();
		if(lista.isEmpty()){}
		else{
			int n=0;
			while(n!=lista.size()){
				games.add(file.getGson().fromJson(lista.get(n), Game.class));
				n=n+1;}
			}
		}
	
	public void SaveGames(){
		file.OpenFile("write");
		if(games.isEmpty()){}
		else{
			int n=0;
			while(n!=games.size()){
				file.WriteClass(games.get(n));
				n=n+1;}
			}
		file.CloseFile();
		}
	
	
}
