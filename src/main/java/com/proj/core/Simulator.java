package main.java.com.proj.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.com.proj.gui.MainWindow;

public class Simulator extends Thread implements ActionListener {
	private SimulatorState state;
	private MainWindow view;
	private boolean isLaunched = false;
	public int speed;
	public int numberOfMouseDoorOne;
	public int numberOfMouseDoorTwo;
	
	public Simulator() {
	}

	public void run() {
		for (int i=0; i<50; i++) {
			//update state
			this.state.incrementTurn();
			this.speed = this.view.getSpeed();
			this.numberOfMouseDoorOne = this.view.getNumberOfMouseDoorOne();
			this.numberOfMouseDoorTwo = this.view.getNumberOfMouseDoorTwo();
			
			
			//update view
			this.view.update();
			
			System.out.println("turn: "+state.getTurn());
			System.out.println("speed: "+speed);
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!isLaunched) {
				try {
					synchronized (this) {
						this.wait();
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
		if(e.getSource() == this.view.getLaunchButton()){
			onLaunchListener();
		}
	}
	
	public void onLaunchListener(){
		if (this.getState() == State.WAITING) { // if simulator is paused we relaunch it
			synchronized(this){
				this.notify();
				this.isLaunched = true;
				this.view.getLaunchButton().setText("Pause");

				System.out.println("Door 1: "+numberOfMouseDoorOne);
				System.out.println("Door 2: " + numberOfMouseDoorTwo);
			}
		} else if (this.getState() == State.NEW) { // if simulator is just created we start it
			this.start();
			this.isLaunched = true;
			this.view.getLaunchButton().setText("Pause");
		} else { // or else we force off it
			synchronized(this){
				this.isLaunched = false;
				this.view.getLaunchButton().setText("Lancer");
			}
		}
	}
}
