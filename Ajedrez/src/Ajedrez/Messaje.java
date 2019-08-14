package Ajedrez;

import java.io.Serializable;

public class Messaje implements Serializable{
	String ip, Nick,mesaje;
	boolean turn;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getNick() {
		return Nick;
	}

	public void setNick(String nick) {
		Nick = nick;
	}

	public String getMesaje() {
		return mesaje;
	}

	public void setMesaje(String mesaje) {
		this.mesaje = mesaje;
	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}
	
	
}