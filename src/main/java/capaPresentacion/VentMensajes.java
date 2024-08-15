package capaPresentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javaUsbConection.UsbMicrobit;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentMensajes extends JFrame {

	private JPanel contentPane;
    private String port;
    private JLabel lblInfo; 
    private JTextField txtEnviar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentMensajes frame = new VentMensajes();
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
	public VentMensajes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 733, 557);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblInfo = new JLabel("CONECTADO A:");
		lblInfo.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblInfo.setBounds(115, 39, 229, 57);
		contentPane.add(lblInfo);
		
		txtEnviar = new JTextField();
		txtEnviar.setBounds(211, 152, 420, 43);
		contentPane.add(txtEnviar);
		txtEnviar.setColumns(10);
		
		JButton btnEnviar = new JButton("ENVIAR");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UsbMicrobit().enviarMensaje(port, txtEnviar.getText());
			}
		});
		btnEnviar.setBounds(597, 240, 85, 21);
		contentPane.add(btnEnviar);
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
		 lblInfo.setText("CONECTADO A: " + this.port);
	}
}
