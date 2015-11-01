package main.java.com.proj.core.cell;

import main.java.com.proj.graph.impl.GenericEdge;
import main.java.com.proj.graph.interfaces.INode;

public class CellsEdge extends GenericEdge<Integer, Cell>{

	public CellsEdge(INode<Integer, Cell> nodeFirst, INode<Integer, Cell> nodeSecond) {
		super(nodeFirst, nodeSecond);
	}
}
