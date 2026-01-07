package assignment_05;
import java.util.*;

class Stack{
	int maxSize;
	int top;
	int[] Stack;
	
	public Stack(int maxSize) {
		this.maxSize = maxSize;
		this.top = -1;
		Stack = new int[maxSize];
	}
	
	public boolean isEmpty() {
		return top == -1;	
	}
	
	public boolean isfull() {
		return top == maxSize -1;
	}
	
	public void push(int element) {
		if(isfull()) {
			System.out.println("Stack Overflow.");
		}else {
			Stack[++top] = element;
		}
	}
	
	public int pop() {
		if(isEmpty()) {
			System.out.println("Stack Underflow.");
			return -1;
		}else {
			return Stack[top--];
		}
	}
	
	public void Evaluate(String expr) {
		Scanner sc = new Scanner (System.in);
		
		Stack s = new Stack(expr.length());
		
		for(int i = 0; i < expr.length(); i++) {
			char ch = expr.charAt(i);
			
			if(Character.isDigit(ch)) {
				if(s.isfull()) {
					System.out.println("Stack Overflow.");
					System.out.println("Invalid Postfix Expression.");
					return;
				}
				s.push(ch - '0');
			}
			else if(ch == '+' || ch == '-' || ch == '*' || ch == '/') {
				int val2 = s.pop();
				if(val2 == -1 && s.isEmpty()) {
					System.out.println("Invalid Postfix Expression.");
					return;
				}
				
				int val1 = s.pop();
				if(val1 == -1 && s.isEmpty()) {
					System.out.println("Invalid Postfix Expression.");
					return;
				}
				
				switch(ch) {
				
				case '+':
					s.push(val1 + val2);
					break;
					
				case '-':
					s.push(val1 - val2);
					break;
					
				case '*':
					s.push(val1 * val2);
					break;
					
				case '/':
					
					if(val2 == 0) {
						System.out.println("Division by zero error.");
						return;
					}
					s.push(val1 / val2);
					break;
				}
			}else {
				System.out.println("Invalid operator.");
				return;
			}
		}
		if(s.top != 0) {
			System.out.println("Invalid postfix expression.");
		}else {
			System.out.println("Result: " + s.pop());
		}
	}
	
	public void reverse(String str) {
		Stack s = new Stack(str.length());
		
		for(int i = 0; i<str.length(); i++) {
			if(s.isfull()) {
				System.out.println("Stack Overflow.");
				return;
			}
			s.push(str.charAt(i));
		}
		System.out.print("Reversed String: ");
		while(!s.isEmpty()) {
			System.out.print((char) s.pop());
		}
		
		System.out.println();
	}
}
public class Assignment_05 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		char ans;
		
		do {
			System.out.println("===MENU===");
			System.out.println("1. Evaluate Postfix Expression");
            System.out.println("2. Reverse a String");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            
            switch(choice) {
            
            case 1:
            	System.out.println("Enter expression: ");
            	String expr = sc.next();
            	Stack evalStack = new Stack(expr.length());
            	evalStack.Evaluate(expr);
            	break;
            
            case 2:
            	System.out.println("Enter String: ");
            	String str = sc.next();
            	Stack revStack = new Stack(str.length());
            	revStack.reverse(str);
            	break;
            	
            default:
                System.out.println("Enter Valid Option!!");
            }
            
            System.out.println("Would you like to continue (Y/N): ");
            ans = sc.next().charAt(0);
			
		}while(ans == 'Y' || ans =='y');
		sc.close();
	}

}
