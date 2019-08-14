package Ajedrez;
import java.util.*;
public class Tablero{
	private String[][] tablero =
		{{"TORREB","CABALLOB","ALFILB","DAMAB","REYB","ALFILB","CABALLOB","TORREB"},
			{"PEONB","PEONB","PEONB","PEONB","PEONB","PEONB","PEONB","PEONB"},
			{"","","","","","","",""},
			{"","","","","","","",""},
			{"","","","","","","",""},
			{"","","","","","","",""},
			{"PEONW","PEONW","PEONW","PEONW","PEONW","PEONW","PEONW","PEONW"},
			{"TORREW","CABALLOW","ALFILW","DAMAW","REYW","ALFILW","CABALLOW","TORREW"}
			};
	
	private String coronacion = "";
	private char color;
	private boolean firstWC = true;
	private boolean firstWL = true;
	private boolean firstBC= true;
	private boolean firstBL = true;
	private String [][]tab = new String[8][8];
	private String [][]tabgen = new String[8][8];
	private String [][]tabInit= new String[8][8];
	String enroque="";
	public String move="";
	int m;
	int n;
	
	public Tablero(String ruta, String nombre, String extension) {
		
		for(int i = 0; i < 8;i++)
			for(int j = 0; j < 8;j++)
			{
				tab[i][j]=tablero[i][j];
				tabgen[i][j]=tablero[i][j];
				tabInit[i][j]=tablero[i][j];
			}
		
	}
	
	public void setCoronacion(String cor)
	{
		coronacion = cor;
	}
	
	public String getEnroque()
	{
		return enroque;
	}
	public void setEnroque(String s)
	{
		enroque =s;
	}
	public String[][] getTablero() {
		return tablero;
	}
	public void setTablero(String[][] tablero) {
		this.tablero = tablero;
	}
	
