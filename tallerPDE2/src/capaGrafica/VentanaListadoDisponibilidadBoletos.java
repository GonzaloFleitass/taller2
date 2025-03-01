package capaGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

public class VentanaListadoDisponibilidadBoletos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListadoDisponibilidadBoletos frame = new VentanaListadoDisponibilidadBoletos();
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
	public VentanaListadoDisponibilidadBoletos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Menu.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cantidad de boletos");
		lblNewLabel.setBounds(6, 47, 168, 16);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(154, 42, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.setBounds(307, 42, 117, 29);
		contentPane.add(btnNewButton);
		
		table = new JTable();
		table.setBounds(6, 76, 438, 190);
		contentPane.add(table);
		
		JButton btnNewButton_1 = new JButton("Volver");
		btnNewButton_1.setBounds(6, 0, 117, 29);
		contentPane.add(btnNewButton_1);
	}
}
