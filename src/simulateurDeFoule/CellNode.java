package simulateurDeFoule;

public class CellNode extends GenericNode<String, Cell> {
	
	public CellNode(String id, Cell cell) {
		super(id);
		this.setValue(cell);
	}
}
