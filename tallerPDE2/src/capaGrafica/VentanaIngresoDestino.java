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
import javax.swing.UIManager;


public class VentanaIngresoDestino extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
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
    
    	 ControladorIngresoDestino controladorIngresoDestino = new ControladorIngresoDestino(this);
        setTitle("INGRESO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("Menu.background"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Nombre");
        lblNewLabel.setBounds(200, 72, 61, 16);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(159, 100, 130, 26);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton Ingresar = new JButton("Ingresar");
        Ingresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String destino = textField.getText().trim();
                if (!destino.isEmpty()) {
                    try {
                       
						controladorIngresoDestino.ingresoDest(destino);
                        JOptionPane.showMessageDialog(null, "Destino ingresado correctamente: " + destino);
                    } catch (RemoteException | DestinoException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El campo de destino está vacío.", 
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        Ingresar.setBounds(70, 188, 130, 29);
        contentPane.add(Ingresar);

        JButton Volver = new JButton("Cancelar");
        Volver.setBounds(245, 188, 117, 29);
        contentPane.add(Volver);
        
        JLabel lblNewLabel_1 = new JLabel("Ingresar destino");
        lblNewLabel_1.setBounds(172, 34, 102, 16);
        contentPane.add(lblNewLabel_1);
    }
}
