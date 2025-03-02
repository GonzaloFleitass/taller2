package capaGrafica;

import java.rmi.Naming;
import java.rmi.RemoteException;
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

	
public void ListarPasPorDestino(Destino dest)throws RemoteException,paseoException{
	try {
    	
        fach.listarPaseosPorDestinos(dest);
        
       
    } catch (Exception e) {
        e.printStackTrace();

    	}
	}
}