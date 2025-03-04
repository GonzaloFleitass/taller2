package capaLogica.boletos;

import java.io.Serializable;

public class VOBoleto implements Serializable {

	private int numBol;
	private String nombre;
	private int edad;
	private int celular;

	// Constructor para inicializar el VOBoleto con los datos proporcionados
	public VOBoleto(int numBo, String nomb, int ed, int celu) {
		numBol = numBo;
		nombre = nomb;
		edad = ed;
		celular = celu;
	}

	// Métodos getter para obtener los atributos de VOBoleto
	public int getNumBoleto() {
		return numBol;
	}

	public String getNombre() {
		return nombre;
	}

	public int getEdad() {
		return edad;
	}

	public int getCelular() {
		return celular;
	}
	
	
}
