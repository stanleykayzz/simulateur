package main.java.com.proj.gui.optionsBar;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import main.java.com.proj.gui.components.PropertyView;

public class StatusBar extends JPanel{
	private PropertyView attrTurn;
	private PropertyView attrMove;
	private PropertyView attrMovingMouse;
	private PropertyView attrMouseArrived;
	
	public StatusBar() {
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(179, 166, 125));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		
		attrTurn = new PropertyView("TOUR","0");
		attrTurn.setValue("1");
		c.gridx = 1;
		this.add(attrTurn, c);
		
		attrMove = new PropertyView("DEPLACEMENTS","0");
		attrMove.setValue("2");
		c.gridx++;
		this.add(attrMove, c);
		
		attrMovingMouse = new PropertyView("SOURIS EN DEPLACEMENTS","0");
		c.gridx++;
		this.add(attrMovingMouse, c);

		attrMouseArrived = new PropertyView("SOURIS ARRIVES","0");
		c.gridx++;
		this.add(attrMouseArrived, c);
	}
	
	public PropertyView getAttrTurn() {
		return attrTurn;
	}
	
	public PropertyView getAttrMove() {
		return attrMove;
	}
	
	public PropertyView getAttrMovingMouse() {
		return attrMovingMouse;
	}
	
	public PropertyView getAttrMouseArrived() {
		return attrMouseArrived;
	}
}
