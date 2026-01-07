package assignment_07;
import java.util.*;

class Node {
	String word, meaning;
	Node left, right;
	
	public Node(String word, String meaning){
		this.word = word;
		this.meaning = meaning;
		this.left= null;
		this.right =null;
	}
}

class BST{
	Node root;
	Scanner sc = new Scanner(System.in);
	
	public void create() {
		if (root != null) {
			System.out.print("Tree already exists.");
			return;
		}
		
		System.out.print("Enter first word: ");
		String word = sc.nextLine();
		
		System.out.print("Enter the meaning: ");
		String meaning = sc.nextLine();
		
		Node newNode = new Node (word , meaning);
		root = newNode;
		
		System.out.print("Tree created successfully.");
	}
	
	public void insert(String word, String meaning) {
		
		Node newNode = new Node(word, meaning);
		
		if (root == null) {
			root = newNode;
			return;
		}
		
		Node ptr = root;
		Node parent = null;
		
		while(ptr != null) {
			parent = ptr;
			
			if(word.equalsIgnoreCase(ptr.word)) {
				System.out.print("No duplicates aloowed.");
				return;
			}
			
			if(word.compareToIgnoreCase(ptr.word) < 0) {
				ptr = ptr.left;
			}else {
				ptr = ptr.right;
			}
		}
		
		if(word.compareToIgnoreCase(parent.word) < 0) {
			parent.left = newNode;
		}else {
			parent.right = newNode;
		}
		System.out.print("Inserted Successfully.");
	}
	
	
	
	
	Node search(String key) {
		if (root == null) {
			System.out.print("Tree is empty");
			return null;
		}
		
		Node ptr = root;
		while(ptr != null) {
			if(key.equalsIgnoreCase(ptr.word)) {
				return ptr;
			}
			
			if(key.compareToIgnoreCase(ptr.word)< 0) {
				ptr = ptr.left;
			}else {
				ptr = ptr.right;
			}
		}
		return null;
	}
	
	
	
	
	Node delete(Node root, String key) {
		if (root == null) {
			System.out.print("Tree is empty");
			return null;
		}
		
		if(key.compareToIgnoreCase(root.word)< 0) {
			root.left = delete(root.left, key);
		}
		else if(key.compareToIgnoreCase(root.word)> 0) {
			root.right = delete(root.right, key);
		}
		
		else {
			if(root.left == null && root.right == null) {
				root = null;
			}
			else if(root.left == null) {
				return root.right;
			}
			
			else if(root.right== null) {
				return root.left;
			}
			
			else {
				Node successor = minNode(root.right);
				root.word = successor.word;
				root.meaning = successor.meaning;
				root.right = delete(root.right, successor.word);
			}
		}
		return root;
	}
		
	
	
	Node minNode(Node ptr) {
		while(ptr.left != null) {
			ptr = ptr.left;
		}
		return ptr;
	}
	
	
	void update(String key, String newMeaning ) {
		if (root == null) {
			System.out.print("Tree is empty");
			return;
		}
		
		Node ptr = root;
		while(ptr != null) {
			if(key.equalsIgnoreCase(ptr.word)) {
				ptr.meaning = newMeaning;
				System.out.print("Updated successfully.");
				return;
			}
			
			if(key.compareToIgnoreCase(ptr.word)< 0) {
				ptr = ptr.left;
			}else {
				ptr = ptr.right;
			}
		}
		System.out.print("Keyword not found.");
	}
	
	
	void preorder(Node ptr) {
		if( ptr == null) {
			return;
		}
		System.out.println(ptr.word+ " : " + ptr.meaning);
		preorder(ptr.left);
		preorder(ptr.right);
		
	}
	
	void inorder(Node ptr) {
		if( ptr == null) {
			return;
		}
		preorder(ptr.left);
		System.out.println(ptr.word+ " : " + ptr.meaning);
		preorder(ptr.right);
		
	}
	
	void postorder(Node ptr) {
		if( ptr == null) {
			return;
		}
		preorder(ptr.left);
		preorder(ptr.right);
		System.out.println(ptr.word+ " : " + ptr.meaning);
	}
	
	
	void printleaves(Node ptr) {
		if (ptr == null) {
			return;
		}
		if(ptr.left == null && ptr.right == null) {
			System.out.println(ptr.word);
		}
		printleaves(ptr.left);
		printleaves(ptr.right);
	}
	
	void interior(Node ptr) {
		if (ptr == null) {
			return;
		}
		if(!(ptr.left == null && ptr.right == null)) {
			System.out.println(ptr.word);
		}
		interior(ptr.left);
		printleaves(ptr.right);
			
	}
	
	
}
public class Assignment_07 {

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		BST binary = new BST();
		int ch;
		
		do {
			System.out.println("\n========= DICTIONARY USING BST =========");
            System.out.println("0) Exit");
            System.out.println("1) Create Tree");
            System.out.println("2) Insert Keyword");
            System.out.println("3) Search Keyword");
            System.out.println("4) Delete Keyword");
            System.out.println("5) Update Meaning");
            System.out.println("6) Preorder Traversal");
            System.out.println("7) Inorder Traversal");
            System.out.println("8) Postorder Traversal");
            System.out.println("9) Print Leaf Nodes");
            System.out.println("10) Print Interior Nodes");
            System.out.print("Enter choice: ");
            ch = sc.nextInt();
            sc.nextLine();
            
			switch(ch) {
				
			case 0 :
				System.out.println("Terminated");
				break;
				
			case 1:
				binary.create();
				break;
				
			case 2: 
				System.out.print("Enter word: ");
				String word = sc.nextLine();
				
				System.out.print("Enter meaning: ");
				String meaning = sc.nextLine();
				
				binary.insert(word, meaning);
				break;
				
			case 3: 
				System.out.println("Enter word to search meaning: ");
				String keyword = sc.nextLine();
				
				Node res = binary.search(keyword);
				
				if(res == null) {
					System.out.print("Keyword not found.");
				}else {
					System.out.print("Meaning: "+ res.meaning);
				}
				break;
				
			case 4:
				System.out.print("Enter keyword to delete: ");
				String del = sc.nextLine();
				
				binary.root = binary.delete(binary.root, del);
				System.out.print("Keyword deleted successfully");
				
				break;
				
			case 5: 
				System.out.print("enter keyword to update: ");
				String key = sc.nextLine();
				
				System.out.print("Enter new meaning: ");
				String newMean = sc.nextLine();
				
				binary.update(key, newMean);
				
				break;
				
			case 6:
				binary.preorder(binary.root);
				break;
				
			case 7:
				binary.inorder(binary.root);
				break;
				
			case 8:
				binary.postorder(binary.root);
				break;
				
			case 9:
				binary.printleaves(binary.root);
				break;
				
			case 10:
				binary.interior(binary.root);
				break;
				
			default:
				System.out.print("Enter a valid choice");
			}
		
			
		}while (ch != 0);
		sc.close();

	}

}
