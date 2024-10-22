package coms363;
import java.sql.*;

public class DropTables {
	private static Connection connect = null;
	
	public static void main(String[] args) {
		try {
			//Set up connection parameters
			String userName = "test"; // FOR THE PROJECT
			//String userName = "coms363"; // FOR MY PERSONAL USE
			String password = "password";
			String dbServer = "jdbc:mysql://localhost:3306/project1_del_data"; // FOR THE PROJECT
			//String dbServer = "jdbc:mysql://localhost:3306/project1"; // FOR MY PERSONAL USE
			//Set up connection
			connect = DriverManager.getConnection(dbServer,userName,password);
		} catch(Exception e) {
			
		}
		
		
		Statement sqlStmt = null;
		
		
		// Drop tables
		try {
			sqlStmt = connect.createStatement();
			sqlStmt.addBatch("DROP TABLE major;\r\n");
			sqlStmt.addBatch("DROP TABLE minor;\r\n");
			sqlStmt.addBatch("DROP TABLE degrees;\r\n");
			sqlStmt.addBatch("DROP TABLE register;\r\n");
			sqlStmt.addBatch("DROP TABLE courses;\r\n");
			sqlStmt.addBatch("DROP TABLE students;\r\n");
			sqlStmt.addBatch("DROP TABLE departments;\r\n");
			sqlStmt.executeBatch();
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