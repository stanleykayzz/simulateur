package main.java.com.proj.graph.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.java.com.proj.graph.impl.GenericEdge;
import main.java.com.proj.graph.impl.GenericNode;
import main.java.com.proj.graph.impl.Graph;
import main.java.com.proj.graph.interfaces.IEdge;
import main.java.com.proj.graph.interfaces.INode;

public class Dijsktra<K,V>{
	Graph<K,V> graph = new Graph<>();
	List<Triplet> exploredNodes = new ArrayList<>();
	List<Triplet> closedNodes = new ArrayList<>();
	
	public void setGraph(Graph<K,V> graph) {
		this.graph = graph;
	}
	
	public ArrayList<K> findShortestPath(K startNodeId, K endNodeId) {
		exploredNodes.add(new Triplet(graph.getNode(startNodeId), 0, null));
		while(!exploredNodes.isEmpty()) {
			Collections.sort(exploredNodes);
			printExploredNodes();
			Triplet tripletOfLowestCostSum = exploredNodes.remove(0);
			////System.out.println("Triplet of lowest cost sum: "+tripletOfLowestCostSum.toString());
			closedNodes.add(tripletOfLowestCostSum);
			printClosedNodes();
			if (tripletOfLowestCostSum.node.equals(graph.getNode(endNodeId))) {
				break;
			}
			for (IEdge<K,V> edge : tripletOfLowestCostSum.node.getEdges()) {
				INode<K,V> neighbor = edge.getOther(tripletOfLowestCostSum.node);
				if (findTripletByNode(neighbor) != null ) {
					////System.out.println("Already in closedNodes:"+neighbor.getId());
					continue;
				}
				int costSum = tripletOfLowestCostSum.costSum + ((Integer) edge.getAttribute("cost")).intValue();
				Triplet tempTriplet = new Triplet(neighbor, costSum, tripletOfLowestCostSum.node);
				boolean tempFound = false;
				for (int i=0; i<exploredNodes.size(); i++) {
					if (exploredNodes.get(i).node.getId().equals(tempTriplet.node.getId())) {
						tempFound = true;
						if (exploredNodes.get(i).compareTo(tempTriplet) > 0) {
							exploredNodes.set(i, tempTriplet);
							break;
						}
					}
				}
				if (!tempFound) {
					exploredNodes.add(tempTriplet);
				}
			}
		}
		ArrayList<K> path = buildPath();
		System.out.println(path);
		return path; 
	}

	public void printExploredNodes(){
		List<Object> nodes = new ArrayList<>();
		for( Triplet triplet : exploredNodes){
			nodes.add(triplet.node.getId());
		}
		//System.out.println("Explored Nodes: "+nodes);
	}

	public void printClosedNodes(){
		List<K> nodes = new ArrayList<>();
		for( Triplet triplet : closedNodes){
			nodes.add(triplet.node.getId());
		}
		//System.out.println("Closed Nodes: "+nodes);
	}
	
	public ArrayList<K> getListString() {
		ArrayList<K> nodes = new ArrayList<>();
		for( Triplet triplet : closedNodes){
			nodes.add(triplet.node.getId());
		}
		return nodes;
	}
	
	public ArrayList<K> buildPath() {
		//System.out.println("ShowPath:");
		ArrayList<K> path = new ArrayList<>();
		Triplet lastTriplet = closedNodes.get(closedNodes.size()-1);
		path.add(lastTriplet.node.getId());
		INode<K,V> parent = lastTriplet.parent;
		while (lastTriplet!=null && parent!=null){
			lastTriplet = findTripletByNode(parent);
			path.add(lastTriplet.node.getId());
			parent = lastTriplet.parent;
		}
		Collections.reverse(path);
		System.out.println(path);
		return path;
	}
	
	public Triplet findTripletByNode(INode<K,V> node) {
		for (Triplet triplet : closedNodes) {
			if (triplet.node!=null && triplet.node.getId().equals(node.getId())) {
				return triplet;
			}
		}
		return null;
	}

	public class Triplet implements Comparable<Triplet> {
		INode<K,V> node;
		int costSum;
		INode<K,V> parent;
		
		public Triplet(INode<K,V> genericNode, int costSum, INode<K,V> nodeParent) {
			this.node = genericNode;
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
		
		public String toString() {
			return String.format("{%s;%d;%s}", 
					node == null ? "" : node.getId(),
					costSum,
					parent == null ? "" : parent.getId());
		}
	}
	
	public static void main(String[] args) {
		//System.out.println("Dijsktra...");
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

		Dijsktra<String, Object> dijsktra = new Dijsktra<>();
		dijsktra.setGraph(graph);
		
		dijsktra.findShortestPath("I", "J");
		
		//System.out.println("End");
	}
}