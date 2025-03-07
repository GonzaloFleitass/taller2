package capaGrafica;

import java.rmi.Naming;
import java.rmi.RemoteException;

import capaLogica.Ifachada;
import capaPersistencia.PersistenciaException;

public class ControladorRespaldar {
	private Ifachada fach;
	private VentanaMenuPrincipal VentanaMenuPrincipal;
	
	public ControladorRespaldar (VentanaMenuPrincipal VentanaMenuPrincipal) {
		this.VentanaMenuPrincipal = VentanaMenuPrincipal;
		try {
			this.fach = (Ifachada) Naming.lookup("//localhost:1099/fachada");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void respaldar() throws PersistenciaException, RemoteException{
		try {
		fach.respaldar();
	}catch(PersistenciaException e) {
		throw e;
	}catch(Exception e) {
		 e.printStackTrace();
	}
}
	
	public void recuperar() throws PersistenciaException, RemoteException{
		try {
			fach.recuperar();
		}catch(PersistenciaException e) {
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}


