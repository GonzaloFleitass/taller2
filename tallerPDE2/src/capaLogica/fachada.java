package capaLogica;

import capaLogica.paseos.*;
import capaPersistencia.PersistenciaException;
import capaPersistencia.VOPersistencia;
import capaPersistencia.persistencia;
import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Properties;

import capaLogica.boletos.Boleto;
import capaLogica.boletos.Boletos;
import capaLogica.boletos.Especial;
import capaLogica.boletos.VOBoleto;
import capaLogica.boletos.boletoException;
import capaLogica.minivanes.*;
import capaLogica.monitor.Monitor;
import capaLogica.paseos.*;
import capaLogica.destinos.*;

public class fachada extends UnicastRemoteObject implements Ifachada {
	private static final LinkedList<VoMinivan> VOminivanes = null;
	private Paseos paseos;
	private Minivanes minivanes;
	private Monitor monitor;
	private Destinos destinos;

	public fachada() throws RemoteException {
		// TODO Auto-generated constructor stubbsnb
		paseos = new Paseos();
		minivanes = new Minivanes();
		monitor = new Monitor();
		destinos = new Destinos();
	}

	@Override
	public void insertMinivan(String matricula, String marca, String modelo, int cantAsientos)
			throws InterruptedException, miniVanException, RemoteException {
		Paseos paseosAsignados = new Paseos();
		monitor.comienzoEscritura();

		// Verificamos si ya existe una minivan con la matrícula dada
		if (minivanes.member(matricula) == true) {
			monitor.terminoEscritura();
			throw new miniVanException("Error - Ya existe la minivan");
		}

		// Verificamos si la cantidad de asientos es válida
		if (cantAsientos <= 0) {
			monitor.terminoEscritura();
			throw new miniVanException("Error - la cantidad de asientos es menor o igual a 0");
		}

		// Creamos la nueva Minivan y la insertamos
		Minivan mini = new Minivan(matricula, marca, modelo, cantAsientos, paseosAsignados);
		minivanes.insert(mini);

		// Terminamos la escritura
		monitor.terminoEscritura();
	}



	@Override
	public void insertPaseo(String cod, Destino dest, LocalTime hpart, LocalTime hllega, Double prec)
			throws paseoException, RemoteException {
		monitor.comienzoEscritura();
		if (paseos.member(cod) == true) {
			monitor.terminoEscritura();
			throw new paseoException("Error - ya existe ese codigo de paseo");

		}
		Minivan mini = minivanes.findMinivanDisponible(hpart, hllega);
		if (mini == null) {
			monitor.terminoEscritura();
			throw new paseoException("Error- no hay minivan disponible en ese horario");
		} else {
			if (prec <= 0) {
				monitor.terminoEscritura();
				throw new paseoException("Error- el precio base debe ser mayor a cero");
			} else {
				Boletos bol = new Boletos(mini.getcantAsientos());
				Paseo pas = new Paseo(cod, dest, hpart, hllega, prec, mini.getMatricula(), bol);
				paseos.insertPaseo(pas);
				mini.getPaseosAsignados().insertPaseo(pas);
				monitor.terminoEscritura();
			}

		}

	}
	// Lista las minivanes
	@Override
	public LinkedList<VoMinivan> listadoMinivanes() throws RemoteException {
		monitor.comienzoLectura();
		
		monitor.terminoLectura();
		return minivanes.listarMinivanes();
	
	}
	@Override
	public LinkedList<VOPaseo> listarPaseosPorMinivan(String matri) throws miniVanException, RemoteException {

		monitor.comienzoLectura();
		if (minivanes.member(matri) != true) {
			monitor.terminoLectura();
			throw new miniVanException("Error- no existe minivan con esa matricula");
		}
		monitor.terminoLectura();
		return minivanes.listarPaseosPorMinivan(matri);
	}

	@Override
	public LinkedList<VOPaseo> listarPaseosPorDestinos(Destino destino) throws paseoException, RemoteException {
		monitor.comienzoLectura();
	
		if (paseos.listarPaseosPorDestino(destino, minivanes) == null) {
			monitor.terminoLectura();
			throw new paseoException("Error- no existen paseos para ese destino");
		}
		monitor.terminoLectura();
		
		return paseos.listarPaseosPorDestino(destino, minivanes);

	}

