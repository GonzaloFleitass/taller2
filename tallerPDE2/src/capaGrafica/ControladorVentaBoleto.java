package capaGrafica;

import java.rmi.Naming;
import java.rmi.RemoteException;
import capaLogica.paseos.paseoException;
import capaLogica.Ifachada;

public class ControladorVentaBoleto{

	private Ifachada fach;
	private VentanaVentaBoleto VVenBol;
	
	public ControladorVentaBoleto (VentanaVentaBoleto VVenBol) {
		
		        this.VVenBol = VVenBol; // Guardar la referencia de la ventana

		        try {
		            // Hacer el lookup del objeto remoto
		            this.fach = (Ifachada) Naming.lookup("//localhost:1099/fachada");
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }

	
public void VentBol(String codigoBol, String nombre, int edad, int celu, String codigoPas, double descuento,
boolean comun)throws RemoteException,paseoException{
	try {
    	
        fach.ventaBoleto(nombre, nombre, edad, celu, nombre, descuento, comun);
        
        
       
    } catch (Exception e) {
        e.printStackTrace();

    	}
	}
}