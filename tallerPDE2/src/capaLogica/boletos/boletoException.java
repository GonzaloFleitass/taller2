package capaLogica.boletos;

public class boletoException extends Exception {

	private String mensaje;

	// Constructor con mensaje personalizado
	public boletoException(String mens) {
		this.mensaje = mens;
	}

	public String darMensaje() {
		return mensaje;
	}

}