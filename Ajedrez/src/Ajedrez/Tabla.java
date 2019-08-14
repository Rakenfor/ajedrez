package Ajedrez;
import javax.swing.table.AbstractTableModel;

import java.util.*;
public class Tabla extends AbstractTableModel {
	private String[] columnas = {"NUM","BLANCO","NEGRO"};
	private List<Move> juego = new ArrayList<Move>();
	private List<Move> play = new ArrayList<Move>();
	private Archivo arch= new Archivo("D","chess","ds");;
	public boolean color= true;
	int n = 0;
	int m=0;

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}
	@Override
	public int getRowCount() {
		return juego.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		Object valor;
		if(arg1==0)
		{
			valor = juego.get(arg0).getNum();
		}else if(arg1 ==1)
		{
			valor = juego.get(arg0).getWhite();
		}else if(arg1 ==2)
		{
			valor = juego.get(arg0).getBlack();
		}
		else
		{
			valor ="";
		}
		return valor;
	}
	
	@Override 
	public String getColumnName(int column) {
		return columnas[column];
	}
	
	public void addWhite(String move)
	{
		this.juego.add(new Move(juego.size()+1,move,""));
		this.play.add(new Move(play.size()+1,move,""));
		color =!color;
		fireTableDataChanged();
	}
	
	public void setMoveBlack(String move)
	{
		juego.get(juego.size()-1).setBlack(move);
		play.get(juego.size()-1).setBlack(move);
		n++;
		color=!color;
		fireTableDataChanged();
	}
	
	public void save()
	{
		String str="";
		for(int i = 0;i < juego.size();i++)
		{
			if(!str.equals(""))
				str = str +"/";
			str = str + play.get(i).getWhite();
			if(!play.get(i).getBlack().equals(""))
				str = str+"#"+play.get(i).getBlack();
			else
				str = str +"#";
		}
		if(!str.equals(""))
			arch.Escribir(str);
		reset();
	}
	public void reset()
	{
		for(int i = juego.size()-1; i >=0;i--)
		{
			juego.remove(i);
			play.remove(i);
			n=0;
			m=0;
		}
		fireTableDataChanged();
	}
	
	public int chageSaved()
	{
		String str = arch.Leer();
		for(int i = 0; i < str.split("/").length;i++)
		{
			juego.add(new Move(i+1, str.split("/")[i].split("#")[0],""));
			play.add(new Move(i+1, str.split("/")[i].split("#")[0],""));
			
			if(str.split("/")[i].length()!=8)
			{
				juego.get(i).setBlack(str.split("/")[i].split("#")[1]);
				play.get(i).setBlack(str.split("/")[i].split("#")[1]);
				n++;
			}	
		}
		return juego.size();
	}
	
	public Save move(boolean back)
	{	
		char turno[] = new char[7];
		Save move = null;
		if(m>=0&&m<=n)
		{
			if(color)
			{
				for(int j = 0; j < play.get(m).getWhite().length();j++)
					turno[j]=play.get(m).getWhite().toCharArray()[j];
			
				if(play.get(m).getWhite().length()==7)
					move = new Save(turno[0],play.get(m).getWhite().substring(4, 5),
							turno[3],7-(turno[2]-49),(int)(turno[1])-97,7-(turno[6]-49),(int)(turno[5])-97);
				else if(play.get(m).getWhite().length()==3)
					move = new Save('E',"",'C',0,0,0,0);
				else 
					move = new Save('E',"",'L',0,0,0,0);

			}
			else
			{
				
				if(play.get(m).getBlack().length()>0)
				{
					for(int j = 0; j < play.get(m).getBlack().length();j++)
						turno[j]=play.get(m).getBlack().toCharArray()[j];
					
					if(play.get(m).getBlack().length()==7)
						move = new Save(turno[0],play.get(m).getBlack().substring(4, 5),
								turno[3],7-(turno[2]-49),(int)(turno[1])-97,7-(turno[6]-49),(int)(turno[5])-97);
					else if(play.get(m).getWhite().length()==3)
						move = new Save('E',"",'C',0,0,0,0);
					else 
						move = new Save('E',"",'L',0,0,0,0);
				}
				
				if(!back&&m<n)
					m++;
			}
			
			color = !color;
		}
		fireTableDataChanged();
		return move;
	}
	public Save back()
	{
		Save move = null;
		char turno[]=new char[7];
		if(juego.size()-1>=0)
			if(!juego.get(juego.size()-1).getBlack().equals(""))
			{
				for(int j = 0; j < juego.get(juego.size()-1).getBlack().length();j++)
					turno[j]=play.get(juego.size()-1).getBlack().toCharArray()[j];
			
				if(juego.get(juego.size()-1).getBlack().length()==7)
					move = new Save(turno[0],juego.get(juego.size()-1).getBlack().substring(4, 5),
							turno[3],7-(turno[2]-49),(int)(turno[1])-97,7-(turno[6]-49),(int)(turno[5])-97);
				else if(juego.get(juego.size()-1).getBlack().length()==3)
					move = new Save('E',"",'C',0,0,0,0);
				else 
					move = new Save('E',"",'L',0,0,0,0);
				
				juego.get(juego.size()-1).setBlack("");
				color = false;
			}
			else
			{
				for(int j = 0; j < juego.get(juego.size()-1).getWhite().length();j++)
					turno[j]=juego.get(juego.size()-1).getWhite().toCharArray()[j];
			
				if(juego.get(juego.size()-1).getWhite().length()==7)
					move = new Save(turno[0],juego.get(juego.size()-1).getWhite().substring(4, 5),
							turno[3],7-(turno[2]-49),(int)(turno[1])-97,7-(turno[6]-49),(int)(turno[5])-97);
				else if(juego.get(juego.size()-1).getWhite().length()==3)
					move = new Save('E',"",'C',0,0,0,0);
				else 
					move = new Save('E',"",'L',0,0,0,0);
				color = true;
				 
				m = juego.size()-1;
				if(m >=0)
					juego.remove(m);
			}
		fireTableDataChanged();
		return move;
	}
	
	public Save next()
	{
		Save move = null;
		char turno[] = new char[7];
		if(juego.isEmpty())
		{
			juego.add(new Move(1,play.get(0).getWhite(),""));
			
			
			for(int j = 0; j < juego.get(juego.size()-1).getWhite().length();j++)
				turno[j]=juego.get(juego.size()-1).getWhite().toCharArray()[j];
		
			if(juego.get(juego.size()-1).getWhite().length()==7)
				move = new Save(turno[0],juego.get(juego.size()-1).getWhite().substring(4, 5),
						turno[3],7-(turno[2]-49),(int)(turno[1])-97,7-(turno[6]-49),(int)(turno[5])-97);
			else if(juego.get(juego.size()-1).getWhite().length()==3)
				move = new Save('E',"",'C',0,0,0,0);
			else 
				move = new Save('E',"",'L',0,0,0,0);
			color = true;
		}
		
		else if(!juego.get(juego.size()-1).getBlack().equals(""))
		{
			if(play.size()>juego.size())
			{
				
				juego.add(new Move(juego.size()+1,play.get(juego.size()).getWhite(),""));
				
				for(int j = 0; j < juego.get(juego.size()-1).getWhite().length();j++)
					turno[j]=juego.get(juego.size()-1).getWhite().toCharArray()[j];
			
				if(juego.get(juego.size()-1).getWhite().length()==7)
					move = new Save(turno[0],juego.get(juego.size()-1).getWhite().substring(4, 5),
							turno[3],7-(turno[2]-49),(int)(turno[1])-97,7-(turno[6]-49),(int)(turno[5])-97);
				else if(juego.get(juego.size()-1).getWhite().length()==3)
					move = new Save('E',"",'C',0,0,0,0);
				else 
					move = new Save('E',"",'L',0,0,0,0);
				color = true;
			}
		}
		else
		{
			juego.get(juego.size()-1).setBlack(play.get(juego.size()-1).getBlack());
			
			for(int j = 0; j < juego.get(juego.size()-1).getBlack().length();j++)
				turno[j]=play.get(juego.size()-1).getBlack().toCharArray()[j];
		
			if(juego.get(juego.size()-1).getBlack().length()==7)
				move = new Save(turno[0],juego.get(juego.size()-1).getBlack().substring(4, 5),
						turno[3],7-(turno[2]-49),(int)(turno[1])-97,7-(turno[6]-49),(int)(turno[5])-97);
			else if(juego.get(juego.size()-1).getBlack().length()==3)
				move = new Save('E',"",'C',0,0,0,0);
			else 
				move = new Save('E',"",'L',0,0,0,0);
			color = false;
		}
		fireTableDataChanged();
		return move;
	}
	
	public Save actualizar()
	{
		char turno[] = new char[7];
		Save move;
		if(!juego.get(juego.size()-1).getBlack().equals(""))
		{
			for(int j = 0; j < juego.get(juego.size()-1).getBlack().length();j++)
				turno[j]=play.get(juego.size()-1).getBlack().toCharArray()[j];
		
			if(juego.get(juego.size()-1).getBlack().length()==7)
				move = new Save(turno[0],juego.get(juego.size()-1).getBlack().substring(4, 5),
						turno[3],7-(turno[2]-49),(int)(turno[1])-97,7-(turno[6]-49),(int)(turno[5])-97);
			else if(juego.get(juego.size()-1).getBlack().length()==3)
				move = new Save('E',"",'C',0,0,0,0);
			else 
				move = new Save('E',"",'L',0,0,0,0);
			color = false;
		}else
		{
			for(int j = 0; j < juego.get(juego.size()-1).getWhite().length();j++)
				turno[j]=juego.get(juego.size()-1).getWhite().toCharArray()[j];
		
			if(juego.get(juego.size()-1).getWhite().length()==7)
				move = new Save(turno[0],juego.get(juego.size()-1).getWhite().substring(4, 5),
						turno[3],7-(turno[2]-49),(int)(turno[1])-97,7-(turno[6]-49),(int)(turno[5])-97);
			else if(juego.get(juego.size()-1).getWhite().length()==3)
				move = new Save('E',"",'C',0,0,0,0);
			else 
				move = new Save('E',"",'L',0,0,0,0);
			color = true;
		}
		return move;
	}
	
	
}
