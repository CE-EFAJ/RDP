package Logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import Arduino.Arduino;
import Server.Server;

public class Foo {
	private Arduino arduino = new Arduino();
	private Regex regex = new Regex();
	private List<Game> games = new LinkedList<Game>();
	private JsonFile gameFile = new JsonFile("games.txt");
	private List<User> users = new ArrayList<User>();
	private JsonFile userFile = new JsonFile("users.txt");
	private Server servidor;

	/**
	 * Constructor Carga los juegos y usuarios de los archivos en disco
	 * 
	 */

	public Foo() {
		LoadGames();
		LoadUsers();

	}

	/**
	 * Crea un nuevo juego y lo agrega a la lista de juegos Guarda los juegos
	 * activos en disco
	 * 
	 * @param creator
	 *            :nombre de usuario del creador del juego
	 * @param pattern
	 *            :expresion regular a adivinar
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public Soluciones NewGame(String creator, String pattern) {
		if (regex.IsARegex(pattern)) {
			/*try {
				//arduino.writeData(creator, 4);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			Game game = new Game(creator, pattern);
			games.add(game);
			SaveGames();
			return game.getExamples();
		}
		return null;
	}

	/**
	 * Una vez que el juego se crea este tiene un numero unico, para eliminar el
	 * juego se inserta ese numero Actualiza el archivo en disco.
	 * 
	 * @param number
	 *            :numero asociado al juego que se desea eliminar
	 */
	public void DeleteGame(String number) {
		for (int i = 0; i < games.size(); i++) {
			if (games.get(i).getNumber().equals(number)) {
				games.remove(i);
				SaveGames();
				break;
			}
		}
	}

	/**
	 * Carga los juegos del archivo en disco
	 */
	private void LoadGames() {
		games.clear();
		gameFile.OpenFile("read");
		List<String> lista = gameFile.Read();
		gameFile.CloseFile();
		if (lista.isEmpty()/* |lista.get(0)=="" */) {
			System.out.println("no hay Juegos");
		} else {
			int n = 0;
			while (n != lista.size()) {
				games.add(gameFile.getGson().fromJson(lista.get(n), Game.class));
				n = n + 1;
			}
		}
	}

	/**
	 * Guarda los juegos activos en disco
	 */
	private void SaveGames() {
		gameFile.OpenFile("write");
		if (games.isEmpty()) {
		} else {
			int n = 0;
			while (n != games.size()) {
				gameFile.WriteClass(games.get(n));
				n = n + 1;
			}
		}
		gameFile.CloseFile();
	}

	/**
	 * Carga los usuarios del archivo en disco
	 */
	private void LoadUsers() {
		users.clear();
		userFile.OpenFile("read");
		List<String> lista = userFile.Read();
		userFile.CloseFile();
		if (lista.isEmpty()/* & lista.get(0)!="" */) {
			System.out.println("no hay usuarios");
		} else {
			int n = 0;
			while (n != lista.size()) {
				users.add(userFile.getGson().fromJson(lista.get(n), User.class));
				n = n + 1;
			}
		}
	}

	/**
	 * Guarda los usuarios actuales en disco
	 */
	private void SaveUsers() {
		userFile.OpenFile("write");
		if (users.isEmpty()) {
		} else {
			int n = 0;
			while (n != users.size()) {
				userFile.WriteClass(users.get(n));
				n = n + 1;
			}
		}
		userFile.CloseFile();
	}

	/**
	 * Solicita un juego especifico a la lista de juegos con el numero del juego
	 * 
	 * @param numGame
	 *            :numero del juego que se desea obtener
	 * @return Retorna el Game, asociado al numGame solicitado, para usar
	 *         posteriormente
	 */
	private Game getGame(String numGame) {

		for (int i = 0; i < games.size(); i++) {
			if (games.get(i).getNumber().equals(numGame)) {
				return games.get(i);
			}
		}
		return null;
	}

	/**
	 * Modo 1: Corresponde al modo de solucion donde el usuario, participante
	 * del juego, tiene que realizar una continuacion en cada una de las 5
	 * soluciones creadas por el regex a descubrir. La funcion recibe un array
	 * de soluciones (continuaciones de los patrones generados por el creador)
	 * valida si cada continuacion corresponde al patron creado. Si el usuario
	 * logra realizar la continuacion del patron de las 5 soluciones, gano el
	 * juego por el modo 1, retorna un true añade un mensaje de notificacion al
	 * usuario creador del patron y borra el juego de la lista de juegos Si el
	 * usuario falla el juego, se le suma 1 al contador de intentos del juego,
	 * 
	 * 
	 * @param numGame
	 *            :numero del juego a vencer
	 * @param nameUser
	 *            :nombre del usuario que intenta completar el juego
	 * @param solutions
	 *            :Arreglo de soluciones creadas por el jugador
	 * @return : True: si el juego se completa, False: si el juego falla
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public boolean IsCorrectMode1(String numGame, String nameUser, String[] solutions) {
		Game gameTested = getGame(numGame);
		if (gameTested != null) {
			for (int i = 0; i < 5; i++) {
				String conca = gameTested.getExamples().getSoluciones()[i] + solutions[i];
				if (regex.Validate(gameTested.getPattern(), conca)) {
				} else {
					/* try {
						//arduino.writeData(nameUser, 2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					gameTested.plusAttempts();
					SaveGames();
					return false;
				}

			}
			/*try {
				//arduino.writeData(nameUser, 3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/

			SetNotify(numGame, nameUser, "1", Arrays.toString(solutions));
			DeleteGame(numGame);
			return true;

		}
		return false;
	}

	/**
	 * Añade una notificacion (mensaje) al usuario creador del juego que se
	 * logro vencer
	 * 
	 * @param numGame
	 *            :numero del juego terminado
	 * @param nameUser
	 *            :nombre del usuario ganador
	 * @param metodo
	 *            :metodo por el que fue completado
	 * @param solution
	 *            :Solucion(es) que uso el jugador para completar el juego
	 */
	private void SetNotify(String numGame, String nameUser, String metodo, String solution) {
		String message = "El juego #" + numGame + " fue solucionado por " + nameUser + " mediante el metodo " + metodo
				+ " y la(s) solucion(es): " + solution + ".";
		getUser(getGame(numGame).getCreator()).AddMessage(message);
		SaveUsers();
	}

