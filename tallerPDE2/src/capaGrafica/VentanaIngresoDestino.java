package capaGrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import capaLogica.destinos.DestinoException;

public class VentanaIngresoDestino extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaIngresoDestino frame = new VentanaIngresoDestino();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * @throws RemoteException 
     */
    public VentanaIngresoDestino() throws RemoteException {
        // Configuración básica de la ventana
        setTitle("Ingreso de Destino");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Panel principal
        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Márgenes
        contentPane.setLayout(new GridLayout(4, 1, 10, 10)); // 4 filas, 1 columna, espacio entre componentes
        setContentPane(contentPane);

        // Título de la ventana
        JLabel lblTitulo = new JLabel("Ingreso de Destino", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        contentPane.add(lblTitulo);

        // Panel para el campo de texto y su etiqueta
        JPanel panelCampo = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel lblNombre = new JLabel("Nombre del Destino:");
        textField = new JTextField(20); // Campo de texto con 20 columnas
        panelCampo.add(lblNombre);
        panelCampo.add(textField);
        contentPane.add(panelCampo);

        // Botón Ingresar
        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String destino = textField.getText().trim();
                if (!destino.isEmpty()) {
                    try {
                        ControladorIngresoDestino controladorIngresoDestino = new ControladorIngresoDestino(VentanaIngresoDestino.this);
                        controladorIngresoDestino.ingresoDest(destino);
                        JOptionPane.showMessageDialog(null, "Destino ingresado correctamente: " + destino);
                    } catch (DestinoException ex) {
                        JOptionPane.showMessageDialog(null, ex.darMensaje(), 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El campo de destino está vacío.", 
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        contentPane.add(btnIngresar);

        // Botón Cancelar
        JButton Cancelar = new JButton("Cancelar");
        Cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana
            }
        });
        contentPane.add(Cancelar);
    }
}