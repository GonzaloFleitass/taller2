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

	
	public VoMinivan(String mat, String marc, String mod, int cantA, int cantPas) {
		matricula = mat;
		marca = marc;
		modelo = mod;
		cantAsientos = cantA;
		cantPaseos = cantPas;
	}

	
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


}
