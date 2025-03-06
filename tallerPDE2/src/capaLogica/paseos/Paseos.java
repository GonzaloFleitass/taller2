package capaLogica.paseos;

import capaLogica.destinos.*;
import capaLogica.minivanes.*;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeMap;

public class Paseos implements Serializable {

	private TreeMap<String, Paseo> Paseos; // Mapa de Paseos con el código como clave

	// Constructor que inicializa Paseos
	public Paseos() {
		Paseos = new TreeMap<>();
	}

	// Método para insertar un nuevo paseo en el sistema
	public void insertPaseo(Paseo Pa) {
		Paseos.put(Pa.getCodigo(), Pa);
	}

	// Método para obtener todos los Paseos
	public TreeMap<String, Paseo> getPaseos() {
		return Paseos;
	}

	public boolean member(String codigo) {
		return Paseos.containsKey(codigo);
	}

	// Método que lista los paseos asignados a un destino específico
	public LinkedList<VOPaseo> listarPaseosPorDestino(Destino des, Minivanes mini) {
	    LinkedList<VOPaseo> voPaseos = new LinkedList<>();


	    // Recorrer todas las minivans en el TreeMap
	    for (Minivan miniv : mini.getMinivans().values()) { // Obtener los valores (Minivan) del TreeMap
	      

	        // Obtener los paseos asociados a esta minivan
	        Paseos paseosDeMinivan = miniv.getPaseosAsignados(); // Asume que Minivan tiene un método getPaseosAsignados()
	        if (paseosDeMinivan == null || paseosDeMinivan.getPaseos().isEmpty()) {
	           
	            continue; // Saltar a la siguiente minivan
	        }

	        // Recorrer los paseos de esta minivan
	        for (Paseo pas : paseosDeMinivan.getPaseos().values()) { // Obtener los valores (Paseo) del TreeMap
	            // Verificar si el destino coincide
	        	if (pas.getDestino().getNombre().equals(des.getNombre())) {
	                System.out.println("Paseo encontrado: " + pas.getCodigo()); // Debug

	                // Calcular la cantidad máxima de boletos y los boletos disponibles
	                int maxBoletos = pas.cantMaxBoletos(miniv);
	                int boletosDisp = pas.cantBolDisp(miniv);

	                // Crear el objeto VOPaseo y agregarlo a la lista
	                VOPaseo vpas = new VOPaseo(
	                    pas.getCodigo(),
	                    pas.getDestino(),
	                    pas.getHoraPartida(),
	                    pas.getHoraLlegada(),
	                    pas.getPrecioBase(),
	                    maxBoletos,
	                    boletosDisp
	                );
	                voPaseos.add(vpas);
	            }
	        }
	    }

	    System.out.println("Paseos encontrados: " + voPaseos.size()); // Debug
	    return voPaseos;
	}
	// Método que lista los paseos disponibles con una cantidad mínima de boletos
	public LinkedList<VOPaseo> listadoPaseosDispoBoletos(int boletos, Minivanes mini) {
		Iterator<Paseo> iter = Paseos.values().iterator();
		LinkedList<VOPaseo> VOPaseos = new LinkedList<>();

		// Filtra los paseos por la disponibilidad de boletos
		while (iter.hasNext()) {
			Paseo pas = iter.next();
			String mat = pas.getMatricula();
			Minivan miniv = mini.find(mat);
			if (pas.cantBolDisp(miniv) >= boletos) {

				VOPaseo vpas = new VOPaseo(pas.getCodigo(), pas.getDestino(), pas.getHoraPartida(),
						pas.getHoraLlegada(), pas.getPrecioBase(), pas.cantMaxBoletos(miniv), pas.cantBolDisp(miniv));
				VOPaseos.add(vpas);
			}
		}

		return VOPaseos;
	}

	// Método que calcula el monto recaudado de un paseo específico
	public double montoRecuadadoPaseo(String x) {
		Paseo aux = Paseos.get(x);
		return aux.getBoletos().ventasFinalesBoletos(aux.getPrecioBase());
	}

	// Método para buscar un paseo por su código
	public Paseo find(String codigo) {
		return Paseos.get(codigo);
	}

	// Método que devuelve la cantidad total de paseos
	public int cantPaseos() {
		int contador = 0;
		Iterator<Paseo> iter = Paseos.values().iterator();
		// Cuenta los paseos existentes
		while (iter.hasNext()) {
			iter.next();
			contador++;
		}
		return contador;
	}
}
