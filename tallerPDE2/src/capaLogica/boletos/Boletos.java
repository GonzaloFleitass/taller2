package capaLogica.boletos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import capaLogica.paseos.Paseos;

public class Boletos implements Serializable {
    private Boleto[] boletos;       // Arreglo para almacenar los boletos
    private int cantidadMax;        // Cantidad máxima de boletos que se pueden almacenar
    private int tope;               // Número de boletos almacenados actualmente

    // Constructor
    public Boletos(int cantidadMax) {
        this.cantidadMax = cantidadMax;
        this.boletos = new Boleto[cantidadMax];
        this.tope = 0;
    }

    // Verifica si un boleto con un número específico ya existe
    public boolean member(int numero) {
        for (int i = 0; i < tope; i++) {
            if (boletos[i].getNumBoleto() == numero) {
                return true;
            }
        }
        return false;
    }

    // Insertar un nuevo boleto en el arreglo
    public void insert(Boleto boleto) {
        if (tope < cantidadMax) {
            boletos[tope] = boleto;
            tope = tope++;
        } 
        
    }

    // Eliminar un boleto del arreglo
    public void delete(Boleto boleto) {
        for (int i = 0; i < tope; i++) {
            if (boletos[i].equals(boleto)) {
                boletos[i] = boletos[tope - 1];
                boletos[tope - 1] = null;
                tope--;
                return;
            }
        }
        System.out.println("Boleto no encontrado.");
    }

    // Registrar la venta de un boleto
    public void ventaBoleto(int numero, String nombre, int edad, int celular, String codigo) {
        insert(new Boleto(numero, nombre, edad, celular, codigo));
    }

    // Devuelve una lista de VOBoleto con la información de los boletos
    public List<VOBoleto> printBoletos() {
        List<VOBoleto> lista = new ArrayList<>();
        for (int i = 0; i < tope; i++) {
            VOBoleto boletoVO = new VOBoleto(boletos[i].getNumBoleto(), boletos[i].getNombre(), boletos[i].getEdad(), boletos[i].getCelular()); 
            lista.add(boletoVO);
        }
        return lista;
    }

    // Faltan controles de tipo de boleto y orden de los VOBoleto
    public LinkedList<VOBoleto> listarBoletosPorPaseo(String codigo, char tipoBoleto, Paseos pa) {
        LinkedList<VOBoleto> VOBoletos = new LinkedList<>();
        	
            for (int i = 0; i < tope; i++) {
            	if(tipoBoleto == boletos[i].tipoBoleto()) {
            		VOBoleto vbol = new VOBoleto(boletos[i].getNumBoleto(), boletos[i].getNombre(), boletos[i].getEdad(), boletos[i].getCelular());
            		VOBoletos.add(vbol);
            }
        }
        return VOBoletos;
    }

    // Devuelve el total de boletos registrados
    public int boletosTotales() {
        return tope;
    }

    // Calcula el total de ventas de boletos, considerando los descuentos para boletos especiales
    public double ventasFinalesBoletos(double x) {
        double valor = 0;
        for (int i = 0; i < tope; i++) {
            if (boletos[i].tipoBoleto() == 'C') {
                // Boleto común
                if (boletos[i].getEdad() > 18) {
                    valor += x;
                } else {
                    valor += x * 0.75;  // Descuento para menores de 18 años
                }
            } else {
                // Boleto especial
                Especial boletoEsp = (Especial) boletos[i];
                if (boletos[i].getEdad() > 18) {
                    valor += x - boletoEsp.getDesc();  // Descuento en boleto especial para mayores de 18 años
                } else {
                    valor += (x * 0.75) - boletoEsp.getDesc();  // Descuento combinado para menores de 18 años
                }
            }
        }
        return valor;
    }
}
