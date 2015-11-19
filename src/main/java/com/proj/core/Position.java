package main.java.com.proj.core;

public class Position {
	public int i;
	public int j;
	
	public Position(int row, int col) {
		this.i = row;
		this.j = col;
	}
	
	public boolean isValid(int rows, int cols) {
		if ((this.i >= 0 && this.i <= rows-1) && (this.j >= 0 && this.j <= cols-1)) {
			return true;
		}
		return false;
	}
	
	public String toString(){
		return "Position("+this.i+","+this.j+")";
	}
	
	@Override
	public boolean equals(Object obj){
		 if (obj == null) {
		        return false;
		    }
		    if (getClass() != obj.getClass()) {
		        return false;
		    }
		    final Position other = (Position) obj;
		 // System.out.println("Comparing "+ this + " and "+ other);
		return i == other.i && j == other.j;
	}
}
