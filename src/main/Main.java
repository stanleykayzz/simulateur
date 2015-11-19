package main;

import main.java.com.proj.core.Simulator;
import main.java.com.proj.core.SimulatorState;
import main.java.com.proj.gui.MainWindow;
import main.java.com.proj.utils.Constants;

public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println("Simulateur de foule");
		
		String filename = Constants.PATH_MAP+"map03.txt";
		SimulatorState state = new SimulatorState(filename);
			
		Simulator simulator = new Simulator();

		MainWindow mw = new MainWindow(state);
		mw.setState(state);
		mw.getLaunchButton().addActionListener(simulator);
		
		simulator.setState(state);
		simulator.setView(mw);
	}
}