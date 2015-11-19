package main.java.com.proj.gui;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import main.java.com.proj.core.Mouse;
import main.java.com.proj.core.cell.Cell;
import main.java.com.proj.core.land.Land;
import main.java.com.proj.utils.Constants;

public class LandView extends Canvas {
	private Land land;
	private ArrayList<Mouse> mice;  // Simulator class is responsible to update the positions of each mouse in this list.
	private ImageProvider cellView;
	private int width;
	private int height;
	// BufferedImage buffy; // this may be needed for further graphics optimizations
	
	public LandView(Land oneLand, ArrayList<Mouse> mice) {
		this.land = oneLand;
		this.cellView = new ImageProvider();
		this.mice = mice;

	    width = land.getColumns()*Constants.IMAGE_SIZE;
	    height = land.getRows()*Constants.IMAGE_SIZE;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(this.width, this.height);
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics g2 = g;
		try {
			g2 = this.getBufferStrategy().getDrawGraphics();
			//g = buffy.getGraphics(); 			// this may be needed for further graphics optimizations
			render(g2);
			// g2.drawImage(buffy,0,0,null);	// this may be needed for further graphics optimizations
		} finally {
			g2.dispose();
		}		
				
		this.getBufferStrategy().show();
	}
	
	public void render(Graphics g) {
		renderLand(g);
		renderMice(g);
	}
	
	public void renderLand(Graphics g) {
		for (int i=0; i<land.getRows(); i++) {
			for (int j=0; j<land.getColumns(); j++) {
				Cell cell = land.getCell(i, j);
				int dx = cell.getY()*Constants.IMAGE_SIZE;
				int dy = cell.getX()*Constants.IMAGE_SIZE;

				g.drawImage(cellView.getImageIcon(cell.getNature()).getImage(), 
						dx, dy , null);
			}
		}
	}
	
	public void renderMice(Graphics g) {
		for(Mouse mouse : mice) {
			int dx = mouse.getPosition().j*Constants.IMAGE_SIZE;
			int dy = mouse.getPosition().i*Constants.IMAGE_SIZE;
			g.drawImage(cellView.getImageIcon('P').getImage(), dx, dy, null);
		}
	}
}
