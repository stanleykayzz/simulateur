package main.java.com.proj.gui.optionsBar;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class OptionsBar extends JPanel{
	public StatusBar statusBar;
	public OptionsBar(){
		//this.setBackground(new Color(23,159,47));

		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;

		statusBar = new StatusBar();
		add(statusBar,c);
		ControlBar controlBar = new ControlBar();
		add(controlBar,c);
	}
}
