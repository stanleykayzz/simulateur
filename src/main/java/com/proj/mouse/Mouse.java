package main.java.com.proj.mouse;

import java.util.ArrayList;

import javax.swing.JFrame;

import main.java.com.proj.core.cell.Cell;
import main.java.com.proj.core.land.Land;
import main.java.com.proj.gui.LandView;

public class Mouse /*extends Cell*/{
	
	private int idMouse;
	private ArrayList<Cell> chemin = new ArrayList<>();
	private Cell position, lastPosition;
	int posX,posY;
	private Cell sabelette;
	//test avec un jlabel
	//JLabel contenu = new JLabel("src/main/res/souri.png");
	
	/*public Mouse(int x, int y,int id, Cell cellule){
		super(x,y);
		this.idMouse = id;
		/*Faire appel � l'algo A* qui renvoi une Arraylist*/
		//this.chemin = ??? ;
		/*this.lastPosition = cellule;
		this.position = cellule;
		cellule.setNature('P');
		cellule.setStatut(true);
	}*/
	
	public Mouse(/*int x, int y,*/ int id)
	{
		//on cr�e une cellule pour une souris et on lui donne une nature
		this.idMouse = id;
		/*this.posX = x;
		this.posY = y;*/
		this.position = new Cell();
		this.lastPosition = new Cell();
		//this.sabelette = new Cell(x,y,'P');
		this.sabelette = new Cell('P');
	}
	//on ajoute une souri dans le pLand � la position pass� en param�tre et on r�cup�re la cell � cette position
	public void addMouse(Land land, int px, int py)
	{
		//on r�cup la cell � la position en param
		this.lastPosition = land.get(px,py);
		//on remplace la cell � la position en param par la cellule de la souris
		land.getPland().put(""+px+""+py,this.sabelette);
	}
	
	private Cell getMouse() {
		return this.sabelette;
	}
	public ArrayList<Cell> getChemin(){
		return this.chemin;
	}

	//on cr�e une methode pour bouger la souris
	public void moveMouseTo(Land lan,int posX, int posY)
	{
		//quand on d�pplace une souris c'est qu'elle existe et qu'elle a d�j� une lastPosition
		//avant de deplacer on remet l'ancienne valeur o� elle �tait
		lan.getPland().put(""+this.lastPosition.getX()+""+this.lastPosition.getY(),this.lastPosition);
		//lan.getPland().put(""+posX+""+posY,this.lastPosition);
		this.addMouse(lan, posX, posY);
	}
	
	public int getIdMouse(){
		return this.idMouse;
	}
	
	public void setPosition(Cell newCellule, Cell lastCellule){
		lastCellule.setNature(lastPosition.getNature());
		lastCellule.setStatut(false);
		this.lastPosition = newCellule;
		this.position = newCellule;
		newCellule.setNature('P');
		newCellule.setStatut(true);
	}
	
	public Cell getPosition(){
		return this.position;
	}
	public static void main(String[] args) throws Exception
	{
		//on cr�e une fenere
		JFrame fen = new JFrame("test souris");
		//on cr�e un land de 2 lignes et 2 colonnes
		Land l = Land.buildFromFile("src/main/res/m.txt");
		//test affiche land
		// ce qu'on veut faire:
		//cr�er un objet souris
		Mouse pika = new Mouse(/*2, 2,*/ 1);
		//donner l'image � la souris
		//ajouter la souris dans le land aux coordon�es (1,1)
		//pika.addMouse(l, 1, 1);
		//pika.addMouse(l, 1, 2);
		//pika.addMouse(l, 1, 2);
		//visualiser la souris dans le land
		//faire bouger la souris sur le landView
		//pika.moveMouseTo(l, 1, 3);
		//l.showLand(); //test ok
		//on veut afficher la land en graphique
		LandView lv = new LandView(l);
		//on ajoute le landview a la fenetre et on le rend visible
		fen.add(lv);
		fen.setVisible(true);
		
		Thread.sleep(2000);
		pika.addMouse(l, 1, 1);
		lv = new LandView(l);
		fen.add(lv);
		fen.setVisible(true);
		
		Thread.sleep(2000);
		pika.moveMouseTo(l, 1, 2);
		lv = new LandView(l);
		fen.add(lv);
		fen.setVisible(true);
		
		Thread.sleep(2000);
		pika.moveMouseTo(l, 1, 3);
		lv = new LandView(l);
		fen.add(lv);
		fen.setVisible(true);

	}

}
