package Ajedrez;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Server extends JFrame implements Runnable {

	private JPanel contentPane;
	JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server frame = new Server();
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
	public Server() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		Thread hilo = new  Thread(this);
		hilo.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Escuchado");
		
		try {
			ServerSocket server = new ServerSocket(9999);
			Messaje recivido;
			while(true)
			{
				Socket socket = server.accept();
				ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
				recivido = (Messaje) entrada.readObject();
				textArea.append(recivido.Nick+": "+recivido.mesaje+" Para: "+recivido.ip+"\n");
				
				
				Socket reenviar = new Socket(recivido.ip,9090);
				ObjectOutputStream salida = new ObjectOutputStream(reenviar.getOutputStream());
				salida.writeObject(recivido);
				socket.close();
				reenviar.close();
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

}
