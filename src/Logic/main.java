package Logic;
import Logic.Foo;

import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
public class main {

	public static void main(String[] args) {
		Foo a= new Foo();
		//String regex = "(AB)*";
		//boolean b = a.IsARegex(regex);
		//String example=a.Generator(regex);
		//System.out.println(example);
		//Boolean c= a.Validate(regex, example);
		//System.out.println(b);
		//Date G=Calendar.getInstance().getTime();
		//a.connectedUser("Javier");
		//System.out.println(G.toString());
		/*JsonFile f = new JsonFile("users.txt");
		Game juego= new Game("Javier","AB");
		Game game1= new Game("Ernesto","(AB)*");*/
		/*int n=0;
		while(n<10){
		a.NewGame("Javier", "(AB)*+(A+C)(F+G)*X?");
		a.NewGame("Ernesto", "(AB)*");
		n++;}
		a.SaveGames();
		a.LoadGames();*/
		//a.DeleteGame("0");
		//a.SaveGames();
		//a.newUser("Astorga");
		a.NewGame("Astorga", "(A+B)*A(B*A)?");
		//a.getUser("Astorga").AddMessage("Un juego tuyo a sido terminado");
		//a.SaveUsers();
		//a.SaveGames();
		
		/*a.OpenFile("write");
		User us1=new User("Javier");
		User us2 = new User("Ernesto");
		us2.AddMessage("Hola");
		a.WriteClass(us1);
		a.WriteClass(us2);
		//a.WriteClass(juego);
		//a.WriteClass(game1);
		a.CloseFile();
		a.OpenFile("read");
		List lista=a.Read();
		a.CloseFile();*/

	}
}
