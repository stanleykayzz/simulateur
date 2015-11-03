package main.java.com.proj.mouse;

import java.util.ArrayList;
import main.java.com.proj.core.cell.Cell;

public class Mouse {
	
	private int idMouse;
	private ArrayList<Cell> chemin = new ArrayList<>();
	private Cell position, lastPosition;
	
	public Mouse(int id, Cell cellule){
		this.idMouse = id;
		/*Faire appel à l'algo A* qui renvoi une Arraylist*/
		//this.chemin = ??? ;
		this.lastPosition = cellule;
		this.position = cellule;
		cellule.setNature('P');
		cellule.setStatut(true);
	}
	
	public ArrayList<Cell> getChemin(){
		return this.chemin;
	}
	
	public int getIdMouse(){
		return this.idMouse;
	}
	
	public void setPosition(Cell newCellule, Cell lastCellule){
		lastCellule.setNature(lastPosition.getNature());
		lastCellule.setStatut(false);
		this.lastPosition = newCellule;
		this.position = newCellule;
		newCellule.setNature('P');
		newCellule.setStatut(true);
	}
	
	public Cell getPosition(){
		return this.position;
	}

}
