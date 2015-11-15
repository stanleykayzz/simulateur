package main.java.com.proj.gui.optionsBar;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import main.java.com.proj.gui.components.PropertyView;

public class StatusBar extends JPanel{
	private PropertyView numberOfTurn;
	private PropertyView numberOfTrips;
	private PropertyView numberOfMouseMoving;
	private PropertyView numberOfMouseArrived;
	
	public StatusBar() {
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(179, 166, 125));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		
		numberOfTurn = new PropertyView("TOUR","0");
		c.gridx = 1;
		this.add(numberOfTurn, c);
		
		numberOfTrips = new PropertyView("DEPLACEMENTS","0");
		c.gridx++;
		this.add(numberOfTrips, c);
		
		numberOfMouseMoving = new PropertyView("SOURIS EN DEPLACEMENTS","0");
		c.gridx++;
		this.add(numberOfMouseMoving, c);

		numberOfMouseArrived = new PropertyView("SOURIS ARRIVES","0");
		c.gridx++;
		this.add(numberOfMouseArrived, c);
	}
	
	public PropertyView getNumberOfTurn() {
		return numberOfTurn;
	}
	
	public PropertyView getNumberOfTrips() {
		return numberOfTrips;
	}
	
	public PropertyView getNumberOfMouseMoving() {
		return numberOfMouseMoving;
	}
	
	public PropertyView getNumberOfMouseArrived() {
		return numberOfMouseArrived;
	}
}
