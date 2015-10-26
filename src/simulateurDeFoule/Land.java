package simulateurDeFoule;

import java.util.HashMap;
import java.util.Map;

public class Land {
	//TODO créer un fonction qui prend en paramètre un fichier avec des int colonne row
	//TODO parser ce fichier
	//TODO créer un graphe à la volet à partir des données récupérer
	//TODO stocker dans une variable d'instance les positions du noeud cible
	private Map<String, Cell> pLand;
	private int columns;
	private int rows;
	
	public Land(int x, int y){
		this.columns = x;
		this.rows = y;
		this.pLand = new HashMap<>();
		buildLand();
	}
	
	public void buildLand() {
		for (int i=1; i<=rows; i++) {
			for (int j=1; j<=columns; j++) {
				pLand.put(""+i+""+j, new Cell(i,j, " "));
			}
		}
	}
	
	public void showLand() {
		for (int i=1; i<=rows; i++) {
			for (int j=1; j<=columns; j++) {
				System.out.print(pLand.get(""+i+""+j)+"\t");
			}
			System.out.println("");
		}
	}
	
	private Graph<String, Cell> convertLandToGraph() {
		Graph<String, Cell> graph = new Graph<>();
		//on lie les noeuds horizontallement
		for (int i=1; i<=rows; i++) {
			for (int j=1; j<=columns; j++) {
				graph.registerNode(new CellNode(""+i+""+j, pLand.get(""+i+""+j)));
				if (j>1) {
					GenericEdge<String, Cell> edge = new GenericEdge<>(graph.getNode(""+i+""+j), graph.getNode(""+i+""+(j-1)));
					edge.setAttribute("cost", 1);
				}
			}
		}
		//on lie les noeuds verticallement
		for (int j=1; j<=columns; j++) {
			for (int i=1; i<=rows; i++) {
				if (i>1) {
					GenericEdge<String, Cell> edge = new GenericEdge<>(graph.getNode(""+i+""+j), graph.getNode(""+(i-1+""+j)));
					edge.setAttribute("cost", 1);
				}
			}
		}
		return graph;
	}
	
	public static void main(String[] args) {
		Land land = new Land(7, 7);
		land.showLand();
		//land.convertLandToGraph();
	}
}
