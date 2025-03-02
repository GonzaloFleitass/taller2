package cliente;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalTime;
import java.util.LinkedList;

import capaLogica.Ifachada;
import capaLogica.destinos.Destino;
import capaLogica.destinos.DestinoException;
import capaLogica.minivanes.VoMinivan;
import capaLogica.minivanes.miniVanException;
import capaLogica.boletos.Boleto;
import capaLogica.boletos.Boletos;
import capaLogica.boletos.boletoException;
import capaLogica.paseos.Paseo;
import capaLogica.paseos.Paseos;
import capaLogica.paseos.VOPaseo;
import capaLogica.paseos.paseoException;

public class Cliente {
    public static void main(String[] args) throws DestinoException, paseoException, boletoException, InterruptedException, miniVanException {
        try {
            // Conectar con la fachada remota
            Ifachada fach = (Ifachada) Naming.lookup("//localhost:1099/fachada");

            // Insertar destinos
            fach.insertDestino("Punta Del Este");
            fach.insertDestino("Piriapolis");
        	Boletos boletos1 = new Boletos(10);
    		Boleto bol1 = new Boleto(1, "Migue", 10, 02021, "MLNP12");
    		Boleto bol2 = new Boleto(2, "Antonio", 25, 03021, "MLNP12");
    		boletos1.insert(bol1);
    		boletos1.insert(bol2);

    		// Crear boletos para el segundo paseo
    		Boletos boletos2 = new Boletos(10);
    		Boleto bol3 = new Boleto(1, "Carlos", 30, 04021, "1112");
    		Boleto bol4 = new Boleto(2, "Laura", 28, 05021, "1112");
    		boletos2.insert(bol3);
    		boletos2.insert(bol4);

    		// Crear paseos con sus propias colecciones de boletos
    		Paseo pas1 = new Paseo("MLNP12", new Destino("Punta Del Este"), LocalTime.of(14, 0), LocalTime.of(15, 0), 20.99, "MFL392", boletos1);
    		Paseo pas2 = new Paseo("1112", new Destino("Piriapolis"), LocalTime.of(11, 30), LocalTime.of(13, 30), 20.99, "MFL392", boletos2);
    		Paseo pas3 = new Paseo("1114",new Destino("Piriapolis"), LocalTime.of(16, 0), LocalTime.of(17, 0), 20.99, "MFL392", boletos2);
      
            Paseos paseos = new Paseos();
            Paseos paseos2 = new Paseos();

            fach.insertMinivan("MFL392", "Mercedes", "Lugi La Ferrari", 9, paseos);
            fach.insertMinivan("MFL393", "Toyota", "Corolla", 9, paseos);
            fach.insertMinivan("MFL394", "Ford", "Mustang", 6, paseos);
            fach.insertMinivan("MFL333", "Toyota", "Corolla", 9, paseos2);

            // Insertar paseos 
            fach.insertPaseo("MLNP12", new Destino("Punta Del Este"), LocalTime.of(14, 0), LocalTime.of(15, 0), 20.99);
            fach.insertPaseo("1112", new Destino("Piriapolis"), LocalTime.of(11, 30), LocalTime.of(13, 30), 20.99);
            fach.insertPaseo("1114", new Destino("Piriapolis"), LocalTime.of(16, 0), LocalTime.of(17, 0), 20.99);
            fach.insertPaseo("2224", new Destino("Punta Del Este"), LocalTime.of(14, 0), LocalTime.of(15, 0), 25.99);

            // Listar minivanes
            System.out.println("=== Listado de Minivanes ===");
            try {
                LinkedList<VoMinivan> minivanes = fach.listadoMinivanes();
                for (VoMinivan mini : minivanes) {
                    mini.printVOMinivan();
                }
            } catch (RemoteException e) {
                System.err.println("Error al listar minivanes: " + e.getMessage());
                e.printStackTrace();
            }
         

            // Listar paseos por minivan
            System.out.println("\n=== Listado de Paseos por Minivan (MFL392) ===");
            LinkedList<VOPaseo> paseosMinivan = fach.listarPaseosPorMinivan("MFL393");
            for (VOPaseo paseo : paseosMinivan) {
                paseo.printVOPaseo();
            }

            // Listar paseos con boletos disponibles
            System.out.println("\n=== Listado de Paseos con Boletos Disponibles (Cantidad >= 1) ===");
            LinkedList<VOPaseo> paseosDisponibles = fach.listarPaseosDispBoletos(1);
            for (VOPaseo paseo : paseosDisponibles) {
                paseo.printVOPaseo();
            }

            // Vender boletos (ajustar números de teléfono como en el main de referencia)
            System.out.println("\n=== Venta de Boletos ===");
            boolean boleto = true;
            fach.ventaBoleto("MLNP14", "Carlos", 30, 04021, "MLNP12", 0, boleto); // Boleto común
            boleto = false;
            fach.ventaBoleto("MLNP15", "Laura", 28, 05021, "MLNP12", 14, boleto); // Boleto especial

            // Mostrar monto recaudado por paseo
            System.out.println("\n=== Monto Recaudado por Paseo ===");
            System.out.println("Monto recaudado para el paseo MLNP12: " + fach.montoRecaudado("MLNP12"));
            System.out.println("Monto recaudado para el paseo 1112: " + fach.montoRecaudado("MLNP12"));
            
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}