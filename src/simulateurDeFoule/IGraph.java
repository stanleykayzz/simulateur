package simulateurDeFoule;

public interface IGraph<K, V> {
	@SuppressWarnings("rawtypes")
	public GenericNode getNode(K key);

	@SuppressWarnings("rawtypes")
	public void registerNode(GenericNode node);

	public void unregisterNode(K key);
}