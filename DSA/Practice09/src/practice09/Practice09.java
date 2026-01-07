package practice09;
import java.util.*;

class Contact{
	
	long number;
	String name;
	String email;
	
	public Contact(long number, String name, String email){
		this.number = number;
		this.name = name;
		this.email = email;
	}
}

class ContactList{
	int size;
	Contact[] hashtable;
	
	public ContactList(int size){
		this.size = size;
		hashtable = new Contact[size];
	}
	
	int hash(long number) {
		return (int)(Math.abs(number % size));
	}
	
	Scanner sc = new Scanner (System.in);
	public void insert() {
		
		System.out.print("Enter name: ");
		String name = sc.nextLine();
		
		long number;
		while(true) {
			System.out.print("Enter number :");
			number = sc.nextLong();
			sc.nextLine();
			
			if(number >= 1000000000L && number <= 9999999999L) {
				break;
			}else {
				System.out.print("Enter a valid 10 digit number.");
			}
		}
		
		String email;
		while (true) {
			System.out.print("Enter email :");
			email = sc.nextLine();
			
			if(email.contains("@") && email.contains(".")) {
				break;
			}else {
				System.out.print("Enter a valid email.");
			}
		}
		
		Contact con = new Contact(number, name, email);
		int index = hash(number);
		int startindex = index;
		
		while(hashtable[index] != null) {
			index = (index + 1) % size;
			
			if(index == startindex) {
				System.out.print("Hashtable full.");
				return;
			}
		}
		
		hashtable[index] = con;
		System.out.println("Contact inserted into hashtable.");
	}
	
	public void searchbyname() {
		System.out.print("Enter name to search.");
		String name = sc.nextLine();
		
		for(int i = 0; i < size; i++) {
			if(hashtable[i] != null && hashtable[i].name.equalsIgnoreCase(name)) {
				Contact c = hashtable[i];
				System.out.print("Index : " + i + " | " + "Name : " + c.name + " | " + "Number: " + c.number + " | " + "Email: " + c.email);
				return;
			}
			System.out.print("Contact not found.");
		}
	}
	
	public void searchbynumber() {
		System.out.print("Enter number to search.");
		long number = sc.nextLong();
		
		int index = hash(number);
		int startindex = index;
		
		while(hashtable[index] != null) {
			if(hashtable[index].number == number) {
				Contact c = hashtable[index];
				System.out.print("Index : " + index + " | " + "Name : " + c.name + " | " + "Number: " + c.number + " | " + "Email: " + c.email);
				return;
			}
			System.out.print("Contact not found.");
		}
	}
		
	public void delete() {
		System.out.print("Enter number to delete.");
		long number = sc.nextLong();
		
		int index = hash(number);
		int startindex = index;
		
		while(hashtable[index] != null) {
			if(hashtable[index].number == number) {
				Contact c = hashtable[index];
				hashtable[index] = null;
				System.out.print("Index : " + index + " | " + "Name : " + c.name + " | " + "Number: " + c.number + " | " + "Email: " + c.email);
				return;
			}
			System.out.print("Contact not found.");
		}
	}
	
	public void display() {
		
		for(int i = 0; i < size; i++) {
			Contact c = hashtable[i];
			
			if(c == null) {
				System.out.println("Index: " + i + " | Empty");
			}else {
				System.out.print("Index : " + i + " | " + "Name : " + c.name + " | " + "Number: " + c.number + " | " + "Email: " + c.email);
			}
		}
	}
}

public class Practice09 {
	public static void main(String [] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the size of the hashtable: ");
		int size = sc.nextInt();
		
		ContactList t = new ContactList(size);
		int choice;
		char ans;
		do {
			System.out.println("===Menu===");
			System.out.println("1. Insert");
			System.out.println("2. Search By name");
			System.out.println("3. Search by number");
			System.out.println("4. Display");
			System.out.println("5. Delete");
			System.out.println("6. Exit");
			
			System.out.println("Enter Choice: ");
			choice = sc.nextInt();
			
			switch (choice) {
			case 1: t.insert(); break;
			case 2 : t.searchbyname(); break;
			case 3 : t.searchbynumber(); break;
			case 4 : t.display(); break;
			case 5 : t.delete(); break;
			case 6 : System.out.println("Exiting"); return;
			default : System.out.println("Enter a valid choice");
			}
			
			System.out.print("Would you like to continue (Y/N): " );
			ans = sc.next().charAt(0);
			
		}while(ans == 'Y' || ans == 'y');
		
	}
}
