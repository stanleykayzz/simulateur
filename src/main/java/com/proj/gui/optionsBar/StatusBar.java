package main.java.com.proj.gui.optionsBar;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import main.java.com.proj.gui.components.StatusBarItem;

public class StatusBar extends JPanel{
	private StatusBarItem numberOfTurn;
	private StatusBarItem numberOfTrips;
	private StatusBarItem numberOfMouseMoving;
	private StatusBarItem numberOfMouseArrived;
	
	public StatusBar() {
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(179, 166, 125));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		
		numberOfTurn = new StatusBarItem("TOUR","0");
		c.gridx = 1;
		this.add(numberOfTurn, c);
		
		numberOfTrips = new StatusBarItem("DEPLACEMENTS","0");
		c.gridx++;
		this.add(numberOfTrips, c);
		
		numberOfMouseMoving = new StatusBarItem("SOURIS EN DEPLACEMENTS","0");
		c.gridx++;
		this.add(numberOfMouseMoving, c);

		numberOfMouseArrived = new StatusBarItem("SOURIS ARRIVES","0");
		c.gridx++;
		this.add(numberOfMouseArrived, c);
	}
	
	public StatusBarItem getNumberOfTurn() {
		return numberOfTurn;
	}
	
	public StatusBarItem getNumberOfTrips() {
		return numberOfTrips;
	}
	
	public StatusBarItem getNumberOfMouseMoving() {
		return numberOfMouseMoving;
	}
	
	public StatusBarItem getNumberOfMouseArrived() {
		return numberOfMouseArrived;
	}
}
