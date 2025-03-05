package capaGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import capaLogica.minivanes.miniVanException;

import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class VentanaVentaBoleto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField codigoPaseoField;
	private JTextField nombreField;
	private JTextField edadField;
	private JTextField numCelularField;
	private JTextField DescuentoField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaVentaBoleto frame = new VentanaVentaBoleto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaVentaBoleto() {
	    setTitle("Venta");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 800, 600);
	    contentPane = new JPanel();
	    contentPane.setBackground(UIManager.getColor("Menu.background"));
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);
	    contentPane.setLayout(null);
	    setLocationRelativeTo(null);
	    JLabel CodPaseo = new JLabel("Codigo de paseo");
	    CodPaseo.setBounds(179, 104, 105, 16);
	    contentPane.add(CodPaseo);
	    
	    codigoPaseoField = new JTextField();
	    codigoPaseoField.setBounds(341, 99, 179, 26);
	    contentPane.add(codigoPaseoField);
	    codigoPaseoField.setColumns(10);
	    
	    JLabel Nombre = new JLabel("Nombre");
	    Nombre.setBounds(179, 179, 50, 16);
	    contentPane.add(Nombre);
	    
	    nombreField = new JTextField();
	    nombreField.setBounds(341, 174, 179, 26);
	    contentPane.add(nombreField);
	    nombreField.setColumns(10);
	    
	    JLabel Edad = new JLabel("Edad");
	    Edad.setBounds(179, 244, 134, 16);
	    contentPane.add(Edad);
	    
	    edadField = new JTextField();
	    edadField.setBounds(341, 239, 179, 26);
	    contentPane.add(edadField);
	    edadField.setColumns(10);
	    
	    JLabel NumeroCelular = new JLabel("Numero de celular");
	    NumeroCelular.setBounds(179, 299, 115, 16);
	    contentPane.add(NumeroCelular);
	    
	    numCelularField = new JTextField();
	    numCelularField.setBounds(341, 294, 179, 26);
	    contentPane.add(numCelularField);
	    numCelularField.setColumns(10);
	    
	  
	 
	    
	    
	    JLabel Descuento = new JLabel("Descuento");
	    Descuento.setBounds(179, 401, 134, 16);
	    contentPane.add(Descuento);
	    Descuento.setVisible(false);
	    DescuentoField = new JTextField();
	    DescuentoField.setBounds(341, 396, 179, 26);
	    contentPane.add(DescuentoField);
	    DescuentoField.setColumns(10);
	    DescuentoField.setVisible(false);
	    
	    JCheckBox TipBoleto = new JCheckBox("Especial?");
	    TipBoleto.setBounds(179, 350, 128, 23);
	    contentPane.add(TipBoleto);
	    TipBoleto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				  if (TipBoleto.isSelected()) {
			          
			            DescuentoField.setVisible(true);
			            Descuento.setVisible(true);
			        } else {
			            
			            DescuentoField.setVisible(false);
			            Descuento.setVisible(false);
			        }
				
			}
	    	
	    });
	    
	    
	    JButton btnVender = new JButton("Vender");
	    btnVender.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            try {
	                // Obtener los datos de los campos de texto
	                String codigoPaseo = codigoPaseoField.getText().trim();
	                String nombre = nombreField.getText().trim();
	                String edadStr = edadField.getText().trim();
	                String numCelularStr = numCelularField.getText().trim();
	                char tipoBoleto = TipBoleto.isSelected() ? 'E' : 'C';
	                String descuentoStr = DescuentoField.getText().trim();
	                
	                // Validación de campos
	                if (codigoPaseo.isEmpty() || nombre.isEmpty() || edadStr.isEmpty() || numCelularStr.isEmpty()) {
	                    JOptionPane.showMessageDialog(VentanaVentaBoleto.this, "Todos los campos deben estar completos.", "Error", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }

	                // Validar edad (debe ser un número entero)
	                int edad;
	                try {
	                    edad = Integer.parseInt(edadStr);
	                } catch (NumberFormatException ex) {
	                    JOptionPane.showMessageDialog(VentanaVentaBoleto.this, "Ingrese una edad válida.", "Error", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }

	                // Validar número de celular (debe ser un número entero)
	                int numeroCelular;
	                try {
	                    numeroCelular = Integer.parseInt(numCelularStr);
	                } catch (NumberFormatException ex) {
	                    JOptionPane.showMessageDialog(VentanaVentaBoleto.this, "Ingrese un número de celular válido.", "Error", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }

	                // Validar descuento (opcional)
	                double descuento = 0;
	                if (!descuentoStr.isEmpty()) {
	                    try {
	                        descuento = Double.parseDouble(descuentoStr);
	                    } catch (NumberFormatException ex) {
	                        JOptionPane.showMessageDialog(VentanaVentaBoleto.this, "Ingrese un descuento válido.", "Error", JOptionPane.ERROR_MESSAGE);
	                        return;
	                    }
	                }
	                
	                ControladorVentaBoleto controlador = new ControladorVentaBoleto(VentanaVentaBoleto.this);	  
	                controlador.VentBol(codigoPaseo, nombre, edad, numeroCelular, codigoPaseo, descuento, tipoBoleto);
	                JOptionPane.showMessageDialog(VentanaVentaBoleto.this, "Boleto vendido con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	                
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(VentanaVentaBoleto.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    });
	    btnVender.setBounds(141, 488, 144, 29);
	    contentPane.add(btnVender);
	    
	    JButton Cancelar = new JButton("Cancelar");
	    Cancelar.setBounds(546, 488, 144, 29);
	    Cancelar.setBackground(UIManager.getColor("MenuItem.disabledForeground"));
	    contentPane.add(Cancelar);
	    
	    JLabel lblNewLabel = new JLabel("Venta de boleto");
	    lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
	    lblNewLabel.setBounds(341, 19, 242, 16);
	    contentPane.add(lblNewLabel);
	    
	    
	    Cancelar.addActionListener(e -> dispose());
	}
}
