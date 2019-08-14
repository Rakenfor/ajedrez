package Ajedrez;
import java.util.*;
import javax.swing.table.AbstractTableModel;
public class Partidas extends AbstractTableModel{
	private String [] col = {"NUM","NOMBRE"};
	private List<NPartida> partida = new ArrayList<NPartida>();
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 2;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return partida.size();
	}
	@Override
	public Object getValueAt(int arg0, int arg1) {
		Object valor;
		if(arg1==0)
			valor = partida.get(arg0).getNum();
		else if(arg1 ==1)
			valor = partida.get(arg0).getNombre();
		else 
			valor ="";
		return valor;
	}
	public String getColumnName(int column)
	{
		return col[column];
	}
	
}
class NPartida{
	private int num;
	private String nombre;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
