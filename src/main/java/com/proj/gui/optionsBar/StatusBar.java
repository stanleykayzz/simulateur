package main.java.com.proj.gui.optionsBar;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import main.java.com.proj.gui.components.PropertyView;

public class StatusBar extends JPanel{
	
	public PropertyView propertyTour;
	public StatusBar() {
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(179, 166, 125));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		
		propertyTour = new PropertyView("TOUR","0");
		propertyTour.setValue("1");
		c.gridx = 1;
		this.add(propertyTour, c);
		
		PropertyView pv2 = new PropertyView("DEPLACEMENTS","0");
		pv2.setValue("2");
		c.gridx++;
		this.add(pv2, c);
		
		PropertyView pv3 = new PropertyView("SOURIS EN DEPLACEMENTS","0");
		c.gridx++;
		this.add(pv3, c);

		PropertyView pv4 = new PropertyView("SOURIS ARRIVES","0");
		c.gridx++;
		this.add(pv4, c);
	}
}
