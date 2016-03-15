package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import JsonFacade.Controller;

public class Connection extends Thread {

	private Socket _cliente;
	private DataInputStream _entrada;
	private DataOutputStream _salida;
	private Controller ctrl= new Controller();
	

	public Connection(Socket socket) {
		try {
			_cliente = socket;
			_salida = null;
			_entrada = null;
			
		} catch (Exception e) {
		}
	}

	public void run() {
		try {
			_salida = new DataOutputStream(_cliente.getOutputStream());
			_entrada = new DataInputStream(_cliente.getInputStream());

			while (true) {
				String msg = _entrada.readUTF();
				
				if (msg == null) {
					break;
				} else {
					
					System.out.println("Mensaje recibido: " + msg);
					/*********************************************/
					String paquete = ctrl.processMessage(msg);
					System.out.println(paquete);
					_salida.writeUTF(paquete);
					_salida.flush();
					/*********************************************/
				}
			}
			_cliente.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
