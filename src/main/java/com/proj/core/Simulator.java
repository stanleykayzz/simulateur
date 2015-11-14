package main.java.com.proj.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.com.proj.gui.MainWindow;

public class Simulator extends Thread implements ActionListener {
	private SimulatorState state;
	private MainWindow view;
	
	public Simulator() {
	}

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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.start();
	}
}
