/*package main.java.com.proj.graph.algo;

public class test {
	
	package main.java.com.proj.graph.algo;

	import java.io.File;
	import java.io.FileReader;
	import java.util.ArrayList;
	import java.util.Collections;
	import java.util.LinkedList;
	import java.util.List;

	import org.w3c.dom.ls.LSInput;

	import main.java.com.proj.core.cell.Cell;
	import main.java.com.proj.core.cell.CellNode;
	import main.java.com.proj.graph.impl.GenericEdge;
	import main.java.com.proj.graph.impl.GenericNode;
	import main.java.com.proj.graph.impl.Graph;
	import main.java.com.proj.graph.interfaces.IEdge;
	import main.java.com.proj.graph.interfaces.INode;

	public class AStar2 {
		//Cell[][] node;
		int high = 19;
		int width = 48;
		Cell[][] node = new Cell[width][high];
		List <Cell> openList;
		List <Cell> closedList;
		boolean CANMOVEDIAGONALY = false;
		
		public void setWalkable(int x, int y, boolean bool) {
	        node[x][y].setWalkable(bool);
	    }
		
		public final Cell getNode(int x, int y) {
	        return node[x][y];
	    }

		//public final List<Cell> findPath(int oldX, int oldY, int newX, int newY) {
		public final List<Cell> findPath(Object source, Object destination) {
	        openList = new LinkedList<Cell>();
	        closedList = new LinkedList<Cell>();
	        openList.add(node[getCoordinatesX(source)][getCoordinatesY(source)]); // add starting node to open list

	        boolean done = false;
	        Cell current;
	        while (!done) {
	            current = lowestFInOpen(); // get node with lowest fCosts from openList
	            closedList.add(current); // add current node to closed list
	            openList.remove(current); // delete current node from open list

	            if ((current.getX() == getCoordinatesX(destination))
	                    && (current.getY() == getCoordinatesY(destination))) { // found goal
	                return calcPath(node[getCoordinatesX(source)][getCoordinatesY(source)], current);
	            }

	            // for all adjacent nodes:
	            List<Cell> adjacentNodes = getAdjacent(current);
	            for (int i = 0; i < adjacentNodes.size(); i++) {
	            	Cell currentAdj = adjacentNodes.get(i);
	            	//if(currentAdj.getWalkable() == false || closedList.contains(currentAdj)) continue;
	                if (!openList.contains(currentAdj)) { // node is not in openList
	                    currentAdj.setParent(current); // set current node as previous for this node
	                    currentAdj.setH(node[getCoordinatesX(destination)][getCoordinatesY(destination)]); // set h costs of this node (estimated costs to goal)
	                    currentAdj.setG(current); // set g costs of this node (costs from start to this node)
	                    openList.add(currentAdj); // add node to openList
	                } else { // node is in openList
	                    if (currentAdj.getG() > currentAdj.calculategCosts(current)) { // costs from current node are cheaper than previous costs
	                        currentAdj.setParent(current); // set current node as previous for this node
	                        currentAdj.setG(current); // set g costs of this node (costs from start to this node)
	                    }
	                }
	            }

	            if (openList.isEmpty()) { // no path exists
	                return new LinkedList<Cell>(); // return empty list
	            }
	        }
	        return null; // unreachable
	    }
		
		private Cell lowestFInOpen() {
	        // traverse tout la liste ouverte pour récuperer le noeaud avec le F le plus petit
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
	        if (y < high) {
	            temp = this.getNode(x, (y + 1));
	            if (temp.getWalkable() && !closedList.contains(temp)) {
	                temp.setIsDiagonaly(false);
	                adj.add(temp);
	            }
	        }
	        // add nodes that are diagonaly adjacent too:
	        if (CANMOVEDIAGONALY) {
	            if (x < width && y < high) {
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

	            if (x > 0 && y < high) {
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
		
		private void setGraphCells(Graph<String, Object> g)
		{
			int x =0,y=0;
			
			List<GenericNode<String,Object>> l = g.getListOfNodes();
			
			for(GenericNode<String,Object> n : l)
			{
				x = getCoordinatesX(n.getId());
				y = getCoordinatesY(n.getId());
				System.out.println("xxxxxxxxxxxx");
				System.out.println("le grahe a un node de x = "+x);
				System.out.println("le grahe a un node de y = "+y);
				Cell newCell = new Cell(x,y);
				this.node[x][y] = newCell;
			}
		}
		//fonction qui permet de recupérer les coordonnées x et y sur une clée String
		private int getCoordinatesX(Object key)
		{
			String k = (String) key;
			int m=0;
			String [] str = new String[2];
			for(String  c : k.split(","))
			{
				str[m] = c;
				m++;
			}
			System.out.println(" on a x = "+Integer.parseInt(str[0]));
			
			return Integer.parseInt(str[0]);
		}
		private int getCoordinatesY(Object key)
		{
			String k = (String) key;
			int m=0;
			String [] str = new String[2];
			for(String  c : k.split(","))
			{
				str[m] = c;
				m++;
			}
			System.out.println(" on a y = "+Integer.parseInt(str[1]));

			return Integer.parseInt(str[1]);
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
		
	public static void main(String[] args) {
		
		System.out.println("A*...");
		Graph<String, Object> graph = new Graph<>();
		GenericNode<String, Object> nodeA = new GenericNode<>("0,0");
		GenericNode<String, Object> nodeB = new GenericNode<>("0,1");
		GenericNode<String, Object> nodeC = new GenericNode<>("0,2");
		GenericNode<String, Object> nodeD = new GenericNode<>("1,0");
		GenericNode<String, Object> nodeE = new GenericNode<>("1,1");
		GenericNode<String, Object> nodeF = new GenericNode<>("1,2");
		GenericNode<String, Object> nodeG = new GenericNode<>("2,0");
		GenericNode<String, Object> nodeH = new GenericNode<>("2,1");
		GenericNode<String, Object> nodeI = new GenericNode<>("2,2");	
		
		graph.registerNode(nodeA);
		graph.registerNode(nodeB);
		graph.registerNode(nodeC);
		graph.registerNode(nodeD);
		graph.registerNode(nodeE);
		graph.registerNode(nodeF);
		graph.registerNode(nodeG);
		graph.registerNode(nodeH);
		graph.registerNode(nodeI);
		
		
		GenericEdge<String, Object> edgeDA = new GenericEdge<>(nodeD, nodeA);
		edgeIA.setAttribute("cost", 2);
		GenericEdge<String, Object> edgeAD = new GenericEdge<>(nodeA, nodeD);
		edgeIA.setAttribute("cost", 2);
		GenericEdge<String, Object> edgeAB = new GenericEdge<>(nodeA, nodeB);
		edgeIG.setAttribute("cost", 8);
		GenericEdge<String, Object> edgeBA = new GenericEdge<>(nodeB, nodeA);
		edgeIB.setAttribute("cost",3);
		GenericEdge<String, Object> edgeAD = new GenericEdge<>(nodeA, nodeD);
		edgeAD.setAttribute("cost", 9);		
		GenericEdge<String, Object> edgeAG = new GenericEdge<>(nodeA, nodeG);
		edgeAG.setAttribute("cost", 5);
		GenericEdge<String, Object> edgeBC = new GenericEdge<>(nodeB, nodeC);
		edgeBC.setAttribute("cost", 7);
		GenericEdge<String, Object> edgeBG = new GenericEdge<>(nodeB, nodeG);
		edgeBG.setAttribute("cost", 6);
		GenericEdge<String, Object> edgeGD = new GenericEdge<>(nodeG, nodeD);
		edgeGD.setAttribute("cost", 2);
		GenericEdge<String, Object> edgeGC = new GenericEdge<>(nodeG, nodeC);
		edgeGC.setAttribute("cost", 1);
		
		GenericEdge<String, Object> edgeDC = new GenericEdge<>(nodeD, nodeC);
		edgeDC.setAttribute("cost", 2);
		GenericEdge<String, Object> edgeDE = new GenericEdge<>(nodeD, nodeE);
		edgeDE.setAttribute("cost", 7);
		GenericEdge<String, Object> edgeDH = new GenericEdge<>(nodeD, nodeH);
		edgeDH.setAttribute("cost", 4);
		GenericEdge<String, Object> edgeCF = new GenericEdge<>(nodeC, nodeF);
		edgeCF.setAttribute("cost", 8);		
		GenericEdge<String, Object> edgeCH = new GenericEdge<>(nodeC, nodeH);
		edgeCH.setAttribute("cost", 3);
		GenericEdge<String, Object> edgeHE = new GenericEdge<>(nodeH, nodeE);
		edgeHE.setAttribute("cost", 3);
		GenericEdge<String, Object> edgeHJ = new GenericEdge<>(nodeH, nodeJ);
		edgeHJ.setAttribute("cost", 9);
		GenericEdge<String, Object> edgeHF = new GenericEdge<>(nodeH, nodeF);
		edgeHF.setAttribute("cost", 5);
		GenericEdge<String, Object> edgeEJ = new GenericEdge<>(nodeE, nodeJ);
		edgeEJ.setAttribute("cost", 6);
		
		GenericEdge<String, Object> edgeFJ = new GenericEdge<>(nodeF, nodeJ);
		edgeFJ.setAttribute("cost", 4);

		Dijsktra dijsktra = new Dijsktra();
		dijsktra.setGraph(graph);
		
		dijsktra.findShortestPath("I", "J");
		AStar2 astar = new AStar2();
		astar.setGraphCells(graph);
		Object depart ="0,0";
		Object arrive ="2,2";
		List<Cell> result =	astar.findPath(depart,arrive);

		
		for(Cell c : result)
		{
			System.out.println("["+c.getX()+","+c.getY()+"] ->");
		}

		System.out.println("End");
			
			Graph myMap = new Graph(48, 19, new GenericNode());
		        
		            System.out.println("(" + g.node[0][0].toString());
		        //List<GenericNode> path = myMap.findPath(0, 0, 5, 5);
		        List<GenericNode> path = g.findPath(0, 0, 5, 5);
		      //  if(path.get(5).getXValue() == 5 && path.get(5).getYValue() == 3)
		        	//
		        myMap.setWalkable(5, 3, true);
		        for (int i = 0; i < path.size(); i++) {
		            System.out.print("(" + path.get(i).getXValue() + ", " + path.get(i).getYValue() + ") -> ");
		        }
		}
	}
	}


}
*/