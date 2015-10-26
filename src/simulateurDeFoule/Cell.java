package simulateurDeFoule;

public class Cell {
	int x;
	int y;
	String nature;
	
	public Cell(int x, int y, String nature) {
		this.x = x;
		this.y = y;
		this.nature = nature;
	}
	
	public String toString() {
		return String.format("{%s, %s, %s}", x, y, nature);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
