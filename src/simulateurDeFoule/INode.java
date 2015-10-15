package simulateurDeFoule;

import java.util.List;

public interface INode<K, V>{
    public K getId();
    public V getValue();
    public List<IEdge> getEdges();
    public void addNeighbor(INode i);

}