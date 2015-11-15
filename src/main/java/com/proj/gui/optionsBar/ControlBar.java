package main.java.com.proj.gui.optionsBar;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.java.com.proj.core.Simulator;
import main.java.com.proj.gui.components.OptionView;

public class ControlBar extends JPanel {
	private OptionView numberOfMouseDoorOne;
	private OptionView numberOfMouseDoorTwo;
	private OptionView speed;
	JButton launchButton;
	
	public ControlBar() {
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(131, 104, 65));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 1;
		
		numberOfMouseDoorOne = new OptionView("PORTE 1","0");
		c.gridx = 1;
		this.add(numberOfMouseDoorOne, c);
		
		numberOfMouseDoorTwo = new OptionView("PORTE 2","0");
		c.gridx++;
		this.add(numberOfMouseDoorTwo, c);
		
		speed = new OptionView("VITESSE","0");
		c.gridx++;
		this.add(speed, c);

		launchButton = new JButton("Lancer");
		launchButton.setBackground(new Color(70, 44, 21));
		launchButton.setForeground(new Color(255,255,255));
		c.gridx++;
		this.add(launchButton, c);
	}
	
	public JButton getLaunchButton(){
		return this.launchButton;
	}
	
	public OptionView getNumberOfMouseDoorOne() {
		return this.numberOfMouseDoorOne;
	}
	
	public OptionView getNumberOfMouseDoorTwo() {
		return this.numberOfMouseDoorTwo;
	}
	
	public OptionView getSpeed() {
		return this.speed;
	}
}