	public void setPosition(int x, int y)
	{
		
		if(color =='w')
		{

			if(!enroque.equals("enroquec")&&!enroque.equals("enroquel"))
			{
				if(!tabgen[x][y].equals(""))
				{
					if(coronacion.equals(""))
						move = move +"x"+tabgen[x][y].substring(0, 1)
								+(char)(y+97)+(8-x);
					else
						move = move +"+"+coronacion.substring(0,1)
						+(char)(y+97)+(8-x);
				}
				else
				{
					if(coronacion.equals(""))
						move = move +"-"+move.substring(0,1)+(char)(y+97)+(8-x);
						
					else
						move = move +"="+coronacion.substring(0,1)+(char)(y+97)+(8-x);
						
				}
				
			}
			else if(enroque.equals("enroquec"))
			{
				move ="O-O";
				tabgen[7][5]="TORREW";
				tabgen[7][7]="";
				enroque = "";
			}
			else if(enroque.equals("enroquel"))
			{
				move ="O-O-O";
				tabgen[7][3]="TORREW";
				tabgen[7][0]="";
				enroque ="";
			}
		}
		
		else
		{
			if(!enroque.equals("enroquec")&&!enroque.equals("enroquel"))
			{
				if(!tabgen[x][y].equals(""))
				{
					move = move +"x"+tabgen[x][y].substring(0, 1)
							+(char)(y+97)+(8-x);
				}
				else
				{
					move = move +"-"+move.substring(0,1)+(char)(y+97)+(8-x);
				}
				
				
			}
			else if(enroque.equals("enroquec"))
			{
				move ="O-O";
				tabgen[0][5]="TORREB";
				tabgen[0][7]="";
				enroque ="";
			}
			else if(enroque.equals("enroquel"))
			{
				move ="O-O-O";
				tabgen[0][3]="TORREB";
				tabgen[0][0]="";
				enroque ="";
			}
		}
		
		if(!coronacion.equals(""))
		{
			tabgen[x][y]=coronacion;
			coronacion="";
		}
		else
			tabgen[x][y]=tabgen[m][n];
		tabgen[m][n]="";
		
		if(firstWL||firstWC||firstBL||firstBC)
		{
			if(firstWL||firstWC)
				if(!tablero[7][4].equals("REYW"))
				{
					firstWL= false;
					firstWC = false;
				}
			
			if(firstBL||firstBC)
				if(!tablero[0][4].equals("REYB"))
				{
					firstBL= false;
					firstBC = false;
				}
			
			if(firstWC)
					if(!tablero[7][7].equals("TORREW"))
						firstWC= false;
			
			if(firstWL)
				if(!tablero[7][0].equals("TORREW"))
					firstWL= false;
			
			if(firstBL)
				if(!tablero[0][0].equals("TORREB"))
					firstBC= false;
		
			if(firstBL)
				if(!tablero[0][7].equals("TORREB"))
					firstBL= false;
		}
		setOriginal();
		System.out.println();
	}
	
	
	public void setOriginal()
	{
		for(int i = 0; i < 8;i++)
			for(int j = 0; j < 8;j++)
				tablero[i][j]=tabgen[i][j];
	}
	public void setOriginalTab()
	{
		for(int i = 0; i < 8;i++)
			for(int j = 0; j < 8;j++)
				tab[i][j]="";
	}
	public void setPeligro(String pieza)
	{
		setOriginalTab();
		for(int i =0;i < 8;i++)
			for(int j = 0;j< 8;j++)
				if(!tablero[i][j].equals(""))
				{
					if(tablero[i][j].substring(tablero[i][j].length()-1).equals(pieza))
					{
						
						switch(tablero[i][j].substring(0,tablero[i][j].length()-1))
						{
						case "REY":
							mRey(i,j,pieza);
							break;
						case "DAMA":
							mDama(i,j,pieza,"DAMA");
							break;
						case "TORRE":
							mDama(i,j,pieza,"TORRE");
							break;
						case "ALFIL":
							mDama(i,j,pieza,"ALFIL");
							break;
						case "CABALLO":
							mCaballo(i,j,pieza);
							break;
						case "PEON":
							mPeon(i,j,pieza);
							break;
						}
					}
				}
				
		
		for(int i = 0; i < 8;i++)
			for(int j = 0; j < 8;j++)
				if(tablero[i][j]=="e")
					tab[i][j]="p";
		setOriginal();
	}
	public void blockTab()
	{
		for(int i = 0; i < 8;i++)
			for(int j = 0; j < 8;j++)
				tablero[i][j]="";
	}
	public void startTablero()
	{
		for(int i = 0; i < 8;i++)
		{
			for(int j = 0; j < 8;j++)
			{
				//System.out.print(tabgen[i][j]+" ");
				tab[i][j]=tabInit[i][j];
				tabgen[i][j]=tabInit[i][j];
				tablero[i][j]=tabInit[i][j];
				System.out.print(tablero[i][j]+" ");
			}
			System.out.println();
		}
		firstWC = true;
		firstWL = true;
		firstBC = true;
		firstBL = true;
		enroque = "";
		move ="";
	}
	
	public String [][] getDisponible(int x, int y, boolean turno)
	{
		m = x;
		n = y;
	
		if(tablero[x][y].substring(tablero[x][y].length()-1).equals("W")&&turno)
		{
			color = 'w';
			move = algebra(tablero[x][y].substring(0,1),x,y);
			System.out.println(tablero[x][y].substring(0,tablero[x][y].length()-1));
			if(tablero[x][y].substring(0,tablero[x][y].length()-1).equals("DAMA")||
					tablero[x][y].substring(0,tablero[x][y].length()-1).equals("ALFIL")||
					tablero[x][y].substring(0,tablero[x][y].length()-1).equals("TORRE"))
				mDama(x, y, "W",tablero[x][y].substring(0,tablero[x][y].length()-1));
			
			else if(tablero[x][y].substring(0,tablero[x][y].length()-1).equals("CABALLO"))
				mCaballo(x,y,"W");

			else if(tablero[x][y].substring(0,tablero[x][y].length()-1).equals("REY"))
			{
				setPeligro("B");
				mRey(x,y,"W");
			}

			else if(tablero[x][y].substring(0,tablero[x][y].length()-1).equals("PEON"))
				mPeon(x,y,"W");
		}
		
		else if(tablero[x][y].substring(tablero[x][y].length()-1).equals("B")&&!turno)
		{
			color ='b';
			move = algebra(tablero[x][y].substring(0,1),x,y);
			System.out.println(tablero[x][y].substring(0,tablero[x][y].length()-1));
			if(tablero[x][y].substring(0,tablero[x][y].length()-1).equals("DAMA")||
					tablero[x][y].substring(0,tablero[x][y].length()-1).equals("ALFIL")||
					tablero[x][y].substring(0,tablero[x][y].length()-1).equals("TORRE"))

				mDama(x, y, "B",tablero[x][y].substring(0,tablero[x][y].length()-1));

			else if(tablero[x][y].substring(0,tablero[x][y].length()-1).equals("CABALLO"))
				mCaballo(x,y,"B");

			else if(tablero[x][y].substring(0,tablero[x][y].length()-1).equals("REY"))
			{
				setPeligro("W");
				mRey(x,y,"B");
			}

			else if(tablero[x][y].substring(0,tablero[x][y].length()-1).equals("PEON"))
				mPeon(x,y,"B");
		}
		
		return tablero;
	}
	
