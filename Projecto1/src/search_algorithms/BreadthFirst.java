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
		
		//Verifica se há caminho a percorrer
		if (adjNodes != null) {
			//Proximo node a visitar será o primeiro dos nós adjacentes
			nextNode = adjNodes.get(0).getN1();
			//Prepara um iterador da lista de nos adjacentes, de forma a adicionar à lista de caminhos a percorrer
			//O primeiro a visitar, no final da lista, para solucionar backtracking
			edgeIter = adjNodes.listIterator(adjNodes.size());
		
			//Percorre todos os caminhos a percorrer e adiciona-o à lista
			while (edgeIter.hasPrevious()) {
				atualEdge = edgeIter.previous();
				
				//se encontrar o node objectivo, adiciona-o à lista da caminho ideal, devolve e termina
				if (atualEdge.getN1() == n_final) {
					bestPath.add(atualEdge.getN1());
		 			return bestPath;
		 		}
				//Adiciona o node à lista de caminhos a percorer
				nodePath.add(atualEdge.getN1());
			}
			//Adiciona o proximo node a visitar à lista de caminho ideal
			bestPath.add(nextNode);
		}
		else {
		//Caso não tenha caminho, retira o node visitado da lista de caminho ideal, de caminho a percorrer
		//E Seleciona o proximo node a visitar, que será o proxima na lista de caminhos
			nodePath.remove(nextNode);
			bestPath.remove(nextNode);
			nextNode = nodePath.get(nodePath.size()-1);
		}

		//Chamada recursiva com lista de caminhos a percorrer
		search(nodePath,n_final);
		
		return null;
	}
}
