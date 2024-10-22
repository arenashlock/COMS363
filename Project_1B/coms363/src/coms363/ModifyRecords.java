package coms363;
import java.sql.*;

public class ModifyRecords {
	private static Connection connect = null;
	
	public static void main(String[] args) {
		try {
			//Set up connection parameters
			String userName = "coms363";
			String password = "password";
			String dbServer = "jdbc:mysql://localhost:3306/project1";
			//Set up connection
			connect = DriverManager.getConnection(dbServer,userName,password);
		} catch(Exception e) {
			
		}
		
		
		Statement sqlStmt = null;
		
		
		// Update SSN to Scott
		try {
			sqlStmt = connect.createStatement();
			String update_scott = "UPDATE students SET students.name=\"Scott\" WHERE students.ssn=144673371;";
			// To update data in a database, call the executeUpdate(String SQL) method
			sqlStmt.executeUpdate(update_scott);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		// Update major and level of snum
		try {
			sqlStmt = connect.createStatement();
			String update_major_level = "UPDATE major SET major.name=\"Computer Science\", major.level=\"MS\" WHERE major.snum=(SELECT students.snum FROM students WHERE students.ssn=144673371);";
			// To update data in a database, call the executeUpdate(String SQL) method
			sqlStmt.executeUpdate(update_major_level);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		// Delete Summer2024 registers
		try {
			sqlStmt = connect.createStatement();
			String delete_registers = "DELETE FROM register WHERE register.regtime=\"Summer2024\";";
			// To update data in a database, call the executeUpdate(String SQL) method
			sqlStmt.executeUpdate(delete_registers);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		// Delete duplicate courses
		try {
			sqlStmt = connect.createStatement();
			String delete_registers = "DELETE register, courses\n"
			+ "FROM courses\n"
			+ "LEFT JOIN register ON courses.number=register.course_number\n"
			+ "WHERE courses.number NOT IN (SELECT min_number FROM (SELECT MIN(courses.number) as min_number\n"
			+ "FROM courses\n"
			+ "GROUP BY courses.level, courses.department_code) T1);";
			// To update data in a database, call the executeUpdate(String SQL) method
			sqlStmt.executeUpdate(delete_registers);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		finally {
			try {
				// Close connection
				if (sqlStmt != null) {
					sqlStmt.close();
				}
				if (connect != null) {
					connect.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}