	public String algebra(String pieza, int x, int y)
	{
		return pieza+(char)(y+97)+(8-x);
	}
		
	public void mDama(int x, int y,String c, String pieza)
	{
		if(pieza.equals("TORRE")||pieza.equals("DAMA"))
		{
			for(int i = x;i < 7;i++)
				if(tablero[i+1][y].equals(""))
					tablero[i+1][y]="e";
				else if(!tablero[i+1][y].substring(tablero[i+1][y].length()-1).equals(c))
				{
					tablero[i+1][y]="e";
					break;
				}
				else
					break;
			
			for(int i = x; i>0;i--)
				if(tablero[i-1][y].equals(""))
					tablero[i-1][y]="e";
				else if(!tablero[i-1][y].substring(tablero[i-1][y].length()-1).equals(c))
				{
					tablero[i-1][y]="e";
					break;
				}
				else
					break;
			
			for(int i = y;i < 7;i++)
				if(tablero[x][i+1].equals(""))
					tablero[x][i+1]="e";
				else if(!tablero[x][i+1].substring(tablero[x][i+1].length()-1).equals(c))
				{
					tablero[x][i+1]="e";
					break;
				}
				else 
					break;
			
			for(int i = y;i > 0;i--)
				if(tablero[x][i-1].equals(""))
					tablero[x][i-1]="e";
				else if(!tablero[x][i-1].substring(tablero[x][i-1].length()-1).equals(c))
				{
					tablero[x][i-1]="e";
					break;
				} 
				else
					break;
					
			
		}
		if(pieza.equals("ALFIL")||pieza.equals("DAMA"))
		{
			for(int i = 1; i < 7;i++ )
				if(x+i<8 && y+i <8)
					if(tablero[x+i][y+i].equals(""))
						tablero[x+i][y+i]="e";
					else if(!tablero[x+i][y+i].substring(tablero[x+i][y+i].length()-1).equals(c))
					{
						tablero[x+i][y+i]="e";
						break;
					}
					else
						break;
			
			for(int i = -1; i >- 7;i-- )
				if(x+i>=0 && y+i >=0)
					if(tablero[x+i][y+i].equals(""))
						tablero[x+i][y+i]="e";
					else if(!tablero[x+i][y+i].substring(tablero[x+i][y+i].length()-1).equals(c))
					{
						tablero[x+i][y+i]="e";
						break;
					}
					else
						break;
			
			for(int i = 1; i < 7;i++ )
				if(x-i>=0 && y+i <8)
					if(tablero[x-i][y+i].equals(""))
						tablero[x-i][y+i]="e";
					else if(!tablero[x-i][y+i].substring(tablero[x-i][y+i].length()-1).equals(c))
					{
						tablero[x-i][y+i]="e";
						break;
					}
					else 
						break;
			
			for(int i = 1; i < 7;i++ )
				if(x+i<8 && y-i >=0)
					if(tablero[x+i][y-i].equals(""))
						tablero[x+i][y-i]="e";
					else if(!tablero[x+i][y-i].substring(tablero[x+i][y-i].length()-1).equals(c))
					{
						tablero[x+i][y-i]="e";
						break;
					}
					else
						break;
		}
		
		
	}
	
