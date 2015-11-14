package main.java.com.proj.core;

public class SimulatorState {
	private int turn;
	
	public SimulatorState() {
		turn = 0;
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
}
