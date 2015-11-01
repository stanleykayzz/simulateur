package main.java.com.proj.graph.interfaces;

public interface IEdge<K,V> {
	public INode<K,V> getOther(INode<K,V> me);

	public void setAttribute(String key, Object value);

	public Object getAttribute(String value);
}