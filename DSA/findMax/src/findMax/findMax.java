package findMax;
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
class Edge{
	int s;
	int d;
	int w;
	
	public Edge (int s, int d, int w) {
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
	public void findmax() {
		
		ArrayList<Edge> edges = new ArrayList<>();
		
		for(int i = 1; i<= v; i++) {
			Node temp = adjList[i].head;
			while(temp != null) {
				if(i < temp.data) {
					edges.add(new Edge(i, temp.data, temp.weight));
				}
				temp = temp.next;
			}
		}
		
		edges.sort((a, b) -> b.w - a.w);
		
		int []pred = new int[v+1];
		for(int i = 0; i <= v; i++) {
			pred[i] = i;
		}
		
		int totalcost =0;
		int count = 0;
		for(Edge edge : edges) {
			int sRoot = find(pred, edge.s);
			int dRoot = find(pred, edge.d);
			
			if(sRoot != dRoot) {
				totalcost = totalcost+ edge.w;
				count++;
				pred[dRoot] = sRoot;
			}
			if(count ==(v-1)) {
				break;
			}
		}
		System.out.print("Max Bandwidth: " + totalcost);
	}
	
	int find(int [] pred, int v) {
		if(pred[v] == v) {
			return v;
		}
		return pred[v] = find(pred, pred[v]);
	}
}

public class findMax {

	public static void main(String[] args) {
		
Scanner sc =new Scanner(System.in);
		
		System.out.print("Enter the number of vertices: ");
		int v =sc.nextInt();
		
		GraphList g = new GraphList(v);
		g.create();
		g.findmax();
	}

}
