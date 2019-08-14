package Ajedrez;

//biblioteca de entrada y salida
import java.io.*;

public class Archivo {
	private String ruta;
	private String nombre;
	private String extension;
	
	public Archivo() {
		
	}
	
	
	
	public Archivo(String ruta, String nombre, String extension) {
		super();
		this.ruta = ruta;
		this.nombre = nombre;
		this.extension = extension;
	}



	public void Escribir(String texto)
	{
		File file;
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		
		try {
			file = new File(ruta+":\\"+nombre+"."+extension);
			fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			
			bufferedWriter.write(texto);
			
			bufferedWriter.close();
			fileWriter.close();
			
		}
		catch(Exception e)
		{
			System.out.println((e.getMessage()));
		}
		
		finally
		{
			if(bufferedWriter!=null)
			{
				try
				{
					bufferedWriter.close();
				}
				
				catch(Exception e)
				{
					System.out.println(e.getMessage());
					
				}
				
			}
			
			if(fileWriter!=null)
			{
				try
				{
					fileWriter.close();
				}
				
				catch(Exception e)
				{
					System.out.println(e.getMessage());
					
				}
				
			}
		}
		
	}
	
	public String Leer()
	{
		File file;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		String texto="";
		String cadena="";
		
		try {
			file = new File(ruta+":\\"+nombre+"."+extension);
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			
			while(cadena!=null)
			{
				texto = texto + cadena;
				cadena = bufferedReader.readLine();
			}
			
		}
		catch(Exception e)
		{
			System.out.println((e.getMessage()));
		}
		
		finally
		{
			if(bufferedReader!=null)
			{
				try
				{
					bufferedReader.close();
				}
				
				catch(Exception e)
				{
					System.out.println(e.getMessage());
					
				}
				
			}
			
			if(fileReader!=null)
			{
				try
				{
					fileReader.close();
				}
				
				catch(Exception e)
				{
					System.out.println(e.getMessage());
					
				}
				
			}
		}
	
		return texto;
	}

}
