package capaLogica;

import java.rmi.Remote;

import java.rmi.RemoteException;
import java.time.LocalTime;
import java.util.LinkedList;

import capaLogica.boletos.VOBoleto;
import capaLogica.boletos.boletoException;
import capaLogica.destinos.Destino;
import capaLogica.destinos.DestinoException;
import capaLogica.minivanes.VoMinivan;
import capaLogica.minivanes.miniVanException;
import capaLogica.paseos.Paseos;
import capaLogica.paseos.VOPaseo;
import capaLogica.paseos.paseoException;
import capaPersistencia.PersistenciaException;

public interface Ifachada extends Remote {

	void insertMinivan(String matricula, String marca, String modelo, int cantAsientos, Paseos paseosAsignados)
			throws InterruptedException, miniVanException, RemoteException;

	// Lista las minivanes
	public LinkedList<VoMinivan> listadoMinivanes() throws RemoteException ;


	void insertPaseo(String cod, Destino dest, LocalTime hpart, LocalTime hllega, Double prec)
			throws paseoException, RemoteException;


	LinkedList<VOPaseo> listarPaseosPorMinivan(String matri) throws miniVanException, RemoteException;

	LinkedList<VOPaseo> listarPaseosPorDestinos(Destino destino) throws paseoException, RemoteException;

	
	LinkedList<VOPaseo> listarPaseosDispBoletos(int cantBol) throws boletoException, RemoteException, RemoteException;

	void ventaBoleto(String codigoBol, String nombre, int edad, int celu, String codigoPas, double descuento,
			boolean comun) throws paseoException, RemoteException;

	LinkedList<VOBoleto> listarBoletosPorPaseo(String codigo, char tipoBoleto) throws paseoException, RemoteException;

	double montoRecaudado(String cod) throws paseoException, RemoteException;
	

	void respaldar(String nomArch) throws PersistenciaException, RemoteException;

	void recuperar(String nomArch) throws PersistenciaException, RemoteException;

	void insertDestino(String des) throws DestinoException, RemoteException;

}