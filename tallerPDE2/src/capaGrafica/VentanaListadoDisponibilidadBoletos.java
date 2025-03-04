package capaGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import capaLogica.boletos.boletoException;
import capaLogica.minivanes.VoMinivan;
import capaLogica.paseos.VOPaseo;

import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.awt.event.ActionEvent;

public class VentanaListadoDisponibilidadBoletos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField CantidadBol;
	private JTable table;
	private ControladorListadoDisponibilidadBoletos controladorListadoDispBol;
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
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Menu.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cantidad de boletos");
		lblNewLabel.setBounds(179, 47, 168, 16);
		contentPane.add(lblNewLabel);
		
		CantidadBol = new JTextField();
		CantidadBol.setBounds(396, 42, 130, 26);
		contentPane.add(CantidadBol);
		CantidadBol.setColumns(10);
		controladorListadoDispBol = new ControladorListadoDisponibilidadBoletos(this);
		
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int CBol = Integer.parseInt(CantidadBol.getText()); 
				try {
					LinkedList<VOPaseo> ListarPasDisBol = controladorListadoDispBol.ListarPasDisBol(CBol);
					if (ListarPasDisBol != null) {
						mostrarListadoEnTabla(ListarPasDisBol);
					} else {
						System.out.println("No se encontraron minivanes o hubo un error al obtener el listado.");
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
					System.out.println("Error de conexión RMI al obtener el listado de minivanes.");
				} catch (boletoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		

		
	

		btnNewButton.setBounds(554, 42, 117, 29);
		contentPane.add(btnNewButton);
		
		table = new JTable();
		table.setBounds(6, 76, 788, 467);
		contentPane.add(table);
		
		JButton btnNewButton_1 = new JButton("Volver");
		btnNewButton_1.setBounds(6, 0, 117, 29);
		contentPane.add(btnNewButton_1);
	}
	private void mostrarListadoEnTabla ( LinkedList<VOPaseo> ListarPasDisBol) {
		DefaultTableModel model = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        // Todas las celdas no son editables
		        return false;
		    }
		};
		model.addColumn("Codigo");
		model.addColumn("Destino");
		model.addColumn("Hora Partida");
		model.addColumn("Hota Llegada");
		model.addColumn("Precio base");
		model.addColumn("Cant. Max Boletos");
		model.addColumn("Cant. Bol Disponibles");

		for (VOPaseo paseos : ListarPasDisBol) {
			model.addRow(new Object[] {
					paseos.getCodigo(),
					paseos.getDestino(),
					paseos.getHoraPartida(),
					paseos.getHoraLlegada(),
					paseos.getPrecioBase(),
					paseos.getCantMaximaBoletos(),
					paseos.getCantBolDisp(),
			});
		}
		table.setModel(model);
	}
}

