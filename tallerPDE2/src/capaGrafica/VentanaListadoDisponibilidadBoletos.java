package capaGrafica;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import capaLogica.boletos.boletoException;
import capaLogica.paseos.VOPaseo;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;  // Importar JScrollPane
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class VentanaListadoDisponibilidadBoletos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField CantidadBol;
    private JTable table;
    private ControladorListadoDisponibilidadBoletos controladorListadoDispBol;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaListadoDisponibilidadBoletos frame = new VentanaListadoDisponibilidadBoletos();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VentanaListadoDisponibilidadBoletos() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("Panel.background"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        JLabel lblCantidadBoletos = new JLabel("Cantidad de boletos");
        lblCantidadBoletos.setBounds(171, 47, 168, 16);
        contentPane.add(lblCantidadBoletos);

        CantidadBol = new JTextField();
        CantidadBol.setBounds(351, 42, 130, 26);
        contentPane.add(CantidadBol);
        CantidadBol.setColumns(10);
        controladorListadoDispBol = new ControladorListadoDisponibilidadBoletos(this);

        // Botón de búsqueda sin icono
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Verificar si el campo de cantidad de boletos está vacío
                if (CantidadBol.getText().trim().isEmpty()) {
                    // Mostrar un mensaje de error si el campo está vacío
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese la cantidad de boletos.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        // Convertir la cantidad de boletos a entero
                        int CBol = Integer.parseInt(CantidadBol.getText());

                        // Obtener la lista de paseos disponibles con la cantidad de boletos
                        LinkedList<VOPaseo> ListarPasDisBol = controladorListadoDispBol.ListarPasDisBol(CBol);
                        
                        // Verificar si se encontró la lista de paseos
                        if (ListarPasDisBol != null && !ListarPasDisBol.isEmpty()) {
                            mostrarListadoEnTabla(ListarPasDisBol);
                        } else {
                        	JOptionPane.showMessageDialog(null, "No se encontraron minivanes o hubo un error al obtener el listado.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                        System.out.println("Error de conexión RMI al obtener el listado de minivanes.");
                    } catch (boletoException e1) {
                        e1.printStackTrace();
                    } catch (NumberFormatException e1) {
                        // Mostrar mensaje de error si el valor ingresado no es un número válido
                        JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para la cantidad de boletos.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        btnBuscar.setBounds(523, 42, 117, 29);
        contentPane.add(btnBuscar);

        
        table = new JTable();
        table.setBounds(6, 76, 788, 467);

        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(6, 76, 788, 467);
        contentPane.add(scrollPane);

       
        JButton Cancelar = new JButton("Cancelar");
        Cancelar.addActionListener(e -> dispose());
        Cancelar.setBounds(6, 0, 117, 29);
        contentPane.add(Cancelar);
        
        JLabel lblNewLabel = new JLabel("Listado de paseos por boletos");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
        lblNewLabel.setBounds(246, 4, 337, 16);
        contentPane.add(lblNewLabel);
    }

    private void mostrarListadoEnTabla(LinkedList<VOPaseo> ListarPasDisBol) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // Todas las celdas no son editables
            }
        };
        model.addColumn("Código");
        model.addColumn("Destino");
        model.addColumn("Hora Partida");
        model.addColumn("Hora Llegada");
        model.addColumn("Precio base");
        model.addColumn("Cant. Max Boletos");
        model.addColumn("Cant. Bol Disponibles");

        for (VOPaseo paseos : ListarPasDisBol) {
            model.addRow(new Object[]{
                paseos.getCodigo(),
                paseos.getDestino().getNombre(),
                paseos.getHoraPartida(),
                paseos.getHoraLlegada(),
                paseos.getPrecioBase(),
                paseos.getCantMaximaBoletos(),
                paseos.getCantBolDisp(),
            });
        }
        table.setModel(model);
    }
}
