package jdbc_display;
import java.util.*;
import java.sql.*;

public class jdbc_display {
	public static void main(String [] args) {
		try {
			String url = "jdbc:mysql://localhost:3306/nouf1";
			String username = "root";
			String password = "nouf0125";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded Succesfully");
			
			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connection established succesfully");
			
			String displayquery = "Select * from emp";
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery(displayquery);
			
			System.out.println("EMPLOYEE DEATIALS: ");
			System.out.println("--------------------------------");
			
			int count = 0;
			while(resultset.next()) {
				System.out.println(
						resultset.getInt("empno") + " | " +
						resultset.getString("ename") + " | "+
						resultset.getString("job") + " | " +
						resultset.getDouble("salary") + " | " +
						resultset.getDouble("commission") + " | " +
						resultset.getDate("date_of_joining") + " | " +
						resultset.getInt("dept_no"));
						count++;
			}
			
		}catch(ClassNotFoundException e) {
			System.out.println("Error in loading driver." + e.getMessage());
		}catch(SQLException e) {
			System.out.println("Connection error." + e.getMessage());
		}
	}
}
