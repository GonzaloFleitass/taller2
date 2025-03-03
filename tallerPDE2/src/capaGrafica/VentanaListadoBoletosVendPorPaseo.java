package capaGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class VentanaListadoBoletosVendPorPaseo extends JFrame {

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
					VentanaListadoBoletosVendPorPaseo frame = new VentanaListadoBoletosVendPorPaseo();
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
	public VentanaListadoBoletosVendPorPaseo() {
		setTitle("LISTADO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Menu.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.setBounds(6, 0, 117, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Ingrese Codigo de Paseo:");
		lblNewLabel.setBounds(138, 82, 203, 16);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(330, 77, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(476, 77, 117, 29);
		contentPane.add(btnNewButton_1);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Especial?");
		chckbxNewCheckBox.setBounds(138, 120, 128, 23);
		contentPane.add(chckbxNewCheckBox);
		
		table = new JTable();
		table.setBounds(42, 155, 739, 384);
		contentPane.add(table);
		
		JLabel lblNewLabel_1 = new JLabel("Liostado de boletos vendido por paseo");
		lblNewLabel_1.setBounds(252, 5, 335, 16);
		contentPane.add(lblNewLabel_1);
	}
}
