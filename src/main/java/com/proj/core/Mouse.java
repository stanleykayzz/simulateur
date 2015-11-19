package main.java.com.proj.core;

import main.java.com.proj.core.cell.Cell;
import main.java.com.proj.core.land.Land;

public class Mouse extends Cell {
	public Mouse(int x, int y) {
		super(x, y, 'p');
	}
	
	public void move(Land land) {
		System.out.println(this.toString());
	}
}
