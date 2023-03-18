import java.io.Serializable;

public class Perju implements Serializable{
	private String Player; //public
	private int Wins;
	private int Losses;
	private int Matches;
	
	public Perju(String Player) {
		this.Player=Player;
		this.Wins=0;
		this.Losses=0;
		this.Matches=0;
	}
	public String getPlayer() {
		return Player;
	}
	
	public int getWins() {
		return Wins;
	}
	
	public int getLosses() {
		return Losses;
	}
	
	public int getMatches() {
		return Matches;
	}
	
	/////////////////////////
	public void addWins() {
		Wins++;
	}
	
	public void addLosses() {
		Losses++;
	}
	
	public void addMatches() {
		Matches++;
	}
	
	public String toString() {
        return "Jugador: " + Player + "\nJuegos jugados: " + Matches + "\nJuegos ganados: " + Wins+ "\nJuegos Perdidos: " + Losses;
    }
}
