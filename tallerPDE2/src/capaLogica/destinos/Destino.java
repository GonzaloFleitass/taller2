package capaLogica.destinos;

import java.io.Serializable;

public class Destino implements Serializable{
	private String nombre;
	
	public Destino(String nomb)
	{ nombre = nomb;}
	
	public String getNombre() {
		return nombre;
	}
}
