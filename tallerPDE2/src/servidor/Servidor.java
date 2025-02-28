package servidor;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.time.LocalTime;
import java.util.LinkedList;

import capaLogica.Ifachada;
import capaLogica.fachada;
import capaLogica.boletos.Boleto;
import capaLogica.boletos.Boletos;
import capaLogica.boletos.boletoException;
import capaLogica.destinos.Destino;
import capaLogica.destinos.Destinos;
import capaLogica.minivanes.Minivan;
import capaLogica.minivanes.Minivanes;
import capaLogica.minivanes.miniVanException;
import capaLogica.paseos.Paseo;
import capaLogica.paseos.Paseos;
import capaLogica.paseos.VOPaseo;
import capaLogica.paseos.paseoException;

public class Servidor {
    
    public static void main(String[] args) throws paseoException, boletoException, miniVanException, InterruptedException {
        try {
            int puerto = 1099;

            // Verificar si el RMI Registry ya está activo
            try {
                LocateRegistry.createRegistry(puerto);
                System.out.println("RMI  iniciado en el puerto " + puerto);
            } catch (RemoteException e) {
                System.out.println("El puerto " + puerto + " ya está en uso. Verificando...");
            }

       

            // Instanciar fachada
            fachada fach = new fachada();

            Minivanes minivanes = new Minivanes();
            Paseos paseos = new Paseos();
            Paseos paseos2 = new Paseos();
            Destinos destinosDeLaEmpresa = new Destinos();

            // Crear destinos
            Destino des1 = new Destino("Punta Del Este");
            Destino des2 = new Destino("Piriapolis");
            destinosDeLaEmpresa.insert(des1);
            destinosDeLaEmpresa.insert(des2);

            // Crear boletos para paseos
            Boletos boletos1 = new Boletos(10);
            boletos1.insert(new Boleto(1, "Migue", 10, 2021, "MLNP12"));
            boletos1.insert(new Boleto(2, "Antonio", 25, 3021, "MLNP12"));

            Boletos boletos2 = new Boletos(10);
            boletos2.insert(new Boleto(1, "Carlos", 30, 4021, "1112"));
            boletos2.insert(new Boleto(2, "Laura", 28, 5021, "1112"));

            // Crear paseos
            Paseo pas1 = new Paseo("MLNP12", des1, LocalTime.of(14, 0), LocalTime.of(15, 0), 20.99, "MFL392", boletos1);
            Paseo pas2 = new Paseo("1112", des2, LocalTime.of(11, 30), LocalTime.of(13, 30), 20.99, "MFL392", boletos2);
            Paseo pas3 = new Paseo("1114", des2, LocalTime.of(16, 0), LocalTime.of(17, 0), 20.99, "MFL392", boletos2);
            paseos.insertPaseo(pas1);
            paseos2.insertPaseo(pas2);
            paseos.insertPaseo(pas3);

            // Crear minivanes
            minivanes.insert(new Minivan("MFL392", "Mercedes", "Lugi La Ferrari", 9, paseos));
            minivanes.insert(new Minivan("MFL393", "Toyota", "Corolla", 9, paseos));

            // Insertar minivan y paseo usando la fachada
            fach.insertMinivan("MFL394", "Ford", "Mustang", 6, paseos);
            fach.insertMinivan("MFL333", "Toyota", "Corolla", 9, paseos2);
            fach.insertPaseo("2224", des1, LocalTime.of(14, 0), LocalTime.of(15, 0), 25.99);

            // Listar minivanes
            System.out.println("=== Listado de Minivanes ===");
            fach.listadoMinivanes();

            // Listar paseos por minivan
            System.out.println("\n=== Listado de Paseos por Minivan (MFL392) ===");
            fach.listarPaseosPorMinivan("MFL392");

            // Listar paseos con boletos disponibles
            System.out.println("\n=== Listado de Paseos con Boletos Disponibles (Cantidad >= 1) ===");
            for (VOPaseo paseo : fach.listarPaseosDispBoletos(1)) {
                paseo.printVOPaseo();
            }

            /* Vender boletos
           System.out.println("\n=== Venta de Boletos ===");
           fach.ventaBoleto("MLNP14", "Carlos", 30, 4021, "MLNP12", 0, true); // Boleto común
           fach.ventaBoleto("MLNP15", "Laura", 28, 5021, "MLNP12", 14, false); // Boleto especial

            // Mostrar monto recaudado por paseo
            System.out.println("\n=== Monto Recaudado por Paseo ===");
            System.out.println("Monto recaudado para el paseo MLNP12: " + fach.montoRecaudado("MLNP12"));
            System.out.println("Monto recaudado para el paseo 1112: " + fach.montoRecaudado("1112"));

            // Publicar el objeto remoto
            System.out.println("\nPublicando fachada en RMI...");
            Naming.rebind("//localhost:" + puerto + "/fachada", fach);
            System.out.println("Fachada publicada exitosamente.");
            */

        } catch (RemoteException e) {
            System.err.println("Error en RMI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

