package capaLogica.destinos;

import java.util.Iterator;
import java.util.LinkedList;

import capaLogica.minivanes.Minivan;
import capaLogica.minivanes.VoMinivan;

import java.io.Serializable;

public class Destinos implements Serializable {

	private LinkedList<Destino> Destinos;

	public Destinos() {
		this.Destinos = new LinkedList<>();
	}

	public void insert(Destino Dest) {
		Destinos.addLast(Dest);

	}
	
	

	public boolean member(String nombre) {
		Iterator<Destino> iter = Destinos.iterator();

		while (iter.hasNext()) {
			String nombdest = iter.next().getNombre();
			if (nombdest.equals(nombre)) {
				return true;
			}
		}
		return false;
	}


	
	
	
	public LinkedList<String> getDestinos() {
	    LinkedList<String> nombres = new LinkedList<>();
	    //recorre todos lso destinos y crea una lsita solo con los nombres
	        for (Destino d : Destinos) {
	            nombres.add(d.getNombre());
	        }

	    return nombres;
	}


}
