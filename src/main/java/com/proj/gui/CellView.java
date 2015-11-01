package main.java.com.proj.gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import main.java.com.proj.core.cell.Cell;
import main.java.com.proj.utils.Constants;

public class CellView extends JLabel{
	Cell cell;
	ImageIcon imageIcon;
	
	public CellView(Cell oneCell) {
		super();
		this.cell = oneCell;
		setImageIcon();
		this.setIcon(imageIcon);;
	}
	
	private void setImageIcon() {
		if (cell.getNature() == 'G') {
			imageIcon = new ImageIcon(Constants.GRASS);
			return;
		}
		if (cell.getNature() == '*') {
			imageIcon = new ImageIcon(Constants.WALL);
			return;
		}
		if (cell.getNature() == 'A') {
			imageIcon = new ImageIcon(Constants.CHEESE);
			return;
		}
		if (cell.getNature() == 'D') {
			imageIcon = new ImageIcon(Constants.DOOR);
			return;
		}
		if (cell.getNature() == ' ' || cell.getNature() == 'P') {
			imageIcon = new ImageIcon(Constants.FLOOR);
			return;
		}
	}
}
