package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Connection extends Thread {

	private Socket _cliente;
	private DataInputStream _entrada;
	private DataOutputStream _salida;
	private static Server _server;

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

			_server.addClient(_salida);
			_salida.writeUTF("Connection successful");
			_salida.flush();

			_entrada = new DataInputStream(_cliente.getInputStream());

			while (true) {
				String msg = _entrada.readUTF();
				if (msg == null) {
					break;
				} else {
					if (msg.trim().equals("BYE"))
						break;
					System.out.println("Mensaje recibido: " + msg);
				}
			}
			_cliente.close();
			_server.removeClient(_salida);
		} catch (Exception e) {
		}
		while (true) {

		}
	}
}