	/**
	 * Modo 2: El usuario participante debera generar una regex en base a los
	 * ejemplos generados por esta si el regex generado por el usuario es valido
	 * para cada ejemplo gana el juego, de lo contrario si logra acertar
	 * unicamente 4 ejemplos se agregara al arreglo de mejores jugadores del
	 * juego. Se verifica cada ejemplo del juego con el regex generado por el
	 * participante, si el regex es valido para todos los ejemplos retorna un
	 * true y se le añade una notificacion al usuario creador del juego y se
	 * borra el juego completado, de lo contrario se suma uno al contador de
	 * intentos del juego
	 * 
	 * 
	 * @param numGame
	 *            :numero del juego a completar
	 * @param nameUser
	 *            :Usuario participante
	 * @param solution
	 *            : solucion generada por el usuario
	 * @return True: si el juego se completa, False: si el juego falla
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public boolean IsCorrectMode2(String numGame, String nameUser, String solution) {
		Game gameTested = getGame(numGame);
		if (gameTested != null & regex.IsARegex(solution)) {
			if (gameTested.validateSolution(solution) == 5) {
				SetNotify(numGame, nameUser, "2", solution);
				DeleteGame(numGame);
				/*try {
					//arduino.writeData(nameUser, 3);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				return true;
			}
			if (gameTested.validateSolution(solution) == 4) {
				getGame(numGame).addPlayers(nameUser);
				getGame(numGame).plusAttempts();
				SaveGames();
				/*try {
					//arduino.writeData(nameUser, 2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				return false;
			} else {
				getGame(numGame).plusAttempts();
				SaveGames();
				/*try {
					//arduino.writeData(nameUser, 2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				return false;
			}
		}
		return false;
	}

	/**
	 * retorna el User asociado al nombre de usuario solicitado
	 * 
	 * @param userName
	 *            :nombre de usuario que se desea
	 * @return User
	 */
	private User getUser(String userName) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getName().equals(userName)) {
				return users.get(i);
			}
		}
		return null;
	}

	/**
	 * Crea un nuevo usuario y lo agrega a la lista de usuarios y actualiza el
	 * archivo en disco
	 * 
	 * @param userName
	 */
	private void newUser(String userName) {
		users.add(new User(userName));
		SaveUsers();
	}

	/**
	 * @param userName
	 *            :usuario conectado
	 * @return String vacio si se crea un nuevo usuario, mensajes si tiene
	 *         mensajes, "no message" si no hay mensajes
	 */
	public void connectedUser(String userName) {
		User cUser = getUser(userName);
		if (cUser == null) {
			newUser(userName);
		}

	}

	/**
	 * Retorna las notificaciones del usuario solicitado, retorna "no message"
	 * en caso de que no tenga mensajes
	 * 
	 * @param userName
	 *            :usuario a consultar por mensajes
	 * @return String
	 */
	public String UserNotifications(String userName) {
		User cUser = getUser(userName);
		if (cUser.HaveMessages()) {
			getUser(userName).setNotify(false);
			String[] retorno = cUser.getMessages();
			SaveUsers();
			return userFile.getGson().toJson(retorno);
		}
		return "empty";

	}

	/**
	 * Solicitud de cambio de soluciones de un juego Retorna una clase
	 * soluciones con el cambio ya realizado
	 * 
	 * @param Sol
	 *            :Clase soluciones que aporta el id del juego y el cambio que
	 *            se desea hacer
	 * @return
	 */
	public Soluciones changeExample(Soluciones Sol) {
		if (getGame(Sol.getIdGame()) == null) {
		}
		getGame(Sol.getIdGame()).changeExample(Sol);
		return getGame(Sol.getIdGame()).getExamples();

	}

	/**
	 * Solicita un juego ya creado anteriormente para ser jugado
	 * 
	 * @param numGame
	 *            :numero del juego a utilizar
	 * @param userName
	 *            :Jugador que solicita el juego
	 * @return Game :retorna objeto juego para ser usado
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public Game askGame(String numGame, String userName) {
		/*try {
			//arduino.writeData(userName, 1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return getGame(numGame);

	}

	public Game[] getGameList(int index) {
		Game[] gameList = new Game[5];
		if ((index + 1) * 5 == games.size()) {
			for (int i = 0; i < 5; i++) {
				gameList[i] = games.get(i + (index * 5));
			}
			
		} else {
			
			for (int i = 0; i + index * 5 < (games.size()); i++) {
				if (i==5){
					break;
				}
				gameList[i] = games.get(i + index * 5);
			}
		}
		return gameList;
	}

}
