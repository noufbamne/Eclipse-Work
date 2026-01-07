package mock;
import java.util.*;
import java.sql.*;

public class Mock {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String url = "jdbc:mysql://localhost:3306/nouf1";
		String username = "root";
		String password = "nouf0125";
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded Successfully.");
			
			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.print("Connection esatblished succesfully.");
			
		
			while(true) {
				
				System.out.println("MENU");
				System.out.println("1. Insert");
				System.out.println("2. Update");
				System.out.println("3. Delete");
				System.out.println("4. Display");
				System.out.println("5. Exit");
						
				System.out.println("Enter choice: ");
				int choice =sc.nextInt();
						
				switch(choice) {
						
					case 1:
						try {
							
							System.out.println("Enter emp no: ");
							int empno = sc.nextInt();
							sc.nextLine();
							
							System.out.println("Enter Name: ");
							String ename = sc.nextLine();
							sc.nextLine();
							
							System.out.print("Enter Job: ");
							String job = sc.nextLine();
							sc.nextLine();

							System.out.print("Enter Salary: ");
							double salary = sc.nextDouble();
							sc.nextLine();

							System.out.print("Enter Commission: ");
							double commission = sc.nextDouble();
							sc.nextLine();

							System.out.print("Enter Date of Joining (YYYY-MM-DD): ");
							String doj = sc.nextLine();
							sc.nextLine();

							System.out.print("Enter Department No: ");
							int deptno = sc.nextInt();
							sc.nextLine();
							
							String insertQuery = "INSERT INTO emp VALUES(?, ?, ?, ?, ?, ?, ?)";
							PreparedStatement preparedstatement = connection.prepareStatement(insertQuery);
							
							preparedstatement.setInt(1, empno);
							preparedstatement.setString(2, ename);
							preparedstatement.setString(3, job);
							preparedstatement.setDouble(4, salary);
							preparedstatement.setDouble(5, commission);
							preparedstatement.setString(6, doj);
							preparedstatement.setInt(7, deptno);
							
							int rowsaffected = preparedstatement.executeUpdate();
							
							if(rowsaffected > 0) {
								System.out.println("Number of rowsaffected: "+rowsaffected);
							}else {
								System.out.println("0 rows affceted.");
							}
						}catch (SQLException e) {
							System.out.print("Connection Error." + e.getMessage());
						}
						break;
						
					case 2:
						try {
							System.out.println("Enter new salary: ");
							double newsalary = sc.nextDouble();
							
							System.out.println("Enter empno to update: ");
							int empno = sc.nextInt();
							
							String updateQuery = "UPDATE emp SET salary = ? WHERE empno = ?";
							PreparedStatement preparedstatement = connection.prepareStatement(updateQuery);
							
							preparedstatement.setDouble(1, newsalary);
							preparedstatement.setDouble(2, empno);
							
							int rowsaffected = preparedstatement.executeUpdate();
							
							if(rowsaffected > 0) {
								System.out.println("Number of rowsaffected: "+rowsaffected);
							}else {
								System.out.println("0 rows affceted.");
							} 
							
						}catch (SQLException e) {
							System.out.print("Connection Error." + e.getMessage());
						}
						break;
					case 3:
						try {
							System.out.println("Enter empno to delete: ");
							int empno = sc.nextInt();
							
							String deleteQuery = "Delete from emp WHERE empno = ?";
							PreparedStatement preparedstatement = connection.prepareStatement(deleteQuery);
							
							preparedstatement.setInt(1, empno);
							
							int rowsaffected = preparedstatement.executeUpdate();
							
							if(rowsaffected > 0) {
								System.out.println("Number of rowsaffected: "+rowsaffected);
							}else {
								System.out.println("0 rows affceted.");
							}
							
						}catch (SQLException e) {
							System.out.print("Connection Error." + e.getMessage());
						}
						break;
					case 4:
						try {
							String displayQueury = "Select * from emp";
							Statement statement = connection.createStatement();
							ResultSet resultset = statement.executeQuery(displayQueury);
							
							System.out.println("Emp details");
							System.out.println("EmpNo | Name | Job | Salary | Commission | DOJ |DeptNo");
							
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
							
						}catch (SQLException e) {
							System.out.print("Connection Error." + e.getMessage());
						}
						break;
					case 5:
						connection.close();
						System.out.println("Connection closed succesfully.");
						break;
					default: 
						System.out.print("Invalid choice");
				}
			}

		}catch(ClassNotFoundException e) {
			System.out.print("Error in Loading Driver" + e.getMessage());
		}catch (SQLException e) {
			System.out.print("Connection Error." + e.getMessage());
		}
	}
}
