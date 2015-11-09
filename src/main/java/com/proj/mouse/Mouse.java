package main.java.com.proj.mouse;

import java.awt.List;
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
	
	/* browseMyPath
	 * fonction qui prend en param une liste de cellule
	 * la souris parcour toutes les cellules de la liste
	*/
	public void browseMyPath(JFrame jf,Land terrain,ArrayList<Cell> yourPath) throws InterruptedException
	{
		//on r�cupere la taille de la liste
		int pathSize = yourPath.size();
		//on ajoute la souris � la premi�re cell
		this.addMouse(terrain, yourPath.get(0).getX(), yourPath.get(0).getY());
		LandView lv = new LandView(terrain);
		//on ajoute le landview a la fenetre et on le rend visible
		jf.add(lv);
		jf.setVisible(true);
		//on prend une seconde pour le mouvement suivant
		Thread.sleep(4000);
		for(int i=1;i<pathSize;i++)
		{
			this.moveMouseTo(terrain, yourPath.get(i).getX(), yourPath.get(i).getY());
			lv = new LandView(terrain);
			jf.add(lv);
			jf.setVisible(true);
			Thread.sleep(1000);
		}
	}
	
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
		
		//***********test 1 -- deplacer la souris le map
		//on cr�e une fenere
		JFrame fen = new JFrame("test souris");
		//on cr�e un land de 2 lignes et 2 colonnes
		Land l = Land.buildFromFile("src/main/res/m.txt");
		//test affiche land
		// ce qu'on veut faire:
		//cr�er un objet souris
		Mouse pika = new Mouse(/*2, 2,*/ 1);
		Mouse chu = new Mouse(/*2, 2,*/ 1);
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
		//LandView lv = new LandView(l);
		//on ajoute le landview a la fenetre et on le rend visible
		//fen.add(lv);
		//fen.setVisible(true);
		
		/*Thread.sleep(2000);
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
		fen.setVisible(true);*/
		
		//***************test 2 - d�placer la souris avec une liste de cellule a parcourir en param�tre
		//on cr�e une liste de c�lulle a parcourir 
		/*ArrayList<Cell> li = new ArrayList<Cell>();
		li.add(new Cell(1,1));
		li.add(new Cell(1,2));
		li.add(new Cell(1,3));
		li.add(new Cell(1,4));
		li.add(new Cell(1,5));
		li.add(new Cell(1,6));
		li.add(new Cell(1,7));
		li.add(new Cell(1,8));
		
		//on passe la liste en parametre � la fonction
		pika.browseMyPath(fen,l, li);*/
		
		//*****************test 3 - on essai de d�placer deux souris simultan�ment dans des directions oppos�es
		ArrayList<Cell> li = new ArrayList<Cell>();
		li.add(new Cell(1,1));
		li.add(new Cell(1,2));
		li.add(new Cell(1,3));
		li.add(new Cell(1,4));
		li.add(new Cell(1,5));
		li.add(new Cell(1,6));
		li.add(new Cell(1,7));
		li.add(new Cell(1,8));
		ArrayList<Cell> lu = new ArrayList<Cell>();
		lu.add(new Cell(2,8));
		lu.add(new Cell(2,7));
		lu.add(new Cell(2,6));
		lu.add(new Cell(2,5));
		lu.add(new Cell(2,4));
		lu.add(new Cell(2,3));
		lu.add(new Cell(2,2));
		lu.add(new Cell(2,1));
		
		//on passe la liste en parametre � la fonction
		pika.browseMyPath(fen,l, li);
		chu.browseMyPath(fen,l, lu);

	}

}
