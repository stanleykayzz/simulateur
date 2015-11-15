package main.java.com.proj.core.cell;

public class Cell {
	int x;
	int y;
	boolean diagonally;
	private int g;
	private double f;
	private double h;
	private Cell parent;
	char nature;
	boolean walkable;
	
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
	
	public boolean getWalkable() {
		return this.walkable;
	}
	public void setH(double sqrt) {
		this.h = sqrt;
	}
	protected void setH(int h) {
        this.h = h;
    }

	public void setG(int i) {
		this.g = i;
	}

	public void setG(Cell i) {
		this.g = i.g;
	}

	public int getG() {
		return this.g;
	}

	public void setF(double i) {
		this.f = i;
	}

	public void setHeuristic(int i) {
		this.h = i;
	}

	public void setHeuristic(Cell i) {
		this.h = i.h;
	}

	public double getHeuristic() {
		return this.h;
	}

	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public double getF() {
		return this.g + this.h;
	}
	public void setgCosts(Cell previousAbstractNode, int basicCost) {
		setG(previousAbstractNode.getG() + basicCost);
	}

	public String toString2(){
		return "(" + getX() + ", " + getY() + "): h: " + getH() + " g: " + getG() + " f: "
				+ getF() +" Walkable : " + getWalkable();
	}
	
	public Cell getParent() {
		return this.parent;
	}
	
	public void setParent(Cell g) {
		this.parent = g;
	}
	
	public int calculategCosts(Cell n) {
		if (diagonally)
			return (n.getG() + 1);
		else
			return (n.getG() + 1);
	}
	
	public double getH() {
		return this.h;
	}
	
	public void setIsDiagonaly(boolean b) {
		this.diagonally= b;		
	}
	
	private int absolute(int a) {
		return a > 0 ? a : -a;
	}
	
	public void setH(Cell genericNode) {
		genericNode.setH((absolute(this.getX() - genericNode.getX())
                + absolute(this.getY() - genericNode.getY())));
	}
	
	public void setWalkable(boolean b){
		this.walkable = b;
	}
}
