package main.java.com.proj.core;

import main.java.com.proj.gui.MainWindow;

public class Simulator implements Runnable {
	private SimulatorState state;
	private MainWindow view;
	
	public Simulator() {
	}

	@Override
	public void run() {
		for (int i=0; i<10; i++) {
			//update state
			this.state.incrementTurn();
			
			
			
			
			//update view
			this.view.update();
			
			System.out.println(state.getTurn());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void setState(SimulatorState state) {
		this.state = state;
	}
	
	public void setView(MainWindow view) {
		this.view = view;
	}
}
