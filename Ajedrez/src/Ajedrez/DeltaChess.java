package Ajedrez;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class DeltaChess extends JFrame implements Runnable{

	/**
	 * 
	 */
	private Tabla tabla;
	private Partidas partidast;
	private Players playerst;
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	public List<Casilla> casilla;
	private Tablero tab = new Tablero("D","chess","txt");
	private boolean select = false;
	private int k;
	public String pieza ="";
	private boolean turn = true;
	private JTable players;
	private int num = 0;
	private String[] coronacion = {"DAMA","TORRE","ALFIL","CABALLO"};
	private JTable player;
	private JTable partidas;
	private boolean online=false;
	private boolean estadoPartida=false;
	JLabel player2;
	JLabel player1;
	private String IpOponent;
	private String IpServer;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeltaChess frame = new DeltaChess();
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
	
	public DeltaChess() {
	
		String str ="";
		while(str.equals(""))
			{
			str= JOptionPane.showInputDialog("Ingrese Su usuario");
			}
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("src\\Piezas\\i.png"));
		setTitle("DeltaChess");
		setResizable(false);
		casilla = new ArrayList<Casilla>();
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 1108, 800);
		panel = new JPanel();
		panel.setBackground(new Color(255, 140, 0));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		
		JPanel tablero = new JPanel();
		tablero.setBackground(new Color(128, 0, 128));
		tablero.setBorder(null);
		tablero.setLayout(new GridLayout(8, 8, 0, 0));
		tablero.setBounds(0, 0, 800, 800);
		
		JPanel Interfase = new JPanel();
		Interfase.setBorder(new CompoundBorder());
		Interfase.setBackground(new Color(255, 127, 80));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(tablero, GroupLayout.PREFERRED_SIZE, 784, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(Interfase, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(Interfase, GroupLayout.DEFAULT_SIZE, 761, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(tablero, GroupLayout.PREFERRED_SIZE, 761, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		Interfase.setLayout(null);
		
		player1 = new JLabel("Esperando...");
		player1.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 27));
		player1.setBackground(new Color(102, 205, 170));
		player1.setBounds(26, 6, 145, 43);
		Interfase.add(player1);
		
		player2 = new JLabel(str);
		player2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		player2.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 27));
		player2.setBackground(new Color(102, 205, 170));
		player2.setBounds(134, 179, 138, 43);
		Interfase.add(player2);
		
		JButton btnAbandonar = new JButton("Abandonar");
		btnAbandonar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAbandonar.setBounds(28, 264, 118, 28);
		Interfase.add(btnAbandonar);
		
		JButton btnTablas = new JButton("Tablas");
		btnTablas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnTablas.setBounds(158, 264, 114, 28);
		Interfase.add(btnTablas);
		
		JLabel partida = new JLabel("Partidas Jugadas");
		partida.setForeground(new Color(255, 255, 255));
		partida.setFont(new Font("Cambria Math", Font.PLAIN, 27));
		partida.setBackground(new Color(0, 139, 139));
		partida.setBounds(27, 304, 207, 49);
		Interfase.add(partida);
		
		JLabel lblJugadoresDisponibles = new JLabel("Jugadores Disponibles");
		lblJugadoresDisponibles.setForeground(new Color(240, 255, 255));
		lblJugadoresDisponibles.setFont(new Font("Cambria Math", Font.PLAIN, 27));
		lblJugadoresDisponibles.setBackground(new Color(102, 205, 170));
		lblJugadoresDisponibles.setBounds(17, 526, 268, 49);
		Interfase.add(lblJugadoresDisponibles);
		
		JButton cargar = new JButton("Cargar");
		cargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Save move;
				tab.blockTab();
				int k = tabla.chageSaved();
				for(int i = 0; i< k;i++)
				{
					nWhite(tabla.move(false));
					move=tabla.move(false);
					if(move!=null)
						nBlack(move);
				}
				
			}
		});
		cargar.setBounds(182, 491, 90, 28);
		Interfase.add(cargar);
		
		JButton btnJugar = new JButton("Jugar");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(estadoPartida==true)
				{
					int band = JOptionPane.showConfirmDialog(null, "Usted esta jugando una partida,\n Desea finalizarla?");
					if(band==0)
					{
						estadoPartida = true;
						if(!turn)
							turn = true;
						startTab();
						tab.startTablero();
						tabla.save();
					}
				}
				IpOponent = JOptionPane.showInputDialog(null,"INGRESE LA DIRECCION IP, DEL OPONENTE");
				IpServer = JOptionPane.showInputDialog(null, "INGRESE LADIRECCION IP DEL SERVIDOR");
				online = true;
			}
		});
		btnJugar.setBounds(182, 715, 90, 28);
		Interfase.add(btnJugar);
		
		JButton avanzar = new JButton(">");
		avanzar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Save save = tabla.next();
				if(save!=null)
					if(tabla.color==true)
						nWhite(save);
					else
						nBlack(save);
				
				
			}
		});
		avanzar.setBounds(98, 224, 48, 28);
		Interfase.add(avanzar);
		
		JButton retroceder = new JButton("<");
		retroceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Save save = tabla.back();
				if(save!=null)
					if (tabla.color==true)
						bWhite(save);
					else
						bBlack(save);
					
			}
		});
		retroceder.setFont(new Font("Showcard Gothic", Font.PLAIN, 12));
		retroceder.setBounds(30, 225, 48, 28);
		Interfase.add(retroceder);
		this.tabla = new Tabla();
		
		
		
		JButton btnPartidaNueva = new JButton("Nueva Partida");
		btnPartidaNueva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(estadoPartida ==true)
				{
					int band = JOptionPane.showConfirmDialog(null, "Usted esta jugando una partida,\n Desea finalizarla?");
					if(band==0)
					{
						if(!turn)
							turn = true;
						startTab();
						tab.startTablero();
						tabla.save();
					}
				}
				online = false;
			}
		});
		btnPartidaNueva.setBounds(158, 224, 114, 28);
		Interfase.add(btnPartidaNueva);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 54, 252, 128);
		Interfase.add(scrollPane);
		
		JTable table = new JTable();
		table.setShowHorizontalLines(true);
		table.setFont(new Font("SansSerif", Font.BOLD, 12));
		table.setFillsViewportHeight(true);
		table.setShowGrid(false);
		scrollPane.setViewportView(table);
		panel.setLayout(gl_panel);
		tabla = new Tabla();
		table.setModel(tabla);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(17, 574, 255, 128);
		Interfase.add(scrollPane_2);
		
		player = new JTable();
		scrollPane_2.setViewportView(player);
		playerst = new Players();
		player.setModel(playerst);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(26, 358, 246, 128);
		Interfase.add(scrollPane_1);
		
		partidas = new JTable();
		scrollPane_1.setViewportView(partidas);
		partidast = new Partidas();
		partidas.setModel(partidast);
		
		
		int k = 0;
		boolean color = true;
		for(int i=0;i<8;i++)
		{
			for(int j = 0;j <8;j++)
			{
				if(color)
				{
					JButton blanco = new JButton("");
					blanco.setBackground(new Color(222, 184, 135));
					blanco.setIcon(new ImageIcon(""));
					casilla.add(new Casilla(blanco,i,j));
					
					ControlCasilla cC = new ControlCasilla();
					casilla.get(k).getCas().addActionListener(cC);

					tablero.add(casilla.get(k).getCas());
					color = !color;
				}
				else
				{
					JButton marron = new JButton("");
					marron.setBackground(new Color(139, 69, 19));
					marron.setIcon(new ImageIcon(""));
					casilla.add(new Casilla(marron, i,j));
					
					ControlCasilla cC = new ControlCasilla();
					casilla.get(k).getCas().addActionListener(cC);
					
					tablero.add(casilla.get(k).getCas());
					color = !color;
				}
				k++;
			}
			color =!color;
		}
		
		startTab();
		
		Thread hilo = new  Thread(this);
		hilo.start();
		
	}
	
	
	public void mCamino(String [][] matriz, List<Casilla> casillas)
	{
		for(int i = 0; i < 8;i++)
			for(int j = 0; j < 8;j++)
				if(matriz[i][j].equals("e"))
					for(int k=0; k < 64;k++)
						if(casillas.get(k).getX()==i&&casillas.get(k).getY()==j)
							casilla.get(k).getCas().setText("*");
					
		
	}
	public void dCamino()
	{
		for(int i = 0; i <64;i++)
			if(casilla.get(i).getCas().getText().equals("*"))
				casilla.get(i).getCas().setText("");
		
	}

	private class ControlCasilla implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i =0; i < 64;i++)
				if(e.getSource().equals(casilla.get(i).getCas()))
				{
					int x = casilla.get(i).getX();
					int y = casilla.get(i).getY();
					if(!select)
					{
						if(!casilla.get(i).getCas().getIcon().toString().equals("")) 
						{
							mCamino(tab.getDisponible(x, y,turn),casilla);
							select = true;
							k = i;
							pieza = casilla.get(i).getCas().getIcon().toString();
						}
					}
					else
					{
						estadoPartida=true;
						if(tab.getTablero()[x][y].equals("e"))
						{
							dCamino();
							/*if(pieza.substring(pieza.length()-6,pieza.length()-5).equals("p")&&x==0&&turn)
							{
								int n = JOptionPane.showOptionDialog(null, "SELECCIONE LA PIEZA", "PROMOCION DEL PEON",
										JOptionPane.CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,null, coronacion, coronacion[0]);
								
								switch(n)
								{
								case 0:
									casilla.get(i).getCas().setIcon(new ImageIcon("D:\\Programing\\Eclipse-Wrkspace\\Ejedrez\\src\\Piezas\\dW.png"));
									tab.setCoronacion("DAMAW");
									break;
								case 1:
									casilla.get(i).getCas().setIcon(new ImageIcon("D:\\Programing\\Eclipse-Wrkspace\\Ejedrez\\src\\Piezas\\tW.png"));
									tab.setCoronacion("TORREW");
									break;
								case 2:
									casilla.get(i).getCas().setIcon(new ImageIcon("D:\\Programing\\Eclipse-Wrkspace\\Ejedrez\\src\\Piezas\\aW.png"));
									tab.setCoronacion("ALFILW");
									break;
								case 3:
									casilla.get(i).getCas().setIcon(new ImageIcon("D:\\Programing\\Eclipse-Wrkspace\\Ejedrez\\src\\Piezas\\cW.png"));
									tab.setCoronacion("CABALLOW");
									break;
								}
							}
							else if(pieza.substring(pieza.length()-6,pieza.length()-5).equals("p")&&x==7&&!turn)
							{
								int n = JOptionPane.showOptionDialog(null, "SELECCIONE LA PIEZA", "PROMOCION DEL PEON",
										JOptionPane.CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,null, coronacion, coronacion[0]);
								
								switch(n)
								{
								case 0:
									casilla.get(i).getCas().setIcon(new ImageIcon("D:\\Programing\\Eclipse-Wrkspace\\Ejedrez\\src\\Piezas\\dB.png"));
									tab.setCoronacion("DAMAB");
									break;
								case 1:
									casilla.get(i).getCas().setIcon(new ImageIcon("D:\\Programing\\Eclipse-Wrkspace\\Ejedrez\\src\\Piezas\\tB.png"));
									tab.setCoronacion("TORREB");
									break;
								case 2:
									casilla.get(i).getCas().setIcon(new ImageIcon("D:\\Programing\\Eclipse-Wrkspace\\Ejedrez\\src\\Piezas\\aB.png"));
									tab.setCoronacion("ALFILB");
									break;
								case 3:
									casilla.get(i).getCas().setIcon(new ImageIcon("D:\\Programing\\Eclipse-Wrkspace\\Ejedrez\\src\\Piezas\\cB.png"));
									tab.setCoronacion("CABALLO");
									break;
								}
								
							}*/				
							
							tab.setPosition(x, y);
							if(turn)
							{
								tabla.addWhite(tab.move);
								nWhite(tabla.actualizar());
							}
							else
							{
								tabla.setMoveBlack(tab.move);
								nBlack(tabla.actualizar());
							}
							
	
							if(online)
							{
								try {
									Socket socket = new Socket(IpServer,9999);
									Messaje messaje = new Messaje();
									messaje.setIp(IpOponent);
									messaje.setNick(player2.getName());
									messaje.setMesaje(tab.move);
									messaje.setTurn(turn);
									ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
									salida.writeObject(messaje);
									socket.close();
									
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									System.out.println(e1.getMessage());
								}
							}
							turn =!turn;
							
						}
						else
						{
							dCamino();
							tab.setOriginal();
						}
						select = false;
					}
					
				}
		}
		
	}
	
	public void startTab()
	{
		for(int i = 0; i < casilla.size();i++)
			casilla.get(i).getCas().setIcon(new ImageIcon(""));

		casilla.get(0).getCas().setIcon(new ImageIcon("src\\Piezas\\tB.png"));
		casilla.get(1).getCas().setIcon(new ImageIcon("src\\Piezas\\cB.png"));
		casilla.get(2).getCas().setIcon(new ImageIcon("src\\Piezas\\aB.png"));
		casilla.get(3).getCas().setIcon(new ImageIcon("src\\Piezas\\dB.png"));
		casilla.get(4).getCas().setIcon(new ImageIcon("src\\Piezas\\rB.png"));
		casilla.get(5).getCas().setIcon(new ImageIcon("src\\Piezas\\aB.png"));
		casilla.get(6).getCas().setIcon(new ImageIcon("src\\Piezas\\cB.png"));
		casilla.get(7).getCas().setIcon(new ImageIcon("src\\Piezas\\tB.png"));
		
		casilla.get(56).getCas().setIcon(new ImageIcon("src\\Piezas\\tW.png"));
		casilla.get(57).getCas().setIcon(new ImageIcon("src\\Piezas\\cW.png"));
		casilla.get(58).getCas().setIcon(new ImageIcon("src\\Piezas\\aW.png"));
		casilla.get(59).getCas().setIcon(new ImageIcon("src\\Piezas\\dW.png"));
		casilla.get(60).getCas().setIcon(new ImageIcon("src\\Piezas\\rW.png"));
		casilla.get(61).getCas().setIcon(new ImageIcon("src\\Piezas\\aW.png"));
		casilla.get(62).getCas().setIcon(new ImageIcon("src\\Piezas\\cW.png"));
		casilla.get(63).getCas().setIcon(new ImageIcon("src\\Piezas\\tW.png"));
		
		for(int i = 8;i <16;i++)
			casilla.get(i).getCas().setIcon(new ImageIcon("src\\Piezas\\pB.png"));
		
		for(int i = 55;i >47;i--)
			casilla.get(i).getCas().setIcon(new ImageIcon("src\\Piezas\\pW.png"));
	}
	
	public void nWhite(Save save)
	{
			if(save.pieza!='E')
			{
				for(int i =0; i < casilla.size();i++ )
					if(casilla.get(i).getX()==save.x0&&casilla.get(i).getY()==save.y0)
						for(int k = 0; k < casilla.size();k++)
							if(casilla.get(k).getX()==save.x1&&casilla.get(k).getY()==save.y1)
							{
								casilla.get(k).getCas().setIcon(new 
										ImageIcon(casilla.get(i).getCas().getIcon().toString()));
								casilla.get(i).getCas().setIcon(new 
										ImageIcon(""));
							}
			}
			else
			{
				
				if(save.operador=='L')
				{
					casilla.get(60).getCas().setIcon(new ImageIcon(""));
					casilla.get(58).getCas().setIcon(new ImageIcon("src\\Piezas\\rW.png"));
					casilla.get(56).getCas().setIcon(new ImageIcon(""));
					casilla.get(59).getCas().setIcon(new ImageIcon("src\\Piezas\\tW.png"));
				}
				
				else if(save.operador=='C')
				{
					casilla.get(60).getCas().setIcon(new ImageIcon(""));
					casilla.get(62).getCas().setIcon(new ImageIcon("src\\Piezas\\rW.png"));
					casilla.get(63).getCas().setIcon(new ImageIcon(""));
					casilla.get(61).getCas().setIcon(new ImageIcon("src\\Piezas\\tW.png"));
				}
				
			}
	}
	
	public void bWhite(Save save)
	{
		if(save.pieza!='E')
		{
			for(int i =0; i < casilla.size();i++ )
				if(casilla.get(i).getX()==save.x0&&casilla.get(i).getY()==save.y0)
					for(int k = 0; k < casilla.size();k++)
						if(casilla.get(k).getX()==save.x1&&casilla.get(k).getY()==save.y1)
						{
							casilla.get(i).getCas().setIcon(new 
									ImageIcon(casilla.get(k).getCas().getIcon().toString()));
							casilla.get(k).getCas().setIcon(new 
									ImageIcon(""));
						}
			if(save.operador=='x')
			{
				for(int i =0; i < casilla.size();i++ )
					if(casilla.get(i).getX()==save.x1&&casilla.get(i).getY()==save.y1)
						casilla.get(i).getCas().setIcon(new 
								ImageIcon("src\\Piezas\\"+save.p1.toLowerCase()+"B.png"));
			}
		}
		else
		{
			if(save.operador=='L')
			{
				casilla.get(60).getCas().setIcon(new ImageIcon("src\\Piezas\\rW.png"));
				casilla.get(58).getCas().setIcon(new ImageIcon(""));
				casilla.get(56).getCas().setIcon(new ImageIcon("src\\Piezas\\tW.png"));
				casilla.get(59).getCas().setIcon(new ImageIcon(""));
			}
			
			else if(save.operador=='C')
			{
				casilla.get(60).getCas().setIcon(new ImageIcon("src\\Piezas\\rW.png"));
				casilla.get(62).getCas().setIcon(new ImageIcon(""));
				casilla.get(63).getCas().setIcon(new ImageIcon("src\\Piezas\\tW.png"));
				casilla.get(61).getCas().setIcon(new ImageIcon(""));
			}
		}
	}
	
	public void bBlack(Save save)
	{
		if(save.pieza!='E')
		{
			for(int i =0; i < casilla.size();i++ )
				if(casilla.get(i).getX()==save.x1&&casilla.get(i).getY()==save.y1)
					for(int k = 0; k < casilla.size();k++)
						if(casilla.get(k).getX()==save.x0&&casilla.get(k).getY()==save.y0)
						{
							casilla.get(k).getCas().setIcon(new 
									ImageIcon(casilla.get(i).getCas().getIcon().toString()));
							casilla.get(i).getCas().setIcon(new 
									ImageIcon(""));
						}
			if(save.operador=='x')
			{
				for(int i =0; i < casilla.size();i++ )
					if(casilla.get(i).getX()==save.x1&&casilla.get(i).getY()==save.y1)
						casilla.get(i).getCas().setIcon(new 
								ImageIcon("src\\Piezas\\"+save.p1.toLowerCase()+"W.png"));
			}
		}
		else
		{
			if(save.operador=='L')
			{
				casilla.get(0).getCas().setIcon(new ImageIcon("src\\Piezas\\tB.png"));
				casilla.get(2).getCas().setIcon(new ImageIcon(""));
				casilla.get(3).getCas().setIcon(new ImageIcon(""));
				casilla.get(4).getCas().setIcon(new ImageIcon("src\\Piezas\\rB.png"));
			}
			
			else if(save.operador=='C')
			{
				casilla.get(4).getCas().setIcon(new ImageIcon("src\\Piezas\\rB.png"));
				casilla.get(5).getCas().setIcon(new ImageIcon(""));
				casilla.get(6).getCas().setIcon(new ImageIcon(""));
				casilla.get(7).getCas().setIcon(new ImageIcon("src\\Piezas\\tB.png"));
			}
			
		}
	}
	public void nBlack(Save save)
	{
			if(save.pieza!='E')
			{
				for(int i =0; i < casilla.size();i++ )
					if(casilla.get(i).getX()==save.x0&&casilla.get(i).getY()==save.y0)
						for(int k = 0; k < casilla.size();k++)
							if(casilla.get(k).getX()==save.x1&&casilla.get(k).getY()==save.y1)
							{
								casilla.get(k).getCas().setIcon(new 
										ImageIcon(casilla.get(i).getCas().getIcon().toString()));
								casilla.get(i).getCas().setIcon(new 
										ImageIcon(""));
							}
			}
			else
			{
				if(save.operador=='L')
				{
					casilla.get(0).getCas().setIcon(new ImageIcon(""));
					casilla.get(2).getCas().setIcon(new ImageIcon("src\\Piezas\\rB.png"));
					casilla.get(3).getCas().setIcon(new ImageIcon("src\\Piezas\\tB.png"));
					casilla.get(4).getCas().setIcon(new ImageIcon(""));
				}
				
				else if(save.operador=='C')
				{
					casilla.get(4).getCas().setIcon(new ImageIcon(""));
					casilla.get(5).getCas().setIcon(new ImageIcon("src\\Piezas\\tB.png"));
					casilla.get(6).getCas().setIcon(new ImageIcon("src\\Piezas\\rB.png"));
					casilla.get(7).getCas().setIcon(new ImageIcon(""));
				}
				
			}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Escuchando");
		
		try {
			ServerSocket scliente = new ServerSocket(9090);
			Socket cliente;
			Messaje recivido;
			while(true)
			{
				cliente = scliente.accept();
				ObjectInputStream flujoentrada = new ObjectInputStream(cliente.getInputStream());
				recivido = (Messaje) flujoentrada.readObject();
				System.out.print(recivido.mesaje);
				player1.setText(recivido.Nick);
				turn = recivido.turn;
				
				if(turn)
				{
					tabla.addWhite(recivido.mesaje);
					Save move = tabla.actualizar();
					tab.m = move.x0;
					tab.n = move.y0;
					if(move.operador=='E')
					{
						if(move.pieza=='L')
							tab.enroque="enroquel";
						else
							tab.enroque="enroquec";
					}
					tab.setPosition(move.x1, move.y1);
					nWhite(move);
				}
				else
				{
					tabla.setMoveBlack(recivido.mesaje);
					Save move = tabla.actualizar();
					tab.m = move.x0;
					tab.n = move.y0;
					if(move.operador=='E')
					{
						if(move.pieza=='L')
							tab.enroque="enroquel";
						else
							tab.enroque="enroquec";
					}
					tab.setPosition(move.x1, move.y1);
					nBlack(tabla.actualizar());
					num++;
				}
				turn =!turn;
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

