package capaGrafica;

import java.awt.EventQueue;
import java.text.NumberFormat;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import capaLogica.minivanes.miniVanException;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class VentanaIngresoMinivan extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField Matricula;
    private JTextField Marca;
    private JTextField Modelo;
    private JFormattedTextField cantAsientos;

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

    public VentanaIngresoMinivan() {
        setTitle("INGRESO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("Menu.background"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblMatricula = new JLabel("Matrícula");
        lblMatricula.setBounds(67, 40, 61, 16);
        contentPane.add(lblMatricula);

        JLabel lblMarca = new JLabel("Marca");
        lblMarca.setBounds(67, 79, 61, 16);
        contentPane.add(lblMarca);

        JLabel lblModelo = new JLabel("Modelo");
        lblModelo.setBounds(67, 124, 61, 16);
        contentPane.add(lblModelo);

        JLabel lblCantAsientos = new JLabel("Cantidad de asientos");
        lblCantAsientos.setBounds(6, 177, 153, 16);
        contentPane.add(lblCantAsientos);

        Matricula = new JTextField();
        Matricula.setBounds(140, 35, 130, 26);
        contentPane.add(Matricula);
        Matricula.setColumns(10);

        Marca = new JTextField();
        Marca.setBounds(140, 74, 130, 26);
        contentPane.add(Marca);
        Marca.setColumns(10);

        Modelo = new JTextField();
        Modelo.setBounds(140, 119, 130, 26);
        contentPane.add(Modelo);
        Modelo.setColumns(10);

        NumberFormat formato = NumberFormat.getIntegerInstance();
        formato.setGroupingUsed(false);
        cantAsientos = new JFormattedTextField(formato);
        cantAsientos.setBounds(148, 172, 61, 26);
        contentPane.add(cantAsientos);

        JButton Ingresar = new JButton("Ingresar");
        Ingresar.addActionListener(e -> {
            try {
                String matricula = Matricula.getText().trim();
                String marca = Marca.getText().trim();
                String modelo = Modelo.getText().trim();

                if (matricula.isEmpty() || marca.isEmpty() || modelo.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int cantidadAsientos;
                if (cantAsientos.getValue() != null) {
                    cantidadAsientos = ((Number) cantAsientos.getValue()).intValue();
                    if (cantidadAsientos <= 0) {
                        throw new NumberFormatException();
                    }
                } else {
                    throw new NumberFormatException();
                }

                ControladorIngresoMinivan controladorIngresoMinivan = new ControladorIngresoMinivan(this);
                controladorIngresoMinivan.ingresoMinivan(matricula, marca, modelo, cantidadAsientos);

                JOptionPane.showMessageDialog(this, "Minivan ingresada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese un número válido y positivo en cantidad de asientos.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (RemoteException e1) {
                JOptionPane.showMessageDialog(this, "Error de conexión: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (miniVanException e1) {
                JOptionPane.showMessageDialog(this, "Error al ingresar la minivan: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        Ingresar.setBounds(67, 237, 117, 29);
        contentPane.add(Ingresar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        btnCancelar.setBounds(232, 237, 117, 29);
        contentPane.add(btnCancelar);
    }
}

