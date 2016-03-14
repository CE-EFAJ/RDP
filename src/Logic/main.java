package Logic;
import Logic.Foo;

import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;

public class main {

	public static void main(String[] args) {
		
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
		/*String[] arreglos= {"ABC","ABD","AGD","wer","fer"};
		Arreglo arreglo = new Arreglo( arreglos);
		System.out.println(Arrays.toString(arreglos));
		System.out.println(Arrays.toString(arreglo.getArreglo()));
		String ger=gson.toJson(arreglos);
		String best =gson.toJson(arreglo);
		
		System.out.println(ger);
		System.out.println(best);
		
		String[] newArreglo=  gson.fromJson(ger,String[].class);
		Arreglo hf=gson.fromJson(best, Arreglo.class);
		System.out.println(gson.toJson(hf));
		System.out.println(Arrays.toString(newArreglo));
		System.out.println(Arrays.toString(hf.getArreglo()));*/
		//String[] array= new String[] {"Elem1","Elem2","Elem3"};
		//System.out.println(Arrays.toString(array));
				
		//System.out.println(a.IsCorrectMode2("11", "Javier", "(A*+B*+C*+D*)"));
	}
}
