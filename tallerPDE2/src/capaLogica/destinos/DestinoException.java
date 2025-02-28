package capaLogica.destinos;

public class DestinoException extends Exception {


	private String mensaje;

	// Constructor con mensaje personalizado
	public DestinoException(String mens) {
		super(mens);
	}

	public static String darMensaje(String mensaje) {
		return mensaje;
	}

}