package capaGrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class VentanaMenuPrincipal extends JFrame {
	
	
    public VentanaMenuPrincipal() {
        setTitle("Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,800); // Tamaño de la ventana
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Panel principal con GridLayout para organizar los botones
        JPanel panelPrincipal = new JPanel(new GridLayout(5, 2, 10, 10)); // 5 filas, 2 columnas, espacio entre componentes
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margen exterior

        // Botones para cada ventana
        JButton btnIngresoDestino = new JButton("Ingreso de Destino");
        JButton btnIngresoMinivan = new JButton("Ingreso de Minivan");
        JButton btnIngresoPaseo = new JButton("Ingreso de Paseo");
        JButton btnListadoBoletosVendPorPaseo = new JButton("Listado de Boletos Vendidos por Paseo");
        JButton btnListadoDisponibilidadBoletos = new JButton("Listado de Disponibilidad de Boletos");
        JButton btnListadoMinivanes = new JButton("Listado de Minivanes");
        JButton btnListadoPaseosAsignadoMinivan = new JButton("Listado de Paseos Asignados a Minivan");
        JButton btnListadoPaseosPorDestino = new JButton("Listado de Paseos por Destino");
        JButton btnMontoRecaudado = new JButton("Monto Recaudado");
        JButton btnVentaBoleto = new JButton("Venta de Boleto");

        // Agregar acciones a los botones
        btnIngresoDestino.addActionListener(e -> {
			try {
				abrirVentana(new VentanaIngresoDestino());
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        btnIngresoMinivan.addActionListener(e -> abrirVentana(new VentanaIngresoMinivan()));
        btnIngresoPaseo.addActionListener(e -> abrirVentana(new VentanaIngresoPaseo()));
        btnListadoBoletosVendPorPaseo.addActionListener(e -> abrirVentana(new VentanaListadoBoletosVendPorPaseo()));
        btnListadoDisponibilidadBoletos.addActionListener(e -> abrirVentana(new VentanaListadoDisponibilidadBoletos()));
        btnListadoMinivanes.addActionListener(e -> abrirVentana(new VentanaListadoMinivanes()));
        btnListadoPaseosAsignadoMinivan.addActionListener(e -> abrirVentana(new VentanaListadoPaseosAsignadosMinivan()));
        btnListadoPaseosPorDestino.addActionListener(e -> abrirVentana(new VentanaListadoPaseosPorDestino()));
        btnMontoRecaudado.addActionListener(e -> abrirVentana(new VentanaMontoRecaudado()));
        btnVentaBoleto.addActionListener(e -> abrirVentana(new VentanaVentaBoleto()));

        // Agregar botones al panel principal...
        panelPrincipal.add(btnIngresoDestino);
        panelPrincipal.add(btnIngresoMinivan);
        panelPrincipal.add(btnIngresoPaseo);
        panelPrincipal.add(btnListadoBoletosVendPorPaseo);
        panelPrincipal.add(btnListadoDisponibilidadBoletos);
        panelPrincipal.add(btnListadoMinivanes);
        panelPrincipal.add(btnListadoPaseosAsignadoMinivan);
        panelPrincipal.add(btnListadoPaseosPorDestino);
        panelPrincipal.add(btnMontoRecaudado);
        panelPrincipal.add(btnVentaBoleto);

        // Agregar el panel principal al JFrame
        add(panelPrincipal);

        // Hacer visible la ventana
        setVisible(true);
    }

  
    private void abrirVentana(JFrame ventana) {
        ventana.setVisible(true); // Hacer visible la ventana
    }

    public static void main(String[] args) {
        // Ejecutar la ventana principal en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> new VentanaMenuPrincipal());
    }
}