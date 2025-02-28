package capaLogica.paseos;

public class paseoException extends Exception {
    
	   private String mensaje;
	 

	    // Constructor con mensaje personalizado
	    public paseoException(String mens) {
	       this.mensaje = mens;
	    }

	    
	    public static String darMensajePaseo(String mensaje)
		{ return mensaje; }
		
	}