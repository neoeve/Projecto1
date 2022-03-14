package search_algorithms;

import java.util.ListIterator;
import java.util.LinkedList;
import java.util.List;

import graph_utils.Edge;
import graph_utils.Graph;
import graph_utils.Node;

public class BreadthFirst extends SearchAlgorithm{

	private List<Edge> adjNodes;
	private List<Node> nodePath;
	private List<Node> bestPath;
	private Node nextNode;
	private Edge atualEdge;
	
	ListIterator<Edge> edgeIter = null;  
	
	public BreadthFirst(Graph graph) {
		super(graph);
		nodePath = new LinkedList<Node>(); 
		bestPath = new LinkedList<Node>(); 
	}

	@Override
	public List<Node> start(Node n_initial, Node n_final) {
		nodePath.add(n_initial);
		bestPath.add(n_initial);
		
		search(nodePath,n_final);

		return bestPath;
	}
	
	public List<Node> search(List<Node> nodes, Node n_final) {
		adjNodes = adjacencyOfNode(nextNode);
		
		if (adjNodes != null) {
			nextNode = adjNodes.get(0).getN1();	
			edgeIter = adjNodes.listIterator(adjNodes.size());
		
			while (edgeIter.hasPrevious()) {
				atualEdge = edgeIter.previous();
				
				if (atualEdge.getN1() == n_final) {
					bestPath.add(atualEdge.getN1());
		 			return bestPath;
		 		}
				nodePath.add(atualEdge.getN1());
			}	
			bestPath.add(nextNode);
		}
		else {
			nodePath.remove(nextNode);
			bestPath.remove(nextNode);
			nextNode = nodePath.get(nodePath.size()-1);
		}
		
 		//Chamada recursiva com o node expandido
		search(nodePath,n_final);
		
		return null;
	}
}
