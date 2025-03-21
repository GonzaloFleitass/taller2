package capaGrafica;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.JButton;

public class VentanaAbout extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaAbout instancia; 

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
	public static VentanaAbout getInstancia() throws RemoteException {
        if (instancia == null) {
            instancia = new VentanaAbout();
        }
        return instancia;
    }

	/**
	 * Create the frame.
	 */
	
	
	public VentanaAbout() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(UIManager.getColor("MenuItem.background"));
		contentPane.setBackground(UIManager.getColor("Menu.selectionForeground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setSize(500, 300);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("/Users/gonzalofleitas/Downloads/WhatsApp Image 2025-03-11 at 3.23.53 PM.jpeg"));
		lblNewLabel.setBounds(6, -12, 191, 250);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("<html>Somos un grupo de tres compañeros apasionados por la tecnología. Desarrollamos un software para una empresa de minivanes, con el objetivo de optimizar la gestión, mejorar la logística y ofrecer soluciones personalizadas. Cada uno aportó su experiencia en programación, diseño y análisis de datos, creando una herramienta eficiente y adaptada a la industria del transporte.</html>\n");
		lblNewLabel_1.setBounds(234, 11, 190, 250);
		contentPane.add(lblNewLabel_1);
		
		JButton btnCancelar = new JButton("Volver");
        btnCancelar.setBounds(16, 237, 103, 29);
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana
            }
        });
        contentPane.add(btnCancelar);
    }
		
		
		
	}

