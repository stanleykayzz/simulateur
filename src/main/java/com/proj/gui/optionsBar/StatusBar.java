package main.java.com.proj.gui.optionsBar;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import main.java.com.proj.gui.components.StatusBarItem;

public class StatusBar extends JPanel{
	private StatusBarItem turnNumber;
	private StatusBarItem numberOfTrips;
	private StatusBarItem numberOfMouseMoving;
	private StatusBarItem numberOfMouseArrived;
	
	public StatusBar() {
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(179, 166, 125));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		
		turnNumber = new StatusBarItem("TOUR","");
		c.gridx = 1;
		this.add(turnNumber, c);
		
		numberOfTrips = new StatusBarItem("DEPLACEMENTS","");
		c.gridx++;
		this.add(numberOfTrips, c);
		
		numberOfMouseMoving = new StatusBarItem("SOURIS EN DEPLACEMENTS","");
		c.gridx++;
		this.add(numberOfMouseMoving, c);

		numberOfMouseArrived = new StatusBarItem("SOURIS ARRIVES","");
		c.gridx++;
		this.add(numberOfMouseArrived, c);
	}
	
	public StatusBarItem getTurnNumber() {
		return turnNumber;
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
