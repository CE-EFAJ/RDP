
public class ArduinoJava {

	public static void main(String[] args) throws Exception {
		Arduino main = new Arduino();
		main.initialize();
		main.writeData("Marco", 3);
		main.close();
		
		main.initialize();
		main.writeData("Carlos", 1);
		main.close();
		
		System.out.println("Started");
	}
}