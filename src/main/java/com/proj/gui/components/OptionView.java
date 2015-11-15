package main.java.com.proj.gui.components;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OptionView extends JPanel{
	private JLabel nameLabel;
	private JTextField valueTextField;
	
	public OptionView(String name, String value) {
		this.setOpaque(false);
		this.nameLabel = new JLabel();
		this.nameLabel.setForeground(new Color(255,255,250));
		this.valueTextField = new JTextField();
		this.valueTextField.setForeground(new Color(0,0,0));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=0;
		this.add(nameLabel,c);
		this.add(valueTextField,c);
		this.setName(name);
		this.setValue(value);
	}

	public void setName(String name){
		nameLabel.setText(name + " :");
	}
	
	public void setValue(String value){
		valueTextField.setText(value);
	}
}
