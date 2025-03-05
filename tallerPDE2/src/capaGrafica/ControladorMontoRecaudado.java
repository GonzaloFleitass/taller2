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

	
	public double MontoRec(String Cod) throws RemoteException, paseoException {
	    try {
	        // Intenta obtener el monto recaudado
	        return fach.montoRecaudado(Cod); 
	    } catch (RemoteException e) {
	        // Manejo específico para RemoteException
	        e.printStackTrace();
	        throw e; // Vuelve a lanzar la excepción para que el llamador la maneje
	    } catch (paseoException e) {
	        // Manejo específico para paseoException
	        e.printStackTrace();
	        throw e; // Vuelve a lanzar la excepción para que el llamador la maneje
	    } catch (Exception e) {
	        // Manejo genérico para otras excepciones inesperadas
	        e.printStackTrace();
	        throw new paseoException("Error al obtener el monto recaudado: " + e.getMessage());
	    }
	}

}