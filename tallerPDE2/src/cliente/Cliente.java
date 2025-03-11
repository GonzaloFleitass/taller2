package cliente;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Random;

import capaLogica.Ifachada;
import capaLogica.destinos.Destino;
import capaLogica.destinos.DestinoException;
import capaLogica.minivanes.VoMinivan;
import capaLogica.minivanes.miniVanException;
import capaLogica.boletos.VOBoleto;
import capaLogica.boletos.boletoException;
import capaLogica.paseos.VOPaseo;
import capaLogica.paseos.paseoException;
import capaPersistencia.PersistenciaException;

public class Cliente {
    public static void main(String[] args) throws DestinoException, paseoException, boletoException, InterruptedException, miniVanException {
        try {
            Ifachada fach = (Ifachada) Naming.lookup("//localhost:1099/fachada");

            // Insertar 20 destinos turísticos en Uruguay
            String[] destinos = {
                "Montevideo", "Colonia del Sacramento", "Cabo Polonio", "La Paloma", "Carmelo",
                "José Ignacio", "Termas del Daymán", "Termas de Arapey", "Parque Nacional Santa Teresa",
                "Piriápolis", "Isla Gorriti", "Punta del Este", "Valizas", "La Pedrera", "Rocha",
                "Tacuarembó", "Durazno", "Fray Bentos", "San Gregorio de Polanco", "Atlántida"
            };
            for (String destino : destinos) {
                fach.insertDestino(destino);
            }

            // Insertar 100 minivans con modelos específicos: A1, A2, A3, C1, C2, C3, B1, B2, B3
            Random random = new Random();
            String[] modelos = {"A1", "A2", "A3", "C1", "C2", "C3", "B1", "B2", "B3"};
            for (int i = 0; i < 70; i++) {
                String matricula = "MFL" + (421 + i);
                String marca = (i % 2 == 0) ? "Mercedes" : "Volkswagen";
                String modelo = modelos[random.nextInt(modelos.length)]; // Modelo aleatorio entre A1, A2, A3, C1, C2, C3, B1, B2, B3
                int capacidad = 8 + random.nextInt(5); // Capacidad entre 8 y 12
                fach.insertMinivan(matricula, marca, modelo, capacidad);
            }

            // Insertar 300 paseos
            for (int i = 0; i < 300; i++) {
                String codigoPaseo = "P" + (2222 + i);
                Destino destino = new Destino(destinos[random.nextInt(destinos.length)]);
                LocalTime horaSalida = LocalTime.of(random.nextInt(24), random.nextInt(60));
                LocalTime horaLlegada = horaSalida.plusHours(random.nextInt(5) + 1); // Duración entre 1 y 5 horas
                double precio = 20.0 + random.nextDouble() * 30.0; // Precio entre 20 y 50
                fach.insertPaseo(codigoPaseo, destino, horaSalida, horaLlegada, precio);
            }

         

          
            // Vender 600 boletos
            System.out.println("\n=== Venta de Boletos ===");
            for (int i = 0; i < 600; i++) {
                String cedula = "MLNP" + (16 + i);
                String nombre = "Cliente" + (i + 1);
                int edad = 18 + random.nextInt(50); // Edad entre 18 y 67
                int numeroAsiento = 1000 + i;
                String codigoPaseo = "P" + (2222 + random.nextInt(300)); // Paseo aleatorio
                double precio = 20.0 + random.nextDouble() * 30.0; // Precio entre 20 y 50
                char tipoBoleto = (i % 2 == 0) ? 'C' : 'E'; // Alternar entre 'C' (común) y 'E' (especial)
                fach.ventaBoleto(cedula, nombre, edad, numeroAsiento, codigoPaseo, precio, tipoBoleto);
            }

            // Mostrar monto recaudado por paseo
            System.out.println("\n=== Monto Recaudado por Paseo ===");
            for (int i = 0; i < 300; i++) {
                String codigoPaseo = "P" + (2222 + i);
                System.out.println("Monto recaudado para el paseo " + codigoPaseo + ": " + fach.montoRecaudado(codigoPaseo));
            }

            // Respaldar información
            try {
                fach.respaldar();
            } catch (PersistenciaException | IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}