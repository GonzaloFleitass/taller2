package capaGrafica;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import capaLogica.paseos.paseoException;

import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class VentanaMontoRecaudado extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private static VentanaMontoRecaudado instancia;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaMontoRecaudado frame =VentanaMontoRecaudado.getInstancia();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    // Método estático para obtener la instancia única
    public static VentanaMontoRecaudado getInstancia() {
        if (instancia == null) {
            instancia = new VentanaMontoRecaudado();
        }
        return instancia;
    }
  
    public VentanaMontoRecaudado() {
        setTitle("MONTO RECAUDADO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("Menu.background"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Codigo de Paseo:");
        lblNewLabel.setBounds(191, 50, 108, 16);
        contentPane.add(lblNewLabel);
        
        JButton Cancelar = new JButton("Cancelar");
        Cancelar.setBounds(6, 0, 117, 29);
        contentPane.add(Cancelar);
        Cancelar.addActionListener(e -> dispose());
        
        textField = new JTextField();
        textField.setBounds(336, 45, 130, 26);
        contentPane.add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Monto Recaudado:");
        lblNewLabel_1.setBounds(217, 254, 117, 29);
        contentPane.add(lblNewLabel_1);
        
        textField_1 = new JTextField();
        textField_1.setBounds(365, 248, 145, 40);
        contentPane.add(textField_1);
        textField_1.setColumns(10);
        
        JButton Buscar = new JButton("Buscar");
        Buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String codigoPaseo = textField.getText(); // Obtengo el código del paseo
                try {
                    // Llamo al controlador para obtener el monto recaudado
                    ControladorMontoRecaudado controlador = new ControladorMontoRecaudado(VentanaMontoRecaudado.this);
                    double monto = controlador.MontoRec(codigoPaseo); // Obtengo el monto recaudado
                    textField_1.setText(String.valueOf(monto)); // Convierte el valor double a String
                    
                } catch (paseoException ex) {
                    ex.printStackTrace();
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            }
        });

        Buscar.setBounds(510, 45, 117, 29);
        contentPane.add(Buscar);
        
        JLabel lblNewLabel_2 = new JLabel("Monto Recaudado de un paseo");
        lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 18));
        lblNewLabel_2.setBounds(254, 4, 360, 16);
        contentPane.add(lblNewLabel_2);
    }
    }

