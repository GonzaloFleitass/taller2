package capaGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

import capaLogica.fachada;
import capaLogica.Ifachada;
import capaLogica.destinos.*;


public class VentanaIngresoDestino extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private Ifachada Fachada; 

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
        Fachada = new fachada(); 

        setTitle("DESTINO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Nombre");
        lblNewLabel.setBounds(179, 74, 61, 16);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(144, 102, 130, 26);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton Ingresar = new JButton("Ingresar");
        Ingresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String destino = textField.getText().trim();
                if (!destino.isEmpty()) {
                    try {
                        Fachada.insertDestino(destino);
                        JOptionPane.showMessageDialog(null, "Destino ingresado correctamente: " + destino);
                    } catch (DestinoException | RemoteException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El campo de destino está vacío.", 
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        Ingresar.setBounds(144, 140, 130, 29);
        contentPane.add(Ingresar);

        JButton Volver = new JButton("Volver");
        Volver.setBounds(6, 6, 117, 29);
        contentPane.add(Volver);
    }
}
