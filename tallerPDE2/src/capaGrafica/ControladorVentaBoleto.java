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
			char tipoBoleto) throws RemoteException, paseoException {
			    try {
			        fach.ventaBoleto(codigoBol, nombre, edad, celu, codigoPas, descuento, tipoBoleto);
			    } catch (paseoException e) {
			        e.printStackTrace();
			        throw e; // Relanza la excepción para que sea capturada en la interfaz gráfica
			    }
			}
}