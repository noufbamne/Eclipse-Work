package assignment_08;
import java.util.*;

class Node{
	int data;
	int weight;
	Node next;
	
	public Node(int data, int weight) {
		this.data = data;
		this.weight = weight;
		this.next = null;
	}
}

class CustomLinkedList{
	Node head;
	
	public CustomLinkedList() {
		head = null;
	}
	
	public void addlast(int data, int weight){
		if (head == null) {
			head = new Node(data, weight) ;
			return;
		}
		
		Node node = new Node(data,weight);
		Node ptr = head;
		
		while(ptr != null) {
			ptr = ptr.next;
		}
		
		ptr.next = node;
		
	}
	
	public void remove(int data) {
		if (head.data == data) {
			head = head.next;
			return;
		}
		
		Node ptr = head;
		while(ptr.next!= null && ptr.next.data != data) {
			ptr=ptr.next;
		}
		
		ptr.next= ptr.next.next;
	}
}

class Edge{
	int s;
	int d;
	int w;
	
	public Edge(int s, int d, int w) {
		this.s = s;
		this.d = d;
		this.w = w;
	}
}


class GraphList{
	int v;
	CustomLinkedList[] adjList;
	
	public GraphList(int v) {
		this.v = v;
		adjList = new CustomLinkedList[v + 1];
		
		for (int i = 1; i <= v; i++) {
			adjList[i] = new CustomLinkedList();
		}
	}
	
	public void createGraph() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the number of edges: ");
		int edge = sc.nextInt();
		
		for (int i = 0; i < edge; i++) {
			System.out.print("Enter the vertex pair of the edge: "+ (i+1) +" : ");
			int v1 = sc.nextInt();
			int v2 = sc.nextInt();
			System.out.print("Enter Weight: ");
			int weight = sc.nextInt();
			adjList[v1].addlast(v2, weight);
			adjList[v2].addlast(v1, weight);
		}
	}
	
	public void findDFS(int s, int e) {
		boolean [] visited = new boolean[this.v+1];
		ArrayList<Integer> result = new ArrayList<>();
		dfs (s, e, visited, result);
		
		if (result.size()> 0 && result.get(result.size()-1) == e) {
			System.out.println("path of DFS: " +  result);
		}
		else {
			System.out.print("Path not found.");
		}
	}
	
	void dfs(int s , int e , boolean[]visited, ArrayList<Integer> result) {
		result.add(s);
		if (s == e ) 
			return;
		
		visited[s] = true;
		
		Node temp = adjList[s].head;
		while (temp != null) {
			int neighbour = temp.data;
			if(!visited[neighbour]) {
				dfs(neighbour, e , visited, result );
				if(result.get(result.size()-1) == e) return;
			}
			temp = temp.next;
		}
		result.remove(result.size()-1);
	}
	
	public void FindBFS(int s, int e) {
		boolean [] visited = new boolean [this.v+1];
		int [] parent = new int [this.v+1];
		Arrays.fill(parent, -1);
		
		Queue <Integer> q = new LinkedList<>();
		q.add(s);
		visited [s] = true;
		
		boolean found = false;
		
		while(!q.isEmpty()) {
			int curr = q.remove();
			
			if (curr == e) {
				found = true;
				break;
			}
			
			Node temp = adjList[curr].head;
			while(temp != null) {
				int neighbour = temp.data;
				if(!visited[neighbour]) {
					visited[neighbour]= true;
					parent[neighbour] = curr;
					q.add(neighbour);
				}
				temp = temp.next;
			}
		}
		
		if (found) {
			ArrayList <Integer> path = new ArrayList<>();
			
			int temp = e;
			while(temp != -1) {
				path.add(temp);
				temp = parent[temp];
			}
			Collections.reverse(path);
			System.out.println("Path of BFS: "+ path);
		}else {
			System.out.println("Path not found");
		}
	}
	public void FindMax() {
		ArrayList <Edge> edges = new ArrayList<>();
		
		for (int i = 1; i <= v; i ++ ) {
			Node temp = adjList[i].head;
			while(temp != null) {
				if(i < temp.data)
					edges.add(new Edge(i, temp.data, temp.weight));
				temp = temp.next;
			}
		}
			
		edges.sort((a,b) -> b.w - a.w);
		
		int[]pred = new int[v+1];
		for(int i = 1; i <=v; i++) {
			pred[i] = i;
		}
		
		int totalcost =0, count =0;
		for(Edge edge: edges) {
			int sRoot = find(pred, edge.s);
			int dRoot = find(pred, edge.d);
			
			if(sRoot != dRoot) {
				totalcost = totalcost+edge.w;
				count++;
				pred[dRoot]= sRoot;
			}
			if (count == (v-1)) 
				break;
		}
		System.out.println("Max Bandwidth: " + totalcost);
	}
	
	int find(int [] pred, int v) {
		if(pred[v] == v) 
			return v;
		return pred[v] = find(pred, pred[v]);
	
	}
	
}


public class Assignment_08 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the number of Vertices: ");
		int v = sc.nextInt();
		
		GraphList g = new GraphList(v);
		g.createGraph();
		
		System.out.print("Enter the start and end vertices: ");
		int s = sc.nextInt();
		int e = sc.nextInt();
		
		g.FindBFS(s, e);
		
		g.findDFS(s, e);
		
		g.FindMax();
	}

}

/*2) Map BFS idea → variables in your program (exam phrasing)

Write this mapping in the exam so the examiner knows you understand code:

boolean[] visited — marks nodes already seen (prevents re-visiting).

int[] parent — stores parent of each node to reconstruct the path (parent[s] = -1).

Queue<Integer> q — FIFO structure that holds nodes to process (level order).

q.add(s) and visited[s] = true — initialize with start vertex.

while(!q.isEmpty()) { curr = q.remove(); ... } — main loop: pop current vertex, visit its neighbors.

Inner loop Node temp = adjList[curr].head; while(temp != null) { neighbour = temp.data; ... temp = temp.next; } — iterate adjacency linked-list for curr.

When neighbour unseen: mark visited, set parent[neighbour] = curr, q.add(neighbour).

If curr == e → found = true and break. After loop, if found reconstruct path by following parent[] from e to s, then reverse.

3) How to write the algorithm step-by-step (exam style — copy this)

Init: Create visited[V+1] all false; parent[V+1] fill with -1. Create an empty queue q.

Start: q.add(s); visited[s] = true;

Loop: while queue is not empty:

curr = q.remove().

If curr == e set found = true and break.

Traverse adjacency list of curr:

for each neighbour if !visited[neighbour]:

visited[neighbour] = true

parent[neighbour] = curr

q.add(neighbour)

After loop: if found == true:

reconstruct path: start at temp = e, while temp != -1: add temp to path, temp = parent[temp].

reverse path and print.

else print "Path not found".*/
