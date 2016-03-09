package Logic;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;

public class JsonFile {
	private String ruta;
	private File file=null;
	private FileWriter writer=null;
	private FileReader reader=null;
	private BufferedReader breader= null;
	private BufferedWriter bwriter=null;
	private Gson gson=null;
	private PrintWriter pwriter;
	
	public JsonFile(String ruta){
		this.ruta=ruta;
		gson = new Gson();
		
	}
	public void OpenFile(String status){
		file=new File(ruta);
		if (status=="write"){
		try {
			writer= new FileWriter(file);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("fallo al crear writer");
		}
		bwriter = new BufferedWriter(writer);
		pwriter = new PrintWriter(writer);
		}
		if (status=="read"){
		try {
			reader =new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("fallo al crear reader");
		}
		breader = new BufferedReader(reader);
		}
		
		
		
	}
	public void WriteClass(Object object){
		String json= gson.toJson(object);
		System.out.println(json);
		Write(json);
	}
	public void Write(String text) {
			bwriter=new BufferedWriter(writer);
			pwriter= new PrintWriter(writer);
			pwriter.write(text);
			pwriter.write("\r\n");			
		
	}
	public void CloseFile(){
		try {
			if(bwriter!=null){
		bwriter.close();}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}if(pwriter!=null){
	pwriter.close();}
	}
	
	public List<String> Read(){
		String linea = null;
		List<String> lista = new LinkedList<String>();
        try {
			while((linea=breader.readLine())!=null){
			   System.out.println(linea);
			lista.add(linea);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
		
	}
	public Gson getGson() {
		return gson;
	}

}
