package simulateurDeFoule;

public interface IGraph<K, V> {
	public INode<K,V> getNode(K key);

	public void registerNode(GenericNode<K, V> node);

	public void unregisterNode(K key);
}