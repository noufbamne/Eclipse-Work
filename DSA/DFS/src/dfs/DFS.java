package dfs;
import java.util.*;

class Node{
	int data;
	int weight;
	Node next;
	
	public Node(int data, int weight){
		this.data = data;
		this.weight = weight;
		this.next = null;
	}
}

class CustomLinkedList{
	Node head;
	
	public CustomLinkedList(){
		head = null;
	}
	
	public void addLast(int data, int weight) {
		if(head == null) {
			head = new Node(data, weight);
			return;
		}
		
		Node node = new Node(data, weight);
		Node temp = head;
		while(temp.next != null) {
			temp = temp.next;
		}
		temp.next = node;
	}
	
	public void remove(int data) {
		if (head.data == data) {
			head = head.next;
			return;
		}
		
		Node temp = head;
		while(temp.next != null && temp.next.data != data) {
			temp = temp.next;
		}
		temp.next = temp.next.next;
	}
}

class GraphList{
	int v;
	CustomLinkedList[] adjList;
	
	public GraphList(int v) {
		this.v = v;
		adjList =new CustomLinkedList[v+1];
		
		for(int i = 1; i <= v; i++) {
			adjList[i] = new CustomLinkedList();
		}
	}
	
	public void create() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the number of edges: ");
		int n = sc.nextInt();
		sc.nextLine();
		
		for(int i = 0; i < n; i++) {
			System.out.print("Enter the vertex pair for edge " + (i+1) + ":");
			int v1 = sc.nextInt();
			int v2 = sc.nextInt();
			
			System.out.print("Enter the weight: ");
			int weight = sc.nextInt();
			
			adjList[v1].addLast(v2, weight);
			adjList[v2].addLast(v1, weight);
		}
	}
	
	public void finddfs(int s, int e) {
		
		boolean [] visited = new boolean[this.v+1];
		ArrayList<Integer> result = new ArrayList<>();
		dfs(s, e, visited, result);
		
		if(result.size() > 0 && result.get(result.size()-1) == e) {
			System.out.println("Dfs path : "+ result);
		}
		else {
			System.out.println("Path not found.");
		}
	}

	void dfs(int s, int e, boolean[] visited, ArrayList<Integer> result) {
		
		result.add(s);
		if( s ==e) {
			return;
		}
		visited[s] = true;
		
		Node temp = adjList[s].head;
		while(temp != null) {
			int neighbour = temp.data;
			if(!visited[neighbour]) {
				dfs(neighbour, e, visited, result);
				if(result.get(result.size()- 1) ==e) return;
			}
			temp = temp.next;
		}
		result.remove(result.size() -1);
	}
}
public class DFS {
	public static void main(String[] args) {
		
		Scanner sc =new Scanner(System.in);
		
		System.out.print("Enter the number of vertices: ");
		int v =sc.nextInt();
		
		GraphList g = new GraphList(v);
		g.create();
		
		System.out.print("Enter the start and end vertices: ");
		int s = sc.nextInt();
		int e = sc.nextInt();
		
		g.finddfs(s, e);
	}
}
