package main.java.com.proj.gui.optionsBar;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.java.com.proj.gui.components.ControlBarItem;

public class ControlBar extends JPanel {
	private ControlBarItem numberOfMouseDoorOne;
	private ControlBarItem numberOfMouseDoorTwo;
	private ControlBarItem speed;
	JButton launchButton;
	
	public ControlBar() {
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(131, 104, 65));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 1;
		
		numberOfMouseDoorOne = new ControlBarItem("PORTE 1","0");
		c.gridx = 1;
		this.add(numberOfMouseDoorOne, c);
		
		numberOfMouseDoorTwo = new ControlBarItem("PORTE 2","0");
		c.gridx++;
		this.add(numberOfMouseDoorTwo, c);
		
		speed = new ControlBarItem("VITESSE","2000");
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
	
	public ControlBarItem getNumberOfMouseDoorOne() {
		return this.numberOfMouseDoorOne;
	}
	
	public ControlBarItem getNumberOfMouseDoorTwo() {
		return this.numberOfMouseDoorTwo;
	}
	
	public ControlBarItem getSpeed() {
		return this.speed;
	}
}