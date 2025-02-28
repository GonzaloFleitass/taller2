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

    // Atributo que almacena las minivans en un TreeMap, usando la matrícula como clave
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
    public void insert(Minivan minivan)  {
       
        Minivanes.put(minivan.getMatricula(), minivan);
    		

    }
    
 // Método que lista los paseos asignados a una minivan
    public LinkedList<VOPaseo> listarPaseosPorMinivan(String Ma) {
        LinkedList<VOPaseo> VOPaseos = new LinkedList<>();
    	if(Minivanes.get(Ma)!=null) {
    		Minivan m = Minivanes.get(Ma);
    		VOPaseos = m.listarPaseosAsignados();
    		//comentario random que pidio Gonza
    		
        }

    	 return VOPaseos;
    }

    // Método para listar todas las minivanes en el sistema, creando un LinkedList de VoMinivan
    public LinkedList<VoMinivan> listarMinivanes() {
        Iterator<Minivan> iter = Minivanes.values().iterator();
        LinkedList<VoMinivan> VOMinivanes = new LinkedList<>();
    
        // Itera sobre cada minivan y crea un objeto VoMinivan con la información básica de cada una
        while (iter.hasNext()) {
            Minivan currentMinivan = iter.next();
            VoMinivan vmin = new VoMinivan(
                currentMinivan.getMatricula(),
                currentMinivan.getMarca(),
                currentMinivan.getModelo(),
                currentMinivan.getcantAsientos(),
                currentMinivan.getPaseosAsignados().cantPaseos()
            );
            VOMinivanes.add(vmin);
        }

        return VOMinivanes;
    }


   public Minivan findMinivanDisponible(LocalTime horaPartida, LocalTime horaRegreso) {
	   Iterator<Minivan> iter = Minivanes.values().iterator();
        while (iter.hasNext()) {
			if (iter.next().estaDisponible(horaPartida, horaRegreso)) {
                return iter.next();
            }
        }
        return null;
    }
    
}


/* Falta implementar: findMinivanDisponible(horaPartida, horaRegreso):Minivan
 member(String):boolean
 memberMinivanDisponible(horaPartida,horaRegreso):bool*/