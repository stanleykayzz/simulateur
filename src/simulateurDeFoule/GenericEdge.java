package simulateurDeFoule;

import java.util.HashMap;

public class GenericEdge implements IEdge {

	@SuppressWarnings("rawtypes")
	private INode node1;
	@SuppressWarnings("rawtypes")
	private INode node2;
	// private Object value;
	private HashMap<String, Object> attributes = new HashMap<>();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public GenericEdge(INode firstNode, INode secondNode, int distance) {
		this.node1 = firstNode;
		this.node2 = secondNode;
		this.attributes.put("cost", distance);
		// this.value = distance;
		firstNode.addNeighbor(secondNode);
		secondNode.addNeighbor(firstNode);
		node1.getEdges().add(this);
		node2.getEdges().add(this);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public INode getOther(INode me) {
		if (me == this.node1)
			return this.node2;
		else
			return this.node1;
	}

	@Override
	public void setAttribute(String attributeName, Object attributeValue) {
		// key = nimp et value = taille edge
		// this.value = v;
		this.attributes.put(attributeName, attributeValue);
	}

	@Override
	public Object getAttribute(String attributeName) {
		return this.attributes.get(attributeName);
	}

}
