package main.java.com.proj.gui.optionsBar;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import main.java.com.proj.core.Simulator;

public class OptionsBar extends JPanel {
	private StatusBar statusBar;
	
	public OptionsBar(Simulator simulator) {
		//this.setBackground(new Color(23,159,47));

		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;

		statusBar = new StatusBar();
		add(statusBar,c);
		ControlBar controlBar = new ControlBar(simulator);
		add(controlBar,c);
	}
	
	public StatusBar getStatusBar() {
		return statusBar;
	}
}
