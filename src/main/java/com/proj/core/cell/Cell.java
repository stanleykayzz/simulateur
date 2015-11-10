package main.java.com.proj.core.cell;

import javax.swing.JLabel;

public class Cell extends JLabel{
	int x;
	int y;
	boolean diagonally;
	private int g;
	private double f=0;
	private double h;
	private Cell parent;
	char nature;
	boolean walkable;
	boolean busy = false;
	private boolean statut;
	
	public Cell(int x, int y, char nature){
		this.x = x;
		this.y = y;
		this.nature = nature;
		this.walkable = true;
	}
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		this.h = 0;
		this.g = 0;
		this.walkable = true;
	}
	public Cell() {
		this.walkable = true;
	}

	public Cell(char ch) {
		this.walkable = true;
		this.nature = ch;
	}
	
	public String toString() {
		return String.format("{%s, %s, %s}", x, y, nature);
	}
	
	public int getX() {
		return this.x;
	}
	public boolean getWalkable() {
		return this.walkable;
	}
	public void setH(double sqrt) {
		this.h = sqrt;
	}
	protected void setH(int i) {
        this.h = i;
    }

	public void setG(int i) {
		this.g = i;
	}

	public void setG(Cell i) {
		this.g = i.g ;
	}

	public int getG() {
		return this.g;
	}

	public void setF(double i) {
		this.f = i;
	}
	/* les methodes getBusy et setBusy sont utilisé lorsqu'une souris entre ou sort d'une cellule*/
	public void setBusy(boolean i) {
		this.busy = i;
	}
	public boolean getBusy() {
		return this.busy;
	}

	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public double getF() {
		return this.g + this.h;
	}
	public void setG(double gCosts) {
		this.g = g ;
	}
	public void setgCosts(Cell previousAbstractNode) {
		if (diagonally) {
			setgCosts(previousAbstractNode, 1);
		} else {
			setgCosts(previousAbstractNode, 1);
		}
	}
	public void setgCosts(Cell previousAbstractNode, int basicCost) {
		setG(previousAbstractNode.getG() + basicCost);
	}

	public String toString2(){
		return "(" + getX() + ", " + getY() + "): h: " + getH() + " g: " + getG() + " f: "
				+ getF() +" Walkable : " + getWalkable();
	}

	public int getY() {
		return this.y;
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
	public boolean isDiagonaly() {
		return diagonally;
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
	
	public char getNature() {
		return nature;
	}
	public void setWalkable(boolean b){
		this.walkable = b;
	}
	public void setNature(char c) {
		// TODO Auto-generated method stub
		this.nature = c;
	}
	public void setStatut(boolean b) {
		// TODO Auto-generated method stub
		this.statut = b;
	}
}
