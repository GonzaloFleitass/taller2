package capaGrafica;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import capaPersistencia.PersistenciaException;

import java.awt.*;
import java.rmi.RemoteException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMenuPrincipal extends JFrame {

    public VentanaMenuPrincipal() {
        setTitle("Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
  
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(new Color(240, 240, 240)); // Fondo suave

        JLabel titulo = new JLabel("Menú Principal");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28)); // Fuente limpia y tamaño mayor
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelPrincipal.add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(5, 2, 20, 20));
        panelBotones.setBorder(new EmptyBorder(30, 30, 30, 30));
        panelBotones.setBackground(new Color(240, 240, 240)); // Fondo suave

        JButton btnIngresoDestino = crearBoton("Ingreso de Destino");
        JButton btnIngresoMinivan = crearBoton("Ingreso de Minivan");
        JButton btnIngresoPaseo = crearBoton("Ingreso de Paseo");
        JButton btnListadoBoletosVendPorPaseo = crearBoton("Listado de Boletos Vendidos por Paseo");
        JButton btnListadoDisponibilidadBoletos = crearBoton("Listado de Disponibilidad de Boletos");
        JButton btnListadoMinivanes = crearBoton("Listado de Minivanes");
        JButton btnListadoPaseosAsignadoMinivan = crearBoton("Listado de Paseos Asignados a Minivan");
        JButton btnListadoPaseosPorDestino = crearBoton("Listado de Paseos por Destino");
        JButton btnMontoRecaudado = crearBoton("Monto Recaudado");
        JButton btnVentaBoleto = crearBoton("Venta de Boleto");

        btnIngresoDestino.addActionListener(e -> {
            try {
                VentanaIngresoDestino ventanaDestino = VentanaIngresoDestino.getInstancia();
                ventanaDestino.setVisible(true);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        });
        btnIngresoMinivan.addActionListener(e -> abrirVentana(VentanaIngresoMinivan.getInstancia()));
        btnIngresoPaseo.addActionListener(e -> abrirVentana(VentanaIngresoPaseo.getInstancia()));
        btnListadoBoletosVendPorPaseo.addActionListener(e -> abrirVentana(VentanaListadoBoletosVendPorPaseo.getInstancia()));
        btnListadoDisponibilidadBoletos.addActionListener(e -> abrirVentana(VentanaListadoDisponibilidadBoletos.getInstancia()));
        btnListadoMinivanes.addActionListener(e -> abrirVentana(VentanaListadoMinivanes.getInstancia()));
        btnListadoPaseosAsignadoMinivan.addActionListener(e -> abrirVentana(VentanaListadoPaseosAsignadosMinivan.getInstancia()));
        btnListadoPaseosPorDestino.addActionListener(e -> abrirVentana(VentanaListadoPaseosPorDestino.getInstancia()));
        btnMontoRecaudado.addActionListener(e -> abrirVentana(VentanaMontoRecaudado.getInstancia()));
        btnVentaBoleto.addActionListener(e -> abrirVentana(VentanaVentaBoleto.getInstancia()));
        
        JMenuBar menuBar = new JMenuBar();
        getContentPane().add(menuBar, BorderLayout.NORTH);
        
        JMenu mnNewMenu = new JMenu("Archivo");
        menuBar.add(mnNewMenu);
        
        JMenuItem mntmNewMenuItem = new JMenuItem("Respaldar");
        mntmNewMenuItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ControladorRespaldar controlador = new ControladorRespaldar(VentanaMenuPrincipal.this);
        		try {
					controlador.respaldar();
					 JOptionPane.showMessageDialog(VentanaMenuPrincipal.this, "Guardado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				} catch (RemoteException | PersistenciaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        mnNewMenu.add(mntmNewMenuItem);

        panelBotones.add(btnIngresoDestino);
        panelBotones.add(btnIngresoMinivan);
        panelBotones.add(btnIngresoPaseo);
        panelBotones.add(btnListadoBoletosVendPorPaseo);
        panelBotones.add(btnListadoDisponibilidadBoletos);
        panelBotones.add(btnListadoMinivanes);
        panelBotones.add(btnListadoPaseosAsignadoMinivan);
        panelBotones.add(btnListadoPaseosPorDestino);
        panelBotones.add(btnMontoRecaudado);
        panelBotones.add(btnVentaBoleto);

        panelPrincipal.add(panelBotones, BorderLayout.CENTER);
        getContentPane().add(panelPrincipal);
        setVisible(true);
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setPreferredSize(new Dimension(200, 50)); // Tamaño ajustado
        boton.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // Fuente limpia
        boton.setBackground(new Color(255, 255, 255)); // Fondo blanco para los botones
        boton.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 200, 200)), // Borde gris claro
                new EmptyBorder(10, 20, 10, 20))); // Margen interno
        boton.setFocusPainted(false); // Eliminar el borde de enfoque
        return boton;
    }

    private void abrirVentana(JFrame ventana) {
        ventana.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaMenuPrincipal());
    }
}