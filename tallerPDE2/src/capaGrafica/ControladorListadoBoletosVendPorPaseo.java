package capaGrafica;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.LinkedList;

import capaLogica.Ifachada;
import capaLogica.boletos.VOBoleto;
import capaLogica.paseos.paseoException;


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

	
public LinkedList<VOBoleto> ListarBolVenPas(String codigo, char tipoBoleto)throws RemoteException,paseoException{
	try {
    	
      return  fach.listarBoletosPorPaseo(codigo, tipoBoleto);
        
       
    } catch (Exception e) {
        e.printStackTrace();
        return new LinkedList<>();  // Devuelve una lista vacía en caso de error
    	}

	}

}