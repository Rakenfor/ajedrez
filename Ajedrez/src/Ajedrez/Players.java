package Ajedrez;
import java.util.*;

import javax.swing.table.AbstractTableModel;
public class Players extends AbstractTableModel{
	private String [] col = {"Nombre","Estado"};
	private List<Messaje> players = new ArrayList<Messaje>();
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 2;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return players.size();
	}
	@Override
	public Object getValueAt(int arg0, int arg1) {
		Object valor;
		if(arg1==0) 
			valor = players.get(arg0).Nick;
		else if(arg1==1)
			valor = players.get(arg0).getMesaje();
		else 
			valor = "";
		return valor;
	}
	
	@Override
	public String getColumnName(int column)
	{
		return col[column];
	}
	
	public void addplayerr(Messaje player)
	{
		players.add(player);
		fireTableDataChanged();
	}
	
	public void delPlayer(int index)
	{
		players.remove(index);
		fireTableDataChanged();
	}

}
