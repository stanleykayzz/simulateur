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
	
	public ControlBar(Simulator simulator) {
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(131, 104, 65));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 1;
		
		OptionView pv1 = new OptionView("PORTE 1","0");
		pv1.setValue("1");
		c.gridx = 1;
		this.add(pv1, c);
		
		OptionView pv2 = new OptionView("PORTE 2","0");
		pv2.setValue("2");
		c.gridx++;
		this.add(pv2, c);
		
		OptionView pv3 = new OptionView("VITESSE","0");
		c.gridx++;
		this.add(pv3, c);

		JButton pv4 = new JButton("Lancer");
		pv4.setBackground(new Color(70, 44, 21));
		pv4.setForeground(new Color(255,255,255));
		pv4.addActionListener(simulator);
		c.gridx++;
		this.add(pv4, c);
	}
}