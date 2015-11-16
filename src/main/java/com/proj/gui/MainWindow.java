package main.java.com.proj.gui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import main.java.com.proj.core.SimulatorState;
import main.java.com.proj.core.land.Land;
import main.java.com.proj.gui.optionsBar.OptionsBar;

public class MainWindow extends JFrame {
	private SimulatorState simulatorState;
	private Land land;
	private LandView landView;
	private OptionsBar optionsBar;
	
	public MainWindow(String filename) throws Exception {
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		land = Land.buildFromFile(filename);
		landView = new LandView(this.land);
		optionsBar = new OptionsBar();
		addViews();
		this.pack();
		paramsMainWindow();
		landView.createBufferStrategy(2);
		this.setVisible(true);
	}
	
	private void paramsMainWindow() {
		this.setTitle("Simulateur de foule");
		this.setLocation(0, 0);
	}
	
	private void addViews() {
		//this.setBackground(new Color(23,199,243));
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		this.add(landView);
		this.add(optionsBar);
	}
	
	public void setState(SimulatorState state) {
		this.simulatorState = state;
		updateStatusBar();
		updateControlBar();
	}
	
	public void updateCanvas() {
		landView.repaint();
	}
	
	public void updateStatusBar() {
		optionsBar.getStatusBar().getTurnNumber().setValue(""+simulatorState.getTurn());
	}
	
	public void updateControlBar() {
		optionsBar.getControlBar().getNumberOfMouseDoorOne().setValue(""+simulatorState.getNumberOfMouseDoorOne());
		optionsBar.getControlBar().getNumberOfMouseDoorTwo().setValue(""+simulatorState.getNumberOfMouseDoorTwo());
		optionsBar.getControlBar().getSpeed().setValue(""+simulatorState.getSpeed());
	}
	
	public JButton getLaunchButton() {
		return optionsBar.getControlBar().getLaunchButton();
	}
	
	public int getSpeed() {
		return Integer.parseInt(optionsBar.getControlBar().getSpeed().getValue().getText());
	}
	
	public void setSpeed(String value) {
		this.optionsBar.getControlBar().getSpeed().setValue(value);
	}
	
	public int getNumberOfMouseDoorOne() {
		return Integer.parseInt(this.optionsBar.getControlBar().getNumberOfMouseDoorOne().getValue().getText());
	}
	
	public int getNumberOfMouseDoorTwo() {
		return Integer.parseInt(this.optionsBar.getControlBar().getNumberOfMouseDoorTwo().getValue().getText());
	}
}
