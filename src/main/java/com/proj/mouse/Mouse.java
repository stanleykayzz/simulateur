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
		/*Faire appel à l'algo A* qui renvoi une Arraylist*/
		//this.chemin = ??? ;
		/*this.lastPosition = cellule;
		this.position = cellule;
		cellule.setNature('P');
		cellule.setStatut(true);
	}*/
	
	public Mouse(/*int x, int y,*/ int id)
	{
		//on crée une cellule pour une souris et on lui donne une nature
		this.idMouse = id;
		/*this.posX = x;
		this.posY = y;*/
		this.position = new Cell();
		this.lastPosition = new Cell();
		//this.sabelette = new Cell(x,y,'P');
		this.sabelette = new Cell('P');
	}
	//on ajoute une souri dans le pLand à la position passé en paramètre et on récupère la cell à cette position
	public void addMouse(Land land, int px, int py)
	{
		//on récup la cell à la position en param
		this.lastPosition = land.get(px,py);
		//on remplace la cell à la position en param par la cellule de la souris
		land.getPland().put(""+px+""+py,this.sabelette);
	}
	
	private Cell getMouse() {
		return this.sabelette;
	}
	public ArrayList<Cell> getChemin(){
		return this.chemin;
	}

	//on crée une methode pour bouger la souris
	public void moveMouseTo(Land lan,int posX, int posY)
	{
		//quand on dépplace une souris c'est qu'elle existe et qu'elle a déjà une lastPosition
		//avant de deplacer on remet l'ancienne valeur où elle était
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
		//on crée une fenere
		JFrame fen = new JFrame("test souris");
		//on crée un land de 2 lignes et 2 colonnes
		Land l = Land.buildFromFile("src/main/res/m.txt");
		//test affiche land
		// ce qu'on veut faire:
		//créer un objet souris
		Mouse pika = new Mouse(/*2, 2,*/ 1);
		//donner l'image à la souris
		//ajouter la souris dans le land aux coordonées (1,1)
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
