package main.java.com.proj.core;

import java.util.ArrayList;

import main.java.com.proj.core.land.Land;

public class Door {
	private Position position;
	private int numberOfMice;
	private ArrayList<Position> listExitPositions;
	
	public Door(Position position, Land land) {
		System.out.println("Creating new door at: i"+position.i+" j"+position.j );
		this.position = position;
		listExitPositions = new ArrayList<>();
		this.buildListExitPositions(land);
	}
	
	public void setNumberOfMice(int n) {
		this.numberOfMice = n;
	}
	
	public void popMice(ArrayList<Mouse> movingMice) {
		for(Position currentExit : listExitPositions) {
			boolean exitAccessible = true;
			for(Mouse mouse : movingMice) {
				if(mouse.getY() == currentExit.i && mouse.getX() == currentExit.j){
					//La sortie est occupée
					// Passer à la sortie suivante
					exitAccessible = false;
					break;
				}
			}
			if(exitAccessible) {
				Mouse newMouse = new Mouse(currentExit.i,currentExit.j);
				movingMice.add(newMouse);
				this.numberOfMice--;
			}
		}
	} 
	
	private void buildListExitPositions(Land land) {
		for (int k=-1; k<=1; k++) {
			for (int l=-1; l<=1; l++) {
				if (!(k==0 && l==0)) {
					Position currentPosition = new Position(this.position.i+k, this.position.j+l);
					System.out.println("Examining position: "+currentPosition);
					if (currentPosition.isValid(land.getRows(), land.getColumns())) {
						if(land.isAccessible(currentPosition)) {
							System.out.println("New accessible exit : i"+currentPosition.i +" j"+currentPosition.j);
							listExitPositions.add(currentPosition);	
						}
					}	
				}
			}
		}
	}
}