package capaPersistencia;

public class PersistenciaException extends Exception {

	private String mensaje;

	// Constructor con mensaje personalizado
	public PersistenciaException(String mens) {
		this.mensaje = mens;
	}

	public static String darMensaje(String mensaje) {
		return mensaje;

	}
}
