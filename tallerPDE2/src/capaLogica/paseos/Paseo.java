package capaLogica.paseos;

import capaLogica.destinos.Destino;
import capaLogica.minivanes.Minivan;
import capaLogica.minivanes.Minivanes;
import capaLogica.boletos.Boletos;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Iterator;

public class Paseo implements Serializable {

	private String codigo;
	private Destino destino;
	private LocalTime horaPartida;
	private LocalTime horaLlegada;
	private Double precioBase;
	private String matricula;
	private Boletos boletos;

	// Constructor que inicializa un Paseo con los valores dados
	public Paseo(String cod, Destino dest, LocalTime hpart, LocalTime hllega, Double prec, String mat, Boletos bol) {
		codigo = cod;
		destino = dest;
		horaPartida = hpart;
		horaLlegada = hllega;
		precioBase = prec;
		matricula = mat;
		boletos = bol;
	}

	// Métodos getter para obtener los atributos del Paseo
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

	public String getMatricula() {
		return matricula;
	}

	public Boletos getBoletos() {
		return boletos;
	}

	
	// Para usarlo al momento de usar el cabezal debe llenarse así: LocalTime.of(10,
	// 30) eso sería 10:30
	public void setHoraPartida(LocalTime horaPartida) {
		this.horaPartida = horaPartida;
	}

	// Método setter para la hora de llegada
	// Para usarlo al momento de usar el cabezal debe llenarse así: LocalTime.of(10,
	// 30) eso sería 10:30
	public void setHoraLlegada(LocalTime horaLlegada) {
		this.horaLlegada = horaLlegada;
	}

	// Método que retorna la cantidad máxima de boletos según la minivan asignada
	public int cantMaxBoletos(Minivan mini) {
		return mini.getcantAsientos();
	}

	// Método que calcula la cantidad de boletos disponibles
	public int cantBolDisp(Minivan minivan) {
		return cantMaxBoletos(minivan) - boletos.boletosTotales();
	}

}
