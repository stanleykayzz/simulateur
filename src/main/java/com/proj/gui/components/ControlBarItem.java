package main.java.com.proj.gui.components;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

public class ControlBarItem extends Box{
	private JLabel nameLabel;
	private JTextField valueTextField;
	
	public ControlBarItem(String name, String value) {
		super(BoxLayout.Y_AXIS);
		this.setOpaque(false);
		this.nameLabel = new JLabel();
		this.nameLabel.setForeground(new Color(255,255,250));
		this.valueTextField = new JTextField();
		this.valueTextField.setForeground(new Color(0,0,0));
		

		this.add(nameLabel);
		this.add(valueTextField);
		this.setName(name);
		this.setValue(value);
	}

	public void setName(String name){
		nameLabel.setText(name + " :");
	}
	
	public void setValue(String value){
		valueTextField.setText(value);
	}
	
	public JTextField getValue() {
		return this.valueTextField;
	}
}
