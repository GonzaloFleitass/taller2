package capaLogica.minivanes;

import java.io.Serializable;

import capaLogica.paseos.Paseos;

public class VoMinivan implements Serializable{

	// Atributos que almacenan información básica sobre la minivan
	private String matricula;
	private String marca;
	private String modelo;
	private int cantAsientos;
	private int cantPaseos;

	// Constructor para inicializar todos los atributos
	public VoMinivan(String mat, String marc, String mod, int cantA, int cantPas) {
		matricula = mat;
		marca = marc;
		modelo = mod;
		cantAsientos = cantA;
		cantPaseos = cantPas;
	}

	// Métodos para obtener los valores de los atributos
	public String getMatricula() {
		return matricula;
	}

	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}

	public int getcantAsientos() {
		return cantAsientos;
	}

	public int getcantPaseos() {
		return cantPaseos;
	}

	// Método para imprimir la información de la minivan de forma legible
	public void printVOMinivan() {
		System.out.println("Matricula: " + matricula + "\n" + "Marca: " + marca + "\n" + "Modelo: " + modelo + "\n"
				+ "Cantidad de asientos: " + cantAsientos + "\n" + "Cantidad de paseos: " + cantPaseos);
	}
}
