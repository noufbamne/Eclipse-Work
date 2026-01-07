package assignment_02_DLL;
import java.util.*;

class DNode {
    int id;
    String songName;
    String artist;
    DNode prev;
    DNode next;

    // Constructor
    public DNode(int id, String songName, String artist) {
        this.id = id;
        this.songName = songName;
        this.artist = artist;
        this.prev = null;
        this.next = null;
    }
}

class Playlist{
	DNode head;
	DNode tail;
	DNode current;
	
	public Playlist() {
		this.head = null;
		this.tail = null;
		this.current= null;
	}
	
	Scanner sc = new Scanner(System.in);
	
	public void insertFirst() {
		System.out.print("Enter Song ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Song Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Artist Name: ");
        String artist = sc.nextLine();

        DNode temp = new DNode(id, name, artist);
        
        temp.next = head;
        
        if(head != null) {
        	head.prev = temp;
        }
        
        head = temp;
        
        if (tail == null) {
        	tail = temp;
        }
        
        if (current == null) {
        	current = head;
        }
        
        System.out.println("Song inserted at the first place succesfully");
	}
	
	public void insertLast() {
		System.out.print("Enter Song ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Song Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Artist Name: ");
        String artist = sc.nextLine();

        DNode temp = new DNode(id, name, artist);
        
        if(head == null) {
        	head = tail = temp;
        	current = head;
        	System.out.println("Song added at end successfully!");
        	return;
        }
        
        tail.next = temp;
        temp.prev = tail;
        tail = temp;
        
        System.out.println("Song added at end successfully!");
        
	}
	
	public void insertAt() {
		
		System.out.print("Enter Index to Insert: ");
        int index = sc.nextInt();
        sc.nextLine();
         
		System.out.print("Enter Song ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Song Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Artist Name: ");
        String artist = sc.nextLine();

        DNode temp = new DNode(id, name, artist);
        
        if(index == 0) {
        	insertFirst();
        	System.out.println("Song added at index " + index +" successfully!");
        }
        
        DNode ptr = head;
        for(int i = 0; i < index; i++) {
        	if(ptr == null) {
        		System.out.println("Invalid index!");
                return;
        	}
        	ptr = ptr.next;        
        }
        if(ptr == null) {
        	tail.next = temp;
        	temp.prev = tail;
        	tail = temp;
        	
        	System.out.println("Song added at index " + index + " successfully!");
            return;
        }
        
        temp.prev = ptr.prev;
        temp.next = ptr;
        
        if(ptr.prev != null) {
        	ptr.prev.next = temp;
        }
        ptr.prev = temp;
        
        System.out.println("Song added at index " + index + " successfully!");
	}
	
	public void deleteFirst() {
		if(head == null) {
			System.out.println("Playlist is empty!");
            return;
		}
		
		head = head.next;
		
		if(head != null) {
			head.prev = null;
		}
		
		if(current == head) {
			current = head;
		}
		
		System.out.println("First song deleted!");
	}
	
	public void deleteLast() {
		if(head == null) {
			System.out.println("Playlist is empty!");
            return;
		}
		
		if(head == tail) {
			head = null;
			tail = null;
			current = null;
			
			System.out.println("Last song deleted!");
	        return;
		}
		if(current == tail) {
			tail = tail.prev;
		}
		
		tail = tail.prev;
		tail.next = null;
		
		System.out.println("Last song deleted!");
	}
	
	public void deleteAt() {
		if(head == null) {
			System.out.println("Playlist is empty!");
            return;
		}
		
		System.out.print("Enter Index to Delete: ");
        int index = sc.nextInt();
        sc.nextLine();
		
		if(index == 0) {
			deleteFirst();
			System.out.println("Song deleted at index " + index + " successfully!");
	        return;
		}
		 DNode ptr = head;
	     for(int i = 0; i < index; i++) {
	    	 if(ptr == null) {
	        	System.out.println("Invalid index!");
	               return;
	        }
	        ptr = ptr.next;        
	     }
	     if(ptr == null) {
	        System.out.println("Invalid index!");
            return;
	     }
	     
	     if(ptr == tail) {
	    	 deleteLast();
	    	 System.out.println("Song deleted at index " + index + " successfully!");
	    	 return;
	     }
	     if(ptr.prev != null) {
	    	 ptr.prev.next = ptr.next;
	     }
	     if(ptr.next != null) {
	    	 ptr.next.prev = ptr.prev;
	     }
	     
	     if(ptr == current) {
	    	 if(current.next != null) {
	    		 current = current.next;
	    	 }else {
	    		 current = current.prev;
	    	 }
	     }
	}
	
	public void deletebyid() {
		
	}
	
	public void search() {
		System.out.print("Enter Song ID to Search: ");
        int id = sc.nextInt();
        sc.nextLine();
        
        DNode ptr = head;
        int index = 0;
        
        while(ptr != null) {
        	if(ptr.id == id) {
        		System.out.println("Song found at index: " + index);
                return;
        	}
        	
        	ptr = ptr.next;
        	index = index + 1;
        }
        System.out.println("Song not found!");
	}
}

public class Assignment_02_DLL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
