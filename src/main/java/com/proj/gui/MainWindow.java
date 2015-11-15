package main.java.com.proj.gui;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import main.java.com.proj.core.Simulator;
import main.java.com.proj.core.SimulatorState;
import main.java.com.proj.core.land.Land;
import main.java.com.proj.gui.optionsBar.OptionsBar;
import main.java.com.proj.utils.Constants;

public class MainWindow extends JFrame {
	private SimulatorState simulatorState;
	private int widthWindow;
	private int heightWindow;
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
		paramsMainWindow();
		this.setVisible(true);
	}
	
	private void paramsMainWindow() {
		this.setTitle("Simulateur de foule");
		this.widthWindow = landView.getMinimumSize().width;
		this.heightWindow = land.getRows()*Constants.IMAGE_SIZE + optionsBar.getPreferredSize().height;
		this.setMinimumSize(new Dimension(this.widthWindow, this.heightWindow));
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
	}
	
	public void update() {
		optionsBar.getStatusBar().getAttrTurn().setValue(""+simulatorState.getTurn());
	}
	
	public JButton getLaunchButton(){
		return optionsBar.getControlBar().getLaunchButton();
	}
}
