package assignment_0;
import java.util.*;

class Member{
	int memberID;
	String name;
	String address;
	String position;
	Member next;
	
	public Member(int memberID, String name, String address, String position) {
		this.memberID = memberID;
		this.name = name;
		this.address = address;
		this.position = position;
		this.next = null;
	}
}

class CodeClub{
	Member head;
	
	public CodeClub() {
		head = null;
	}
	
	Scanner sc = new Scanner(System.in);
	public boolean isEmpty() {
		return head == null;
	}
	
	public void display() {
		if(isEmpty()){
			System.out.print("List is empty");
			return;
		}
		
		Member ptr = head;
		while(ptr!= null) {
			System.out.print("ID : " + ptr.memberID + " Name: " + ptr.name + " Adrress: " + ptr.address + " Position: " + ptr.position);
			ptr = ptr.next;
		}
	}
	
	public void insertFirst() {
		
		System.out.print("Enter Member ID: ");
        int memberID = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Address: ");
        String address = sc.nextLine();
        System.out.print("Enter Position: ");
        String position = sc.nextLine();
		
		Member newMember = new Member(memberID, name, address, position);
		
		newMember.next = head;
		head = newMember;
		
		System.out.println("Member inserted at first successfully!");
	}
	
	public void insertLast() {
		
		System.out.print("Enter Member ID: ");
        int memberID = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Address: ");
        String address = sc.nextLine();
        System.out.print("Enter Position: ");
        String position = sc.nextLine();
		
		Member newMember = new Member(memberID, name, address, position);
		
		if(isEmpty()) {
			head = newMember;
			System.out.println("Member added at end successfully!");
			return;
		}
		
		Member ptr = head;
		while(ptr.next != null) {
			ptr = ptr.next;
		}
		
		ptr.next = newMember;
		System.out.println("Member added at end successfully!");
	}
	
	public void insertatIndex() {
		System.out.println("Enter index to insert at:  ");
		int index = sc.nextInt();
		sc.nextLine();
		
		if(index == 0) {
			insertFirst();
			return;
		}
		
		System.out.print("Enter Member ID: ");
        int memberID = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Address: ");
        String address = sc.nextLine();
        System.out.print("Enter Position: ");
        String position = sc.nextLine();
		
		Member newMember = new Member(memberID, name, address, position);
		
		Member ptr = head;
		for(int i = 0; i < index -1; i++) {
			
			if(ptr == null) {
				System.out.print("Invalid index.");
				return;
			}
			ptr = ptr.next;
		}
		if(ptr == null) {
			System.out.print("Invalis index.");
			return;
		}
		newMember.next = ptr.next;
		ptr.next = newMember;
		System.out.println("Member added at index " +index+ " successfully!");
	}
	
	public void update() {
		
		if(isEmpty()){
			System.out.print("List is empty");
			return;
		}
		
		System.out.print("Enter ID to update: ");
		int id = sc.nextInt();
		sc.nextLine();
		
		Member ptr = head;
		while(ptr != null && ptr.memberID != id) {
			ptr = ptr.next;
		}
		
		if(ptr == null) {
			System.out.println("Member not found.");
			return;
		}
		
		System.out.print("Enter new Name: ");
        ptr.name = sc.nextLine();
        System.out.print("Enter new Address: ");
        ptr.address = sc.nextLine();
        System.out.print("Enter new Position: ");
        ptr.position = sc.nextLine();
        System.out.println("Member updated successfully!");
	}
	
	public void search() {
		if(isEmpty()){
			System.out.print("List is empty");
			return;
		}
		
		System.out.print("Enter ID to search: ");
		int id = sc.nextInt();
		sc.nextLine();
		
		Member ptr = head;
		int index = 0;
		while(ptr != null){
			
			if(ptr.memberID == id) {
				System.out.println("Member found at index: " + index);
				System.out.println("Name: " + ptr.name + ", Address: " + ptr.address + ", Position: " + ptr.position);
                return;
			}
			ptr = ptr.next;
			index = index +1;
		}
		System.out.println("Member not found.");
	}
	
	public void deleteFirst() {
		if(isEmpty()) {
			System.out.print("List is empty");
			return;
		}
		head = head.next;
		System.out.println("First member deleted successfully!");
	}
	
	public void deleteatLast() {
		if(isEmpty()) {
			System.out.print("List is empty");
			return;
		}
		
		if(head.next == null) {
			head = null;
			System.out.println("Last member deleted successfully!");
            return;
		}
		Member ptr = head;
		while(ptr.next.next!= null) {
			ptr = ptr.next;
		}
		
		ptr.next = null;
		System.out.println("Last member deleted successfully!");
	}
	
	public void deleteat() {
		if(isEmpty()) {
			System.out.print("List is empty");
			return;
		}
		
		System.out.print("Enter index to delete (0-based): ");
        int index = sc.nextInt();
        sc.nextLine();
        
        if(index == 0) {
        	deleteFirst();
        	return;
        }
        
        Member ptr = head;
        for(int i = 0; i< index -1; i++) {
        	if (ptr.next == null) {
                System.out.println("Invalid index");
                return;
            }
            ptr = ptr.next;
        }
        
        if (ptr.next == null) { // safety check
            System.out.println("Invalid index");
            return;
        }
        
        ptr.next = ptr.next.next;
        System.out.println("Member at index " + index + " deleted successfully!");
    }
}
public class Assignment_0 {

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		
		CodeClub club = new CodeClub();
		char ans;
		
		do {
			 System.out.println("\n--- Code Club Menu ---");
	            System.out.println("1. Insert Member at Start");
	            System.out.println("2. Insert Member at End");
	            System.out.println("3. Insert Member at Index");
	            System.out.println("4. Delete First Member");
	            System.out.println("5. Delete Last Member");
	            System.out.println("6. Delete Member at Index");
	            System.out.println("7. Search Member by ID");
	            System.out.println("8. Update Member by ID");
	            System.out.println("9. Display Members");
	            System.out.println("0. Exit");
	            System.out.print("Enter your choice: ");
	            int choice = sc.nextInt();

	            switch (choice) {
	                case 1 -> club.insertFirst();
	                case 2 -> club.insertLast();
	                case 3 -> club.insertatIndex();
	                case 4 -> club.deleteFirst();
	                case 5 -> club.deleteatLast();
	                case 6 -> club.deleteat();
	                case 7 -> club.search();
	                case 8 -> club.update();
	                case 9 -> club.display();
	                case 0 -> {
	                    System.out.println("Exiting... Thank you!");
	                    return;
	                }
	                default -> System.out.println("Invalid option. Try again!");
	            }

	            System.out.print("Do you want to continue? (Y/N): ");
	            ans = sc.next().charAt(0);
		}while( ans == 'y' || ans == 'Y');

	}

}
