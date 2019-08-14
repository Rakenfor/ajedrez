package Ajedrez;
import java.util.*;
import javax.swing.table.AbstractTableModel;
public class Player extends AbstractTableModel{
	private List<Move>playw =new ArrayList<Move>();
	private List<Move>playb = new ArrayList<Move>();

	@Override
	public int getColumnCount() {
		
		return 3;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return playw.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void addRowa(Move jugada)
	{
		this.playw.add(jugada);
		fireTableDataChanged();
	}
	public void addRowb(Move jugada)
	{
		this.playb.add(jugada);
		fireTableDataChanged();
	}

}
