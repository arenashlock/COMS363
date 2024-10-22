package coms363;
import java.sql.*;

public class InsertRecords {
	private static Connection connect = null;
	
	public static void main(String[] args) {
		try {
			//Set up connection parameters
			String userName = "coms363";
			String password = "password";
			String dbServer = "jdbc:mysql://localhost:3306/project1?allowLoadLocalInfile=true";
			//Set up connection
			connect = DriverManager.getConnection(dbServer,userName,password);
		} catch(Exception e) {
			
		}

		
		Statement sqlStmt = null;
		
				
		// Load student data
		try {
			sqlStmt = connect.createStatement();
			String load_student = "LOAD DATA LOCAL INFILE '../../Documents/COMS363_data/data/students.csv'\r\n" +
			"INTO TABLE students\r\n" +
			"FIELDS TERMINATED BY ','\r\n" +
			"ENCLOSED BY '\"'\r\n" +
			"LINES TERMINATED BY '\\r\\n'\r\n" +
			"IGNORE 1 ROWS\r\n" +
			"(snum,ssn,name,gender,dob,c_addr,c_phone,p_addr,p_phone);\r\n";
			// To update data in a database, call the executeUpdate(String SQL) method
			sqlStmt.executeUpdate(load_student);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		// Load department data
		try {
			sqlStmt = connect.createStatement();
			String load_department = "LOAD DATA LOCAL INFILE '../../Documents/COMS363_data/data/departments.csv'\r\n" +
			"INTO TABLE departments\r\n" +
			"FIELDS TERMINATED BY ','\r\n" +
			"ENCLOSED BY '\"'\r\n" +
			"LINES TERMINATED BY '\\r\\n'\r\n" +
			"IGNORE 1 ROWS\r\n" +
			"(code,name,phone,college);\r\n";
			// To update data in a database, call the executeUpdate(String SQL) method
			sqlStmt.executeUpdate(load_department);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		// Load degrees data
		try {
			sqlStmt = connect.createStatement();
			String load_degrees = "LOAD DATA LOCAL INFILE '../../Documents/COMS363_data/data/degrees.csv'\r\n" +
			"INTO TABLE degrees\r\n" +
			"FIELDS TERMINATED BY ','\r\n" +
			"ENCLOSED BY '\"'\r\n" +
			"LINES TERMINATED BY '\\r\\n'\r\n" +
			"IGNORE 1 ROWS\r\n" +
			"(name,level,department_code);\r\n";
			// To update data in a database, call the executeUpdate(String SQL) method
			sqlStmt.executeUpdate(load_degrees);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		// Load major data
		try {
			sqlStmt = connect.createStatement();
			String load_major = "LOAD DATA LOCAL INFILE '../../Documents/COMS363_data/data/major.csv'\r\n" +
			"INTO TABLE major\r\n" +
			"FIELDS TERMINATED BY ','\r\n" +
			"ENCLOSED BY '\"'\r\n" +
			"LINES TERMINATED BY '\\r\\n'\r\n" +
			"IGNORE 1 ROWS\r\n" +
			"(snum,name,level);\r\n";
			// To update data in a database, call the executeUpdate(String SQL) method
			sqlStmt.executeUpdate(load_major);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		// Load minor data
		try {
			sqlStmt = connect.createStatement();
			String load_minor = "LOAD DATA LOCAL INFILE '../../Documents/COMS363_data/data/minor.csv'\r\n" +
			"INTO TABLE minor\r\n" +
			"FIELDS TERMINATED BY ','\r\n" +
			"ENCLOSED BY '\"'\r\n" +
			"LINES TERMINATED BY '\\r\\n'\r\n" +
			"IGNORE 1 ROWS\r\n" +
			"(snum,name,level);\r\n";
			// To update data in a database, call the executeUpdate(String SQL) method
			sqlStmt.executeUpdate(load_minor);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
				
				
				
				
		// Load courses data
		try {
			sqlStmt = connect.createStatement();
			String load_courses = "LOAD DATA LOCAL INFILE '../../Documents/COMS363_data/data/courses.csv'\r\n" +
			"INTO TABLE courses\r\n" +
			"FIELDS TERMINATED BY ','\r\n" +
			"ENCLOSED BY '\"'\r\n" +
			"LINES TERMINATED BY '\\r\\n'\r\n" +
			"IGNORE 1 ROWS\r\n" +
			"(number,name,description,credithours,level,department_code);\r\n";
			// To update data in a database, call the executeUpdate(String SQL) method
			sqlStmt.executeUpdate(load_courses);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
					e.printStackTrace();
		}
				
				
				
				
				
		// Load register data
		try {
			sqlStmt = connect.createStatement();
			String load_register = "LOAD DATA LOCAL INFILE '../../Documents/COMS363_data/data/register.csv'\r\n" +
			"INTO TABLE register\r\n" +
			"FIELDS TERMINATED BY ','\r\n" +
			"ENCLOSED BY '\"'\r\n" +
			"LINES TERMINATED BY '\\r\\n'\r\n" +
			"IGNORE 1 ROWS\r\n" +
			"(snum,course_number,regtime,grade);\r\n";
			// To update data in a database, call the executeUpdate(String SQL) method
			sqlStmt.executeUpdate(load_register);
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