package search_algorithms;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import graph_utils.Edge;
import graph_utils.Graph;
import graph_utils.Node;

public class BreadthFirst extends SearchAlgorithm{

	private List<Edge> adjNodes;
	private List<Node> nodePath;
	private Node actualNode;
	
	Iterator<Node> nodeIter = null;  
	
	public BreadthFirst(Graph graph) {
		super(graph);
		nodePath = new LinkedList<Node>();
		nodeIter = nodePath.listIterator(); 
	}

	@Override
	public List<Node> start(Node n_initial, Node n_final) {
		nodePath.add(n_initial);
		
		while (nodeIter.hasNext()) {	
			actualNode = nodeIter.next(); 
			if (actualNode == n_final) {
	 			break;
	 		}
			
			adjNodes = adjacencyOfNode(actualNode);
			
			for (Edge edge : adjNodes) 
				nodePath.add(edge.getN1());
		}
		return nodePath;
	}
}
