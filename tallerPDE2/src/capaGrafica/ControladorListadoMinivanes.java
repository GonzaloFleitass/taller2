import java.rmi.Naming;
import java.rmi.RemoteException;
import java.time.LocalTime;

import capaLogica.Ifachada;


public class ControladorListadoMinivanes {

	private Ifachada fach;
	private VentanaListadoMinivanes VLisMini;
	
	public ControladorListadoMinivanes (VentanaListadoMinivanes  VLisMini) {
		
		        this.VLisMini = VLisMini; // Guardar la referencia de la ventana

		        try {
		            // Hacer el lookup del objeto remoto
		            this.fach = (Ifachada) Naming.lookup("//localhost:1099/fachada");
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }

	
public void ListarPasDisBol(char cantBol)throws RemoteException{
	try {
    	
        fach.listadoMinivanes();
        
       
    } catch (Exception e) {
        e.printStackTrace();

    	}
	}
}