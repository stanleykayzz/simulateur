package main.java.com.proj.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.com.proj.gui.MainWindow;

public class Simulator extends Thread implements ActionListener {
	private SimulatorState state;
	private MainWindow view;
	private boolean isLaunched = false;
	
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
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!isLaunched) {
				try {
					synchronized (this) {
						this.wait();
						this.isLaunched = true;	
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		if (this.getState() == State.WAITING) { // if simulator is paused we relaunch it
			synchronized(this){
				this.notify();
				this.isLaunched = false;
			}
		} else if (this.getState() == State.NEW) { // if simulator is just created we start it
			this.start();
			this.isLaunched = true;
		} else { // or else we force off it
			synchronized(this){
				this.notify();
				this.isLaunched = false;
			}
		}
	}
}
