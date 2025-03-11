package capaGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class VentanaAbout extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAbout frame = new VentanaAbout();
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
	public VentanaAbout() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(UIManager.getColor("MenuItem.background"));
		contentPane.setBackground(UIManager.getColor("Menu.selectionForeground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Gonza\\Downloads\\Diseño sin título (1).png"));
		lblNewLabel.setBounds(0, 30, 191, 220);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("<html>Somos un grupo de tres compañeros apasionados por la tecnología. Desarrollamos un software para una empresa de minivanes, con el objetivo de optimizar la gestión, mejorar la logística y ofrecer soluciones personalizadas. Cada uno aportó su experiencia en programación, diseño y análisis de datos, creando una herramienta eficiente y adaptada a la industria del transporte.</html>\n");
		lblNewLabel_1.setBounds(234, 11, 190, 250);
		contentPane.add(lblNewLabel_1);
		
		
		
	}
}
