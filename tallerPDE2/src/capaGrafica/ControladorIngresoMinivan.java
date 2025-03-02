package capaGrafica;

import java.rmi.Naming;
import java.rmi.RemoteException;

import capaLogica.Ifachada;
import capaLogica.minivanes.miniVanException;
import capaLogica.paseos.Paseos;

public class ControladorIngresoMinivan {

	private Ifachada fach;
	private VentanaIngresoMinivan VIngMini;
	
	public ControladorIngresoMinivan (VentanaIngresoMinivan VIngMini) {
		
		        this.VIngMini = VIngMini; // Guardar la referencia de la ventana

		        try {
		            // Hacer el lookup del objeto remoto
		            this.fach = (Ifachada) Naming.lookup("//localhost:1099/fachada");
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }

	
public void ingresoMinivan(String matricula, String marca, String modelo, int cantAsientos)throws RemoteException,miniVanException{
	try {
    	
        fach.insertMinivan(matricula, marca, modelo, cantAsientos);
       
    } catch (Exception e) {
        e.printStackTrace();

    	}
	}
}