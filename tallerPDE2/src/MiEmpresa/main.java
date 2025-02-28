package MiEmpresa;

import capaLogica.Ifachada;

import capaLogica.fachada;
import capaLogica.destinos.*;
import capaLogica.minivanes.*;
import capaLogica.paseos.*;
import capaLogica.boletos.*;

import java.rmi.RemoteException;
import java.time.LocalTime;
import java.util.LinkedList;

public class main {

	public static void main(String[] args)
			throws miniVanException, paseoException, boletoException, RemoteException, InterruptedException {
		// Inicialización de las colecciones
		Minivanes minivanes = new Minivanes();
		Paseos paseos = new Paseos();
		Paseos paseos2 = new Paseos();
		Destinos destinosDeLaEmpresa = new Destinos();

		// Crear la fachad
		Ifachada fach = new fachada();

		// Crear destinos
		Destino des1 = new Destino("Punta Del Este");
		Destino des2 = new Destino("Piriapolis");
		destinosDeLaEmpresa.insert(des1);
		destinosDeLaEmpresa.insert(des2);

		// Crear boletos para el primer paseo
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
		Paseo pas1 = new Paseo("MLNP12", des1, LocalTime.of(14, 0), LocalTime.of(15, 0), 20.99, "MFL392", boletos1);
		Paseo pas2 = new Paseo("1112", des2, LocalTime.of(11, 30), LocalTime.of(13, 30), 20.99, "MFL392", boletos2);
		Paseo pas3 = new Paseo("1114", des2, LocalTime.of(16, 0), LocalTime.of(17, 0), 20.99, "MFL392", boletos2);
		paseos.insertPaseo(pas1);
		paseos2.insertPaseo(pas2);
		paseos.insertPaseo(pas3);
		// Crear minivanes
		Minivan mini1 = new Minivan("MFL392", "Mercedes", "Lugi La Ferrari", 9, paseos);
		Minivan mini2 = new Minivan("MFL393", "Toyota", "Corolla", 9, paseos);

		minivanes.insert(mini1);
		minivanes.insert(mini2);

		// Insertar minivan y paseo usando la fachada
		fach.insertMinivan("MFL394", "Ford", "Mustang", 6, paseos);
		fach.insertMinivan("MFL333", "Toyota", "Corolla", 9, paseos2);
		// fach.insertPaseo("2223", des1, LocalTime.of(15, 0), LocalTime.of(14, 0),
		// 25.99);
		fach.insertPaseo("2224", des1, LocalTime.of(14, 0), LocalTime.of(15, 0), 25.99);

		// Listar minivanes
		System.out.println("=== Listado de Minivanes ===");
		fach.listadoMinivanes();

		// Listar paseos por minivan
		System.out.println("\n=== Listado de Paseos por Minivan (MFL392) ===");
		fach.listarPaseosPorMinivan("MFL392");

		// Listar paseos con boletos disponibles
		System.out.println("\n=== Listado de Paseos con Boletos Disponibles (Cantidad >= 1) ===");
		LinkedList<VOPaseo> paseosDisponibles = fach.listarPaseosDispBoletos(1);
		// fore each se llama esto, lo que hace es basicametne lo mismo que el iterador
		// pero sin tener que creaas instancias
		for (VOPaseo paseo : paseosDisponibles) {
			paseo.printVOPaseo();
		}

		// Vender boletos
		System.out.println("\n=== Venta de Boletos ===");
		boolean boleto = true;
		fach.ventaBoleto("MLNP14", "Carlos", 30, 04021, "MLNP12", 0, boleto); // Boleto común
		boleto = false;
		fach.ventaBoleto("MLNP15", "Laura", 28, 05021, "MLNP12", 14, boleto); // Boleto especial

		// Mostrar monto recaudado por paseo
		System.out.println("\n=== Monto Recaudado por Paseo ===");
		System.out.println("Monto recaudado para el paseo MLNP12: " + fach.montoRecaudado("MLNP12"));
		System.out.println("Monto recaudado para el paseo 1112: " + fach.montoRecaudado("MLNP12"));
	}
}
