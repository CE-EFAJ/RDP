package Logic;
import Logic.Foo;

import java.util.List;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
public class main {

	public static void main(String[] args) throws InterruptedException, IOException {
		Foo a= new Foo();
		Gson gson = new Gson();
		
		//System.out.println(gson.toJson(a.askGame("2", "Javier")));
		//a.NewGame("Javier", "(A+B+C+D)");
		//a.DeleteGame("3");
		//a.DeleteGame("4");
		//System.out.println(a.connectedUser("Astorga"));
		// ["AAAAABBBCCD","AABBBCCD","AABBCD","ABCD","AABBBCCD"]
		/*
		Soluciones sol=new Soluciones("10",arg );
		boolean[] cambios ={true,true,true,true,true};
		sol.setCambios(cambios);
		System.out.println(gson.toJson(sol));
		System.out.println(gson.toJson(a.changeExample(sol)));*/
		String[] arg={"D","D","D","D","D"};

		System.out.println(a.IsCorrectMode2("11", "Javier", "(A*+B*+C*+D*)"));
	}
}
