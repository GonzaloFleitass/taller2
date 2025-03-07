package capaGrafica;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import capaLogica.minivanes.miniVanException;

public class VentanaIngresoMinivan extends JFrame {

    private static VentanaIngresoMinivan instancia; // Instancia única (Singleton)
    private JPanel contentPane;
    private JTextField Matricula;
    private JTextField Marca;
    private JTextField Modelo;
    private JFormattedTextField cantAsientos;

    // Constructor privado para evitar la creación directa de instancias
    private VentanaIngresoMinivan() {
        configurarVentana();
        inicializarComponentes();
    }

    // Método estático para obtener la instancia única
    public static VentanaIngresoMinivan getInstancia() {
        if (instancia == null) {
            instancia = new VentanaIngresoMinivan();
        }
        return instancia;
    }

    // Configuración básica de la ventana
    private void configurarVentana() {
        setTitle("Ingreso de Minivan");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana
        setBounds(100, 100, 800, 600);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }

    // Inicialización de los componentes de la ventana
    private void inicializarComponentes() {
        contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("Menu.background"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Etiquetas
        JLabel lblMatricula = new JLabel("Matrícula");
        lblMatricula.setBounds(160, 124, 61, 16);
        contentPane.add(lblMatricula);

        JLabel lblMarca = new JLabel("Marca");
        lblMarca.setBounds(160, 202, 61, 16);
        contentPane.add(lblMarca);

        JLabel lblModelo = new JLabel("Modelo");
        lblModelo.setBounds(160, 285, 61, 16);
        contentPane.add(lblModelo);

        JLabel lblCantAsientos = new JLabel("Cantidad de asientos");
        lblCantAsientos.setBounds(160, 367, 153, 16);
        contentPane.add(lblCantAsientos);

        // Campos de texto
        Matricula = new JTextField();
        Matricula.setBounds(335, 119, 130, 26);
        contentPane.add(Matricula);
        Matricula.setColumns(10);

        Marca = new JTextField();
        Marca.setBounds(335, 197, 130, 26);
        contentPane.add(Marca);
        Marca.setColumns(10);

        Modelo = new JTextField();
        Modelo.setBounds(335, 280, 130, 26);
        contentPane.add(Modelo);
        Modelo.setColumns(10);

        // Formato para la cantidad de asientos
        NumberFormat formato = NumberFormat.getIntegerInstance();
        formato.setGroupingUsed(false);
        cantAsientos = new JFormattedTextField(formato);
        cantAsientos.setBounds(372, 362, 61, 26);
        contentPane.add(cantAsientos);

        // Botón "Ingresar"
        JButton Ingresar = new JButton("Ingresar");
        Ingresar.setBackground(UIManager.getColor("textHighlight"));
        Ingresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String matricula = Matricula.getText().trim();
                String marca = Marca.getText().trim();
                String modelo = Modelo.getText().trim();
                Matricula.setText("");
                Marca.setText("");
                Modelo.setText("");

                if (matricula.isEmpty() || marca.isEmpty() || modelo.isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaIngresoMinivan.this, "Todos los campos deben estar completos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int cantidadAsientos;
                if (cantAsientos.getValue() != null) {
                    cantidadAsientos = ((Number) cantAsientos.getValue()).intValue();
                    if (cantidadAsientos <= 0) {
                        JOptionPane.showMessageDialog(VentanaIngresoMinivan.this, "La cantidad de asientos debe ser positiva.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(VentanaIngresoMinivan.this, "Ingrese un número válido en cantidad de asientos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    ControladorIngresoMinivan controladorIngresoMinivan = new ControladorIngresoMinivan(VentanaIngresoMinivan.this);
                    controladorIngresoMinivan.ingresoMinivan(matricula, marca, modelo, cantidadAsientos);
                    JOptionPane.showMessageDialog(VentanaIngresoMinivan.this, "Minivan ingresada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (miniVanException ex) {
                    JOptionPane.showMessageDialog(VentanaIngresoMinivan.this, "Error al ingresar la minivan: " + ex.darMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (RemoteException ex) {
                    JOptionPane.showMessageDialog(VentanaIngresoMinivan.this, "Error de conexión: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        Ingresar.setBounds(113, 448, 117, 29);
        contentPane.add(Ingresar);

        // Botón "Cancelar"
        JButton Cancelar = new JButton("Cancelar");
        Cancelar.addActionListener(e -> dispose());
        Cancelar.setBounds(530, 448, 117, 29);
        contentPane.add(Cancelar);

        // Título de la ventana
        JLabel lblNewLabel = new JLabel("Ingreso de Minivan");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
        lblNewLabel.setBounds(295, 57, 216, 29);
        contentPane.add(lblNewLabel);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VentanaIngresoMinivan frame = new VentanaIngresoMinivan();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
