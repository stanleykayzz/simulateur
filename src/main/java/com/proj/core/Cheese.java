package main.java.com.proj.core;

import java.util.ArrayList;

public class Cheese {
	private Position position;
	private ArrayList<Mouse> arrivedMice;
	
	public Cheese(Position pos) {
		this.position = pos;
		this.arrivedMice = new ArrayList<>();
	}
	
	public void addArrivedMice(Mouse mouse) {
		this.arrivedMice.add(mouse);
	}
	
	public int getNumberOfArrivedMice() {
		return this.arrivedMice.size();
	}
	
	public Position getPosition() {
		return this.position;
	}
}
