package main.java.com.proj.core.land;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import main.java.com.proj.core.cell.Cell;
import main.java.com.proj.core.cell.CellNode;
import main.java.com.proj.graph.impl.GenericEdge;
import main.java.com.proj.graph.impl.Graph;

public class Land {
	private Map<String, Cell> pLand;
	private int columns;
	private int rows;
	
	public static Land buildFromFile(String filename) throws Exception {
		LandFileParser landFile = new LandFileParser(filename);
//		System.out.println("landFile.rows:"+landFile.rows);
//		System.out.println("landFile.cols:"+landFile.cols);
//		System.out.println("landFile.allChars.size:"+landFile.allChars.size());
//		System.out.println("landFile.allChars:"+landFile.allChars);
		Land land = new Land(landFile.cols, landFile.rows);
		land.buildLand(landFile.allChars);
		return land;
	}
	
	public Land(int x, int y){
		this.columns = x;
		this.rows = y;
		this.pLand = new HashMap<>();
		buildLand();
	}
	public void buildLand(ArrayList<Character> allChars) {
		for (int i=0; i<rows; i++) {
			for (int j=0; j<columns; j++) {
				pLand.put(""+i+";"+j, new Cell(i,j, allChars.get(i*columns + j)));
			}
		}
	}
	public void buildLand() {
		for (int i=1; i<=rows; i++) {
			for (int j=1; j<=columns; j++) {
				//System.out.println("i:"+i+", j:"+j);
				pLand.put(""+i+";"+j, new Cell(i,j, ' '));
			}
		}
	}
	
	public int getColumns() {
		return this.columns;
	}
	
	public int getRows() {
		return this.rows;
	}
	public Cell get(int i, int j){
		return pLand.get(""+i+";"+j);
	}
	public void showLand() {
		for (int i=1; i<=rows; i++) {
			for (int j=1; j<=columns; j++) {
				System.out.print(pLand.get(""+i+";"+j)+"\t");
			}
			System.out.println("");
		}
	}
	
	private Graph<String, Cell> convertLandToGraph() {
		Graph<String, Cell> graph = new Graph<>();
		//on lie les noeuds horizontallement
		for (int i=1; i<=rows; i++) {
			for (int j=1; j<=columns; j++) {
				graph.registerNode(new CellNode(""+i+";"+j, pLand.get(""+i+";"+j)));
				if (j>1) {
					GenericEdge<String, Cell> edge = new GenericEdge<>(graph.getNode(""+i+";"+j), graph.getNode(""+i+";"+(j-1)));
					edge.setAttribute("cost", 1);
				}
			}
		}
		//on lie les noeuds verticallement
		for (int j=1; j<=columns; j++) {
			for (int i=1; i<=rows; i++) {
				if (i>1) {
					GenericEdge<String, Cell> edge = new GenericEdge<>(graph.getNode(""+i+""+j), graph.getNode(""+(i-1+""+j)));
					edge.setAttribute("cost", 1);
				}
			}
		}
		return graph;
	}
	
	public static void main(String[] args) {
		Land land = new Land(7, 7);
		land.showLand();
		//land.convertLandToGraph();
	}
}
