
package capaGrafica;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import capaLogica.paseos.VOPaseo;
import capaLogica.boletos.VOBoleto;
import capaLogica.minivanes.miniVanException;

public class VentanaListadoPaseosAsignadosMinivan extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldMatricula;
    private JTable table;
    private ControladorListadoPaseoAsignadosMinivan controlador;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaListadoPaseosAsignadosMinivan frame = new VentanaListadoPaseosAsignadosMinivan();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    
    public VentanaListadoPaseosAsignadosMinivan() {
        setTitle("Listado de Paseos Asignados a Minivan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("List.background"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Título
        JLabel lblTitulo = new JLabel("Listado de Paseos por Minivan");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBounds(249, 5, 319, 16);
        contentPane.add(lblTitulo);

        // Etiqueta "Matrícula"
        JLabel lblMatricula = new JLabel("Matrícula:");
        lblMatricula.setBounds(219, 42, 80, 16);
        contentPane.add(lblMatricula);

        // Campo de texto para la matrícula
        textFieldMatricula = new JTextField();
        textFieldMatricula.setBounds(310, 37, 130, 26);
        contentPane.add(textFieldMatricula);
        textFieldMatricula.setColumns(10);

        // Botón Buscar
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(452, 37, 117, 29);
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarPaseos();
            }
        });
        contentPane.add(btnBuscar);

        // Botón Cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(10, 5, 117, 29);
        btnCancelar.addActionListener(e -> dispose());
        contentPane.add(btnCancelar);

        // Tabla con ScrollPane
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(23, 76, 749, 464);
        contentPane.add(scrollPane);

        // Asociamos el controlador
        controlador = new ControladorListadoPaseoAsignadosMinivan(this);
    }

    /**
     * Método para buscar los paseos asignados a una minivan y mostrarlos en la tabla.
     */
    private void buscarPaseos() {
        try {
            String matricula = textFieldMatricula.getText().trim();

            if (matricula.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese una matrícula.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Obtener la lista de paseos desde el controlador
            LinkedList<VOPaseo> listaPaseos = controlador.ListarPasPorMini(matricula);

            if (listaPaseos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay paseos asignados a esta minivan o la matrícula no existe.", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Obtener la lista de boletos desde el controlador
       
            if (listaPaseos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron paseos para la matricula ingresada.", 
                        "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                mostrarPaseosEnTabla(listaPaseos);
            }

        } catch (RemoteException | miniVanException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener los paseos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Método para mostrar los paseos en la tabla.
     */
    private void mostrarPaseosEnTabla(LinkedList<VOPaseo> listaPaseos) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hace que las celdas no sean editables
            }
        };

        // Definir columnas
        model.addColumn("Código");
        model.addColumn("Destino");
        model.addColumn("Hora Partida");
        model.addColumn("Hora Regreso");
        model.addColumn("Precio Base");
        model.addColumn("Máx. Boletos");
        model.addColumn("Boletos Disponibles");

        // Llenar la tabla con los paseos
        for (VOPaseo paseo : listaPaseos) {
            model.addRow(new Object[]{
                    paseo.getCodigo(),
                    paseo.getDestino().getNombre(),
                    paseo.getHoraPartida(),
                    paseo.getHoraLlegada(),
                    paseo.getPrecioBase(),
                    paseo.getCantMaximaBoletos(),
                    paseo.getCantBolDisp(),
            });
        }

        table.setModel(model);
    }
}