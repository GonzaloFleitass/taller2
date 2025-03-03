package capaGrafica;

import java.rmi.Naming;
import java.rmi.RemoteException;

import capaLogica.Ifachada;
import capaLogica.minivanes.miniVanException;
import capaLogica.paseos.Paseos;

public class ControladorIngresoMinivan {

	private Ifachada fach;
	private VentanaIngresoMinivan VIngMini;

	public ControladorIngresoMinivan (VentanaIngresoMinivan VIngMini) {
		this.VIngMini = VIngMini;
		try {
			this.fach = (Ifachada) Naming.lookup("//localhost:1099/fachada");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void ingresoMinivan(String matricula, String marca, String modelo, int cantAsientos) throws miniVanException, RemoteException {
	    try {
	        // Intentamos insertar la minivan
	        fach.insertMinivan(matricula, marca, modelo, cantAsientos);
	    } catch (miniVanException e) {
	        // Relanzamos la miniVanException para que pueda ser capturada fuera del método
	        throw e;
	    } catch (Exception e) {
	        // Si ocurre algún otro error, mostramos el stack trace y lanzamos RemoteException
	        e.printStackTrace();
	        throw new RemoteException("Error inesperado al ingresar la minivan.", e);
	    }
	}

}
