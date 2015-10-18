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
			closedNodes.add(tripletOfLowestCostSum);
			if (tripletOfLowestCostSum.node.equals(graph.getNode(endNodeId))) {
				showPath();
				break;
			}
			for (IEdge<String, Object> edge : tripletOfLowestCostSum.node.getEdges()) {
				INode<String, Object> neighbor = edge.getOther(tripletOfLowestCostSum.node);
				if (closedNodes.contains(neighbor)) {
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
		
		graph.registerNode(nodeA);
		graph.registerNode(nodeB);
		graph.registerNode(nodeC);
		graph.registerNode(nodeD);
		graph.registerNode(nodeE);
		graph.registerNode(nodeF);
		
		GenericEdge<String, Object> edgeAB = new GenericEdge<>(nodeA, nodeB);
		edgeAB.setAttribute("cost", 1);
		GenericEdge<String, Object> edgeAC = new GenericEdge<>(nodeA, nodeC);
		edgeAC.setAttribute("cost", 10);
		GenericEdge<String, Object> edgeBD = new GenericEdge<>(nodeB, nodeD);
		edgeBD.setAttribute("cost", 4);
		GenericEdge<String, Object> edgeBC = new GenericEdge<>(nodeB, nodeC);
		edgeBC.setAttribute("cost", 3);		
		GenericEdge<String, Object> edgeCD = new GenericEdge<>(nodeC, nodeD);
		edgeCD.setAttribute("cost", 3);
		GenericEdge<String, Object> edgeCF = new GenericEdge<>(nodeC, nodeF);
		edgeCF.setAttribute("cost", 3);
		GenericEdge<String, Object> edgeCE = new GenericEdge<>(nodeC, nodeE);
		edgeCE.setAttribute("cost", 2);
		GenericEdge<String, Object> edgeDF = new GenericEdge<>(nodeD, nodeF);
		edgeDF.setAttribute("cost", 1);
		GenericEdge<String, Object> edgeEF = new GenericEdge<>(nodeE, nodeF);
		edgeEF.setAttribute("cost", 0);

		Dijsktra dijsktra = new Dijsktra();
		dijsktra.setGraph(graph);
		dijsktra.findShortestPath("A", "F");
		
		System.out.println("End");
	}
}