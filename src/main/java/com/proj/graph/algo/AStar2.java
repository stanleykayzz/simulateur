package main.java.com.proj.graph.algo;

import java.util.LinkedList;
import java.util.List;
import main.java.com.proj.core.cell.Cell;
import main.java.com.proj.graph.impl.Graph;

public class AStar2{
	
    protected static boolean CANMOVEDIAGONALY = false;
    protected int width;
    protected int height;
    private Cell nodeFactory;
	
	//un graphe a des noeuds
	//un graphe est un tableau bi-dimensionnel qui contient des nodes
	 Cell[][] node;
	
	public AStar2(int x, int y) {
		this.width =x-1;
		this.height = y-1;
		this.node = new Cell[x][y];
	}
	
	public AStar2(int width, int higth, Cell nodeFactory) {
        this.nodeFactory = nodeFactory;        
        node = new Cell[width][higth];
        this.width = width - 1;
        this.height = higth - 1;
        initEmptyNodes();
    }
	
	private void initEmptyNodes() {
        for (int i = 0; i <= width; i++) {
            for (int j = 0; j <= height; j++) {
                node[i][j] = new Cell(i, j);
            }
        }
    }
	
	public void setWalkable(int x, int y, boolean bool) {
        node[x][y].setWalkable(bool);
    }
	
	public final Cell getNode(int x, int y) {
        return node[x][y];
    }

	/** list containing nodes not visited but adjacent to visited nodes. */
    private List<Cell> openList;
    /** list containing nodes already visited/taken care of. */
    private List<Cell> closedList;
    /** done finding path? */
    private boolean done = false;
    
    public final List<Cell> findPath(int oldX, int oldY, int newX, int newY) {
        openList = new LinkedList<Cell>();
        closedList = new LinkedList<Cell>();
        openList.add(node[oldX][oldY]); // add starting node to open list

        done = false;
        Cell current;
        while (!done) {
            current = lowestFInOpen(); // get node with lowest fCosts from openList
            closedList.add(current); // add current node to closed list
            openList.remove(current); // delete current node from open list

            if ((current.getX() == newX)
                    && (current.getY() == newY)) { // found goal
                return calcPath(node[oldX][oldY], current);
            }

            // for all adjacent nodes:
            List<Cell> adjacentNodes = getAdjacent(current);
            for (int i = 0; i < adjacentNodes.size(); i++) {
            	Cell currentAdj = adjacentNodes.get(i);
            	if(currentAdj.getWalkable() == false || closedList.contains(currentAdj)) continue;
                if (!openList.contains(currentAdj)) { // node is not in openList
                    currentAdj.setParent(current); // set current node as previous for this node
                    currentAdj.setH(node[newX][newY]); // set h costs of this node (estimated costs to goal)
                    currentAdj.setgCosts(current); // set g costs of this node (costs from start to this node)
                    openList.add(currentAdj); // add node to openList
                } else { // node is in openList
                    if (currentAdj.getG() > currentAdj.calculategCosts(current)) { // costs from current node are cheaper than previous costs
                        currentAdj.setParent(current); // set current node as previous for this node
                        currentAdj.setgCosts(current); // set g costs of this node (costs from start to this node)
                    }
                }
            }

            if (openList.isEmpty()) { // no path exists
                return new LinkedList<Cell>(); // return empty list
            }
        }
        return null; // unreachable
    }
    
    private List<Cell> calcPath(Cell start, Cell goal) {
        // TODO if invalid nodes are given (eg cannot find from
        // goal to start, this method will result in an infinite loop!)
           LinkedList<Cell> path = new LinkedList<Cell>();

           Cell curr = goal;
           boolean done = false;
           while (!done) {
               path.addFirst(curr);
               curr = curr.getParent();

               if (curr.equals(start)) {
                   done = true;
               }
           }
           return path;
       }

       private Cell lowestFInOpen() {
           // TODO currently, this is done by going through the whole openList!
    	   Cell cheapest = openList.get(0);
           for (int i = 0; i < openList.size(); i++) {
               if (openList.get(i).getF() < cheapest.getF()) {
                   cheapest = openList.get(i);
               }
           }
           return cheapest;
       }

       private List<Cell> getAdjacent(Cell node) {
           // TODO make loop
           int x = node.getX();
           int y = node.getY();
           List<Cell> adj = new LinkedList<Cell>();

           Cell temp;
           if (x > 0) {
               temp = this.getNode((x - 1), y);
               if (temp.getWalkable() && !closedList.contains(temp)) {
                   temp.setIsDiagonaly(false);
                   adj.add(temp);
               }
           }
           if (x < width) {
               temp = this.getNode((x + 1), y);
               if (temp.getWalkable() && !closedList.contains(temp)) {
                   temp.setIsDiagonaly(false);
                   adj.add(temp);
               }
           }
           if (y > 0) {
               temp = this.getNode(x, (y - 1));
               if (temp.getWalkable() && !closedList.contains(temp)) {
                   temp.setIsDiagonaly(false);
                   adj.add(temp);
               }
           }
           if (y < height) {
               temp = this.getNode(x, (y + 1));
               if (temp.getWalkable() && !closedList.contains(temp)) {
                   temp.setIsDiagonaly(false);
                   adj.add(temp);
               }
           }
           // add nodes that are diagonaly adjacent too:
           if (CANMOVEDIAGONALY) {
               if (x < width && y < height) {
                   temp = this.getNode((x + 1), (y + 1));
                   if (temp.getWalkable() && !closedList.contains(temp)) {
                       temp.setIsDiagonaly(true);
                       adj.add(temp);
                   }
               }

               if (x > 0 && y > 0) {
                   temp = this.getNode((x - 1), (y - 1));
                   if (temp.getWalkable() && !closedList.contains(temp)) {
                       temp.setIsDiagonaly(true);
                       adj.add(temp);
                   }
               }

               if (x > 0 && y < height) {
                   temp = this.getNode((x - 1), (y + 1));
                   if (temp.getWalkable() && !closedList.contains(temp)) {
                       temp.setIsDiagonaly(true);
                       adj.add(temp);
                   }
               }

               if (x < width && y > 0) {
                   temp = this.getNode((x + 1), (y - 1));
                   if (temp.getWalkable() && !closedList.contains(temp)) {
                       temp.setIsDiagonaly(true);
                       adj.add(temp);
                   }
               }
           }
           return adj;
       }
	
	public static void main(String[] args) {

		//Graph myMap = new Graph();	  
		//Graph g = new Graph();	    
		AStar2 a = new AStar2(3,3);	        

		//on veut un tableau de 3 lignes et 3 colonnes 
		//a.node= new Cell[3][3];
		a.node[0][0]= new Cell(0,0);
		a.node[0][1]= new Cell(0,1);
		a.node[0][2]= new Cell(0,2);
		a.node[1][0]= new Cell(1,0);
		a.node[1][1]= new Cell(1,1);
		a.node[1][2]= new Cell(1,2);
		a.node[2][0]= new Cell(2,0);
		a.node[2][1]= new Cell(2,1);
		//a.node[2][1].setWalkable(false);
		a.node[2][2]= new Cell(2,2);
		
	        List<Cell> path = a.findPath(0, 0, 2, 2);
	      //  if(path.get(5).getXValue() == 5 && path.get(5).getYValue() == 3)
	        	//
            System.out.println(path.size());
	        //myMap.setWalkable(5, 3, true);
	        for (int i = 0; i < path.size(); i++) {
	            System.out.print("(" + path.get(i).getX() + ", " + path.get(i).getY() + ") -> ");
	        }
	}
	

}
