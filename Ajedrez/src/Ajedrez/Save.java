package Ajedrez;

public class Save {
	public int x0;
	public int y0;
	public int x1;
	public int y1;
	public char pieza;
	String p1;
	public char operador;
	
	public Save() {
		
	}
	
	public Save( char pieza,String p1, char o,int x0, int y0,int x1, int y1)
	{
		this.x0 = x0;
		this.y0= y0;
		this.x1 = x1;
		this.y1= y1;
		this.pieza = pieza;
		this.operador = o;
		this.p1= p1;
	}

}