	// OPCION 2- RETORONA LA LISTA VOPASEOS SEGUN UNA CANTIDAD DE BOLETOS
	@Override
	public LinkedList<VOPaseo> listarPaseosDispBoletos(int cantBol) throws boletoException, RemoteException {
		monitor.comienzoLectura();
		if (cantBol <= 0) {
			monitor.terminoLectura();
			throw new boletoException("Error- la cantidad de boleto debe ser mayor a 0");
		}
		monitor.terminoLectura();
		return paseos.listadoPaseosDispoBoletos(cantBol, minivanes);

	}

	// INGRESA BOLETO A LA COLECCION DE BOLETOS DEL PASEO PASADO POR EL USUARIO
	@Override
	public void ventaBoleto(String codigoBol, String nombre, int edad, int celu, String codigoPas, double descuento,
			char tipoBoleto) throws paseoException, RemoteException {
		monitor.comienzoEscritura();
		Paseo a = paseos.find(codigoPas);
		if (a == null) {
			monitor.terminoEscritura();
			throw new paseoException("Error- No existe Paseo");
		} else {

			if ((a.getBoletos().boletosTotales()) == (a.cantMaxBoletos(minivanes.find(a.getMatricula())))) {
				monitor.terminoEscritura();
				throw new paseoException("Error- NO hay boletos diponibles para el paseo");
			}
		}

		if (tipoBoleto == 'C') {
			Boleto C = new Boleto(a.getBoletos().boletosTotales(), nombre, edad, celu, codigoBol);
			a.getBoletos().insert(C);
			monitor.terminoEscritura();
		} else {
			Especial E = new Especial(a.getBoletos().boletosTotales(), nombre, edad, celu, codigoBol, descuento);
			a.getBoletos().insert(E);
			monitor.terminoEscritura();
		}
	}

	@Override
	public LinkedList<VOBoleto> listarBoletosPorPaseo(String codigo, char tipoBoleto)
			throws paseoException, RemoteException {
		monitor.comienzoLectura();

		if (paseos.member(codigo) == false) {
			monitor.terminoLectura();
			throw new paseoException("Error- no existe paseo");
		}
		Paseo a = paseos.find(codigo);
		monitor.terminoLectura();
		return a.getBoletos().listarBoletosPorPaseo(tipoBoleto);

	}

	@Override
	public double montoRecaudado(String cod) throws paseoException, RemoteException {
		monitor.comienzoLectura();
		if (paseos.member(cod) == false) {
			monitor.terminoLectura();
			throw new paseoException("Error, No existe paseo");
		}
		monitor.terminoLectura();
		return paseos.montoRecuadadoPaseo(cod);
	}

	
	@Override
	public void insertDestino(String des) throws DestinoException, RemoteException{
		monitor.comienzoLectura();
		if(destinos.member(des) == true) {
			throw new DestinoException("Error, Ya existe Destino");
		}
		Destino dest = new Destino(des);
		destinos.insert(dest);
		monitor.terminoLectura();
	}
	
	@Override
	public LinkedList<String> getDestinos() throws DestinoException, RemoteException{
		return destinos.getDestinos();
	}
	@Override
	public void respaldar() throws PersistenciaException, FileNotFoundException, IOException {
		
		monitor.comienzoLectura();
		Properties p = new Properties();
		String nomArch = "src/config/.properties";
		p.load (new FileInputStream (nomArch));
		persistencia P = new persistencia();
		String nomArchivo = p.getProperty("nomarchivo");
		VOPersistencia voPer = new VOPersistencia(minivanes, paseos,destinos);
		P.respaldar(nomArchivo, voPer);
		monitor.terminoLectura();

	}
	@Override
	public void recuperar() throws PersistenciaException, FileNotFoundException, IOException {
		monitor.comienzoEscritura();
		Properties p = new Properties();
		String nomArch = "src/config/.properties";
		// Abro el archivo properties y leo los datos de configuración
		p.load (new FileInputStream (nomArch));
		String nomArchivo = p.getProperty("nomarchivo");
		persistencia P = new persistencia();
		VOPersistencia voPer = P.recuperar(nomArchivo);
		minivanes = voPer.getMinivanes();
		paseos = voPer.getPaseos();
		destinos = voPer.getDestinos();
		monitor.terminoEscritura();
		
	}

	
	}


