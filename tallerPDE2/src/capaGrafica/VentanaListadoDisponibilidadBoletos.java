package capaGrafica;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import capaLogica.boletos.boletoException;
import capaLogica.paseos.VOPaseo;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class VentanaListadoDisponibilidadBoletos extends JFrame {

    private static VentanaListadoDisponibilidadBoletos instancia; // Instancia única (Singleton)
    private JTextField CantidadBol;
    private JTable table;
    private ControladorListadoDisponibilidadBoletos controladorListadoDispBol;

    // Constructor privado para evitar instanciación directa
    private VentanaListadoDisponibilidadBoletos() {
        configurarVentana();
        // Asociar el controlador a esta ventana
        controladorListadoDispBol = new ControladorListadoDisponibilidadBoletos(this);
        inicializarComponentes();
    }

    // Método estático para obtener la instancia única
    public static VentanaListadoDisponibilidadBoletos getInstancia() {
        if (instancia == null) {
            instancia = new VentanaListadoDisponibilidadBoletos();
        }
        return instancia;
    }

    // Configuración básica de la ventana
    private void configurarVentana() {
        setTitle("Listado de Disponibilidad de Boletos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana
        setBounds(100, 100, 800, 600);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }

    // Inicialización de los componentes de la ventana
    private void inicializarComponentes() {
        // Panel principal con GridLayout
        JPanel contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("textHighlight"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Título
        JLabel lblTitulo = new JLabel("Listado de paseos por boletos");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBounds(246, 4, 337, 16);
        contentPane.add(lblTitulo);

        // Etiqueta y campo de texto para la cantidad de boletos
        JLabel lblCantidadBoletos = new JLabel("Cantidad de boletos:");
        lblCantidadBoletos.setBounds(171, 47, 168, 16);
        contentPane.add(lblCantidadBoletos);

        CantidadBol = new JTextField();
        CantidadBol.setBounds(351, 42, 130, 26);
        contentPane.add(CantidadBol);
        CantidadBol.setColumns(10);

        // Botón Buscar
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(523, 42, 117, 29);
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String cantidad = CantidadBol.getText().trim();
                    if (cantidad.isEmpty()) {
                        JOptionPane.showMessageDialog(VentanaListadoDisponibilidadBoletos.this, "Por favor, ingrese la cantidad de boletos.", 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int CBol = Integer.parseInt(cantidad);

                    // Obtener la lista de paseos disponibles con la cantidad de boletos
                    LinkedList<VOPaseo> ListarPasDisBol = controladorListadoDispBol.ListarPasDisBol(CBol);

                    if (ListarPasDisBol != null && !ListarPasDisBol.isEmpty()) {
                        mostrarListadoEnTabla(ListarPasDisBol);
                    } else {
                        JOptionPane.showMessageDialog(VentanaListadoDisponibilidadBoletos.this, "No se encontraron paseos disponibles.", 
                                "Información", JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(VentanaListadoDisponibilidadBoletos.this, "Por favor, ingrese un número válido para la cantidad de boletos.", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (RemoteException | boletoException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(VentanaListadoDisponibilidadBoletos.this, "Error de conexión al obtener el listado de paseos.", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPane.add(btnBuscar);

        // Tabla para mostrar los paseos
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(6, 76, 788, 467);
        contentPane.add(scrollPane);

        // Botón Cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(6, 0, 117, 29);
        btnCancelar.addActionListener(e -> dispose());
        contentPane.add(btnCancelar);
    }

    /**
     * Método para actualizar la tabla con la lista de paseos.
     */
    private void mostrarListadoEnTabla(LinkedList<VOPaseo> ListarPasDisBol) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hace que las celdas no sean editables
            }
        };

        model.addColumn("Código");
        model.addColumn("Destino");
        model.addColumn("Hora Partida");
        model.addColumn("Hora Llegada");
        model.addColumn("Precio base");
        model.addColumn("Cant. Max Boletos");
        model.addColumn("Cant. Bol Disponibles");

        for (VOPaseo paseo : ListarPasDisBol) {
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

    // Método principal para probar la ventana
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VentanaListadoDisponibilidadBoletos frame = VentanaListadoDisponibilidadBoletos.getInstancia(); // Obtener la instancia
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
