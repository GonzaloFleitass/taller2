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
import capaLogica.boletos.Boleto;
import capaLogica.boletos.Boletos;
import capaLogica.boletos.boletoException;
import capaLogica.paseos.Paseo;
import capaLogica.paseos.VOPaseo;
import capaLogica.paseos.paseoException;

public class Cliente {
    public static void main(String[] args) throws DestinoException, paseoException, boletoException {
        try {
            // Conectar con la fachada remota
            Ifachada fach = (Ifachada) Naming.lookup("//localhost:1099/fachada");
            
            // Crear y registrar destinos
            System.out.println("Registrando destinos...");
            fach.insertDestino("Punta Del Este");
            fach.insertDestino("Piriapolis");
            
            // Crear boletos
            Boletos boletos1 = new Boletos(10);
            boletos1.insert(new Boleto(1, "Migue", 10, 2021, "MLNP12"));
            boletos1.insert(new Boleto(2, "Antonio", 25, 3021, "MLNP12"));
            
            // Crear paseo y registrarlo en la fachada
            System.out.println("Registrando paseo...");
            fach.insertPaseo("MLNP12", new Destino("Punta Del Este"), LocalTime.of(14, 0), LocalTime.of(15, 0), 20.99);
            
            // Listar paseos con boletos disponibles
            System.out.println("\n=== Paseos con Boletos Disponibles ===");
            LinkedList<VOPaseo> paseosDisponibles = fach.listarPaseosDispBoletos(1);
            for (VOPaseo paseo : paseosDisponibles) {
                paseo.printVOPaseo();
            }
            
            // Venta de boletos
            System.out.println("\n=== Venta de Boleto ===");
            fach.ventaBoleto("MLNP14", "Carlos", 30, 4021, "MLNP12", 0, true);
            
            // Mostrar monto recaudado
            System.out.println("\n=== Monto Recaudado ===");
            System.out.println("Monto recaudado para MLNP12: " + fach.montoRecaudado("MLNP12"));
            
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}