package capaLogica.minivanes;

public class miniVanException extends Exception {
    
   private String mensaje;
 

    // Constructor con mensaje personalizado
    public miniVanException(String mens) {
       this.mensaje = mens;
    }

    
    public static String darMensaje(String mensaje)
	{ return mensaje; }
	
}