package capaGrafica;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import capaLogica.paseos.VOPaseo;
import capaLogica.destinos.Destino;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class VentanaListadoPaseosPorDestino extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> Destinos;
    private JTable table;
    private ControladorListarPaseosPorDestino controladorListarPaseosPorDestino;
    private static VentanaListadoPaseosPorDestino instancia;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaListadoPaseosPorDestino frame =VentanaListadoPaseosPorDestino.getInstancia();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public VentanaListadoPaseosPorDestino() {
        configurarVentana();
        controladorListarPaseosPorDestino = new ControladorListarPaseosPorDestino(this);
        cargarDestinos();
    }
    
    // Método estático para obtener la instancia única
    public static VentanaListadoPaseosPorDestino getInstancia() {
        if (instancia == null) {
            instancia = new VentanaListadoPaseosPorDestino();
        }
        return instancia;
    }
    
    private void configurarVentana() {
        setTitle("Listado de paseos por destino");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana
        setSize(800, 600);
        setLocationRelativeTo(null); // Centrar en pantalla
        contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("Panel.background"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblDestinos = new JLabel("Destinos");
        lblDestinos.setBounds(225, 43, 61, 16);
        contentPane.add(lblDestinos);

        Destinos = new JComboBox<>();
        Destinos.setBounds(327, 38, 141, 29);
        contentPane.add(Destinos);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String destinoSeleccionado = (String) Destinos.getSelectedItem();
                if (destinoSeleccionado != null) {
                    try {
                        Destino destino = new Destino(destinoSeleccionado);
                        LinkedList<VOPaseo> paseosPorDestino = controladorListarPaseosPorDestino.ListarPasPorDestino(destino);
                        if (paseosPorDestino != null && !paseosPorDestino.isEmpty()) {
                            mostrarListadoEnTabla(paseosPorDestino);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se encontraron paseos para el destino seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error de conexión al obtener los paseos.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al obtener los paseos.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un destino.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnBuscar.setBounds(480, 38, 117, 29);
        contentPane.add(btnBuscar);

        table = new JTable();
        table.setBounds(20, 71, 753, 476);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 71, 753, 476);
        contentPane.add(scrollPane);

        JButton Cancelar = new JButton("Cancelar");
        Cancelar.addActionListener(e -> dispose());
        Cancelar.setBounds(6, 0, 117, 29);
        contentPane.add(Cancelar);

        JLabel lblListadoPaseos = new JLabel("Listado de paseos por destino");
        lblListadoPaseos.setFont(new Font("Arial", Font.BOLD, 18));
        lblListadoPaseos.setBounds(253, 4, 351, 16);
        contentPane.add(lblListadoPaseos);
    }

    private void cargarDestinos() {
        try {
            LinkedList<String> destinos = controladorListarPaseosPorDestino.getListaDestinos();
            if (destinos != null) {
                for (String destino : destinos) {
                    Destinos.addItem(destino);
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar los destinos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarListadoEnTabla(LinkedList<VOPaseo> paseosPorDestino) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // Las celdas no son editables
            }
        };
        
        model.addColumn("Código");
        model.addColumn("Destino");
        model.addColumn("Hora Partida");
        model.addColumn("Hora Llegada");
        model.addColumn("Precio base");
        model.addColumn("Cant. Max Boletos");
        model.addColumn("Cant. Bol Disponibles");

        for (VOPaseo paseo : paseosPorDestino) {
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

