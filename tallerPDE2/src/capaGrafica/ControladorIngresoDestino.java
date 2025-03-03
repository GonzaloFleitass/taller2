package capaGrafica;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.LinkedList;

import capaLogica.Ifachada;
import capaLogica.destinos.Destino;
import capaLogica.destinos.DestinoException;

public class ControladorIngresoDestino {

	private Ifachada fach;
	private VentanaIngresoDestino Vdest;
	
	public ControladorIngresoDestino (VentanaIngresoDestino Vdest) {
		
		        this.Vdest = Vdest; // Guardar la referencia de la ventana

		        try {
		            // Hacer el lookup del objeto remoto
		            this.fach = (Ifachada) Naming.lookup("//localhost:1099/fachada");
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
	
	public ControladorIngresoDestino(VentanaIngresoPaseo Vpaseo) {
	    // Aquí podrías poner lógica si necesitas manejar algo diferente con esta ventana
	    try {
	        this.fach = (Ifachada) Naming.lookup("//localhost:1099/fachada");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
public void ingresoDest(String nombre)throws RemoteException,DestinoException{
	  try {
	       
	        fach.insertDestino(nombre);
	    } catch (DestinoException e) {
	        // aca lo lanza en consola
	        e.printStackTrace();
	     // aca lo lanza en pantalla
	        throw e;
	    }
	}

// Método para obtener la lista de destinos
public LinkedList<String> getListaDestinos() throws RemoteException {
    try {
        return fach.getDestinos();  // Obtener la lista de destinos desde la fachada
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

}

