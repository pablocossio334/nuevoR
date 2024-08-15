package capaPresentacion;
import javaUsbConection.UsbMicrobit;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class VentanaP extends JFrame {

	private JPanel contentPane;
	private ArrayList<UsbMicrobit> puertosDisponibles=new ArrayList<UsbMicrobit>();
	private JTextArea txtinfoPorts = new JTextArea();
	private JComboBox cmbports = new JComboBox();
	private final JButton btnConectar = new JButton("ConectarPuerto");
	
	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaP frame = new VentanaP();
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
	@SuppressWarnings("deprecation")
	public VentanaP() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 867, 561);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
btnConectar.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
	boolean conexion=new UsbMicrobit().enviarMensaje(cmbports.getSelectedItem().toString(),"smile" );
		if(conexion==true)
		{   VentanaP.this.setVisible(false);
			VentMensajes m=new VentMensajes();
			m.setPort(cmbports.getSelectedItem().toString());
			m.setVisible(true);
		}
		else
			JOptionPane.showMessageDialog(null, "Error de conexión", "Error", JOptionPane.ERROR_MESSAGE);
		
		
	}
});
this.btnConectar.setEnabled(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		txtinfoPorts.setBounds(99, 75, 611, 328);
		contentPane.add(txtinfoPorts);
		
		JButton infoPorts = new JButton("DetectarPuertos");
		infoPorts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaP.this.txtinfoPorts.setText("Puertos Disponibles.\n");
				cmbports.removeAllItems();
				btnConectar.setEnabled(true);
				puertosDisponibles=new UsbMicrobit().obtengoDatosPuertos();
				for(UsbMicrobit puerto:puertosDisponibles) {
				VentanaP.this.txtinfoPorts.append(puerto.getNombrePuerto()+".\n");
				VentanaP.this.txtinfoPorts.append(puerto.getDescripcion()+".\n");
				if(puerto.getNumero().equals("0-0.3"))
					VentanaP.this.txtinfoPorts.append("Microbit detectada"+".\n");
				else
				VentanaP.this.txtinfoPorts.append(puerto.getNumero()+".\n");
				
				VentanaP.this.txtinfoPorts.append("----------------"+".\n");
				
				cmbports.addItem(puerto.getNombrePuerto());
				
					
				}
			}
		});
		infoPorts.setBounds(79, 449, 156, 41);
		contentPane.add(infoPorts);
		
		
		cmbports.setBounds(644, 459, 111, 21);
		contentPane.add(cmbports);
		btnConectar.setBounds(418, 454, 193, 31);
		
		contentPane.add(btnConectar);
	}
}
