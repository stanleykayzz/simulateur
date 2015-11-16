package main.java.com.proj.gui;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.java.com.proj.core.cell.Cell;
import main.java.com.proj.core.land.Land;
import main.java.com.proj.utils.Constants;

public class LandView extends Canvas{
	private Land land;
	//private ArrayList<Mouse> mice;  // Simulator class is responsible to update the positions of each mouse in this list.
	private CellView cellView;
	// BufferedImage buffy; // this may be needed for further graphics optimizations
	
	public LandView(Land oneLand) {
		land = oneLand;
		cellView = new CellView();
		initCellViews();

	    int width = land.getColumns()*Constants.IMAGE_SIZE;
	    int height = land.getRows()*Constants.IMAGE_SIZE;
	    
		/* This may be needed for further graphics optimizations
	     * It allows to create a BufferedImage adapted to the screen device 
	     * in order to speed up the drawing on screen.
	     */
		//GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    //GraphicsDevice device = env.getDefaultScreenDevice();
	    //GraphicsConfiguration config = device.getDefaultConfiguration();
	    //buffy = config.createCompatibleImage(width, height, Transparency.TRANSLUCENT);
	    
	}
	
	private void initCellViews() {
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(land.getColumns()*Constants.IMAGE_SIZE, land.getRows()*Constants.IMAGE_SIZE);
	}
	
	@Override
	public void paint(Graphics g) {
		/*
		 * We paint the canvas using a BufferedStrategy.
		 * A BufferedStrategy manages several BufferedImages internally.
		 * The number of BufferedImages is set by the call of createBufferStrategy(int) method.
		 * The BufferedStrategy provides a Graphics instance associated with
		 * one of the internal BufferedImage. More precisely, getDrawGraphics()
		 * gives an instance of Graphics associated to a BufferedImage that is not currently shown.
		 * Then we use the graphics to draw and render what we have to (land and mice).
		 * Finally, we tell the bufferedStrategy to flip its internal BufferedImage
		 * in order to show the new BufferedImage we have drawn on.
		 * 
		 * This strategy allow to construct the new image in background
		 * while the previous image is still at the foreground. When the background
		 * image is ready it is directly shown to the foreground.
		 * 
		 * Indeed, this prevent us to draw the new image "publicly". Without this 
		 * strategy, we could see the image being constructed on the screen.
		 */
		Graphics g2;
		try{
			g = this.getBufferStrategy().getDrawGraphics();
			//g = buffy.getGraphics(); 			// this may be needed for further graphics optimizations
			render(g);
			// g2.drawImage(buffy,0,0,null);	// this may be needed for further graphics optimizations
		}finally{
			g.dispose();
		}		
				
		this.getBufferStrategy().show();
	}
	
	public void render(Graphics g){
		renderLand(g);
		renderMice(g);
	}
	
	public void renderLand(Graphics g){
		/*
		 * This method draws the image of each cell of the land.
		 * As the land is not supposed to dynamically change during a simulation
		 * (unless it is supposed to be a future feature)
		 * we could draw the land only once at the beginning in a BufferedImage and store
		 * this result. Then each time we have to draw the land we could directly draw the 
		 * stored BufferedImage.
		 */
		for (int i=0; i<land.getRows(); i++) {
			for (int j=0; j<land.getColumns(); j++) {
				Cell cell = land.get(i, j);
				int dx = cell.getY()*Constants.IMAGE_SIZE;
				int dy = cell.getX()*Constants.IMAGE_SIZE;

				g.drawImage(cellView.getImageIcon(cell.getNature()).getImage(), 
						dx, dy , null);
			}
		}
	}
	public void renderMice(Graphics g){
		/*
		 * This method draws the images of the mice based on their internal position.
		 * For each mouse of the list, it fetches the mouse coordinates,
		 * then get a image from CellView, then draw the image on the canvas.
		 */
		/*
		for(Mouse mouse : mice){
			int dx = mouse.getY()*Constants.IMAGE_SIZE;
			int dy = mouse.getX()*Constants.IMAGE_SIZE;
			g.drawImage(cellView.getImageIcon('P').getImage(), dx, dy, null);
		}
		*/
	}
}
