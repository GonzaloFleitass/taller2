package capaGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class VentanaListadoPaseosPorDestino extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JComboBox comboBox = new JComboBox();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListadoPaseosPorDestino frame = new VentanaListadoPaseosPorDestino();
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
	public VentanaListadoPaseosPorDestino() {
		setTitle("LISTADO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("List.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Destinos");
		lblNewLabel.setBounds(77, 43, 61, 16);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.setBounds(315, 38, 117, 29);
		contentPane.add(btnNewButton);
		comboBox.setBounds(150, 38, 141, 29);
		contentPane.add(comboBox);
		
		JButton btnNewButton_1 = new JButton("Volver");
		btnNewButton_1.setBounds(6, 0, 117, 29);
		contentPane.add(btnNewButton_1);
		
		table = new JTable();
		table.setBounds(20, 71, 395, 195);
		contentPane.add(table);
		
		JLabel lblNewLabel_1 = new JLabel("Listado de paseos por destino");
		lblNewLabel_1.setBounds(135, 5, 215, 16);
		contentPane.add(lblNewLabel_1);
	}
}
