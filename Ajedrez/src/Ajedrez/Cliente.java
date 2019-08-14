package Ajedrez;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.event.ActionEvent;
import java.net.*;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;

public class Cliente extends JFrame implements Runnable {

	private JPanel contentPane;
	private JTextField campo1;
    private JTextArea textArea;
	private JTextField ip;
	private JTextField nick;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente frame = new Cliente();
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
	public Cliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		campo1 = new JTextField();
		campo1.setColumns(10);		
		JButton btnEnviar = new JButton("ENVIAR");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Socket socket = new Socket("127.0.0.1",9999);
					Messaje datos = new Messaje();
					datos.setNick(nick.getText());
					datos.setMesaje(campo1.getText());
					datos.setIp(ip.getText());
					
					//para objetos de tipo datos
					
					ObjectOutputStream envio = new ObjectOutputStream(socket.getOutputStream());
					envio.writeObject(datos);
					socket.close();
					/*para salida de datos de tipo texto
					DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
					para enviar mensaje
					salida.writeUTF(campo1.getText());*/
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			}
		});
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		
		JLabel lblChat = new JLabel("CHAT");
		lblChat.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 14));
		
		ip = new JTextField();
		ip.setColumns(10);
		
		nick = new JTextField();
		nick.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(campo1, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnEnviar, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(nick, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
							.addComponent(lblChat, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
							.addGap(28)
							.addComponent(ip, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblChat)
						.addComponent(ip, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(nick, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(1)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(campo1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEnviar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(9, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		Thread hilo = new Thread(this);
		hilo.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			ServerSocket servidor_client = new ServerSocket(9090);
			Socket cliente;
			Messaje recibido;
			
			while(true){
				cliente = servidor_client.accept();
				ObjectInputStream flujoentrada = new ObjectInputStream(cliente.getInputStream());
				recibido = (Messaje) flujoentrada.readObject();
				textArea.append(recibido.getNick()+": "+recibido.getMesaje()+"\n");
				cliente.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
//serializabla para poder conbertirce en bytes y ser enviado
