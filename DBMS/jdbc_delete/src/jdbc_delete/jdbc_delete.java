package jdbc_delete;
import java.util.*;
import java.sql.*;

public class jdbc_delete {

	public static void main(String[] args) {
		
		try {
			
			Scanner sc = new Scanner (System.in);
			
			String url = "jdbc:mysql://localhost:3306/nouf1";
			String username = "root";
			String password = "nouf0125";
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection(url,username, password);
				
				System.out.print("Enter emp no to delete: ");
				int empno = sc.nextInt();
				
				String deletequery =" Delete from emp WHERE empno = ?";
				PreparedStatement preparedstatement = connection.prepareStatement(deletequery);
				
				preparedstatement.setInt(1, empno);
				
				int rowsaffected = preparedstatement.executeUpdate();
				if(rowsaffected > 0) {
					System.out.print("rowsaffected : " + rowsaffected);
				}else {
					System.out.print("No employee number found.");
				}
			}catch(SQLException e) {
				System.out.println("Deletion failed" + e.getMessage());
			}
			
		}catch(ClassNotFoundException e) {
			System.out.println("Error in loadind Driver." + e.getMessage());
		}
	}

}
