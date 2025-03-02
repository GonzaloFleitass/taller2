package capaGrafica;

import java.rmi.Naming;
import java.rmi.RemoteException;
import capaLogica.minivanes.miniVanException;
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

	
public void ListarPasPorMini(String Mat)throws RemoteException,miniVanException{
	try {
    	
        fach.listarPaseosPorMinivan(Mat);
        
       
    } catch (Exception e) {
        e.printStackTrace();

    	}
	}
}