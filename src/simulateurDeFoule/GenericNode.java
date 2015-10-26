package simulateurDeFoule;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;

// la class Node correspond à une case du terrain
public class GenericNode extends JLabel {

	private int x, y;
	private double h;
	private boolean diagonally;
	private int g;
	private double f;
	private GenericNode parent ;
	private boolean Walkable;
	//neighbors à supprimer
	private List<GenericNode> neighbors = new ArrayList<GenericNode>();
	JLabel contenu;
	char c;
	private boolean setIsDiagonaly;

	public GenericNode(int x, int y) {
		this.x = x;
		this.y = y;
		this.f=0;
		this.g=0;
		this.h=0;
		this.Walkable = true;
	}
	public GenericNode() {
		this.f=0;
		this.g=0;
		this.h=0;
		this.Walkable = true;
	}

	public void setNeighbors() {
		// on récupère le noeud haut
		GenericNode up = new GenericNode(this.x, this.y - 1);
		if (this.y - 1 >= 0)
			this.neighbors.add(up);
		// on récupère le noeud bas
		GenericNode down = new GenericNode(this.x, this.y + 1);
		if (this.y + 1 <= 18)
			this.neighbors.add(down);
		// on récupère le noeud gauche
		GenericNode left = new GenericNode(this.x - 1, this.y);
		if (this.x - 1 >= 0)
			this.neighbors.add(left);
		// on récupère le noeud droit
		GenericNode right = new GenericNode(this.x + 1, this.y);
		if (this.x + 1 <= 47)
			this.neighbors.add(right);
	}

	public void addNeighbor(GenericNode i) {
		this.neighbors.add(i);
	}

	public JLabel getContenu() {
		return this.contenu;
	}

	public ArrayList<GenericNode> getNeighborList() {
		return (ArrayList<GenericNode>) this.neighbors;
	}

	public void setWalkable(boolean b) {
		this.Walkable = b;
	}

	public boolean getWalkable() {
		return this.Walkable;
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

	public void setG(GenericNode i) {
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

	public void setHeuristic(GenericNode i) {
		this.h = i.h;
	}

	public double getHeuristic() {
		return this.h;
	}

	public void setChar(char ch)
	{
		this.c = ch;
	}
	public char getChar()
	{
		return this.c;
	}

	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public double getF() {
		return this.g + this.h;
	}
	public void setgCosts(GenericNode previousAbstractNode, int basicCost) {
		setG(previousAbstractNode.getG() + basicCost);
	}

	@Override
	public String toString() {
		return "(" + getXValue() + ", " + getYValue() + "): h: " + getH() + " g: " + getG() + " f: "
				+ getF() +" Walkable : " + getWalkable();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final GenericNode other = (GenericNode) obj;
		if (this.x != other.x) {
			return false;
		}
		if (this.y != other.y) {
			return false;
		}
		return true;
	}

	private int absolute(int a) {
		return a > 0 ? a : -a;
	}

	public int getXValue() {
		return this.x;
	}

	public int getYValue() {
		return this.y;
	}

	public void setXValue(int i) {
		this.x = i;
	}

	public void setYValue(int i) {
		this.y = i;
	}
	public int compareNodes(GenericNode sommet1, GenericNode sommet2) {
		if (sommet1.h < sommet2.h)
			return 1;
		else if (sommet1.h == sommet2.h)
			return 0;
		else
			return -1;
	}
	public GenericNode getParent() {
		return this.parent;
	}
	public void setParent(GenericNode g) {
		this.parent = g;
	}		
	public int calculategCosts(GenericNode n) {
		if (diagonally)
			return (n.getG() + 1);
		else
			return (n.getG() + 1);
	}
	public double getH() {
		return this.h;
	}
	public void setIsDiagonaly(boolean b) {
		this.setIsDiagonaly= b;		
	}
	public void setH(GenericNode genericNode) {
		genericNode.setH((absolute(this.getXValue() - genericNode.getXValue())
                + absolute(this.getYValue() - genericNode.getYValue())));
	}
}
