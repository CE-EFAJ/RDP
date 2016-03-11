package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import JsonFacade.Controller;

public class Connection extends Thread {

	private Socket _cliente;
	private DataInputStream _entrada;
	private DataOutputStream _salida;
	private static Server _server;
	Controller Controlador;
	private String _msg;
	boolean _procesado;

	public Connection(Socket socket) {
		try {
			_cliente = socket;
			_server = Server.getInstance();
			_salida = null;
			_entrada = null;
		} catch (Exception e) {
		}
	}

	public void run() {
		try {
			_salida = new DataOutputStream(_cliente.getOutputStream());
			_entrada = new DataInputStream(_cliente.getInputStream());

			_server.addClient(_salida); // Lista enlazada organizacion
			Controlador= new Controller(this);
			_procesado=false;
			while (true) {
				
				//int num = _entrada.readInt();
				_msg = _entrada.readUTF();
				
				//Detener hilo
				// Procesar Mensaje
				
				while(_msg != null) {
					wait(1);
					if(_procesado){
						break;
					}
				}
				break;
			}
			_cliente.close();
			_server.removeClient(_salida);
		} catch (Exception e) {
		}
	}
	
	public void SendMessage(String message){
		
	}
	
	public void setProcesado(){
		_procesado=true;
	}

	public String getMsg() {
		return _msg;
	}
}
