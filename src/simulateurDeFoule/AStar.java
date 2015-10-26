package simulateurDeFoule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AStar {
	Graph<String, Object> graph;
	List<Quadruplet> exploredNodes;
	List<Quadruplet> closedNodes;
	int heuristic;
	CellNode finalNode;
	
	public AStar(int heuritic, int targetPosX, int targetPosY) {
		 graph = new Graph<>();
		 exploredNodes = new ArrayList<>();
		 closedNodes = new ArrayList<>();
		 heuristic = 2;
		 finalNode = new CellNode(""+targetPosX+""+targetPosY, new Cell(targetPosX, targetPosY, ""));
	}
	
	public void setGraph(Graph<String, Object> graph) {
		this.graph = graph;
	}
	
	public double getHeuristic(int currentX, int currentY) {
		return Math.sqrt(Math.pow(currentX-finalNode.getValue().getX(), 2) + Math.pow(currentY-finalNode.getValue().getY(), 2));
	}
	
	public void findShortestPath(String startNodeId, String endNodeId) {
		exploredNodes.add(new Quadruplet(graph.getNode(startNodeId), 0, null, getHeuristic(0,0)));
		while(!exploredNodes.isEmpty()) {
			Collections.sort(exploredNodes);
			printExploredNodes();
			Quadruplet quadrupletOfLowestCostSum = exploredNodes.remove(0);
			System.out.println("Quadruplet of lowest cost sum: "+quadrupletOfLowestCostSum.toString());
			closedNodes.add(quadrupletOfLowestCostSum);
			printClosedNodes();
			
			if (quadrupletOfLowestCostSum.node.equals(graph.getNode(endNodeId))) {
				showPath();
				break;
			}
			
			for (IEdge<String, Object> edge : quadrupletOfLowestCostSum.node.getEdges()) {
				INode<String, Object> neighbor = edge.getOther(quadrupletOfLowestCostSum.node);
				if (findQuadrupletByNode(neighbor) != null ) {
					//System.out.println("Already in closedNodes:"+neighbor.getId());
					continue;
				}
				int costSum = quadrupletOfLowestCostSum.costSum + ((Integer) edge.getAttribute("cost")).intValue();
				
				double estimateOfTheCostOfOptimalPath = costSum + quadrupletOfLowestCostSum.estimateOfTheCostOfOptimalPath;
				
				Quadruplet tempQuadruplet = new Quadruplet(neighbor, costSum, quadrupletOfLowestCostSum.node, 
						estimateOfTheCostOfOptimalPath);
				
				boolean tempFound = false;
				for (int i=0; i<exploredNodes.size(); i++) {
					if (exploredNodes.get(i).node.getId().equals(tempQuadruplet.node.getId())) {
						tempFound = true;
						if (exploredNodes.get(i).compareTo(tempQuadruplet) > 0) {
							exploredNodes.set(i, tempQuadruplet);
							break;
						}
					}
				}
				if (!tempFound) {
					exploredNodes.add(tempQuadruplet);
				}
			}
		}
	}

	public class Quadruplet implements Comparable<Quadruplet> {
		INode<String, Object> node;
		int costSum;
		INode<String, Object> parent;
		double estimateOfTheCostOfOptimalPath;
		
		public Quadruplet(INode<String, Object> node, int costSum, INode<String, Object> nodeParent,
				double costOfOptimalPath) {
			this.node = node;
			this.costSum = costSum;
			this.parent = nodeParent;
			this.estimateOfTheCostOfOptimalPath = costOfOptimalPath;
		}
		
		@Override
		public int compareTo(Quadruplet o2) {
			if (this.estimateOfTheCostOfOptimalPath > o2.estimateOfTheCostOfOptimalPath) {
				return 1;
			} else if (this.estimateOfTheCostOfOptimalPath == o2.estimateOfTheCostOfOptimalPath) {
				return 0;
			}
			return -1;
		}
		
		public String toString() {
			return String.format("{%s;%d;%s}", 
					node == null ? "" : node.getId(),
					costSum,
					parent == null ? "" : parent.getId(),
					estimateOfTheCostOfOptimalPath);
		}
	}
	
	public Quadruplet findQuadrupletByNode(INode<String, Object> node) {
		for (Quadruplet quadruplet : closedNodes) {
			if (quadruplet.node!=null && quadruplet.node.getId().equals(node.getId())) {
				return quadruplet;
			}
		}
		return null;
	}

	public void printExploredNodes(){
		List<String> nodes = new ArrayList<>();
		for(Quadruplet quadruplet : exploredNodes){
			nodes.add(quadruplet.node.getId());
		}
		System.out.println("Explored Nodes: "+nodes);
	}

	public void printClosedNodes(){
		List<String> nodes = new ArrayList<>();
		for(Quadruplet quadruplet : closedNodes){
			nodes.add(quadruplet.node.getId());
		}
		System.out.println("Closed Nodes: "+nodes);
	}
	
	public void showPath() {
		System.out.println("ShowPath:");
		List<String> path = new ArrayList<>();
		Quadruplet lastTriplet = closedNodes.get(closedNodes.size()-1);
		path.add(lastTriplet.node.getId());
		INode<String, Object> parent = lastTriplet.parent;
		while (lastTriplet!=null && parent!=null){
			lastTriplet = findQuadrupletByNode(parent);
			path.add(lastTriplet.node.getId());
			parent = lastTriplet.parent;
		}
		Collections.reverse(path);
		System.out.println(path);
	}
	
	public static void main(String[] args) {
		System.out.println("AStar...");
		Graph<String, Object> graph = new Graph<>();
		GenericNode<String, Object> nodeA = new GenericNode<>("A");
		GenericNode<String, Object> nodeB = new GenericNode<>("B");
		GenericNode<String, Object> nodeC = new GenericNode<>("C");
		GenericNode<String, Object> nodeD = new GenericNode<>("D");
		GenericNode<String, Object> nodeE = new GenericNode<>("E");
		GenericNode<String, Object> nodeF = new GenericNode<>("F");
		GenericNode<String, Object> nodeG = new GenericNode<>("G");
		GenericNode<String, Object> nodeH = new GenericNode<>("H");
		GenericNode<String, Object> nodeI = new GenericNode<>("I");
		GenericNode<String, Object> nodeJ = new GenericNode<>("J");
		
		graph.registerNode(nodeA);
		graph.registerNode(nodeB);
		graph.registerNode(nodeC);
		graph.registerNode(nodeD);
		graph.registerNode(nodeE);
		graph.registerNode(nodeF);
		graph.registerNode(nodeG);
		graph.registerNode(nodeH);
		graph.registerNode(nodeI);
		graph.registerNode(nodeJ);
		
		GenericEdge<String, Object> edgeIA = new GenericEdge<>(nodeI, nodeA);
		edgeIA.setAttribute("cost", 2);
		GenericEdge<String, Object> edgeIG = new GenericEdge<>(nodeI, nodeG);
		edgeIG.setAttribute("cost", 8);
		GenericEdge<String, Object> edgeIB = new GenericEdge<>(nodeI, nodeB);
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
		
		System.out.println("End");
	}
}
