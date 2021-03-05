package com.amazon.oa2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class PowerGrid {
	static class GraphNode {
		char node;
		int price;
		
		GraphNode(char node, int price){
			this.node = node;
			this.price = price;
		}
	}
//	use bfs to traverse all the nodes
	public static List<GraphNode> minimumCost(int n, char[][] connections) {
//		create a graph
		Map<Character, List<GraphNode>> graph = new HashMap<>();
		for(char[] connection : connections) {
			graph.putIfAbsent(connection[0], new ArrayList<>());
			
			graph.get(connection[0]).add(new GraphNode(connection[1], Integer.parseInt(String.valueOf(connection[2]))));
			
		}
//		Use minHeap
		Queue<GraphNode> minHeap = new PriorityQueue<GraphNode>(new Comparator<GraphNode>() {
			public int compare(GraphNode a, GraphNode b) {
				return a.price - b.price;
			}
		});
		
		Set<GraphNode> visited = new HashSet<>();
		List<GraphNode> server = new ArrayList<>();
//		int numberOfServer = 0;
		minHeap.offer(new GraphNode('A', 0));
		while(!minHeap.isEmpty()) {
			GraphNode u = minHeap.poll();
			
			if(visited.contains(u)) continue;
						
			visited.add(u);
			server.add(u);
			
			if(u.node == 'E')
				return server;
//			numberOfServer++;
			List<GraphNode> graphNodeList = graph.get(u.node);
			if(graphNodeList != null) {
				for(GraphNode v : graph.get(u.node)) {
					if(!visited.contains(v)) {
						minHeap.add(v);
					}
				}
			}
				
		}
		return server;
	}
	
	public static void main(String [] arg) {
		char[][] connections = new char[][] {{'A','B','1'},
			{'B','C','4'},
			{'B','D','6'},
			{'D','E','5'},
			{'C','E','1'}};
		int n = 5;
		minimumCost(n, connections);
	}
}
