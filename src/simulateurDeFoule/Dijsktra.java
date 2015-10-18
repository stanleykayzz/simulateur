package simulateurDeFoule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dijsktra {
	Graph<String, Object> graph = new Graph<>();
	List<Triplet> exploredNodes = new ArrayList<>();
	List<Triplet> closedNodes = new ArrayList<>();
	
	public void setGraph(Graph<String, Object> graph) {
		this.graph = graph;
	}
	
	public void findShortestPath(String startNodeId, String endNodeId) {
		exploredNodes.add(new Triplet(graph.getNode(startNodeId), 0, null));
		while(!exploredNodes.isEmpty()) {
			Collections.sort(exploredNodes);
			Triplet tripletOfLowestCostSum = exploredNodes.remove(0);
			System.out.println(tripletOfLowestCostSum.node.getId());
			closedNodes.add(tripletOfLowestCostSum);
			if (tripletOfLowestCostSum.node.equals(graph.getNode(endNodeId))) {
				showPath();
				break;
			}
			for (IEdge<String, Object> edge : tripletOfLowestCostSum.node.getEdges()) {
				INode<String, Object> neighbor = edge.getOther(tripletOfLowestCostSum.node);
				if (findTripletByNode(neighbor) != null ) {
					System.out.println("Already in closedNodes:"+neighbor.getId());
					continue;
				}
				int costSum = tripletOfLowestCostSum.costSum + ((Integer) edge.getAttribute("cost")).intValue();
				Triplet tempTriplet = new Triplet(neighbor, costSum, tripletOfLowestCostSum.node);
				boolean tempFound = false;
				for (Triplet triplet : exploredNodes) {
					if (triplet.node.getId().equals(tempTriplet.node.getId())) {
						if (triplet.compareTo(tempTriplet) > 0) {
							triplet = tempTriplet;
							break;
						}
						tempFound = true;
					}
				}
				if (!tempFound) {
					exploredNodes.add(tempTriplet);
				}
			}
		}
	}
	
	public void showPath() {
		System.out.println("ShowPath:");
		List<String> path = new ArrayList<>();
		Triplet lastTriplet = closedNodes.get(closedNodes.size()-1);
		path.add(lastTriplet.node.getId());
		INode<String, Object> parent = lastTriplet.parent;
		while (lastTriplet!=null && parent!=null){
			lastTriplet = findTripletByNode(parent);
			path.add(lastTriplet.node.getId());
			parent = lastTriplet.parent;
		}
		Collections.reverse(path);
		System.out.println(path);
	}
	
	public Triplet findTripletByNode(INode<String, Object> node) {
		for (Triplet triplet : closedNodes) {
			if (triplet.node!=null && triplet.node.getId().equals(node.getId())) {
				return triplet;
			}
		}
		return null;
	}

	public class Triplet implements Comparable<Triplet> {
		INode<String, Object> node;
		int costSum;
		INode<String, Object> parent;
		
		public Triplet(INode<String, Object> node, int costSum, INode<String, Object> nodeParent) {
			this.node = node;
			this.costSum = costSum;
			this.parent = nodeParent;
		}
		
		@Override
		public int compareTo(Triplet o2) {
			if (this.costSum > o2.costSum) {
				return 1;
			} else if (this.costSum == o2.costSum) {
				return 0;
			}
			return -1;
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Dijsktra...");
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