package main.java.com.proj.core.cell;

import main.java.com.proj.core.Position;
import main.java.com.proj.graph.impl.GenericNode;

public class CellNode extends GenericNode<Position, Cell> {
	
	public CellNode(Position id, Cell cell) {
		super(id);
		this.setValue(cell);
	}
}
