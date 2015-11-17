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
		for (int i=0; i<50; i++) {
			//update state
			this.state.incrementTurn();
			try {
				this.state.setSpeed(this.view.getSpeed());
				this.state.setNumberOfMouseDoorOne(this.view.getNumberOfMouseDoorOne());
				this.state.setNumberOfMouseDoorTwo(this.view.getNumberOfMouseDoorTwo());	
			} catch (NumberFormatException e) {
				System.out.println("Ignoring wrong format input");
			}
			
			//update view
			this.view.updateCanvas();
			this.view.updateStatusBar();
			this.view.updateControlBar();
			
			try {
				Thread.sleep(this.state.getSpeed());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!isLaunched) {
				try {
					// Ici c'est le thread simulator qui dépose un verrou si elle dispose
					// (ou attend une ressource)
					synchronized (this) {
						this.wait();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			showLogs();
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
		// Executé dans le thread UI.
		if(e.getSource() == this.view.getLaunchButton()){
			onLaunchListener();
		}
	}
	
	public void onLaunchListener(){
		// Executé dans le thread UI.
		if (this.getState() == State.WAITING) { // if simulator is paused we relaunch it
			
			synchronized(this){
				this.notify();
				this.isLaunched = true;
				this.view.getLaunchButton().setText("Pause");
			}
		} else if (this.getState() == State.NEW) {
			// Lors du premier clic sur le boutton Launch
			// Le thread de backdground (this) n'a pas encore été activé.
			// Pas de risque d'accès concurrent. Pas besoin donc de synchronisation.
			this.isLaunched = true;
			this.view.getLaunchButton().setText("Pause");
			this.start();
		} else {
			// Lors d'un appui sur le boutton pause
			// On souhaite placer le thread du simulateur dans l'état WAITING
			// On doit donc appeler la fonction wait().
			// Sauf que, au moment du clic sur le boutton pause, on n'a pas l'assurance
			// que le thread du simulateur soit dans l'état RUNNABLE, état nécessaire pour passer
			// à l'état pause. De plus, compte tenu des opérations effectuées dans la fonction run()
			// l'état le plus probable du thread simulator est TIMED_WAITING.
			// Ainsi, on délegue l'appel de la méthode wait() à la méthode run en lui signalant
			// par un booléen que l'état doit être mis à WAITING. En effet, la méthode run() pourra 
			// faire l'appel à wait() immédiatement après la sortie de l'état TIMED_WAITING.
			synchronized(this){
				// Ici, on a besoin d'une synchronisation, parce que le thread simulator
				// est en cours de fonctionnement et pourrait potentiellement faire
				// un accès concurrent aux variables membres auxquelles on accède ici.
				//
				this.isLaunched = false;
				this.view.getLaunchButton().setText("Lancer");
			}
		}
	}
	
	public void showLogs() {
		System.out.println("turn: "+state.getTurn());
		System.out.println("Door 1: "+this.state.getNumberOfMouseDoorOne());
		System.out.println("Door 2: "+this.state.getNumberOfMouseDoorTwo());
		System.out.println("speed: "+this.state.getSpeed());
	}
}
