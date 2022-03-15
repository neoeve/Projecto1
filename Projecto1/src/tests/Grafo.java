package tests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graph_utils.Edge;
import graph_utils.Graph;
import graph_utils.Node;
import search_algorithms.Astar;
import search_algorithms.BreadthFirst;
import search_algorithms.DepthFirst;
import search_algorithms.SearchAlgorithm;

public class Grafo {
	public static void main(String[] args) {
		/* baseado no grafo na imagem: grafo.jpg
		 */
	
		Graph graph = new Graph();
		// creating the nodes
		
		//Heuristaca calculada com valor da masmorra final - masmorra final
		Node n1 = new Node("1",10);
		Node n2 = new Node("2",9);
		Node n3 = new Node("3",8);
		Node n4 = new Node("4",7);
		Node n5 = new Node("5",6);
		Node n6 = new Node("6",5);
		Node n7 = new Node("7",4);
		Node n8 = new Node("8",3);
		Node n9 = new Node("9",2);
		Node n10 = new Node("10",1);
		Node n11 = new Node("11",0);
	
		// creating and adding edges to graph\
		//Custos calculados com o numero de quadrados do mapa
		graph.addEdge(n6, n10, 1);
		graph.addEdge(n10, n4, 1);
		graph.addEdge(n4, n3, 13);
		graph.addEdge(n4, n8, 11);
		graph.addEdge(n3, n9, 2);
		graph.addEdge(n3, n11, 17);
		graph.addEdge(n9, n7, 7);
		graph.addEdge(n9, n11, 7);
		graph.addEdge(n8, n5, 11);
		graph.addEdge(n5, n1, 1);
		graph.addEdge(n5, n2, 11);
		graph.addEdge(n5, n11, 1);
	
		System.out.println(graph.toString());
		
		System.out.println("-----------------------------------------");
		System.out.println("Initial node: " + n6.getLabel());
		System.out.println("Final node: " + n11.getLabel());
		
		System.out.println("------------------DFS----------------------");
		SearchAlgorithm dfsAlg = new DepthFirst(graph);
		dfsAlg.printResult(dfsAlg.startSearch(n6, n11));
		
		System.out.println("------------------BFS----------------------");
		SearchAlgorithm bfsAlg = new BreadthFirst(graph);
		bfsAlg.printResult(bfsAlg.startSearch(n6, n11));
		
		System.out.println("------------------A*-----------------------");
		SearchAlgorithm afsAlg = new Astar(graph);
		afsAlg.printResult(afsAlg.startSearch(n6, n11));
	}
	
}
