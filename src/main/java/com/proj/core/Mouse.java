package main.java.com.proj.core;

import java.util.ArrayList;

import main.java.com.proj.core.land.Land;

public class Mouse {
	private ArrayList<Position> path;
	private Position position;
	private int indicePath;
	
	public Mouse(Position position) {
		this.path = new ArrayList<>();
		this.position = position;
		//this.path = Dijsktra(position, cheesePosition)
		this.path = test(position);
		this.indicePath = 0;
	}
	
	public void move(Land land) {
		this.indicePath++;
		System.out.println("Moving "+this.toString());
		this.position = path.get(indicePath);
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
}