package main.java.com.proj.graph.interfaces;

import java.util.List;

public interface INode<K,V> {
	public K getId();

	public V getValue();

	public List<IEdge<K,V>> getEdges();

	public void addNeighbor(INode<K,V> i);
}