package main;

import main.java.com.proj.core.Simulator;
import main.java.com.proj.core.SimulatorState;
import main.java.com.proj.gui.MainWindow;
import main.java.com.proj.utils.Constants;

public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println("Simulateur de foule");
		SimulatorState state = new SimulatorState();
			
		Simulator simulator = new Simulator();

		MainWindow mw = new MainWindow(Constants.PATH_MAP+"map.txt");
		mw.setState(state);
		mw.getLaunchButton().addActionListener(simulator);
		
		simulator.setState(state);
		simulator.setView(mw);
	}
}