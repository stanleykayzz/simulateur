package simulateurDeFoule;

public interface IGraph<K,V> {
	public GenericNode getNode(K key);
	public void registerNode(GenericNode node);
	public void unregisterNode(K key);
}