package simulateurDeFoule;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

public class GenericNode<K, V> implements INode<K,V>{
	
	private K id;
	private V node;
	private int x,y;
	int heuristique;
	private GenericNode wall =null;
	private List<IEdge> edges = new ArrayList<IEdge>();
	private List<INode> neighbors =  new ArrayList<INode>();;
	JLabel contenu;
	
	//Constructeur de sommets de graphe
	public GenericNode(int x,int y, GenericNode g) {
		this.x = x;
		this.y = y;
		this.wall = g;
	}
	public GenericNode() {
		this.wall = null;
	}
	
	public void addNeighbor(INode i)
	{
		this.neighbors.add(i);
	}
	public ArrayList<INode> getNeighborList()
	{
		return (ArrayList<INode>) this.neighbors;
	}
	
	public void setHeuristic(int i)
	{
		this.heuristique = i;
	}
	public int getHeuristic()
	{
		return this.heuristique;
	}

	@Override
	public K getId() {
		return null;
	}

	@Override
	public V getValue() {
		return this.node;
	}

	@Override
	public List<IEdge> getEdges() {	
		return this.edges;
	}
	
	public int compareNodes(GenericNode sommet1, GenericNode sommet2)
	{
		 if(sommet1.heuristique < sommet2.heuristique) 
         	return 1;
		 else if(sommet1.heuristique  == sommet2.heuristique) 
         	return 0;
		 else 
         	return -1;
	}
}
