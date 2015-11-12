package main.java.com.proj.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.java.com.proj.core.cell.Cell;
import main.java.com.proj.core.land.Land;

public class LandView extends JPanel{
	private Land land;
	
	public LandView(Land oneLand) {
		land = oneLand;
		//this.setBackground(new Color(203, 0, 1));
		//System.out.println("LandView");
		this.setLayout(new GridBagLayout());
		initCellViews();
	}
	
	private void initCellViews() {
		GridBagConstraints c = new GridBagConstraints();
		for (int i=0; i<land.getRows(); i++) {
			for (int j=0; j<land.getColumns(); j++) {
				Cell cell = land.get(i, j);
				c.gridx = j;
				c.gridy = i;
				
				CellView cellView = new CellView(cell);
				this.add(cellView,c);
			}
		}
	}	
}