	public void mCaballo(int x, int y, String c)

	{
		if(x+2<8&&y+1<8)
			if(tablero[x+2][y+1].equals(""))
				tablero[x+2][y+1]="e";
			else if(!tablero[x+2][y+1].substring(tablero[x+2][y+1].length()-1).equals(c))
				tablero[x+2][y+1]="e";
		
		if(x+2<8&&y-1>=0)
			if(tablero[x+2][y-1].equals(""))
				tablero[x+2][y-1]="e";
			else if(!tablero[x+2][y-1].substring(tablero[x+2][y-1].length()-1).equals(c))
				tablero[x+2][y-1]="e";
		
		if(x-2>=0&&y+1<8)
			if(tablero[x-2][y+1].equals(""))
				tablero[x-2][y+1]="e";
			else if(!tablero[x-2][y+1].substring(tablero[x-2][y+1].length()-1).equals(c))
				tablero[x-2][y+1]="e";
		
		if(x-2>=0&&y-1>=0)
			if(tablero[x-2][y-1].equals(""))
				tablero[x-2][y-1]="e";
			else if(!tablero[x-2][y-1].substring(tablero[x-2][y-1].length()-1).equals(c))
				tablero[x-2][y-1]="e";
		
		if(x+1<8&&y+2<8)
			if(tablero[x+1][y+2].equals(""))
				tablero[x+1][y+2]="e";
			else if(!tablero[x+1][y+2].substring(tablero[x+1][y+2].length()-1).equals(c))
				tablero[x+1][y+2]="e";
		
		if(x-1>=0&&y+2<8)
			if(tablero[x-1][y+2].equals(""))
				tablero[x-1][y+2]="e";
			else if(!tablero[x-1][y+2].substring(tablero[x-1][y+2].length()-1).equals(c))
				tablero[x-1][y+2]="e";
		
		if(x+1<8&&y-2>=0)
			if(tablero[x+1][y-2].equals(""))
				tablero[x+1][y-2]="e";
			else if(!tablero[x+1][y-2].substring(tablero[x+1][y-2].length()-1).equals(c))
				tablero[x+1][y-2]="e";
		
		if(x-1>=0&&y-2>=0)
			if(tablero[x-1][y-2].equals(""))
				tablero[x-1][y-2]="e";
			else if(!tablero[x-1][y-2].substring(tablero[x-1][y-2].length()-1).equals(c))
				tablero[x-1][y-2]="e";
		
	}
	
