package simulateurDeFoule;

import java.awt.Button;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("rawtypes")
public class Field extends Graph {

	private File map = new File("src/res/map.txt");
	private int numberOfChars;
	private JFrame fenetre;
	@SuppressWarnings("unused")
	private GridLayout grid1, grid2, grid3;
	private JPanel simulateur, options;
	private ImageIcon mur = new ImageIcon("src/res/mur.png");
	private ImageIcon sol = new ImageIcon("src/res/sol.png");
	private ImageIcon herbe = new ImageIcon("src/res/herbe.png");
	private ImageIcon porte = new ImageIcon("src/res/porte.png");
	private ImageIcon fromage = new ImageIcon("src/res/fromage.png");

	@SuppressWarnings("unchecked")
	public Field() {
		// initField();
		int s = 19;
		int v = 48;
		this.fenetre = new JFrame("simulateur 2");
		this.fenetre.setLayout(new GridLayout(s, v));
		GenericNode graphN = new GenericNode();
		JLabel[][] grid = new JLabel[s][v];

		this.numberOfChars = 0;
		FileReader reader = null;
		try {
			reader = new FileReader(this.map);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int character;

		try {
			for (int i = 0; i < 19; i++) {
				for (int j = 0; j < 48; j++) {
					while ((character = reader.read()) != -1) {
						numberOfChars++;
						if ((char) character == '*')
							this.fenetre
									.add(grid[i][j] = graphN.contenu = new JLabel(
											mur));
						else if ((char) character == ' ')
							this.fenetre
									.add(grid[i][j] = graphN.contenu = new JLabel(
											sol));
						else if ((char) character == 'G')
							this.fenetre
									.add(grid[i][j] = graphN.contenu = new JLabel(
											herbe));
						else if ((char) character == 'A')
							this.fenetre
									.add(grid[i][j] = graphN.contenu = new JLabel(
											fromage));
						else if ((char) character == 'D')
							this.fenetre
									.add(grid[i][j] = graphN.contenu = new JLabel(
											porte));
						else {
							char vv = (char) character;
							this.fenetre.add(grid[i][j] = new JLabel("" + vv));
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.fenetre.setVisible(true);
	}

	/**
	 * La fonction initField() permet d'initialiser le terrain il est pr�f�rable
	 * d'utiliser une fonction car � chaque appel de celle-ci on cr�e le terrain
	 * m�me si certain composants ont �t� modifi� comme par exemeple une souris
	 * qui se d�place
	 * */
	@SuppressWarnings("unused")
	private void initField() {
		// On met en place les composant de la fenetre
		this.fenetre = new JFrame(" Simulateur");
		this.grid1 = new GridLayout(2, 1);
		fenetre.setLayout(grid1);
		simulateur = new JPanel();
		options = new JPanel();
		fenetre.add(simulateur);
		fenetre.add(options);

		// pour le simulateur , on ajoute un grid 19 x 48
		simulateur.setLayout(new GridLayout(19, 48));
		FileParser(this.map);

		// on met une coleur pourri dans option pour le distinguer
		options.setLayout(new GridLayout(1, 2));
		options.add(new Button("OPTION"));
		options.add(new Button("OPTION2"));

		fenetre.setSize(500, 500);
		fenetre.setVisible(true);
	}

	private void FileParser(File f) {
		f = this.map;
		this.numberOfChars = 0;
		FileReader reader = null;
		try {
			reader = new FileReader(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int character;

		try {
			while ((character = reader.read()) != -1) {
				numberOfChars++;
				System.out.print(numberOfChars);
				if ((char) character == '*')
					simulateur.add(new JLabel(mur));
				else if ((char) character == ' ')
					simulateur.add(new JLabel(sol));
				else if ((char) character == 'G')
					simulateur.add(new JLabel(herbe));
				else if ((char) character == 'A')
					simulateur.add(new JLabel(fromage));
				else if ((char) character == 'D')
					simulateur.add(new JLabel(porte));
				else {
					char v = (char) character;
					simulateur.add(new JLabel("" + v));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
