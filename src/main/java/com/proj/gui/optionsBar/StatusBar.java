package main.java.com.proj.gui.optionsBar;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import main.java.com.proj.gui.components.PropertyView;

public class StatusBar extends JPanel{
	private PropertyView attrNumberOfTurn;
	private PropertyView attrNumberOfTrips;
	private PropertyView attrNumberOfMouseMoving;
	private PropertyView attrNumberOfMouseArrived;
	
	public StatusBar() {
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(179, 166, 125));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		
		attrNumberOfTurn = new PropertyView("TOUR","0");
		c.gridx = 1;
		this.add(attrNumberOfTurn, c);
		
		attrNumberOfTrips = new PropertyView("DEPLACEMENTS","0");
		c.gridx++;
		this.add(attrNumberOfTrips, c);
		
		attrNumberOfMouseMoving = new PropertyView("SOURIS EN DEPLACEMENTS","0");
		c.gridx++;
		this.add(attrNumberOfMouseMoving, c);

		attrNumberOfMouseArrived = new PropertyView("SOURIS ARRIVES","0");
		c.gridx++;
		this.add(attrNumberOfMouseArrived, c);
	}
	
	public PropertyView getAttrTurn() {
		return attrNumberOfTurn;
	}
	
	public PropertyView getAttrMove() {
		return attrNumberOfTrips;
	}
	
	public PropertyView getAttrMovingMouse() {
		return attrNumberOfMouseMoving;
	}
	
	public PropertyView getAttrMouseArrived() {
		return attrNumberOfMouseArrived;
	}
}
