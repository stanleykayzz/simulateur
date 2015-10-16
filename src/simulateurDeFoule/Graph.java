package simulateurDeFoule;

import java.util.ArrayList;
import java.util.List;

public class Graph<K, V> implements IGraph<K, V> {

	// un graphe a des noeuds
	@SuppressWarnings("rawtypes")
	private List<GenericNode> graphNode = new ArrayList<GenericNode>();
	private List<GenericEdge> graphEdges = new ArrayList<GenericEdge>();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Graph() {
		this.graphNode = new ArrayList();
	}

	@SuppressWarnings("rawtypes")
	public Graph(List<GenericNode> lesSommets, List<GenericEdge> lesChemins) {
		this.graphNode = lesSommets;
		this.graphEdges = lesChemins;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public GenericNode getNode(K key) {
		int i = 0;
		while ((this.graphNode.get(i)).getValue() != key)
		// while(( this.graphNode.get(i)).getValue() != key)
		{
			i++;
		}
		return this.graphNode.get(i);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void registerNode(GenericNode node) {
		this.graphNode.add(node);
	}

	@Override
	public void unregisterNode(K key) {
		this.graphNode.remove(key);
	}

	@SuppressWarnings("rawtypes")
	public List<GenericNode> getListOfNodes() {
		return this.graphNode;
	}

	public List<GenericEdge> getListOfEdges() {
		return this.graphEdges;
	}

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {

		@SuppressWarnings("unused")
		Graph g = new Graph<>();
		/*
		 * GenericNode a = new GenericNode("A"); GenericNode b = new
		 * GenericNode("B"); GenericNode c = new GenericNode("C"); GenericNode d
		 * = new GenericNode("D"); GenericNode e = new GenericNode("E");
		 * GenericNode f = new GenericNode("F");
		 * 
		 * GenericEdge ab = new GenericEdge(a, b, 1); GenericEdge cd = new
		 * GenericEdge(c, d, 1); GenericEdge ef = new GenericEdge(e, f, 1);
		 * GenericEdge ac = new GenericEdge(a, c, 1); GenericEdge bd = new
		 * GenericEdge(b, d, 1); GenericEdge ce = new GenericEdge(c, e, 1);
		 * GenericEdge df = new GenericEdge(d, f, 1);
		 */

		// for(Object n : a.())
		// System.out.println((GenericNode)n);

	}

}
