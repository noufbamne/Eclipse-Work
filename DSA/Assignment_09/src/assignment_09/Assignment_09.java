package assignment_09;

import java.util.*;

class Contact{
	long number;
	String name;
	String email;
	
	public Contact(long number, String name, String email){
		this.number=number;
		this.name=name;
		this.email=email;
		
	}
}

class ContactList{
	int size;
	Contact [] hashtable;
	
	public ContactList(int size){
		this.size=size;
		hashtable = new Contact[size];
	}
	
	int hash(long number) {
		return (int) (Math.abs(number % size));
	}
	
	public void insert() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Name: ");
		String name = sc.nextLine();
		
		long number;
		while(true) {
			System.out.println("Enter 10 digit number: ");
			number = sc.nextLong();
			sc.nextLine();
			
			if(number >= 1000000000L && number <= 9999999999L) {
				break;
			}
			else {
				System.out.print("Enter a valid digit number.");
			}
		}
		
		String email;
        while (true) {
            System.out.print("Enter Email: ");
            email = sc.nextLine();
            
            if (email.contains("@") && email.contains(".")) {
                break;
            } else {
                System.out.println("Invalid email! Must contain '@' and '.'");
            }
        }
		
		Contact newCon = new Contact(number, name, email);
		int index = hash(number);
		int startindex = index;
		
		while(hashtable[index] != null) {
			index = (index + 1) % size;
			
			if(index == startindex) {
				System.out.println("Hash table full");
				return;
			}
		}
		hashtable[index] = newCon;
		System.out.println("Inserted Successfully at index: " + index);
	}
	
	public void searchbyname() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter name to search: ");
		String name = sc.nextLine();
		
		for (int i = 0; i < size; i++) {
			if(hashtable[i]!= null && hashtable[i].name.equalsIgnoreCase(name)) {
				Contact c = hashtable[i];
				System.out.println("Index: "+ i + " | "+ c.name +" | "+ c.number +" | "+ c.email);
				return;
			}
		}
		System.out.println("Contact not found.");
	}
	
	public void searchbynumber() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Number to search: ");
		long number = sc.nextLong();
		
		int index = hash(number);
		int startindex = index;
		
		while(hashtable[index]!= null) {
			if(hashtable[index].number == number) {
				Contact c = hashtable[index];
				System.out.println("Index: "+ index + " | "+ c.name +" | "+ c.number +" | "+ c.email);
				return;
			}
			index = (index + 1) % size;
			if (index == startindex)
				break;
		}System.out.println("Contact not found");
	}
	
	public void display() {
		
		
		for(int i = 0; i < size; i++) {
			Contact c = hashtable[i];
			if(c == null) {
				System.out.println("Index: "+ i + ": Empty");
			}
			else {
			System.out.println("Index: "+ i + " | "+ c.name +" | "+ c.number +" | "+ c.email);
			}
		}
		
	}
	public void delete() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Number to delete: ");
		long number = sc.nextLong();
		
		int index = hash(number);
		int startindex = index;
		
		while(hashtable[index]!= null) {
			if(hashtable[index].number == number) {
				Contact c = hashtable[index];
				hashtable[index] = null;
				System.out.println("Deleted: "+ c.name +" | "+ c.number +" | "+ c.email);
				return;
			}
			index = (index + 1) % size;
			if (index == startindex)
				break;
		}System.out.println("Contact not found");
	}
}

public class Assignment_09 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the size of the hashtable: ");
		int size = sc.nextInt();
		
		ContactList t = new ContactList(size);
		int choice;
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
			
			switch(choice) {
				
				case 1 -> t.insert();
				case 2 -> t.searchbyname();
				case 3 -> t.searchbynumber();
				case 4 -> t.display();
				case 5 -> t.delete();
				case 6 -> System.out.println("Exiting");
				default -> System.out.println("Enter a valid choice");
			}
		}while (choice != 6);

	}

}
