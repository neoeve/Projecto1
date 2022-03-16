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
		//Verifica se há caminho a percorrer
		if (adjNodes != null) {
			//Verifica se o node atual já foi visitado
 			if (bestPath.contains(nextNode)) {
 				bestPath.remove(nextNode);
 				nodePath.remove(nextNode);
 			}
 			else {
 			//Senão foi visitado adiciona-o à lista de caminho ideal
 				bestPath.add(nextNode);
 				
 				//Para cada caminho possível vai calcular o seu custo
 				for (Edge edge : adjNodes) {
 					nextCost = edge.getCost() + edge.getN1().getHeuristic();
 			
 					//Caso o custo de destino seja inferior ao anteriormente apurado da lista, seleciona-o
 					if (nextCost < f) {
	 					nextNode = edge.getN1();
	 					f = nextCost;
	 				}
 					//adiciona cada node à lista de caminhos
		 			nodePath.add(edge.getN1());
	 			}
 			}
 		}
		else 
		//Se não tem caminho a percorrer, retira o node da lista
			nodePath.remove(nextNode);
		
		//Caso o proximo node a visitar seja o objectivo, adiciona-o à lista de caminho ideal e termina
		if (nextNode == n_final) {
 			bestPath.add(nextNode);
 			return bestPath;
 		}

		//Chamada recursiva com o caminho do proximo node
		search(adjacencyOfNode(nextNode),n_final);
		
		return null;
	}

}
