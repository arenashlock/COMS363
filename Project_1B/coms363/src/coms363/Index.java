package coms363;
import java.sql.*;
import com.github.javafaker.Faker;
import java.text.SimpleDateFormat;
//import java.util.Date;

public class Index {
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
		
		
		try {
			sqlStmt = connect.createStatement();
			Faker faker = new Faker();
			
			for(int i = 0; i < 5000; i++) {
				String snum = String.valueOf(1101 + i);
				
				String ssn = String.valueOf(faker.number().numberBetween(100000000, 999999999));
				
				String name = "'" + faker.name().firstName() + "'";
				
				String gender = null;
					if(faker.bool().bool()) {
						gender = "'F'";
					} else {
						gender = "'M'";
					}
					
				SimpleDateFormat formatter = new SimpleDateFormat("M/dd/yyyy");
				String dob = "'" + formatter.format(faker.date().birthday()) + "'";
				
				String c_addr = "'" + faker.address().buildingNumber() + " " + faker.address().stateAbbr() + "'";
				
				String c_phone = "'" + faker.phoneNumber().subscriberNumber(10) + "'";
				
				String p_addr = "'" + faker.address().buildingNumber() + " " + faker.address().stateAbbr() + "'";
				
				String p_phone = "'" + faker.phoneNumber().subscriberNumber(10) + "'";
				
				String create_more_student = "INSERT INTO students VALUE ("
				+ snum + ","
				+ ssn + ","
				+ name + ","
				+ gender + ","
				+ dob + ","
				+ c_addr + ","
				+ c_phone + ","
				+ p_addr + ","
				+ p_phone
				+ ");";
				
				sqlStmt.executeUpdate(create_more_student);
				
				
				
				String[] degree_names = {"'Applied Mathematics'", "'Chemical Engineering'", "'Computer Science'", "'Landscape Architect'", "'Software Engineering'"};
				String[] degree_levels = {"'BS'", "'MS'", "'PhD'"};
				
				int insert_table = faker.number().numberBetween(0, 3);
				
				if(insert_table == 0) {
					String degree_name = degree_names[faker.number().numberBetween(0, 5)];
					String degree_level = degree_levels[faker.number().numberBetween(0, 3)];
					
					String create_more_major = "INSERT INTO major VALUE ("
					+ snum + ","
					+ degree_name + ","
					+ degree_level
					+ ");";
					
					sqlStmt.executeUpdate(create_more_major);
				}
				else if(insert_table == 1) {
					String degree_name = degree_names[faker.number().numberBetween(0, 5)];
					String degree_level = degree_levels[faker.number().numberBetween(0, 3)];
					
					String create_more_minor = "INSERT INTO minor VALUE ("
					+ snum + ","
					+ degree_name + ","
					+ degree_level
					+ ");";
					
					sqlStmt.executeUpdate(create_more_minor);
				}
				else {
					String degree_name = degree_names[faker.number().numberBetween(0, 5)];
					String degree_level = degree_levels[faker.number().numberBetween(0, 3)];
					
					String create_more_major = "INSERT INTO major VALUE ("
					+ snum + ","
					+ degree_name + ","
					+ degree_level
					+ ");";
					
					sqlStmt.executeUpdate(create_more_major);
					
					degree_name = degree_names[faker.number().numberBetween(0, 5)];
					degree_level = degree_levels[faker.number().numberBetween(0, 3)];
					
					String create_more_minor = "INSERT INTO minor VALUE ("
					+ snum + ","
					+ degree_name + ","
					+ degree_level
					+ ");";
					
					sqlStmt.executeUpdate(create_more_minor);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		try {
			sqlStmt = connect.createStatement();
			
			//Date start = new Date();
			
			//To execute a SELECT query, call the executeQuery(String) method with the SQL to use
			String before_index = "SELECT MALE.name, MALE.level\n"
					+ "FROM\n"
					+ "(SELECT T4.name, T4.level, COUNT(T4.snum) as male_count\n"
					+ "FROM (\n"
					+ "SELECT students.snum, T3.name, T3.level\n"
					+ "FROM students\n"
					+ "JOIN (SELECT snum, T1.name, T1.level\n"
					+ "FROM (SELECT snum, degrees.name, degrees.level FROM degrees JOIN major ON degrees.name=major.name AND degrees.level=major.level) T1\n"
					+ "UNION\n"
					+ "SELECT snum, T2.name, T2.level\n"
					+ "FROM (SELECT snum, degrees.name, degrees.level FROM degrees JOIN minor ON degrees.name=minor.name AND degrees.level=minor.level) T2) T3 ON students.snum=T3.snum\n"
					+ "WHERE students.gender=\"M\") T4\n"
					+ "GROUP BY T4.name, T4.level) MALE\n"
					+ "JOIN\n"
					+ "(SELECT T9.name, T9.level, COUNT(T9.snum) as female_count\n"
					+ "FROM (\n"
					+ "SELECT students.snum, T8.name, T8.level\n"
					+ "FROM students\n"
					+ "JOIN (SELECT snum, T6.name, T6.level\n"
					+ "FROM (SELECT snum, degrees.name, degrees.level FROM degrees JOIN major ON degrees.name=major.name AND degrees.level=major.level) T6\n"
					+ "UNION\n"
					+ "SELECT snum, T7.name, T7.level\n"
					+ "FROM (SELECT snum, degrees.name, degrees.level FROM degrees JOIN minor ON degrees.name=minor.name AND degrees.level=minor.level) T7) T8 ON students.snum=T8.snum\n"
					+ "WHERE students.gender=\"F\") T9\n"
					+ "GROUP BY T9.name, T9.level) FEMALE\n"
					+ "ON MALE.name=FEMALE.name AND MALE.level=FEMALE.level\n"
					+ "WHERE male_count > female_count;";
			sqlStmt.executeQuery(before_index);
			
			//Date end = new Date();
			//System.out.println("Before: " + (end.getTime() - start.getTime()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		try {
			sqlStmt = connect.createStatement();
			
			//Date start = new Date();
			
			//To execute a SELECT query, call the executeQuery(String) method with the SQL to use
			sqlStmt.execute("CREATE INDEX idx_gender ON students (gender);");
			String after_index = "SELECT MALE.name, MALE.level\n"
					+ "FROM\n"
					+ "(SELECT T4.name, T4.level, COUNT(T4.snum) as male_count\n"
					+ "FROM (\n"
					+ "SELECT students.snum, T3.name, T3.level\n"
					+ "FROM students\n"
					+ "JOIN (SELECT snum, T1.name, T1.level\n"
					+ "FROM (SELECT snum, degrees.name, degrees.level FROM degrees JOIN major ON degrees.name=major.name AND degrees.level=major.level) T1\n"
					+ "UNION\n"
					+ "SELECT snum, T2.name, T2.level\n"
					+ "FROM (SELECT snum, degrees.name, degrees.level FROM degrees JOIN minor ON degrees.name=minor.name AND degrees.level=minor.level) T2) T3 ON students.snum=T3.snum\n"
					+ "WHERE students.gender=\"M\") T4\n"
					+ "GROUP BY T4.name, T4.level) MALE\n"
					+ "JOIN\n"
					+ "(SELECT T9.name, T9.level, COUNT(T9.snum) as female_count\n"
					+ "FROM (\n"
					+ "SELECT students.snum, T8.name, T8.level\n"
					+ "FROM students\n"
					+ "JOIN (SELECT snum, T6.name, T6.level\n"
					+ "FROM (SELECT snum, degrees.name, degrees.level FROM degrees JOIN major ON degrees.name=major.name AND degrees.level=major.level) T6\n"
					+ "UNION\n"
					+ "SELECT snum, T7.name, T7.level\n"
					+ "FROM (SELECT snum, degrees.name, degrees.level FROM degrees JOIN minor ON degrees.name=minor.name AND degrees.level=minor.level) T7) T8 ON students.snum=T8.snum\n"
					+ "WHERE students.gender=\"F\") T9\n"
					+ "GROUP BY T9.name, T9.level) FEMALE\n"
					+ "ON MALE.name=FEMALE.name AND MALE.level=FEMALE.level\n"
					+ "WHERE male_count > female_count;";
			sqlStmt.executeQuery(after_index);
			
			//Date end = new Date();
			//System.out.println("After: " + (end.getTime() - start.getTime()));
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