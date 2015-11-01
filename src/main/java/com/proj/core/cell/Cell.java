package main.java.com.proj.core.cell;

public class Cell {
	int x;
	int y;
	char nature;
	
	public Cell(int x, int y, char nature) {
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
	
	public char getNature() {
		return nature;
	}
}
