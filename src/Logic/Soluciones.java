package Logic;

public class Soluciones {
	private String idGame;
	private String[] soluciones;
	private boolean[] cambios;
	
	public Soluciones(String id, String[] soluciones) {
		idGame = id;
		this.soluciones = soluciones;
		cambios = new boolean[5];
		noCambiar();
	}
	
	public void noCambiar() {
		for (boolean valor : cambios) {
			valor = false;
		}
	}
	
	public String getIdGame() {
		return idGame;
	}

	public void setIdGame(String idGame) {
		this.idGame = idGame;
	}

	public String[] getSoluciones() {
		return soluciones;
	}

	public void setSoluciones(String[] soluciones) {
		this.soluciones = soluciones;
		
	}

	public boolean[] getCambios() {
		return cambios;
	}

	public void setCambios(boolean[] cambios) {
		this.cambios = cambios;
	}
}
