package simulateurDeFoule;

public class CellsEdge extends GenericEdge<Integer, Cell>{

	public CellsEdge(INode<Integer, Cell> nodeFirst, INode<Integer, Cell> nodeSecond) {
		super(nodeFirst, nodeSecond);
	}
}
