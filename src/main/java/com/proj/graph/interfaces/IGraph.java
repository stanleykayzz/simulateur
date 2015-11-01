package main.java.com.proj.graph.interfaces;

import main.java.com.proj.graph.impl.GenericNode;

public interface IGraph<K, V> {
	public INode<K,V> getNode(K key);

	public void registerNode(GenericNode<K, V> node);

	public void unregisterNode(K key);
}