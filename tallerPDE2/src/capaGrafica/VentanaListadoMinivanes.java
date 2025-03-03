package capaGrafica;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import capaLogica.minivanes.VoMinivan;

import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.awt.event.ActionEvent;

public class VentanaListadoMinivanes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private ControladorListadoMinivanes controladorListadoMinivanes; // Instancia del controlador

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListadoMinivanes frame = new VentanaListadoMinivanes();
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
	public VentanaListadoMinivanes() {
		setTitle("LISTADO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600); // Ajustar el tamaño inicial de la ventana
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Menu.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());

		// Título centrado
		JPanel panelTitulo = new JPanel();
		contentPane.add(panelTitulo, BorderLayout.NORTH);
		JLabel lblNewLabel = new JLabel("Listado general de minivanes");
		panelTitulo.add(lblNewLabel);

		// Botón "Volver" centrado
		JPanel panelBoton = new JPanel();
		contentPane.add(panelBoton, BorderLayout.WEST);
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// agregar la acción para volver a la ventana principal
				dispose();
			}
		});
		panelBoton.add(btnVolver);

		// Tabla centrada con scroll
		JPanel panelTabla = new JPanel();
		contentPane.add(panelTabla, BorderLayout.CENTER);
		panelTabla.setLayout(new BorderLayout());
		table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		panelTabla.add(scrollPane, BorderLayout.CENTER);
		
		controladorListadoMinivanes = new ControladorListadoMinivanes(this); // Inicializar el controlador
		cargarListadoMinivanes(); // Cargar el listado al iniciar la ventana
	}

	public void cargarListadoMinivanes() {
		try {
			LinkedList<VoMinivan> listadoMinivanes = controladorListadoMinivanes.ListarMini();
			if (listadoMinivanes != null) {
				mostrarListadoEnTabla(listadoMinivanes);
			} else {
				System.out.println("No se encontraron minivanes o hubo un error al obtener el listado.");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out.println("Error de conexión RMI al obtener el listado de minivanes.");
		}
	}

	private void mostrarListadoEnTabla(LinkedList<VoMinivan> listadoMinivanes) {
		DefaultTableModel model = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        // Todas las celdas no son editables
		        return false;
		    }
		};
		model.addColumn("Matrícula");
		model.addColumn("Marca");
		model.addColumn("Modelo");
		model.addColumn("Asientos");
		model.addColumn("Paseos Asignados");

		for (VoMinivan minivan : listadoMinivanes) {
			model.addRow(new Object[] {
					minivan.getMatricula(),
					minivan.getMarca(),
					minivan.getModelo(),
					minivan.getcantAsientos(),
					minivan.getcantPaseos()
			});
		}
		table.setModel(model);
	}
}
