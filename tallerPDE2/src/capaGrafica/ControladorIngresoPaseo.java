import java.rmi.Naming;
import java.rmi.RemoteException;
import java.time.LocalTime;

import capaLogica.Ifachada;
import capaLogica.destinos.Destino;
import capaLogica.paseos.paseoException;
import capaLogica.paseos.Paseos;

public class ControladorIngresoPaseo {

	private Ifachada fach;
	private VentanaIngresoPaseo VIngPas;
	
	public ControladorIngresoPaseo (VentanaIngresoPaseo VIngPas) {
		
		        this.VIngPas = VIngPas; // Guardar la referencia de la ventana

		        try {
		            // Hacer el lookup del objeto remoto
		            this.fach = (Ifachada) Naming.lookup("//localhost:1099/fachada");
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }

	
public void ingresoPaseo(String cod, Destino dest, LocalTime hpart, LocalTime hllega, Double prec)throws RemoteException,paseoException{
	try {
    	
        fach.insertPaseo(cod, dest, hpart, hllega, prec);
        
       
    } catch (Exception e) {
        e.printStackTrace();

    	}
	}
}