package Ajedrez;

public class Move {
	private int num;
	private String White;
	private String black;

	public Move()
	{
		
	}
	
	public Move(int n, String w, String b)
	{
		num =n;
		White = w;
		black = b;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getWhite() {
		return White;
	}

	public void setWhite(String white) {
		White = white;
	}

	public String getBlack() {
		return black;
	}

	public void setBlack(String black) {
		this.black = black;
	}
	
}
