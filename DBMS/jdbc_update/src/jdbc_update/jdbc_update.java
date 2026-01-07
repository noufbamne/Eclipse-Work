package jdbc_update;
import java.util.*;
import java.sql.*;

public class jdbc_update {

	public static void main(String[] args) {
		
		try {
			 
			String url="jdbc:mysql://localhost:3306/nouf1";
			String username = "root";
			String password = "nouf0125";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.print("Driver loaded succesfully");
			
			
			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.print("Connection established");
			try {
				Scanner sc = new Scanner(System.in);
				
				System.out.print("Enter empno to update: ");
				int empno = sc.nextInt();
				
				System.out.print("Enter newsalary: ");
				int newsalary  = sc.nextInt();
				
				String updatequery = "Update emp SET salary = ? WHERE empno = ?";
				PreparedStatement preparedstatement = connection.prepareStatement(updatequery);
				
				preparedstatement.setInt(1, newsalary);
				preparedstatement.setInt(2, empno);
				
				int rowsaffected = preparedstatement.executeUpdate();
				if(rowsaffected > 0) {
					System.out.print("Rowsaffected: " + rowsaffected);
				}
				
			}catch(SQLException e) {
				System.out.println("Update failed" + e.getMessage());
			}
			
		}catch(ClassNotFoundException e) {
			System.out.println("Error in loading Driver"+ e.getMessage());
		}catch(SQLException e) {
			System.out.println("Connection error" + e.getMessage());
		}

	}

}
