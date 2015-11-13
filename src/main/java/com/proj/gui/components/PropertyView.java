package main.java.com.proj.gui.components;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PropertyView extends JPanel {
	private JLabel nameLabel;
	private JLabel valueLabel;
	
	public PropertyView(String name, String value){
		this.nameLabel = new JLabel();
		this.nameLabel.setForeground(new Color(0,0,0));
		this.valueLabel = new JLabel();
		this.valueLabel.setForeground(new Color(0,0,0));
		
		this.add(nameLabel);
		this.add(valueLabel);
		this.setName(name);
		this.setValue(value);
		this.setOpaque(false);
	}
	
	public void setName(String name){
		nameLabel.setText(name + " :");
	}
	
	public void setValue(String value){
		valueLabel.setText(value);
	}
}
