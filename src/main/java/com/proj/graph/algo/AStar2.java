package main.java.com.proj.graph.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import main.java.com.proj.core.cell.Cell;
import main.java.com.proj.core.cell.CellNode;
import main.java.com.proj.graph.impl.GenericEdge;
import main.java.com.proj.graph.impl.GenericNode;
import main.java.com.proj.graph.impl.Graph;
import main.java.com.proj.graph.interfaces.IEdge;
import main.java.com.proj.graph.interfaces.INode;

public class AStar {
	Cell[][] node;
	int high = 19;
	int width = 48;
	List <Cell> openList;
	List <Cell> closedList;
	boolean CANMOVEDIAGONALY = false;
	
	public void setWalkable(int x, int y, boolean bool) {
        node[x][y].setWalkable(bool);
    }
	
	public final Cell getNode(int x, int y) {
        return node[x][y];
    }
	
	public final List<Cell> findPath(int oldX, int oldY, int newX, int newY) {
        openList = new LinkedList<Cell>();
        closedList = new LinkedList<Cell>();
        openList.add(node[oldX][oldY]); // add starting node to open list

        boolean done = false;
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
            	//if(currentAdj.getWalkable() == false || closedList.contains(currentAdj)) continue;
                if (!openList.contains(currentAdj)) { // node is not in openList
                    currentAdj.setParent(current); // set current node as previous for this node
                    currentAdj.setH(node[newX][newY]); // set h costs of this node (estimated costs to goal)
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
}
