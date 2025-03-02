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
import capaLogica.boletos.boletoException;
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

            // Insertar minivanes con una colección de paseos vacía
            fach.insertMinivan("MFL392", "Mercedes", "Lugi La Ferrari", 9, new capaLogica.paseos.Paseos());
            fach.insertMinivan("MFL393", "Toyota", "Corolla", 9, new capaLogica.paseos.Paseos());
            fach.insertMinivan("MFL394", "Ford", "Mustang", 6, new capaLogica.paseos.Paseos());
            fach.insertMinivan("MFL333", "Toyota", "Corolla", 9, new capaLogica.paseos.Paseos());

            // Insertar paseos con horarios válidos
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
            try {
                LinkedList<VOPaseo> paseosMinivan = fach.listarPaseosPorMinivan("MFL392");
                if (paseosMinivan.isEmpty()) {
                    System.out.println("No hay paseos asignados a la minivan MFL392.");
                } else {
                    for (VOPaseo paseo : paseosMinivan) {
                        paseo.printVOPaseo();
                    }
                }
            } catch (RemoteException | miniVanException e) {
                System.err.println("Error al listar paseos por minivan: " + e.getMessage());
                e.printStackTrace();
            }

            // Listar paseos con boletos disponibles
            System.out.println("\n=== Listado de Paseos con Boletos Disponibles (Cantidad >= 1) ===");
            try {
                LinkedList<VOPaseo> paseosDisponibles = fach.listarPaseosDispBoletos(1);
                for (VOPaseo paseo : paseosDisponibles) {
                    paseo.printVOPaseo();
                }
            } catch (RemoteException | boletoException e) {
                System.err.println("Error al listar paseos disponibles: " + e.getMessage());
                e.printStackTrace();
            }

            // Vender boletos
            System.out.println("\n=== Venta de Boletos ===");
            boolean boleto = true;
            fach.ventaBoleto("MLNP14", "Carlos", 30, 4021, "MLNP12", 0, boleto); // Boleto común
            boleto = false;
            fach.ventaBoleto("MLNP15", "Laura", 28, 5021, "MLNP12", 14, boleto); // Boleto especial

            // Mostrar monto recaudado por paseo
            System.out.println("\n=== Monto Recaudado por Paseo ===");
            System.out.println("Monto recaudado para el paseo MLNP12: " + fach.montoRecaudado("MLNP12"));
            System.out.println("Monto recaudado para el paseo 1112: " + fach.montoRecaudado("1112"));
            
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}