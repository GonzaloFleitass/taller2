package capaGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTable;

public class VentanaListadoPaseosAsignadosMinivan extends JFrame {

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
					VentanaListadoPaseosAsignadosMinivan frame = new VentanaListadoPaseosAsignadosMinivan();
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
	public VentanaListadoPaseosAsignadosMinivan() {
		setTitle("LISTADO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("List.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Matricula");
		lblNewLabel.setBounds(219, 42, 61, 16);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(310, 37, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.setBounds(452, 37, 117, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Listado de paseos por minivan ");
		lblNewLabel_1.setBounds(285, 6, 204, 16);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Volver");
		btnNewButton_1.setBounds(0, 1, 117, 29);
		contentPane.add(btnNewButton_1);
		
		table = new JTable();
		table.setBounds(23, 76, 749, 464);
		contentPane.add(table);
	}

}
