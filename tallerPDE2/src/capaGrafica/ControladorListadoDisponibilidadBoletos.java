package capaGrafica;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.time.LocalTime;

import capaLogica.Ifachada;
import capaLogica.boletos.boletoException;

public class ControladorListadoDisponibilidadBoletos {

	private Ifachada fach;
	private VentanaListadoDisponibilidadBoletos VLisDisBol;
	
	public ControladorListadoDisponibilidadBoletos (VentanaListadoDisponibilidadBoletos  VLisDisBol) {
		
		        this.VLisDisBol = VLisDisBol; // Guardar la referencia de la ventana

		        try {
		            // Hacer el lookup del objeto remoto
		            this.fach = (Ifachada) Naming.lookup("//localhost:1099/fachada");
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }

	
public void ListarPasDisBol(char cantBol)throws RemoteException,boletoException{
	try {
    	
        fach.listarPaseosDispBoletos(cantBol);
        
       
    } catch (Exception e) {
        e.printStackTrace();

    	}
	}
}