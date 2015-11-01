package main.java.com.proj.graph.impl;

import java.util.HashMap;

import main.java.com.proj.graph.interfaces.IEdge;
import main.java.com.proj.graph.interfaces.INode;

public class GenericEdge<K,V> implements IEdge<K,V> {

	private INode<K,V> node1;
	private INode<K,V> node2;
	private HashMap<String, Object> attributes = new HashMap<>();

	public GenericEdge(INode<K,V> nodeFirst, INode<K,V> nodeSecond) {
		this.node1 = nodeFirst;
		this.node2 = nodeSecond;
		nodeFirst.addNeighbor(nodeSecond);
		nodeSecond.addNeighbor(nodeFirst);
		node1.getEdges().add(this);
		node2.getEdges().add(this);
	}

	@Override
	public INode<K,V> getOther(INode<K,V> me) {
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
