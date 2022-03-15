package search_algorithms;

import java.util.LinkedList;
import java.util.List;

import graph_utils.Edge;
import graph_utils.Graph;
import graph_utils.Node;

public class Astar extends SearchAlgorithm{
	
	private List<Edge> adjNodes;
	private List<Node> nodePath;
	private List<Node> bestPath;
	private Node nextNode;
	private int f;
	private int nextCost;

	public Astar(Graph graph) {
		super(graph);
		nodePath = new LinkedList<Node>();
		bestPath = new LinkedList<Node>();
	}

	@Override
	public List<Node> start(Node n_initial, Node n_final) {
		adjNodes = adjacencyOfNode(n_initial);
		
		nodePath.add(n_initial);
		nextNode = n_initial;
		
		search(adjNodes, n_final);
		return bestPath;
	}
	
	public List<Node> search(List<Edge> adjNodes, Node n_final) {
		f = 99999999;
		if (adjNodes != null) {
 			if (bestPath.contains(nextNode)) {
 				bestPath.remove(nextNode);
 				nodePath.remove(nextNode);
 			}
 			else { 			
 				bestPath.add(nextNode);
 				
 				for (Edge edge : adjNodes) {
 					nextCost = edge.getCost() + edge.getN1().getHeuristic();
 			
 					if (nextCost < f) {
	 					nextNode = edge.getN1();
	 					f = nextCost;
	 				}
		 			nodePath.add(edge.getN1());
	 			}
 			}
 		}
		else 
			nodePath.remove(nextNode);
		
		System.out.println("Next node: " + nextNode.getLabel());
		
		if (nextNode == n_final) {
 			bestPath.add(nextNode);
 			return bestPath;
 		}

		search(adjacencyOfNode(nextNode),n_final);
		
		return null;
	}

}
