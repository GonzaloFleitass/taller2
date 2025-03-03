package capaGrafica;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import capaLogica.destinos.DestinoException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Insets;

public class VentanaIngresoDestino extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private GridBagConstraints gbc_1;
    private GridBagConstraints gbc_2;
    private GridBagConstraints gbc_3;
    private GridBagConstraints gbc_4;

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

        ControladorIngresoDestino controladorIngresoDestino = new ControladorIngresoDestino(this);
        setTitle("INGRESO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600); // Ajuste el tamaño inicial
        contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("Menu.background"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        // Establecer el layout GridBagLayout para centrar los componentes
        contentPane.setLayout(new GridBagLayout());
                
                JLabel lblNewLabel_1 = new JLabel("Ingresar destino");
                GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
                gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
                gbc_lblNewLabel_1.gridx = 0;
                gbc_lblNewLabel_1.gridy = 0;
                contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
                
                        // Ajustar la posición de los siguientes componentes
                        gbc_1 = new GridBagConstraints(); // Nueva instancia de GridBagConstraints para el siguiente componente
                        gbc_1.insets = new Insets(0, 0, 5, 0);
                        gbc_1.gridx = 0;
                        gbc_1.gridy = 4; // Cambiar la posición vertical
                        gbc_1.anchor = GridBagConstraints.CENTER;
                        JLabel lblNewLabel = new JLabel("Nombre");
                        contentPane.add(lblNewLabel, gbc_1);
                
                        // Campo de texto para el nombre del destino
                        gbc_2 = new GridBagConstraints(); // Nueva instancia de GridBagConstraints para el siguiente componente
                        gbc_2.insets = new Insets(0, 0, 5, 0);
                        gbc_2.gridx = 0;
                        gbc_2.gridy = 5; // Cambiar la posición vertical
                        gbc_2.fill = GridBagConstraints.HORIZONTAL; // Rellenar el espacio horizontal
                        textField = new JTextField();
                        textField.setColumns(20);
                        contentPane.add(textField, gbc_2);
                
                        // Botón Ingresar
                        gbc_3 = new GridBagConstraints(); // Nueva instancia de GridBagConstraints para el siguiente componente
                        gbc_3.insets = new Insets(0, 0, 5, 0);
                        gbc_3.gridx = 0;
                        gbc_3.gridy = 6; // Cambiar la posición vertical
                        gbc_3.fill = GridBagConstraints.HORIZONTAL; // Rellenar el espacio horizontal
                        JButton Ingresar = new JButton("Ingresar");
                        Ingresar.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                String destino = textField.getText().trim();
                                if (!destino.isEmpty()) {
                                    try {
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
                        contentPane.add(Ingresar, gbc_3);
                
                        // Botón Volver
                        gbc_4 = new GridBagConstraints();
                        gbc_4.insets = new Insets(0, 0, 5, 0);
                        gbc_4.gridx = 0;
                        gbc_4.gridy = 7; // Cambiar la posición vertical
                        gbc_4.fill = GridBagConstraints.HORIZONTAL; // Rellenar el espacio horizontal
                        JButton Volver = new JButton("Cancelar");
                        Volver.addActionListener(e -> dispose());
                        contentPane.add(Volver, gbc_4);
    }
}
