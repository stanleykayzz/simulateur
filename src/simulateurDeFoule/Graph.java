package simulateurDeFoule;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph<K,V> extends GenericNode{
	
    protected static boolean CANMOVEDIAGONALY = true;
    protected int width;
    protected int higth;
   // private GenericNode nodeFactory;
	
	//un graphe a des noeuds
	private List<GenericNode> graphNode;
	private int longeur;
	private int largeur;
	//un graphe est un tableau bi-dimensionnel qui contient des nodes
	 GenericNode[][] node;

	 public Graph(int width, int higth, GenericNode nodeFactory) {
	     //   this.nodeFactory = nodeFactory;        
	        node = new GenericNode[width][higth];
	        this.width = width - 1;
	        this.higth = higth - 1;
	        initEmptyNodes();
	    }
	 public Graph(int width, int higth) {
	       // this.nodeFactory = new GenericNode();        
	        node = new GenericNode[width][higth];
	        this.width = width - 1;
	        this.higth = higth - 1;
	        initEmptyNodes();
	    }
	
	private void initEmptyNodes() {
        for (int i = 0; i <= width; i++) {
            for (int j = 0; j <= higth; j++) {
                node[i][j] = new GenericNode(i, j);
            }
        }
    }
	
	public void setWalkable(int x, int y, boolean bool) {
        node[x][y].setWalkable(bool);
    }
	
	public final GenericNode getNode(int x, int y) {
        return node[x][y];
    }

	/** list containing nodes not visited but adjacent to visited nodes. */
    private List<GenericNode> openList;
    /** list containing nodes already visited/taken care of. */
    private List<GenericNode> closedList;
    /** done finding path? */
    private boolean done = false;
    
    public final List<GenericNode> findPath(int oldX, int oldY, int newX, int newY) {
        openList = new LinkedList<GenericNode>();
        closedList = new LinkedList<GenericNode>();
        openList.add(node[oldX][oldY]); // add starting node to open list

        done = false;
        GenericNode current;
        while (!done) {
            current = lowestFInOpen(); // get node with lowest fCosts from openList
            closedList.add(current); // add current node to closed list
            openList.remove(current); // delete current node from open list

            if ((current.getXValue() == newX)
                    && (current.getYValue() == newY)) { // found goal
                return calcPath(node[oldX][oldY], current);
            }

            // for all adjacent nodes:
            List<GenericNode> adjacentNodes = getAdjacent(current);
            for (int i = 0; i < adjacentNodes.size(); i++) {
            	GenericNode currentAdj = adjacentNodes.get(i);
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
                return new LinkedList<GenericNode>(); // return empty list
            }
        }
        return null; // unreachable
    }
    
    private List<GenericNode> calcPath(GenericNode start, GenericNode goal) {
        // TODO if invalid nodes are given (eg cannot find from
        // goal to start, this method will result in an infinite loop!)
           LinkedList<GenericNode> path = new LinkedList<GenericNode>();

           GenericNode curr = goal;
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

       private GenericNode lowestFInOpen() {
           // TODO currently, this is done by going through the whole openList!
    	   GenericNode cheapest = openList.get(0);
           for (int i = 0; i < openList.size(); i++) {
               if (openList.get(i).getF() < cheapest.getF()) {
                   cheapest = openList.get(i);
               }
           }
           return cheapest;
       }

       private List<GenericNode> getAdjacent(GenericNode node) {
           // TODO make loop
           int x = node.getXValue();
           int y = node.getYValue();
           List<GenericNode> adj = new LinkedList<GenericNode>();

           GenericNode temp;
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
           if (y < higth) {
               temp = this.getNode(x, (y + 1));
               if (temp.getWalkable() && !closedList.contains(temp)) {
                   temp.setIsDiagonaly(false);
                   adj.add(temp);
               }
           }
           // add nodes that are diagonaly adjacent too:
           if (CANMOVEDIAGONALY) {
               if (x < width && y < higth) {
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

               if (x > 0 && y < higth) {
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

	public void registerNode(GenericNode node) {			
		this.graphNode.add((GenericNode)node);	
	}

	public void unregisterNode(K key) {
		this.graphNode.remove(key);		
	}
	
	public List<GenericNode> getListOfNodes()
	{
		return this.graphNode;
	}
	
	public void setLongueur(int i)
	{
		this.longeur = i;
	}
	
	public void setLargeur(int i)
	{
		this.largeur = i;
	}
	
	public int getLargeur()
	{
		return this.largeur;
	}
	
	public int getLongueur()
	{
		return this.longeur;
	}
	
	public GenericNode[][] setNode()
	{
		return this.node = new GenericNode[this.getLongueur()][this.getLargeur()];
	}
	
	public void setNodeInArray()
	{
		int numberOfChars =0;
        FileReader reader = null;
        try{
            reader = new FileReader(new File("src/res/map.txt"));
            int character;
                for(int i=0;i<48;i++)
                {
                    for(int j=0;j<19;j++)
                    {
                    	//tant que le fichier n'est pas vide
                        while ((character = reader.read()) != -1) {
                            numberOfChars++;
                            if((char)character == '*')
                                {
                            		this.node[i][j] = new GenericNode(i,j);
                            		node[i][j].setWalkable(false);
                            		node[i][j].setHeuristic(500);
                            		node[i][j].setChar('*');
                            		/*if(i==0 || j==0 || i==47 || j==18)
                            			g.node[i][j].setWalkable(false);
                            			g.node[i][j].setHeuristic(500);*/
                                }
                            else if((char)character == ' ')
                                {
                            	this.node[i][j] = new GenericNode(i,j);
                        		node[i][j].setChar(' ');
                                }
                            else if((char)character == 'G')
                                {
                            	this.node[i][j] = new GenericNode(i,j);
                        		node[i][j].setChar('G');
                                }
                            else if((char)character == 'A')
                                {
                            	this.node[i][j] = new GenericNode(i,j);
                        		node[i][j].setChar('A');
                                }
                            else if((char)character == 'D')
                                {
                            	this.node[i][j] = new GenericNode(i,j);
                        		node[i][j].setChar('D');
                                }
                            else
                                {
                                    char vv = (char) character;
                                    this.node[i][j] = new GenericNode(i,j);
                            		node[i][j].setChar(vv);
                                }
                        }
                    }
                }
        }catch(Exception e)
        {e.printStackTrace();}
	}
	
	public void setWall()
	{
		for(int i=0;i<48;i++)
		{
			for(int j=0;j<19;j++)
			{
				if(this.node[i][j].getChar() == '*')
					this.node[i][j].setWalkable(false);
			}
		}
	}
}
