package main.java.com.proj.core;

import java.util.ArrayList;

import main.java.com.proj.core.cell.Cell;
import main.java.com.proj.core.land.Land;
import main.java.com.proj.graph.algo.Dijsktra;

public class Mouse {
	private ArrayList<Position> path;
	private Position position;
	private Position targetPosition;
	private int indicePath;
	
	public Mouse(Position position, Land land) {
		this.path = new ArrayList<>();
		this.position = position;
		this.targetPosition = this.chooseCheese(land.getCheeses());
		//this.path = new Dijsktra(position, cheesePosition)

		Dijsktra<Position, Cell> dijsktra = new Dijsktra<>();
		dijsktra.setGraph(land.convertLandToGraph());
		this.path = dijsktra.findShortestPath(position, targetPosition);
		
		//this.path = test(position);
		this.indicePath = 0;
	}
	
	public void move(Land land) {
		if(this.indicePath < (this.path.size()-1)){
			this.indicePath++;
			System.out.println("Moving mouse from "+position +" to "+ path.get(indicePath));
			this.position = path.get(indicePath);
		}
	}
	
	public void setPosition(Position pos) {
		this.position = pos;
	}
	
	private ArrayList<Position> test(Position position) {
		ArrayList<Position> path = new ArrayList<>();
		path.add(new Position(position.i, position.j));
		path.add(new Position(position.i-1, position.j-1));
		path.add(new Position(position.i-2, position.j-1));
		path.add(new Position(position.i-3, position.j-2));
		path.add(new Position(position.i-4, position.j-2));
		return path;
	}
	
	public Position getPosition() {
		return this.position;
	}
	
	private Position chooseCheese(ArrayList<Cheese> cheeses) {
		int min = 0;
		int max = cheeses.size() - 1;
		int index = min + (int)(Math.random() * ((max - min) + 1));
		return cheeses.get(index).getPosition();
	}
}