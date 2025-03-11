package capaLogica.minivanes;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import capaLogica.boletos.VOBoleto;
import capaLogica.paseos.Paseo;
import capaLogica.paseos.VOPaseo;

public class Minivanes implements Serializable {

	// Atributo que almacena las minivans en un TreeMap, usando la matrícula como
	// clave
	private TreeMap<String, Minivan> Minivanes;

	// Constructor que inicializa el TreeMap de Minivanes
	public Minivanes() {
		Minivanes = new TreeMap<>();
	}

	// Método para encontrar una minivan en el TreeMap por su matrícula
	public Minivan find(String matricula) {
		return Minivanes.get(matricula);
	}

	public boolean member(String matricula) {
		return Minivanes.containsKey(matricula);
	}

	// Método para insertar una nueva minivan en el TreeMap
	public void insert(Minivan minivan) {

		Minivanes.put(minivan.getMatricula(), minivan);

	}

	// Método que lista los paseos asignados a una minivan
	public LinkedList<VOPaseo> listarPaseosPorMinivan(String Ma) {
		LinkedList<VOPaseo> VOPaseos = new LinkedList<>();
		if (Minivanes.get(Ma) != null) {
			Minivan m = Minivanes.get(Ma);
			VOPaseos = m.listarPaseosAsignados();
			

		}

		return VOPaseos;
	}

	// Método para listar todas las minivanes en el sistema, creando un LinkedList
	// de VoMinivan
	public LinkedList<VoMinivan> listarMinivanes() {
	    LinkedList<VoMinivan> voMinivanes = new LinkedList<>();

	    for (Minivan minivan : Minivanes.values()) {
	        VoMinivan vmin = new VoMinivan(
	            minivan.getMatricula(),
	            minivan.getMarca(),
	            minivan.getModelo(),
	            minivan.getcantAsientos(),
	            minivan.getPaseosAsignados().cantPaseos()
	        );
	        voMinivanes.add(vmin);
	    }
	    return voMinivanes;
	}

	public Minivan findMinivanDisponible(LocalTime horaPartida, LocalTime horaRegreso) {
	    Iterator<Minivan> iter = Minivanes.values().iterator();
	    while (iter.hasNext()) {
	        Minivan minivan = iter.next(); 
	        if (minivan.estaDisponible(horaPartida, horaRegreso)) {
	            return minivan;
	        }
	    }
	    return null;
	}
	public TreeMap<String, Minivan> getMinivans() {
		return Minivanes;
	}

}

