package capaLogica;

import java.rmi.Remote;

import java.rmi.RemoteException;
import java.time.LocalTime;
import java.util.LinkedList;

import capaLogica.boletos.VOBoleto;
import capaLogica.boletos.boletoException;
import capaLogica.destinos.Destino;
import capaLogica.destinos.DestinoException;
import capaLogica.minivanes.miniVanException;
import capaLogica.paseos.Paseos;
import capaLogica.paseos.VOPaseo;
import capaLogica.paseos.paseoException;
import capaPersistencia.PersistenciaException;

public interface Ifachada extends Remote {

	void insertMinivan(String matricula, String marca, String modelo, int cantAsientos, Paseos paseosAsignados)
			throws InterruptedException, miniVanException, RemoteException;

	// Lista las minivanes
	void listadoMinivanes() throws RemoteException;

	void insertPaseo(String cod, Destino dest, LocalTime hpart, LocalTime hllega, Double prec)
			throws paseoException, RemoteException;

	// OPCION 1- MUESTRA EN PANTALLA TODOS LOS PASEOS SEGUN UNA MINIVAN
	LinkedList<VOPaseo> listarPaseosPorMinivan(String matri) throws miniVanException, RemoteException;

	LinkedList<VOPaseo> listarPaseosPorDestinos(Destino destino) throws paseoException, RemoteException;

	// OPCION 2- RETORONA LA LISTA VOPASEOS SEGUN UNA CANTIDAD DE BOLETOS
	LinkedList<VOPaseo> listarPaseosDispBoletos(int cantBol) throws boletoException, RemoteException, RemoteException;

	// INGRESA BOLETO A LA COLECCION DE BOLETOS DEL PASEO PASADO POR EL USUARIO
	void ventaBoleto(String codigoBol, String nombre, int edad, int celu, String codigoPas, double descuento,
			boolean comun) throws paseoException, RemoteException;

	LinkedList<VOBoleto> listarBoletosPorPaseo(String codigo, char tipoBoleto) throws paseoException, RemoteException;

	double montoRecaudado(String cod) throws paseoException, RemoteException;
	
	public void insertDestino(String des) throws DestinoException, RemoteException;

	void respaldar(String nomArch) throws PersistenciaException, RemoteException;

	void recuperar(String nomArch) throws PersistenciaException, RemoteException;

}