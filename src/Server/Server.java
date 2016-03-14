package Server;

import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Server extends Thread {

	private static Server _singleton;
	private static int _port;
	private static List<DataOutputStream> _clients;

	public static Server getInstance() {
		if (_singleton != null) {
			return _singleton;
		} else {
			_port = 8080;
			_clients = new LinkedList<DataOutputStream>();
			_singleton = new Server();
			return _singleton;
		}
	}

	public static Server getInstance(int port) {
		if (_singleton != null) {
			return _singleton;
		} else {
			_port = port;
			_clients = new LinkedList<DataOutputStream>();
			_singleton = new Server();
			return _singleton;
		}
	}

	public void run() {
		try {
			System.out.println("IP: " + InetAddress.getLocalHost().toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("Servidor iniciado en puerto: " +
				Integer.toString(_port));

		try {
			ServerSocket server = new ServerSocket(_port);
			while (true) {
				Socket socket = server.accept();
				System.out.println("Conexion entrante...");
				new Connection(socket).start();
			}
		} catch (Exception e) {
		}
		System.out.println("Apagando servidor...");
	}

	public void addClient(DataOutputStream cliente) {
		synchronized (_clients) {
			_clients.add(cliente);
		}
	}

	public void removeClient(DataOutputStream cliente) {
		synchronized (_clients) {
			_clients.remove(cliente);
		}
	}
}
