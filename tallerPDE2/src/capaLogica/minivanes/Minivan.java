package capaLogica.minivanes;

import capaLogica.paseos.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.LinkedList;
import capaLogica.paseos.*;
public class Minivan implements Serializable {

    private String matricula;
    private String marca;
    private String modelo;
    private int cantAsientos;
    private Paseos paseosAsignados;

    // Constructor para inicializar la minivan con los valores proporcionados
    public Minivan(String mat, String marc, String mod, int cantA, Paseos PAsig) {
        matricula = mat;
        marca = marc;
        modelo = mod;
        cantAsientos = cantA;
        paseosAsignados = PAsig;
    }

    // Métodos getter para obtener los atributos de la Minivan
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

    public Paseos getPaseosAsignados() {
    	return paseosAsignados;
    }
    
    public void setPaseosAsignados(Paseo pas) {
        paseosAsignados.insertPaseo(pas);
    }
       

    // Método que verifica si la minivan está disponible en un rango de horarios
    public boolean estaDisponible(LocalTime horaPartida, LocalTime horaRegreso) {
        Iterator<Paseo> iter = paseosAsignados.getPaseos().values().iterator();
        while (iter.hasNext()) {
            Paseo pas = iter.next();
            // Si las horas no se solapan, la minivan no está disponible
            if (!(horaRegreso.isBefore(pas.getHoraPartida()) || horaPartida.isAfter(pas.getHoraLlegada()))) {
                return false;
            }
        }
        return true;
    }
    
    public LinkedList<VOPaseo> listarPaseosAsignados(){
    	Iterator<Paseo> iter = paseosAsignados.getPaseos().values().iterator();
    	LinkedList<VOPaseo> VOPaseos = new LinkedList<>();
    	while (iter.hasNext()) {
            Paseo pas = iter.next();
            VOPaseo vpas = new VOPaseo(pas.getCodigo(), pas.getDestino(), pas.getHoraPartida(), pas.getHoraLlegada(), pas.getPrecioBase(), pas.cantMaxBoletos(this), pas.cantBolDisp(this));
            VOPaseos.add(vpas);
    }
    	return VOPaseos;
}
}
