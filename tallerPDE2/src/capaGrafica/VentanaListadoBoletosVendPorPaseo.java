package capaGrafica;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import capaLogica.boletos.VOBoleto;
import capaLogica.paseos.paseoException;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class VentanaListadoBoletosVendPorPaseo extends JFrame {

    private static VentanaListadoBoletosVendPorPaseo instancia; // Instancia única (Singleton)
    private JTextField textFieldCodigo;
    private JTable table;
    private JCheckBox TipoBoleto;
    private ControladorListadoBoletosVendPorPaseo controlador;

    // Constructor privado para evitar instanciación directa
    private VentanaListadoBoletosVendPorPaseo() {
        configurarVentana();
        // Asociar el controlador a esta ventana
        controlador = new ControladorListadoBoletosVendPorPaseo(this);
        inicializarComponentes();
    }

    // Método estático para obtener la instancia única
    public static VentanaListadoBoletosVendPorPaseo getInstancia() {
        if (instancia == null) {
            instancia = new VentanaListadoBoletosVendPorPaseo();
        }
        return instancia;
    }

    // Configuración básica de la ventana
    private void configurarVentana() {
        setTitle("Listado de Boletos Vendidos por Paseo");
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

        // Botón Volver
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(6, 10, 117, 29);
        btnCancelar.addActionListener(e -> dispose());
        contentPane.add(btnCancelar);

        // Título
        JLabel lblTitulo = new JLabel("Listado de boletos vendidos por paseo");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBounds(250, 15, 350, 20);
        contentPane.add(lblTitulo);

        // Etiqueta y campo de texto para ingresar código de paseo
        JLabel lblCodigo = new JLabel("Ingrese Código de Paseo:");
        lblCodigo.setBounds(138, 82, 203, 16);
        contentPane.add(lblCodigo);

        textFieldCodigo = new JTextField();
        textFieldCodigo.setBounds(330, 77, 130, 26);
        contentPane.add(textFieldCodigo);
        textFieldCodigo.setColumns(10);

        // Checkbox para filtrar por boletos especiales
        TipoBoleto = new JCheckBox("Especial?");
        TipoBoleto.setBounds(138, 120, 128, 23);
        contentPane.add(TipoBoleto);

        // Botón Buscar
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(476, 77, 117, 29);
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String codigo = textFieldCodigo.getText().trim();
                    textFieldCodigo.setText("");
               
                    if (codigo.isEmpty()) {
                        JOptionPane.showMessageDialog(VentanaListadoBoletosVendPorPaseo.this, "Por favor ingrese un código de paseo.", 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    char tipoBoleto = TipoBoleto.isSelected() ? 'E' : 'C'; // 'E' para especial, 'C' para común
                    TipoBoleto.setText("");
                    // Obtener la lista de boletos desde el controlador
                    LinkedList<VOBoleto> listaBoletos = controlador.ListarBolVenPas(codigo, tipoBoleto);
                    if (listaBoletos.isEmpty()) {
                        JOptionPane.showMessageDialog(VentanaListadoBoletosVendPorPaseo.this, "No se encontraron boletos para el código de paseo ingresado.", 
                                "Información", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        mostrarBoletosEnTabla(listaBoletos);
                    }

                } catch (paseoException ex) { 
                    JOptionPane.showMessageDialog(VentanaListadoBoletosVendPorPaseo.this, ex.darMensaje(), 
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (RemoteException ex) {
                    JOptionPane.showMessageDialog(VentanaListadoBoletosVendPorPaseo.this, "Error de conexión al obtener el listado de boletos.", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) { // Captura cualquier otra excepción no prevista
                    JOptionPane.showMessageDialog(VentanaListadoBoletosVendPorPaseo.this, "Ocurrió un error inesperado.", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
        contentPane.add(btnBuscar);

        // Tabla para mostrar los boletos
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(42, 155, 700, 400);
        contentPane.add(scrollPane);
    }

    /**
     * Método para actualizar la tabla con la lista de boletos.
     */
    private void mostrarBoletosEnTabla(LinkedList<VOBoleto> listaBoletos) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hace que las celdas no sean editables
            }
        };

        model.addColumn("Número");
        model.addColumn("Nombre");
        model.addColumn("Edad");
        model.addColumn("Celular");

        for (VOBoleto boleto : listaBoletos) {
            model.addRow(new Object[]{
                boleto.getNumBoleto(),
                boleto.getNombre(),
                boleto.getEdad(),
                boleto.getCelular(),
            });
        }
        table.setModel(model);
    }

    // Método principal para probar la ventana
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VentanaListadoBoletosVendPorPaseo frame = VentanaListadoBoletosVendPorPaseo.getInstancia(); // Obtener la instancia
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
