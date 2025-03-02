package capaGrafica;

import java.rmi.Naming;
import java.rmi.RemoteException;

import capaLogica.Ifachada;
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

	
public void ingresoDest(String nombre)throws RemoteException,DestinoException{
	try {
    	
        fach.insertDestino(nombre);
       
    } catch (Exception e) {
        e.printStackTrace();

    	}
	}
}

