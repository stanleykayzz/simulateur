package main.java.com.proj.gui;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import main.java.com.proj.core.land.Land;
import main.java.com.proj.utils.Constants;

public class MainWindow extends JFrame {
	private Land land;
	private LandView landView;
	private OptionsBar optionsBar;
	
	public MainWindow(String filename) throws Exception {
		super();
		land = Land.buildFromFile(filename);
		paramsMainWindow();
		landView = new LandView( this.land);
		optionsBar = new OptionsBar();
		addViews();

		this.setVisible(true);
	}
	
	private void paramsMainWindow() {
		this.setTitle("Simulateur");
		this.setMinimumSize(new Dimension(land.getColumns()*Constants.IMAGE_SIZE,land.getRows()*Constants.IMAGE_SIZE));
		this.setLocation(0, 0);
	}
	
	private void addViews() {
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		this.add(landView);
		this.add(optionsBar);
		optionsBar.setMaximumSize(new Dimension(400,100));
	}
}
