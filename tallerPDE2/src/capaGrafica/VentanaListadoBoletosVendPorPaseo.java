package capaGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import capaLogica.boletos.VOBoleto;
import capaLogica.paseos.paseoException;

import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

/*public class VentanaListadoBoletosVendPorPaseo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;


	 * Launch the application.
	 * 
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


	 * Create the frame.

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




COMENTE LO QUE HIZO GONZA PARA QUE NO SE PERDIERA Y YO NO PUEDO PROBARLO
*/

public class VentanaListadoBoletosVendPorPaseo extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldCodigo;
    private JTable table;
    private JCheckBox checkBoxEspecial;
    private ControladorListadoBoletosVendPorPaseo controlador; // Asociamos el controlador

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
     * Constructor de la ventana.
     */
    public VentanaListadoBoletosVendPorPaseo() {
        setTitle("Listado de Boletos Vendidos por Paseo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        
        contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("Menu.background"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Botón Volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(6, 10, 117, 29);
        btnVolver.addActionListener(e -> dispose());
        contentPane.add(btnVolver);

        // Título
        JLabel lblTitulo = new JLabel("Listado de boletos vendidos por paseo");
        lblTitulo.setBounds(250, 15, 350, 20);
        contentPane.add(lblTitulo);

        // Etiqueta y campo de texto para ingresar código de paseo
        JLabel lblCodigo = new JLabel("Ingrese Código de Paseo:");
        lblCodigo.setBounds(138, 82, 203, 16);
        contentPane.add(lblCodigo);

        textFieldCodigo = new JTextField();
        textFieldCodigo.setBounds(330, 77, 130, 26);
        contentPane.add(textFieldCodigo);
        textFieldCodigo.setColumns(10);

        // Checkbox para filtrar por boletos especiales
        checkBoxEspecial = new JCheckBox("Especial?");
        checkBoxEspecial.setBounds(138, 120, 128, 23);
        contentPane.add(checkBoxEspecial);

        // Botón Buscar
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(476, 77, 117, 29);
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarBoletos();
            }
        });
        contentPane.add(btnBuscar);

        // Tabla para mostrar los boletos
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(42, 155, 700, 400);
        contentPane.add(scrollPane);

        // Asociar el controlador a esta ventana
        controlador = new ControladorListadoBoletosVendPorPaseo(this);
    }

    /**
     * Método para buscar y mostrar los boletos vendidos en la tabla.
     */
    private void buscarBoletos() {
        try {
            String codigo = textFieldCodigo.getText().trim();
            if (codigo.isEmpty()) {
            	System.out.println("No se encontraron boletos o hubo un error al obtener el listado.");
            }

            char tipoBoleto = checkBoxEspecial.isSelected() ? 'E' : 'C'; // 'E' para especial, 'C' para común

            // Obtener la lista de boletos desde el controlador
            LinkedList<VOBoleto> listaBoletos = controlador.ListarBolVenPas(codigo, tipoBoleto);
            mostrarBoletosEnTabla(listaBoletos);

        } catch (RemoteException | paseoException e) {
        	e.printStackTrace();
			System.out.println("Error de conexión RMI al obtener el listado de boletos.");
        }
    }

    /**
     * Método para actualizar la tabla con la lista de boletos.
     */
    private void mostrarBoletosEnTabla(LinkedList<VOBoleto> listaBoletos) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hace que las celdas no sean editables
            }
        };

        model.addColumn("Número");
        model.addColumn("Nombre");
        model.addColumn("Edad");
        model.addColumn("Celular");
      

        for (VOBoleto boleto : listaBoletos) {
            model.addRow(new Object[]{
                boleto.getNumBoleto(),
                boleto.getNombre(),
                boleto.getEdad(),
                boleto.getCelular(),
               
            });
        }
        table.setModel(model);
    }
}

