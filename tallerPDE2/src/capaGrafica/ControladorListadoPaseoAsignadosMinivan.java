package capaGrafica;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.LinkedList;

import capaLogica.minivanes.miniVanException;
import capaLogica.paseos.VOPaseo;
import capaLogica.Ifachada;


public class ControladorListadoPaseoAsignadosMinivan{

	private Ifachada fach;
	private VentanaListadoPaseosAsignadosMinivan VLisPasMini;
	
	public ControladorListadoPaseoAsignadosMinivan (VentanaListadoPaseosAsignadosMinivan  VLisPasMini) {
		
		        this.VLisPasMini = VLisPasMini; // Guardar la referencia de la ventana

		        try {
		            // Hacer el lookup del objeto remoto
		            this.fach = (Ifachada) Naming.lookup("//localhost:1099/fachada");
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }

	
public LinkedList<VOPaseo> ListarPasPorMini(String Mat)throws RemoteException,miniVanException{
	try {
    	
       return  fach.listarPaseosPorMinivan(Mat);
        
       
    } catch (Exception e) {
        e.printStackTrace();
        return new LinkedList<>();  // Devuelve una lista vacía en caso de error
    	}
	}
}