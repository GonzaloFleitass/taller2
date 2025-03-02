package capaGrafica;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.time.LocalTime;

import capaLogica.Ifachada;
import capaLogica.destinos.Destino;
import capaLogica.paseos.paseoException;
import capaLogica.paseos.Paseos;

public class ControladorListadoBoletosVendPorPaseo {

	private Ifachada fach;
	private VentanaListadoBoletosVendPorPaseo VLisBolPas;
	
	public ControladorListadoBoletosVendPorPaseo (VentanaListadoBoletosVendPorPaseo VLisBolPas) {
		
		        this.VLisBolPas = VLisBolPas; // Guardar la referencia de la ventana

		        try {
		            // Hacer el lookup del objeto remoto
		            this.fach = (Ifachada) Naming.lookup("//localhost:1099/fachada");
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }

	
public void ListarBolVenPas(String codigo, char tipoBoleto)throws RemoteException,paseoException{
	try {
    	
        fach.listarBoletosPorPaseo(codigo, tipoBoleto);
        
       
    } catch (Exception e) {
        e.printStackTrace();

    	}
	}
}