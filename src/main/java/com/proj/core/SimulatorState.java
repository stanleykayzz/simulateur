package main.java.com.proj.core;

import java.util.ArrayList;

import main.java.com.proj.core.land.Land;

public class SimulatorState {
	private int turn;
	private int speed;
	private int numberOfMouseDoorOne;
	private int numberOfMouseDoorTwo;
	private int numberOfMiceArrived;
	private ArrayList<Mouse> movingMice;
	private Land land;
	
	public SimulatorState(String filename) throws Exception {
		turn = 0;
		speed = 200;
		numberOfMouseDoorOne = 30;
		numberOfMouseDoorTwo = 30;
		movingMice = new ArrayList<>();

		land = Land.buildFromFile(filename);
		System.out.println("Doors  number: "+ this.land.getDoors().size());
	}
	
	public Land getLand() {
		return land;
	}
	
	public int getTurn() {
		return turn;
	}
	
	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	public void incrementTurn() {
		this.turn++;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getNumberOfMouseDoorOne() {
		return this.numberOfMouseDoorOne;
	}
	
	public void setNumberOfMouseDoorOne(int mousesDoorOne) {
		this.numberOfMouseDoorOne = mousesDoorOne;
	}
	
	public int getNumberOfMouseDoorTwo() {
		return this.numberOfMouseDoorTwo;
	}
	
	public void setNumberOfMouseDoorTwo(int mousesDoorTwo) {
		this.numberOfMouseDoorTwo = mousesDoorTwo;
	}
	
	public ArrayList<Mouse> getListMovingMice() {
		return this.movingMice;
	}
	
	public int numberOfArrivedMice() {
		int num = 0;
		for (Cheese cheese : land.getCheeses()) {
			num += cheese.getNumberOfArrivedMice();
		}
		return num;
	}
}
