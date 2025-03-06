package capaGrafica;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import capaLogica.destinos.Destino;
import capaLogica.paseos.paseoException;
import java.awt.Font;

public class VentanaIngresoPaseo extends JFrame {

    private static VentanaIngresoPaseo instancia; // Instancia única (Singleton)
    private JTextField codPaseo;
    private JComboBox<String> comboDestino;
    private JFormattedTextField horaPartida;
    private JFormattedTextField horaLlegada;
    private JFormattedTextField precio;

    // Constructor privado para evitar instanciación directa
    private VentanaIngresoPaseo() {
        configurarVentana();
        inicializarComponentes();
    }

    // Método estático para obtener la instancia única
    public static VentanaIngresoPaseo getInstancia() {
        if (instancia == null) {
            instancia = new VentanaIngresoPaseo();
        }
        return instancia;
    }

    // Configuración básica de la ventana
    private void configurarVentana() {
        setTitle("INGRESO DE PASEO");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana
        setBounds(100, 100, 800, 600);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }

    // Inicialización de los componentes de la ventana
    private void inicializarComponentes() {
        // Panel principal con GridLayout
        JPanel contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("Menu.background"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Título de la ventana
        JLabel lblTitulo = new JLabel("Ingresar Paseo", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBounds(264, 6, 326, 29);
        contentPane.add(lblTitulo);

        // Código Paseo
        JLabel lblCodPaseo = new JLabel("Código Paseo");
        lblCodPaseo.setBounds(166, 79, 100, 16);
        contentPane.add(lblCodPaseo);

        codPaseo = new JTextField();
        codPaseo.setBounds(316, 74, 130, 26);
        contentPane.add(codPaseo);
        codPaseo.setColumns(10);

        // Destino
        JLabel lblDestino = new JLabel("Destino");
        lblDestino.setBounds(166, 148, 61, 16);
        contentPane.add(lblDestino);

        comboDestino = new JComboBox<>();
        comboDestino.setBounds(316, 144, 130, 26);
        contentPane.add(comboDestino);

        // Llenar el ComboBox con los destinos
        ControladorIngresoDestino controladorDestino = new ControladorIngresoDestino(this);
        try {
            List<String> destinos = controladorDestino.getListaDestinos();
            if (destinos != null) {
                for (String destino : destinos) {
                    comboDestino.addItem(destino);
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        // Hora Partida
        JLabel lblHoraPartida = new JLabel("Hora Partida");
        lblHoraPartida.setBounds(166, 214, 100, 16);
        contentPane.add(lblHoraPartida);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime horaActual = LocalTime.now();

        horaPartida = new JFormattedTextField(formatter);
        horaPartida.setBounds(316, 209, 130, 26);
        horaPartida.setText(horaActual.format(formatter));
        contentPane.add(horaPartida);

        // Hora Llegada
        JLabel lblHoraLlegada = new JLabel("Hora Llegada");
        lblHoraLlegada.setBounds(166, 294, 100, 16);
        contentPane.add(lblHoraLlegada);

        horaLlegada = new JFormattedTextField(formatter);
        horaLlegada.setBounds(316, 289, 130, 26);
        horaLlegada.setText(horaActual.plusMinutes(60).format(formatter));
        contentPane.add(horaLlegada);

        // Precio
        JLabel lblPrecio = new JLabel("Precio");
        lblPrecio.setBounds(166, 371, 61, 16);
        contentPane.add(lblPrecio);

        precio = new JFormattedTextField();
        precio.setBounds(316, 366, 130, 26);
        contentPane.add(precio);

        // Botón Ingresar con funcionalidad escalada
        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtenemos los datos de los campos
                    String cod = codPaseo.getText().trim();

                    // Verificamos si el código está vacío
                    if (cod.isEmpty()) {
                        JOptionPane.showMessageDialog(VentanaIngresoPaseo.this, "El código de paseo no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Verificamos si se ha seleccionado un destino
                    if (comboDestino.getSelectedItem() == null) {
                        JOptionPane.showMessageDialog(VentanaIngresoPaseo.this, "Debe seleccionar un destino.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Creamos el destino seleccionado
                    Destino destino = new Destino((String) comboDestino.getSelectedItem());

                    // Verificamos que las horas no sean nulas
                    if (horaPartida.getValue() == null || horaLlegada.getValue() == null) {
                        JOptionPane.showMessageDialog(VentanaIngresoPaseo.this, "Hora de partida y llegada deben ser válidas.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Convertimos las horas a LocalTime
                    LocalTime horaPartidaLocal = LocalTime.parse(horaPartida.getText());
                    LocalTime horaLlegadaLocal = LocalTime.parse(horaLlegada.getText());

                    // Verificamos el precio
                    double precioBase = 0;
                    try {
                        precioBase = Double.parseDouble(precio.getText());
                        if (precioBase <= 0) {
                            JOptionPane.showMessageDialog(VentanaIngresoPaseo.this, "El precio debe ser mayor a cero.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(VentanaIngresoPaseo.this, "El precio debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Llamamos al controlador para procesar la lógica
                    ControladorIngresoPaseo controlador = new ControladorIngresoPaseo(VentanaIngresoPaseo.this);
                    controlador.ingresoPaseo(cod, destino, horaPartidaLocal, horaLlegadaLocal, precioBase);

                    // Mostramos mensaje de éxito
                    JOptionPane.showMessageDialog(VentanaIngresoPaseo.this, "Paseo ingresado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(VentanaIngresoPaseo.this, "Ocurrió un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnIngresar.setBounds(180, 466, 117, 29);
        contentPane.add(btnIngresar);

        // Botón Cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        btnCancelar.setBounds(484, 466, 117, 29);
        contentPane.add(btnCancelar);
    }

    // Método principal para probar la ventana
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VentanaIngresoPaseo frame = VentanaIngresoPaseo.getInstancia(); // Obtener la instancia
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
