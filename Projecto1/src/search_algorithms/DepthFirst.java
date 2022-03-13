package search_algorithms;

import java.util.LinkedList;
import java.util.List;

import graph_utils.Edge;
import graph_utils.Graph;
import graph_utils.Node;

public class DepthFirst extends SearchAlgorithm{

	private List<Edge> adjNodes;
	private List<Node> nodePath;
	private List<Node> nodeSearch;
	private Node lastNode;
	
	public DepthFirst(Graph graph) {
		super(graph);
		nodePath = new LinkedList<Node>();
		nodeSearch = new LinkedList<Node>();
	}

	@Override
	public List<Node> start(Node n_initial, Node n_final) {
		adjNodes = adjacencyOfNode(n_initial);
		
		nodePath.add(n_initial);
		lastNode = n_initial;
		
		search(adjNodes, n_final);
		return nodeSearch;
	}
	
	public List<Node> search(List<Edge> adjNodes, Node n_final) {
 		//Verificar se o node atual tem caminho
		if (adjNodes != null) {
			//caso tenha caminho verifica se o mesmo já foi aprofundado
 			if (nodeSearch.contains(lastNode)) {
 				//se já foi, significa que é um beco sem saida
 				nodeSearch.remove(lastNode);
 				nodePath.remove(lastNode);
 			}
 			else {
				nodeSearch.add(lastNode);
	 			
				//Adiciona a sua expansão à lista de caminhos a percorrer
	 			for (Edge edge : adjNodes) 
		 			nodePath.add(edge.getN1());
 			}
 		}
		else 
		//Caso node aprofundado não tenha caminho retira-o de nodes a percorrer
			nodePath.remove(lastNode);
 			
		//Prepara o proximo node a aprofundar, sendo o ultimo da lista de caminhos
		lastNode = nodePath.get(nodePath.size()-1);
		
 		//Caso o proximo node a aprofundar seja o objectivo, adiciona-o à lista de caminho ideal e devolve-a
		if (lastNode == n_final) {
 			nodeSearch.add(lastNode);
 			return nodeSearch;
 		}
		
 		//Chamada recursiva com o node expandido
		search(adjacencyOfNode(lastNode),n_final);
 	
 		return null;
	}
}
