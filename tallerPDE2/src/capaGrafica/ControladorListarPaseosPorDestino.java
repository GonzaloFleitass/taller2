package capaGrafica;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.LinkedList;

import capaLogica.paseos.VOPaseo;
import capaLogica.paseos.paseoException;
import capaLogica.Ifachada;
import capaLogica.destinos.Destino;


public class ControladorListarPaseosPorDestino{

	private Ifachada fach;
	private VentanaListadoPaseosPorDestino VLisDest;
	
	public ControladorListarPaseosPorDestino (VentanaListadoPaseosPorDestino VLisDest) {
		
		        this.VLisDest = VLisDest; // Guardar la referencia de la ventana

		        try {
		            // Hacer el lookup del objeto remoto
		            this.fach = (Ifachada) Naming.lookup("//localhost:1099/fachada");
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }

	
public LinkedList <VOPaseo> ListarPasPorDestino(Destino dest)throws RemoteException,paseoException{
	try {
    	
       return  fach.listarPaseosPorDestinos(dest);
        
       
    } catch (Exception e) {
        e.printStackTrace();
        return new LinkedList<>();  // Devuelve una lista vacía en caso de error
    	}
	}

public LinkedList<String> getListaDestinos() throws RemoteException {
    try {
        return fach.getDestinos();  // Obtener la lista de destinos desde la fachada
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
}