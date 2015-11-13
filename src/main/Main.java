package main;

import main.java.com.proj.gui.MainWindow;
import main.java.com.proj.utils.Constants;

@SuppressWarnings("unused")
public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println("Simulateur de foule");
		MainWindow mw = new MainWindow(Constants.PATH_MAP+"map.txt");
	}
}