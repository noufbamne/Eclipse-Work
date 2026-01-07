package assignment_01;
import java.util.*;

class Book{
	int isbn;
	String name;
	String authorname;
	
	public Book(String name, String authorname, int isbn) {
		this.name = name;
		this.authorname = authorname;
		this.isbn = isbn;
	}
	
	public String toString() {
		return ("Name: " + name + " " +  "Author Name: " + authorname + " " + "ISBN NO: " + isbn); 
	}
}

class Library{
	
	Scanner sc = new Scanner (System.in);
	Book[] theBooks = new Book[50];
	int count = 0;
	
	public void addBook() {
		if(count >= theBooks.length) {
			System.out.println("Library full");
			return;
		}
		
		System.out.print("Enter Book name: ");
		String name = sc.nextLine();
		
		System.out.print("Enter Author name: ");
		String authorname = sc.nextLine();
		
		System.out.print("Enter ISBN no: ");
		int isbn = sc.nextInt();
		sc.nextLine();
		
		theBooks[count++] = new Book(name, authorname, isbn);
		
		System.out.print("Book added into the Library!");
	}
	
	
	public void display() {
		if(count == 0) {
			System.out.println("Library empty.");
			return;
		}
		
		for(int i = 0; i < count; i++) {
			System.out.print(theBooks[i]);
		}
	}
	public void search_isbn_binary() {
		
		if(count == 0) {
			System.out.println("Library empty.");
			return;
		}
		
		System.out.print("Enter the ISBN number to search the book");
		int target = sc.nextInt();
		sc.nextLine();
		
		int start = 0;
		int end = count -1;
		int result =-1;
		
		while(start <= end) {
			int mid = start + (end - start) /2 ;
			
			if(theBooks[mid].isbn == target) {
				result = mid;
				break;
			}
			
			if(theBooks[mid].isbn < target) {
				end = mid -1;
			}
			else {
				start = mid +1;
			}
		}
		
		if(result != -1) {
			System.out.println("Book found : " + theBooks[result]);
		}else {
			System.out.println("Book not found with ISBN no: "+ target);
		}
	}
	
	public void search_author_linear() {
		if(count == 0) {
			System.out.println("Library empty.");
			return;
		}
		
		System.out.print("Enter the Author name to search the book");
		String authorname = sc.nextLine();
		boolean found = false;
		
		for(int i = 0; i < count; i++) {
			if(theBooks[i].authorname.equalsIgnoreCase(authorname)) {
				System.out.println(theBooks[i]);
				found = true;
			}
		}
		if(!found) {
			System.out.println("Book not found wiht Author name: " + authorname);
		}
	}


	public void sort() {
		
		if(count == 0) {
			System.out.println("Library empty.");
			return;
		}
		System.out.println("\nChoose Sorting Algorithm:");
        System.out.println("1. Bubble Sort");
        System.out.println("2. Insertion Sort");
        System.out.println("3. Quick Sort");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) { 
            case 1 -> bubbleSort();
            case 2 -> insertionSort();
            case 3 -> quickSort(0, count - 1);
            default -> System.out.println("Invalid choice.");
        }
		
        System.out.print("Books sorted succesfully.");
	}


	public void quickSort(int start, int end) {
		if(start >= end) {
			return;
		}
		
		int mid = start +(end - start) /2;
		int pivot = theBooks[mid].isbn;
		int left = start;
		int right = end;
		
		while(left <= right) {
			
			while(theBooks[left].isbn < pivot) left++;
			
			while(theBooks[right].isbn > pivot) right--;
			
			if(left <= right) {
				Book temp = theBooks[left];
				theBooks[left] = theBooks[right];
				theBooks[right] = temp;
				left++;
				right--;
			}
		}
		
		quickSort(start, right);
		quickSort(left, end);
	}


	public void insertionSort() {
		
		for(int i =1; i< count; i++) {
			Book key = theBooks[i];
			int j = i -1;
			
			while(j >=0 && theBooks[j].isbn > key.isbn) {
				theBooks[j+1] =theBooks[j];
				j = j-1;
			}
			theBooks[j+1] = key;
		}
	}


	public void bubbleSort() {
		
		for (int i = 0; i < count -1; i++) {
			boolean swapped = false;
			
			for(int j = 1; j < count -i; j++) {
				if(theBooks[j].isbn < theBooks[j-1].isbn) {
					Book temp = theBooks[j];
					theBooks[j] = theBooks[j-1];
					theBooks[j-1] = temp;
					swapped = true;
				}	
			}
			if(!swapped == true) break;
		}
	}
}

public class Assignment_01 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Library lib = new Library();
		int choice;
		char ans; 
		
		do {
			System.out.println("\n- Library Menu -");
            System.out.println("1. Add Book");
            System.out.println("2. Display All Books");
            System.out.println("3. Search by ISBN (Binary Search)");
            System.out.println("4. List all books by Author (Linear Search)");
            System.out.println("5. Sort Books by ISBN (Bubble / Insertion / Quick)");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            
            switch(choice) {
            case 1 : lib.addBook(); break;
            case 2 : lib.display();break;
            case 3 : lib.search_isbn_binary();break;
            case 4 : lib.search_author_linear();break;
            case 5 : lib.sort();break;
            case 0 : System.out.println("Exiting..");break;
            default : System.out.println("Enter a valid choice.");
            }
            
            System.out.print("Would you like to continue? (Y/N): ");
            ans = sc.next().charAt(0);
		}while(ans == 'Y' || ans == 'y');

	}

}
