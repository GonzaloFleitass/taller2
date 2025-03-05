package capaLogica;

import capaLogica.*;
import capaLogica.paseos.*;
import capaPersistencia.PersistenciaException;
import capaLogica.boletos.*;
import capaLogica.minivanes.*;
import capaLogica.monitor.*;
import capaLogica.destinos.*;

import java.rmi.RemoteException;
import java.time.LocalTime;
import java.util.LinkedList;

public class MAIN {

    public static void main(String[] args) throws InterruptedException {
        try {
            // Crear una instancia de la fachada
            fachada fachada = new fachada();

            // Insertar un destino
            fachada.insertDestino("Playa del Sol");
            System.out.println("Destino insertado con éxito");

            // Insertar una minivan
            fachada.insertMinivan("ABC123", "Ford", "Transit", 12);
            System.out.println("Minivan insertada con éxito");

            // Crear un objeto Destino para el paseo
            Destino destino = new Destino("Playa del Sol");

            // Insertar un paseo
            fachada.insertPaseo("P001", destino, LocalTime.of(9, 0), LocalTime.of(12, 0), 100.0);
            System.out.println("Paseo insertado con éxito");

            // Listar minivanes
            LinkedList<VoMinivan> minivanes = fachada.listadoMinivanes();
            System.out.println("Listado de minivanes:");
            for (VoMinivan voMinivan : minivanes) {
                System.out.println(voMinivan.toString());
            }

            // Listar paseos por destino
            LinkedList<VOPaseo> paseosPorDestino = fachada.listarPaseosPorDestinos(destino);
            System.out.println("Paseos para el destino 'Playa del Sol':");
            for (VOPaseo voPaseo : paseosPorDestino) {
                System.out.println(voPaseo.toString());
            }

            // Vender un boleto
            fachada.ventaBoleto("B001", "Juan Perez", 30, 123456789, "P001", 0.0, 'E');
            fachada.ventaBoleto("B002", "Juan Perez", 30, 123456789, "P001", 0.0, 'E');
            fachada.ventaBoleto("B003", "Juan Perez", 30, 123456789, "P001", 0.0, 'E');
            fachada.ventaBoleto("B004", "Juan Perez", 30, 123456789, "P001", 0.0, 'E');
            fachada.ventaBoleto("B005", "Juan Perez", 30, 123456789, "P001", 0.0, 'E');
            fachada.ventaBoleto("B006", "Juan Perez", 30, 123456789, "P001", 0.0,'E');
            System.out.println("Boleto vendido con éxito");

            // Listar boletos por paseo
            LinkedList<VOBoleto> boletosPorPaseo = fachada.listarBoletosPorPaseo("P001", 'E');
            System.out.println("Boletos para el paseo P001:");
            for (VOBoleto voBoleto : boletosPorPaseo) {
                System.out.println(voBoleto.getNombre());
                System.out.println(voBoleto.getNumBoleto());
            }
            
            LinkedList<VOPaseo> PaseoPorDestino = fachada.listarPaseosPorDestinos(destino);
            System.out.println("---------------");
            for(VOPaseo paseo:PaseoPorDestino) {
            	System.out.println(paseo.getCodigo());
            	System.out.println(paseo.getPrecioBase());
            }
            System.out.println("---------------");

            // Obtener monto recaudado por un paseo
            double montoRecaudado = fachada.montoRecaudado("P001");
            System.out.println("Monto recaudado para el paseo P001: " + montoRecaudado);

        } catch (RemoteException | miniVanException | paseoException | DestinoException e) {
            e.printStackTrace();
        }
    }
}

