package main.java.com.proj.core.cell;

import main.java.com.proj.graph.impl.GenericNode;

public class CellNode extends GenericNode<String, Cell> {
	
	public CellNode(String id, Cell cell) {
		super(id);
		this.setValue(cell);
	}
}
