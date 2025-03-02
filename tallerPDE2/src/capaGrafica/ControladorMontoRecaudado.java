package capaGrafica;

import java.rmi.Naming;
import java.rmi.RemoteException;
import capaLogica.paseos.paseoException;
import capaLogica.Ifachada;

public class ControladorMontoRecaudado{

	private Ifachada fach;
	private VentanaMontoRecaudado VMonto;
	
	public ControladorMontoRecaudado (VentanaMontoRecaudado VMonto) {
		
		        this.VMonto = VMonto; // Guardar la referencia de la ventana

		        try {
		            // Hacer el lookup del objeto remoto
		            this.fach = (Ifachada) Naming.lookup("//localhost:1099/fachada");
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }

	
public void MontoRec(String Cod)throws RemoteException,paseoException{
	try {
    	
        fach.montoRecaudado(Cod);
        
        
       
    } catch (Exception e) {
        e.printStackTrace();

    	}
	}
}