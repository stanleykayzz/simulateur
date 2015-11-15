package main.java.com.proj.gui;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;

import main.java.com.proj.core.cell.Cell;
import main.java.com.proj.core.land.Land;
import main.java.com.proj.utils.Constants;

public class LandView extends Canvas{
	private Land land;
	private CellView cellView;
	
	public LandView(Land oneLand) {
		land = oneLand;
		cellView = new CellView();
		initCellViews();
	}
	
	private void initCellViews() {
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(land.getColumns()*Constants.IMAGE_SIZE, land.getRows()*Constants.IMAGE_SIZE);
	}
	
	@Override
	public void paint(Graphics g) {
		for (int i=0; i<land.getRows(); i++) {
			for (int j=0; j<land.getColumns(); j++) {
				Cell cell = land.get(i, j);
				g.drawImage(cellView.getImageIcon(cell.getNature()).getImage(), cell.getY()*Constants.IMAGE_SIZE, cell.getX()*Constants.IMAGE_SIZE, null);
			}
		}
	}
}
