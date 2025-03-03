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
            fach.insertDestino("Montevideo");
            fach.insertDestino("Colonia del Sacramento");
            fach.insertDestino("Cabo Polonio");
            fach.insertDestino("La Paloma");
            fach.insertDestino("Carmelo");
            fach.insertDestino("José Ignacio");
            fach.insertDestino("Termas de Arapey");
            fach.insertDestino("Termas de Salto Grande");
            fach.insertDestino("Parque Nacional Santa Teresa");
            fach.insertDestino("Isla Gorriti");
            fach.insertDestino("Punta Ballena");
            fach.insertDestino("Playa Unión");
            fach.insertDestino("Valizas");
            fach.insertDestino("La Pedrera");
            fach.insertDestino("Rocha");
            fach.insertDestino("Tacuarembó");
            fach.insertDestino("Durazno");
            fach.insertDestino("Fray Bentos");
            fach.insertDestino("San Gregorio de Polanco");
            fach.insertDestino("Atlántida");
            fach.insertDestino("Barra de Valizas");
            fach.insertDestino("Punta del Diablo");
            fach.insertDestino("Villa Serrana");
            fach.insertDestino("Villa Rodriguez");
            fach.insertDestino("Las Cañas");
            fach.insertDestino("Cuchilla Alta");
            fach.insertDestino("Aguas Dulces");
            fach.insertDestino("San Luis");
            fach.insertDestino("Solís Grande");


            // Insertar minivanes con una colección de paseos vacía
            fach.insertMinivan("MFL395", "Honda", "Odyssey", 8);
            fach.insertMinivan("MFL396", "Chrysler", "Pacifica", 7);
            fach.insertMinivan("MFL397", "Kia", "Carnival", 8);
            fach.insertMinivan("MFL398", "Nissan", "NV3500", 9);
            fach.insertMinivan("MFL399", "Dodge", "Grand Caravan", 7);
            fach.insertMinivan("MFL400", "Mazda", "5", 6);
            fach.insertMinivan("MFL401", "Volkswagen", "Sharan", 8);
            fach.insertMinivan("MFL402", "Ford", "Transit Connect", 7);
            fach.insertMinivan("MFL403", "Toyota", "Sienna", 8);
            fach.insertMinivan("MFL404", "Hyundai", "Palisade", 7);
            fach.insertMinivan("MFL405", "Chevrolet", "Traverse", 8);
            fach.insertMinivan("MFL406", "Renault", "Espace", 7);
            fach.insertMinivan("MFL407", "Peugeot", "5008", 7);
            fach.insertMinivan("MFL408", "BMW", "Series 2 Gran Tourer", 6);
            fach.insertMinivan("MFL409", "Mercedes", "V-Class", 9);
            fach.insertMinivan("MFL410", "Citroën", "Grand C4 Picasso", 7);
            fach.insertMinivan("MFL411", "Opel", "Zafira", 7);
            fach.insertMinivan("MFL412", "Seat", "Alhambra", 8);
            fach.insertMinivan("MFL413", "Land Rover", "Discovery", 7);
            fach.insertMinivan("MFL414", "Peugeot", "Traveller", 9);
            fach.insertMinivan("MFL415", "Toyota", "Proace Verso", 8);
            fach.insertMinivan("MFL416", "Ford", "Galaxy", 7);
            fach.insertMinivan("MFL417", "Volkswagen", "Caravelle", 9);
            fach.insertMinivan("MFL418", "Nissan", "Elgrand", 8);
            fach.insertMinivan("MFL419", "Kia", "Venga", 6);
            fach.insertMinivan("MFL420", "Chrysler", "Voyager", 7);

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