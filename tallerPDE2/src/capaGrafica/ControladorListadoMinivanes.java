package capaGrafica;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.LinkedList;

import capaLogica.Ifachada;
import capaLogica.minivanes.VoMinivan;


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

	
public LinkedList<VoMinivan> ListarMini()throws RemoteException{
	try {
    	
        return fach.listadoMinivanes();
        
       
    } catch (Exception e) {
        e.printStackTrace();
        return new LinkedList<>();  // Devuelve una lista vacía en caso de error
    	}
	}
}