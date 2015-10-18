package simulateurDeFoule;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

public class GenericNode<K, V> implements INode<K, V> {

	@SuppressWarnings("unused")
	private K id;
	private V node;
	@SuppressWarnings("unused")
	private int positionX, positionY;
	private int heuristique;
	@SuppressWarnings({ "unused" })
	private GenericNode<K, V> wall = null;
	private List<IEdge<K, V>> edges = new ArrayList<IEdge<K, V>>();
	private List<INode<K, V>> neighbors = new ArrayList<INode<K, V>>();
	JLabel contenu;

	public GenericNode(int x, int y, GenericNode<K, V> g) {
		this.positionX = x;
		this.positionY = y;
		this.wall = g;
	}

	public GenericNode() {
		this.wall = null;
	}

	@Override
	public void addNeighbor(INode<K, V> i) {
		this.neighbors.add(i);
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
	public List<IEdge<K, V>> getEdges() {
		return this.edges;
	}

	public ArrayList<INode<K, V>> getNeighborList() {
		return (ArrayList<INode<K, V>>) this.neighbors;
	}

	public void setHeuristic(int i) {
		this.heuristique = i;
	}

	public int getHeuristic() {
		return this.heuristique;
	}

	public int compareNodes(GenericNode<K,V> firstNode, GenericNode<K,V> secondNode) {
		if (firstNode.heuristique < secondNode.heuristique)
			return 1;
		else if (firstNode.heuristique == secondNode.heuristique)
			return 0;
		else
			return -1;
	}
}
