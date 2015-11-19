package main.java.com.proj.core.land;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import main.java.com.proj.core.Cheese;
import main.java.com.proj.core.Door;
import main.java.com.proj.core.Position;
import main.java.com.proj.core.cell.Cell;
import main.java.com.proj.core.cell.CellNode;
import main.java.com.proj.graph.impl.GenericEdge;
import main.java.com.proj.graph.impl.Graph;

public class Land {
	private Map<String, Cell> pLand;
	private int columns;
	private int rows;
	ArrayList<Cheese> cheeses;
	ArrayList<Door> doors;
	
	public static Land buildFromFile(String filename) throws Exception {
		LandFileParser landFile = new LandFileParser(filename);
//		System.out.println("landFile.rows:"+landFile.rows);
//		System.out.println("landFile.cols:"+landFile.cols);
//		System.out.println("landFile.allChars.size:"+landFile.allChars.size());
//		System.out.println("landFile.allChars:"+landFile.allChars);
		Land land = new Land(landFile);
		land.buildLand(landFile.allChars);

		return land;
	}
	
	public Land(LandFileParser landFileParser){
		this.rows = landFileParser.rows;
		this.columns = landFileParser.cols;
		this.pLand = new HashMap<>();
		this.cheeses = new ArrayList<>();
		this.doors = new ArrayList<>();
		
		buildLand(landFileParser.allChars);
		buildDoors();
		buildCheeses();
	}
	public void buildLand(ArrayList<Character> allChars) {
		for (int i=0; i<rows; i++) {
			for (int j=0; j<columns; j++) {
				pLand.put(""+i+";"+j, new Cell(i,j, allChars.get(i*columns + j)));
			}
		}
	}
	
	public int getColumns() {
		return this.columns;
	}
	
	public int getRows() {
		return this.rows;
	}
	
	public Cell getCell(int i, int j) {
		return pLand.get(""+i+";"+j);
	}
	
	public void showLand() {
		for (int i=0; i<rows; i++) {
			for (int j=0; j<columns; j++) {
				System.out.print(pLand.get(""+i+";"+j)+"\t");
			}
			System.out.println("");
		}
	}
	
	public boolean isAccessible(Position position) {
		Cell cell = this.getCell(position.i, position.j);
		if (cell.getNature() == ' ' || cell.getNature() == 'G' || cell.getNature() == 'A') {
			return true;
		}
		return false;
	}
	
	public Graph<Position, Cell> convertLandToGraph() {
		Graph<Position, Cell> graph = new Graph<>();
		//on lie les noeuds horizontallement
		for (int i=0; i<rows; i++) {
			for (int j=0; j<columns; j++) {
				graph.registerNode(new CellNode(new Position(i,j), pLand.get(""+i+";"+j)));
				if (j>1) {
					GenericEdge<Position, Cell> edge = new GenericEdge<>(graph.getNode(new Position(i,j)), graph.getNode(new Position(i,j-1)));
					edge.setAttribute("cost", 1);
				}
			}
		}
		//on lie les noeuds verticallement
		for (int j=0; j<columns; j++) {
			for (int i=0; i<rows; i++) {
				if (i>1) {
					GenericEdge<Position, Cell> edge = new GenericEdge<>(graph.getNode(new Position(i,j)), graph.getNode(new Position(i-1,j)));
					edge.setAttribute("cost", 1);
				}
			}
		}
		return graph;
	}

	public void buildDoors() {
		for (int i=0; i<getRows(); i++) {
			for (int j=0; j<getColumns(); j++) {
				Cell cell = getCell(i, j);
				//System.out.println("Cell i"+i+" j"+j +":"+cell);
				if(cell.getNature() == 'D') {
					Door door = new Door(new Position(i,j), this);
					this.doors.add(door);
				}
			}
		}
	}
	

	public void buildCheeses() {
		for (int i=0; i<getRows(); i++) {
			for (int j=0; j<getColumns(); j++) {
				Cell cell = getCell(i, j);
				if(cell.getNature() == 'A') {
					Cheese cheese = new Cheese(new Position(i,j));
					cheeses.add(cheese);
				}
			}
		}
	}
	
	public ArrayList<Door> getDoors() {
		return this.doors;
	}
	
	public ArrayList<Cheese> getCheeses() {
		return this.cheeses;
	}
	
}
