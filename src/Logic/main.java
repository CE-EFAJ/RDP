package Logic;
import Logic.Foo;

import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
public class main {

	public static void main(String[] args) {
		//Foo a= new Foo();
		//String regex = "(AB)*";
		//boolean b = a.IsARegex(regex);
		//String example=a.Generator(regex);
		//System.out.println(example);
		//Boolean c= a.Validate(regex, example);
		//System.out.println(b);
		//Date G=Calendar.getInstance().getTime();
		
		//System.out.println(G.toString());
		JsonFile a = new JsonFile();
		//Game juego= new Game("Javier");
		//Game game1= new Game("Ernesto");
		a.OpenFile("write");
		//a.WriteClass(juego);
		//a.WriteClass(game1);
		a.CloseFile();
		a.OpenFile("read");
		List lista=a.Read();
		a.CloseFile();

	}
}
