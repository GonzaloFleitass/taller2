package capaGrafica;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.time.LocalTime;
import java.util.LinkedList;

import capaLogica.Ifachada;
import capaLogica.boletos.boletoException;
import capaLogica.paseos.VOPaseo;

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

	
public LinkedList<VOPaseo> ListarPasDisBol(int cantBol)throws RemoteException,boletoException{
	try {
    	
        return fach.listarPaseosDispBoletos(cantBol);
        
       
    } catch (Exception e) {
        e.printStackTrace();
        return new LinkedList<>();  // Devuelve una lista vacía en caso de error
    	}
	}
}