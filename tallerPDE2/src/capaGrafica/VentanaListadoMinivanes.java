package capaGrafica;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import capaLogica.minivanes.VoMinivan;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.awt.Font;

public class VentanaListadoMinivanes extends JFrame {

    private static VentanaListadoMinivanes instancia; // Instancia única (Singleton)
    private JTable table;
    private ControladorListadoMinivanes controladorListadoMinivanes;

    // Constructor privado para evitar instanciación directa
    private VentanaListadoMinivanes() {
        configurarVentana();
        inicializarComponentes();
        // Inicializar controlador
        controladorListadoMinivanes = new ControladorListadoMinivanes(this);
        cargarListadoMinivanes(); // Cargar datos al iniciar
    }

    // Método estático para obtener la instancia única
    public static VentanaListadoMinivanes getInstancia() {
        if (instancia == null) {
            instancia = new VentanaListadoMinivanes();
        }
        return instancia;
    }

    // Configuración básica de la ventana
    private void configurarVentana() {
        setTitle("Listado General de Minivanes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana
        setSize(800, 600);
        setLocationRelativeTo(null); // Centrar en pantalla
    }

    // Inicialización de componentes
    private void inicializarComponentes() {
        JPanel contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("Menu.background"));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(10, 10));
        setContentPane(contentPane);

        // Título
        JLabel lblTitulo = new JLabel("Listado general de minivanes");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        JPanel panelTitulo = new JPanel();
        panelTitulo.add(lblTitulo);
        contentPane.add(panelTitulo, BorderLayout.NORTH);

        // Botón Cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnCancelar);
        contentPane.add(panelBoton, BorderLayout.SOUTH);

        // Tabla con ScrollPane
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);
    }

    // Cargar listado de minivanes
    public void cargarListadoMinivanes() {
        try {
            LinkedList<VoMinivan> listadoMinivanes = controladorListadoMinivanes.ListarMini();
            if (listadoMinivanes != null && !listadoMinivanes.isEmpty()) {
                mostrarListadoEnTabla(listadoMinivanes);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontraron minivanes registradas.", 
                    "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Error de conexión al obtener el listado.", 
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Mostrar datos en la tabla
    private void mostrarListadoEnTabla(LinkedList<VoMinivan> listadoMinivanes) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabla no editable
            }
        };

        model.addColumn("Matrícula");
        model.addColumn("Marca");
        model.addColumn("Modelo");
        model.addColumn("Asientos");
        model.addColumn("Paseos Asignados");

        for (VoMinivan minivan : listadoMinivanes) {
            model.addRow(new Object[]{
                minivan.getMatricula(),
                minivan.getMarca(),
                minivan.getModelo(),
                minivan.getcantAsientos(),
                minivan.getcantPaseos()
            });
        }

        table.setModel(model);
    }

    // Método principal para pruebas
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VentanaListadoMinivanes frame = VentanaListadoMinivanes.getInstancia();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
