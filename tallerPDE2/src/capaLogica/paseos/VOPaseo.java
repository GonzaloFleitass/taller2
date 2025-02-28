package capaLogica.paseos;

import java.io.Serializable;
import java.time.LocalTime;

import capaLogica.destinos.*;
import capaLogica.boletos.*;

public class VOPaseo implements Serializable {

	private String codigo;
	private Destino destino;
	private LocalTime horaPartida;
	private LocalTime horaLlegada;
	private double precioBase;
	private int cantMaximaBoletos;
	private int cantBolDisp;

	// Constructor que inicializa todos los atributos
	public VOPaseo(String cod, Destino des, LocalTime hsPart, LocalTime hsLleg, double precioBa, int cantMaxBol,
			int cantBolDis) {
		codigo = cod;
		destino = des;
		horaPartida = hsPart;
		horaLlegada = hsLleg;
		precioBase = precioBa;
		cantMaximaBoletos = cantMaxBol;
		cantBolDisp = cantBolDis;
	}

	// Métodos getters para obtener los valores de los atributos
	public String getCodigo() {
		return codigo;
	}

	public Destino getDestino() {
		return destino;
	}

	public LocalTime getHoraPartida() {
		return horaPartida;
	}

	public LocalTime getHoraLlegada() {
		return horaLlegada;
	}

	public double getPrecioBase() {
		return precioBase;
	}

	public int getCantMaximaBoletos() {
		return cantMaximaBoletos;
	}

	public int getCantBolDisp() {
		return cantBolDisp;
	}

	// Método que imprime la información completa del paseo
	public void printVOPaseo() {
		System.out.println("Codigo: " + codigo + "\n" + "Destino: " + destino + "\n" + "Hora de Partida: " + horaPartida
				+ "\n" + "Hora de Llegada: " + horaLlegada + "\n" + "Precio Base: " + precioBase + "\n"
				+ "Cantidad maxima de boletos: " + cantMaximaBoletos + "\n" + "Boletos Disponibles: " + cantBolDisp);

	}
}