	public void mRey(int x, int y, String c)
	{
		//Desplazamiento y captura
		if(x+1<8)
			if(!tab[x+1][y].equals("p")&&tablero[x+1][y].equals(""))
				tablero[x+1][y]="e";
			else if(!tab[x+1][y].equals("p")
					&&!tablero[x+1][y].substring(tablero[x+1][y].length()-1).equals(c))
				tablero[x+1][y]="e";
		
		if(x-1>=0)
			if(!tab[x-1][y].equals("p")&&tablero[x-1][y].equals(""))
				tablero[x-1][y]="e";
			else if(!tab[x-1][y].equals("p")
					&&!tablero[x-1][y].substring(tablero[x-1][y].length()-1).equals(c))
				tablero[x-1][y]="e";
		
		if(y+1<8)
			if(!tab[x][y+1].equals("p")&&tablero[x][y+1].equals(""))
				tablero[x][y+1]="e";
			else if(!tab[x][y+1].equals("p")
					&&!tablero[x][y+1].substring(tablero[x][y+1].length()-1).equals(c))
				tablero[x][y+1]="e";
		
		if(y-1>=0)
			if(!tab[x][y-1].equals("p")&&tablero[x][y-1].equals(""))
				tablero[x][y-1]="e";
			else if(!tab[x][y-1].equals("p")
					&&!tablero[x][y-1].substring(tablero[x][y-1].length()-1).equals(c))
				tablero[x][y-1]="e";
		
		if(x+1<8&&y+1<8)
			if(!tab[x+1][y+1].equals("p")&&tablero[x+1][y+1].equals(""))
				tablero[x+1][y+1]="e";
			else if(!tab[x+1][y+1].equals("p")
					&&!tablero[x+1][y+1].substring(tablero[x+1][y+1].length()-1).equals(c))
				tablero[x+1][y+1]="e";
		
		if(x-1>=0&&y+1<8)
			if(!tab[x-1][y+1].equals("p")&&tablero[x-1][y+1].equals(""))
				tablero[x-1][y+1]="e";
			else if(!tab[x-1][y+1].equals("p")
					&&!tablero[x-1][y+1].substring(tablero[x-1][y+1].length()-1).equals(c))
				tablero[x-1][y+1]="e";
		
		if(x+1<8&&y-1>=0)
			if(!tab[x+1][y-1].equals("p")&&tablero[x+1][y-1].equals(""))
				tablero[x+1][y-1]="e";
			else if(!tab[x+1][y-1].equals("p")
					&&!tablero[x+1][y-1].substring(tablero[x+1][y-1].length()-1).equals(c))
				tablero[x+1][y-1]="e";
		
		if(x-1>=0&&y-1>=0)
			if(!tab[x-1][y-1].equals("p")&&tablero[x-1][y-1].equals(""))
				tablero[x-1][y-1]="e";
			else if(!tab[x-1][y-1].equals("p")
					&&!tablero[x-1][y-1].substring(tablero[x-1][y-1].length()-1).equals(c))
				tablero[x-1][y-1]="e";
		
		//Enroque corto
		if(firstWC||firstBC)
			if(y+2<8)
			{
				if(!tab[x][y+2].equals("p")&&tablero[x][y+2]
						.equals("")&&!tab[x][y+1].equals("p")&&tablero[x][y+1].equals("e"))
				{
					tablero[x][y+2]="e";
					enroque = "enroquec";
				}
			
			}
		//Enroque largo
		if(firstWL||firstBL)
			if(!tab[x][y-3].equals("p")&&tablero[x][y-3]
					.equals("")&&!tab[x][y-2].equals("p")&&tablero[x][y-2].equals("")
					&&!tab[x][y-1].equals("p")&&tablero[x][y-1].equals("e"))
			{
				tablero[x][y-2]="e";
				enroque = "enroquel";
			}
		setOriginalTab();
		
	}
	public void mPeon(int x, int y, String c)
	{
		if(c.equals("B"))
			if(x+1<8)
			{
				//Avance del peon
				if(tablero[x+1][y].equals(""))
					tablero[x+1][y] = "e";
				
				if(x==1)
					if(tablero[x+1][y].equals("e"))
						if(tablero[x+2][y].equals(""))
							tablero[x+2][y]="e";
				//Capturar Pieza
				if(y-1>=0)
					if(!tablero[x+1][y-1].equals(""))
						if(!tablero[x+1][y-1].substring(tablero[x+1][y-1].length()-1).equals(c))
							tablero[x+1][y-1]="e";
				if(y+1<8)
					if(!tablero[x+1][y+1].equals(""))
						if(!tablero[x+1][y+1].substring(tablero[x+1][y+1].length()-1).equals(c))
							tablero[x+1][y+1]="e";
			}
		
		if(c.equals("W"))
			if(x-1>=0)
			{
				//Avance del peon	
				if(tablero[x-1][y].equals(""))
					tablero[x-1][y] = "e";
				
				if(x==6)
					if(tablero[x-1][y].equals("e"))
						if(tablero[x-2][y].equals(""))
							tablero[x-2][y]="e";
				
				//Capturar pieza
				if(y-1>=0)
					if(!tablero[x-1][y-1].equals(""))
						if(!tablero[x-1][y-1].substring(tablero[x-1][y-1].length()-1).equals(c))
							tablero[x-1][y-1]="e";
				if(y+1<8)
					if(!tablero[x-1][y+1].equals(""))
						if(!tablero[x-1][y+1].substring(tablero[x-1][y+1].length()-1).equals(c))
							tablero[x-1][y+1]="e";
			}
	}
	
	public boolean move(int x, int y)
	{
		boolean move = false;
		
		if(tablero[x][y].equals("e"))
			move = true;
		
		return move;
		
	}

}