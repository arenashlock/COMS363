package coms363;
import java.sql.*;

public class CreateTables {
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
		
		
		// Create student table
		try {
			sqlStmt = connect.createStatement();
			String create_student = "CREATE TABLE students (\r\n" +
			" snum integer,\r\n" +
			" ssn integer,\r\n" +
			" name varchar(20),\r\n" +
			" gender varchar(1),\r\n" +
			" dob varchar(10),\r\n" +
			" c_addr varchar(20),\r\n" +
			" c_phone varchar(10),\r\n" +
			" p_addr varchar(20),\r\n" +
			" p_phone varchar(10),\r\n" +
			" PRIMARY KEY(ssn),\r\n" +
			" UNIQUE(snum)\r\n" +
			");";
			// To update data in a database, call the executeUpdate(String SQL) method
			sqlStmt.executeUpdate(create_student);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		// Create departments table
		try {
			sqlStmt = connect.createStatement();
			String create_departments = "CREATE TABLE departments (\r\n" +
			" code integer,\r\n" +
			" name varchar(50),\r\n" +
			" phone varchar(10),\r\n" +
			" college varchar(20),\r\n" +
			" PRIMARY KEY (code),\r\n" +
			" UNIQUE (name)\r\n" +
			");";
			// To update data in a database, call the executeUpdate(String SQL) method
			sqlStmt.executeUpdate(create_departments);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		// Create degrees table
		try {
			sqlStmt = connect.createStatement();
			String create_degrees = "CREATE TABLE degrees (\r\n" +
			" name varchar(50),\r\n" +
			" level varchar(5),\r\n" +
			" department_code integer,\r\n" +
			" PRIMARY KEY (name, level),\r\n" +
			" FOREIGN KEY (department_code) REFERENCES departments(code)\r\n" +
			");";
			// To update data in a database, call the executeUpdate(String SQL) method
			sqlStmt.executeUpdate(create_degrees);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		// Create courses table
		try {
			sqlStmt = connect.createStatement();
			String create_courses = "CREATE TABLE courses (\r\n" +
			" number integer,\r\n" +
			" name varchar(50),\r\n" +
			" description varchar(50),\r\n" +
			" credithours integer,\r\n" +
			" level varchar(20),\r\n" +
			" department_code integer,\r\n" +
			" PRIMARY KEY (number),\r\n" +
			" FOREIGN KEY (department_code) REFERENCES departments(code)\r\n" +
			");";
			// To update data in a database, call the executeUpdate(String SQL) method
			sqlStmt.executeUpdate(create_courses);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		// Create register table
		try {
			sqlStmt = connect.createStatement();
			String create_register = "CREATE TABLE register (\r\n" +
			" snum integer,\r\n" +
			" course_number integer,\r\n" +
			" regtime varchar(20),\r\n" +
			" grade integer,\r\n" +
			" PRIMARY KEY (snum, course_number),\r\n" +
			" FOREIGN KEY (snum) REFERENCES students(snum),\r\n" +
			" FOREIGN KEY (course_number) REFERENCES courses(number) ON DELETE CASCADE\r\n" +
			");";
			// To update data in a database, call the executeUpdate(String SQL) method
			sqlStmt.executeUpdate(create_register);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
				
				
				
		// Create major table
		try {
			sqlStmt = connect.createStatement();
			String create_major = "CREATE TABLE major (\r\n" +
			" snum integer,\r\n" +
			" name varchar(50),\r\n" +
			" level varchar(5),\r\n" +
			" PRIMARY KEY (snum, name, level),\r\n" +
			" FOREIGN KEY (snum) REFERENCES students(snum),\r\n" +
			" FOREIGN KEY (name, level) REFERENCES degrees(name, level)\r\n" +
			");";
			// To update data in a database, call the executeUpdate(String SQL) method
			sqlStmt.executeUpdate(create_major);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
				
				
				
				
		// Create minor table
		try {
			sqlStmt = connect.createStatement();
			String create_minor = "CREATE TABLE minor (\r\n" +
			" snum integer,\r\n" +
			" name varchar(50),\r\n" +
			" level varchar(5),\r\n" +
			" PRIMARY KEY (snum, name, level),\r\n" +
			" FOREIGN KEY (snum) REFERENCES students(snum),\r\n" +
			" FOREIGN KEY (name, level) REFERENCES degrees(name, level)\r\n" +
			");";
			// To update data in a database, call the executeUpdate(String SQL) method
			sqlStmt.executeUpdate(create_minor);
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