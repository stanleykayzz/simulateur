package main.java.com.proj.gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import main.java.com.proj.core.cell.Cell;
import main.java.com.proj.utils.Constants;

public class CellView {
	Cell cell;
	ImageIcon imageIconGrass;
	ImageIcon imageIconWall;
	ImageIcon imageIconCheese;
	ImageIcon imageIconDoor;
	ImageIcon imageIconSoil;
	
	public CellView() {
		imageIconGrass = new ImageIcon(Constants.GRASS);
		imageIconWall = new ImageIcon(Constants.WALL);
		imageIconCheese = new ImageIcon(Constants.CHEESE);
		imageIconDoor = new ImageIcon(Constants.DOOR);
		imageIconSoil = new ImageIcon(Constants.SOIL);
	}
	
	public ImageIcon getImageIcon(char nature) {
		if (nature == 'G') {
			return imageIconGrass;
		}
		if (nature == '*') {
			return imageIconWall;
		}
		if (nature == 'A') {
			return imageIconCheese;
		}
		if (nature == 'D') {
			return imageIconDoor;
		}
		if (nature == ' ') {
			return imageIconSoil;
		}
		return new ImageIcon();
	}
}
