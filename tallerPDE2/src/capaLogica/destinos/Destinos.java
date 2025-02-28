package capaLogica.destinos;
import java.util.Iterator;
import java.util.LinkedList;
import java.io.Serializable;

public class Destinos implements Serializable  {

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
            if (nombdest.equals(nombre)){
                return true; 
            }
        }
        return false; 
    }
	
	public Destino find (String nombre) {
		Iterator<Destino> iter = Destinos.iterator();
		while (iter.hasNext()) {
	        Destino destino = iter.next();
	        if (destino.getNombre().equals(nombre)) {
	            return destino; 
	        }
	    }
	    return null; 
	}
	
 
	
}
