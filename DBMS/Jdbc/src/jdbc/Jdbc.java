package jdbc;
import java.util.*;
import java.sql.*;

public class Jdbc {

	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/nouf1";
		String username = "root";
		String password = "nouf0125";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded successfully.");	
			
			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connection established successfully.");
			
			try {
				
				Scanner sc = new Scanner(System.in);
				
				System.out.println("Enter emp no: ");
				int empno = sc.nextInt();
				sc.nextLine();
				
				System.out.println("Enter Name: ");
				String ename = sc.nextLine();
				sc.nextLine();
				
				System.out.println("Enter Job: ");
				String job = sc.nextLine();
				sc.nextLine();

				System.out.println("Enter Salary: ");
				double salary = sc.nextDouble();
				sc.nextLine();

				System.out.println("Enter Commission: ");
				double commission = sc.nextDouble();
				sc.nextLine();

				System.out.println("Enter Date of Joining (YYYY-MM-DD): ");
				String doj = sc.nextLine();
				sc.nextLine();

				System.out.print("Enter Department No: ");
				int deptno = sc.nextInt();
				sc.nextLine();
				
				String insertquery = "Insert INTO emp values(?,?,?,?,?,?,?)";
				PreparedStatement preparedstatement = connection.prepareStatement(insertquery);
				
				preparedstatement.setInt(1, empno);
				preparedstatement.setString(2, ename);
				preparedstatement.setString(3, job);
				preparedstatement.setDouble(4, salary);
				preparedstatement.setDouble(5, commission);
				preparedstatement.setString(6, doj);
				preparedstatement.setInt(7, deptno);
				
				int rowsaffected = preparedstatement.executeUpdate();
				
				if(rowsaffected > 0) {
					System.out.print("Rowsaffected: " + rowsaffected);
				}
				
			}catch(SQLException e) {
				System.out.print("Insertion failed." + e.getMessage());
			}
			
		}catch(ClassNotFoundException e){
			System.out.print("Error in in loading Driver" + e.getMessage());		
		}catch(SQLException e) {
			System.out.print("Connection error" + e.getMessage());	
		}

	}

}
