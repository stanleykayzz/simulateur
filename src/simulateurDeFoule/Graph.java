package simulateurDeFoule;

import java.util.ArrayList;
import java.util.List;

public class Graph<K,V> implements IGraph<K,V> {
	private List<GenericNode<K,V>> nodes;
	private List<GenericEdge<K,V>> edges;

	public Graph() {
		this.nodes = new ArrayList<>();
		this.edges = new ArrayList<>();
	}

	public Graph(List<GenericNode<K, V>> nodesList, List<GenericEdge<K,V>> edgesList) {
		this.nodes = nodesList;
		this.edges = edgesList;
	}

	@Override
	public GenericNode<K, V> getNode(K key) {
		int index = 0;
		while ((this.nodes.get(index)).getId() != key){
			index++;
		}
		return this.nodes.get(index);
	}

	@Override
	public void registerNode(GenericNode<K, V> node) {
		this.nodes.add(node);
	}

	@Override
	public void unregisterNode(K key) {
		this.nodes.remove(key);
	}

	public List<GenericNode<K, V>> getListOfNodes() {
		return this.nodes;
	}

	public List<GenericEdge<K,V>> getListOfEdges() {
		return this.edges;
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
