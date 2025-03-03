package capaLogica.destinos;

public class DestinoException extends Exception {


	private String mensaje;

	// Constructor con mensaje personalizado
	public DestinoException(String mens) {
		this.mensaje = mens;
	}

	public String darMensaje() {
		return mensaje;
	}

}