package search_algorithms;

import java.util.LinkedList;
import java.util.List;

import graph_utils.Edge;
import graph_utils.Graph;
import graph_utils.Node;

public class DepthFirst extends SearchAlgorithm{

	private List<Edge> adjNodes;
	private List<Node> nodePath;
	private List<Node> bestPath;
	private Node lastNode;
	
	public DepthFirst(Graph graph) {
		super(graph);
		nodePath = new LinkedList<Node>();
		bestPath = new LinkedList<Node>();
	}

	@Override
	public List<Node> start(Node n_initial, Node n_final) {
		adjNodes = adjacencyOfNode(n_initial);
		
		nodePath.add(n_initial);
		lastNode = n_initial;
		
		search(adjNodes, n_final);
		return bestPath;
	}
	
	public List<Node> search(List<Edge> adjNodes, Node n_final) {
		//Verifica se existe caminho a percorrer
		if (adjNodes != null) {
			//Verifica se o node já foi visitado, caso seja retira-o da lista de nodes a percorrer e da lista
			//de caminho ideal
 			if (bestPath.contains(lastNode)) {
 				bestPath.remove(lastNode);
 				nodePath.remove(lastNode);
 			}
 			else {
 			//Caso contrário adiciona-o à lista de caminho ideal
				bestPath.add(lastNode);

	 		//E adiciona cada node adjacente à lista de nodes a percorrer	
				for (Edge edge : adjNodes) 
		 			nodePath.add(edge.getN1());
 			}
 		}
		else
		//Caso não haja caminho a percorrer, retira o node da lista de nodes a percorrer
			nodePath.remove(lastNode);
 			
		//proximo node a visitar será o ultimo da lista de nodes a percorrer
		lastNode = nodePath.get(nodePath.size()-1);

		//Caso o próximo node a visitar seja o objectivo, adiciona-o à lista de caminho ideal e retorna essa lista
		//terminando a execução
		if (lastNode == n_final) {
 			bestPath.add(lastNode);
 			return bestPath;
 		}

		//Chamada recursiva com caminhos a percorrer do proximo node a visitar
		search(adjacencyOfNode(lastNode),n_final);
 	
 		return null;
	}
}
