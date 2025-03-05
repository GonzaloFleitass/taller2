package capaGrafica;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import capaLogica.destinos.Destino;
import capaLogica.paseos.Paseo;
import capaLogica.paseos.paseoException;
import java.awt.Font;

public class VentanaIngresoPaseo extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField codPaseo;
    private JComboBox<String> comboDestino;
    private JFormattedTextField horaPartida;
    private JFormattedTextField horaLlegada;
    private JFormattedTextField precio;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VentanaIngresoPaseo frame = new VentanaIngresoPaseo();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public VentanaIngresoPaseo() {
        setTitle("INGRESO DE PASEO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("Menu.background"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        JLabel lblCodPaseo = new JLabel("Código Paseo");
        lblCodPaseo.setBounds(166, 79, 100, 16);
        contentPane.add(lblCodPaseo);

        JLabel lblDestino = new JLabel("Destino");
        lblDestino.setBounds(166, 148, 61, 16);
        contentPane.add(lblDestino);

        JLabel lblHoraPartida = new JLabel("Hora Partida");
        lblHoraPartida.setBounds(166, 214, 100, 16);
        contentPane.add(lblHoraPartida);

        JLabel lblHoraLlegada = new JLabel("Hora Llegada");
        lblHoraLlegada.setBounds(166, 294, 100, 16);
        contentPane.add(lblHoraLlegada);

        JLabel lblPrecio = new JLabel("Precio");
        lblPrecio.setBounds(166, 371, 61, 16);
        contentPane.add(lblPrecio);

        codPaseo = new JTextField();
        codPaseo.setBounds(316, 74, 130, 26);
        contentPane.add(codPaseo);
        codPaseo.setColumns(10);

        // ComboBox para seleccionar el destino
        comboDestino = new JComboBox<>();
        comboDestino.setBounds(316, 144, 130, 26);
        contentPane.add(comboDestino);

        // Llenamos el ComboBox con los destinos,  toy llamando al controlador de destino para eso
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        // Obtener la hora actual
        LocalTime horaActual = LocalTime.now();

        horaPartida = new JFormattedTextField(formatter);
        horaPartida.setBounds(316, 209, 130, 26);
        horaPartida.setText(horaActual.format(formatter)); 
        contentPane.add(horaPartida);

        horaLlegada = new JFormattedTextField(formatter);
        horaLlegada.setBounds(316, 289, 130, 26);
        horaLlegada.setText(horaActual.plusMinutes(60).format(formatter)); 
        contentPane.add(horaLlegada);

        precio = new JFormattedTextField();
        precio.setBounds(316, 366, 130, 26);
        contentPane.add(precio);

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.addActionListener(e -> {
            try {
                String cod = codPaseo.getText().trim();

                // Validamos que el código y el destino no estén vacíos
                if (cod.isEmpty() || comboDestino.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Destino dest = new Destino((String) comboDestino.getSelectedItem());


                // Validación de hora de partida y llegada
                if (horaPartida.getValue() == null || horaLlegada.getValue() == null) {
                    JOptionPane.showMessageDialog(this, "Hora de partida y llegada deben ser válidas.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                LocalTime hpart = LocalTime.parse(horaPartida.getText());
                LocalTime hllega = LocalTime.parse(horaLlegada.getText());

                double precioBase = Double.parseDouble(precio.getText());

                if (precioBase <= 0) {
                    JOptionPane.showMessageDialog(this, "El precio debe ser mayor a cero.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ControladorIngresoPaseo controlador = new ControladorIngresoPaseo(this);

                try {
                    controlador.ingresoPaseo(cod, dest, hpart, hllega, precioBase);
                    JOptionPane.showMessageDialog(this, "Paseo ingresado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (paseoException ex) {
                    JOptionPane.showMessageDialog(this, "Error al ingresar el paseo: " + ex.darMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ingrese datos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnIngresar.setBounds(180, 466, 117, 29);
        contentPane.add(btnIngresar);

        JButton Cancelar = new JButton("Cancelar");
        Cancelar.addActionListener(e -> dispose());
        Cancelar.setBounds(484, 466, 117, 29);
        contentPane.add(Cancelar);
        
        JLabel lblNewLabel = new JLabel("Ingresar Paseo");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
        lblNewLabel.setBounds(264, 6, 326, 29);
        contentPane.add(lblNewLabel);
    }
}
