package capaLogica;
import capaLogica.paseos.*;
import capaPersistencia.PersistenciaException;
import capaPersistencia.VOPersistencia;
import capaPersistencia.persistencia;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.LinkedList;

import capaLogica.boletos.Boleto;
import capaLogica.boletos.Boletos;
import capaLogica.boletos.Especial;
import capaLogica.boletos.VOBoleto;
import capaLogica.boletos.boletoException;
import capaLogica.destinos.Destino;
import capaLogica.minivanes.*;
import capaLogica.monitor.Monitor;
import capaLogica.paseos.*;

//es la fachadaa
public class fachada extends UnicastRemoteObject implements Ifachada {
	private Paseos paseos;
	private Minivanes minivanes;
	private Monitor monitor;
	
	
	public fachada() throws RemoteException{
		// TODO Auto-generated constructor stubbs
		paseos = new Paseos();
		minivanes = new Minivanes();
		monitor = new Monitor();
	}

	@Override
	public void insertMinivan(String matricula, String marca, String modelo, int cantAsientos, Paseos paseosAsignados)throws InterruptedException, miniVanException,RemoteException {
	   
	        monitor.comienzoEscritura();
	   

	        // Verificamos si ya existe una minivan con la matrícula dada
	        if (minivanes.find(matricula) != null) {
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

	
	
	//Lista las minivanes
		@Override
		public void listadoMinivanes() throws RemoteException{
			monitor.comienzoLectura();
			LinkedList<VoMinivan> VOminivanes = minivanes.listarMinivanes();
			Iterator<VoMinivan> iterador = VOminivanes.iterator();
			while(iterador.hasNext()) {
				iterador.next().printVOMinivan();;
			}
			monitor.terminoLectura();
		}
		
    	
	@Override
	public void insertPaseo(String cod, Destino dest, LocalTime hpart, LocalTime hllega, Double prec) throws paseoException,RemoteException {
		monitor.comienzoEscritura();
		if(paseos.find(cod)!=null) {
			 monitor.terminoEscritura();
			throw new paseoException ("Error - ya existe ese codigo de paseo");
			
		}
		Minivan mini = minivanes.findMinivanDisponible(hpart,hllega);
		if(mini==null) {
			 monitor.terminoEscritura();
			throw new paseoException ("Error- no hay minivan disponible en ese horario");
		}else {
			if(prec<=0) {
				 monitor.terminoEscritura();
				throw new paseoException("Error- el precio base debe ser mayor a cero");
			}else {
				Boletos bol= new Boletos(mini.getcantAsientos());
				Paseo pas =  new Paseo(cod,dest,hpart,hllega,prec,mini.getMatricula(),bol);
				paseos.insertPaseo(pas);
				 monitor.terminoEscritura();
			}
			
			
		}
		
		
	}
	
	
	
	
	//ESTA SON LAS DOS FORMAS QUE ENCONTRE PARA USAR LOS VALUE OBJET, PREGUNTAR AL PROFE CUAL SE USA
	
	//OPCION 1- MUESTRA EN PANTALLA TODOS LOS PASEOS SEGUN UNA MINIVAN
	@Override
	public  void listarPaseosPorMinivan(String matri) throws miniVanException,RemoteException {
		monitor.comienzoLectura();
		if(minivanes.find(matri)==null) {
			monitor.terminoLectura();
			throw new miniVanException("Error- no existe minivan con esa matricula");
		}
		 LinkedList<VOPaseo> paseos = minivanes.listarPaseosPorMinivan(matri);
		 Iterator<VOPaseo> iterador = paseos.iterator();
		 
		 while(iterador.hasNext()) {
			 iterador.next().printVOPaseo();
		 }
		 monitor.terminoLectura();
	}
	
	
	@Override
	public LinkedList<VOPaseo> listarPaseosPorDestinos(Destino destino) throws paseoException,RemoteException{
		monitor.comienzoLectura();
		if(paseos.listarPaseosPorDestino(destino, minivanes) == null) {
			  monitor.terminoLectura();
			  throw new paseoException("Error- no existen paseos para ese destino");
		  }
		  monitor.terminoLectura();
		  return paseos.listarPaseosPorDestino(destino, minivanes);
	
		
	}
	
	
	
	//OPCION 2- RETORONA LA LISTA VOPASEOS SEGUN UNA CANTIDAD DE BOLETOS
 	@Override
	public LinkedList<VOPaseo> listarPaseosDispBoletos(int cantBol) throws boletoException, RemoteException {
		monitor.comienzoLectura();
 		if(cantBol<=0) {
			  monitor.terminoLectura();
			throw new boletoException("Error- la cantidad de boleto debe ser mayor a 0");
		}
		  monitor.terminoLectura();
		return paseos.listadoPaseosDispoBoletos(cantBol, minivanes);
		
	}
	
	
	//INGRESA BOLETO A LA COLECCION DE BOLETOS DEL PASEO PASADO POR EL USUARIO
	@Override
	public void ventaBoleto(String codigoBol, String nombre, int edad, int celu, String codigoPas,double descuento,boolean comun)throws paseoException,RemoteException {
		monitor.comienzoEscritura();
		Paseo a = paseos.find(codigoPas);
		if(a==null) {
			monitor.terminoEscritura();
			throw new paseoException("Error- No existe Paseo");
		}else {
			
			if((a.getBoletos().boletosTotales()) == (a.cantMaxBoletos(minivanes.find(a.getMatricula())))) {
				monitor.terminoEscritura();
				throw new paseoException("Error- NO hay boletos diponibles para el paseo");
			}
		}
		
		if(comun == true) {
		Boleto C = new Boleto(a.getBoletos().boletosTotales(),nombre,edad,celu,codigoBol);
		a.getBoletos().insert(C);
		monitor.terminoEscritura();
	}else {
		Especial E = new Especial(a.getBoletos().boletosTotales(),nombre,edad,celu,codigoBol,descuento);
		a.getBoletos().insert(E);
		monitor.terminoEscritura();
	}
	}
	
	
	
	@Override
	public LinkedList<VOBoleto> listarBoletosPorPaseo(String codigo, char tipoBoleto)throws paseoException,RemoteException{
		monitor.comienzoLectura();
		Paseo a = paseos.find(codigo); 
		if(a==null) {
			monitor.terminoLectura();
			throw new paseoException("Error- no existe paseo"); 
		}
		  monitor.terminoLectura();
			return a.getBoletos().listarBoletosPorPaseo(codigo, tipoBoleto, paseos);
			
	}
	
	
	@Override
	public double montoRecaudado(String cod)throws paseoException,RemoteException {
		monitor.comienzoLectura();
		if(paseos.find(cod) == null){
			  monitor.terminoLectura();
			throw new paseoException("Error, No existe paseo");
		}
		  monitor.terminoLectura();
		return paseos.montoRecuadadoPaseo(cod);
	}
	
	
	
	 @Override
	public void respaldar(String nomArch) throws PersistenciaException,RemoteException {
		 monitor.comienzoLectura();
		 persistencia P = new persistencia();
		 VOPersistencia voPer = new VOPersistencia(minivanes,paseos);
		 P.respaldar(nomArch, voPer);
		 monitor.terminoLectura();
		 
		 
		  }
	 @Override
	public void recuperar(String nomArch) throws PersistenciaException,RemoteException {
		 monitor.comienzoEscritura();
		 persistencia P = new persistencia();
		 VOPersistencia voPer = P.recuperar(nomArch);
		 minivanes = voPer.getMinivanes();
		 paseos = voPer.getPaseos();
		 monitor.terminoEscritura();
		 }
	 
	 
	
	
	
}
	
