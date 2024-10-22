package coms363;
import java.sql.*;

public class Query {
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
			
			//To execute a SELECT query, call the executeQuery(String) method with the SQL to use
			String query_average_grades = "SELECT number, name, AVG(register.grade) FROM courses JOIN register ON register.course_number = courses.number GROUP BY number, name;";
			ResultSet rs = sqlStmt.executeQuery(query_average_grades);
			while(rs.next()){
				//Display values
				System.out.println(rs.getInt("number") + "|" + rs.getString("name") + "|" + rs.getInt("AVG(register.grade)"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		try {
			sqlStmt = connect.createStatement();
			
			//To execute a SELECT query, call the executeQuery(String) method with the SQL to use
			String count_managed = "SELECT COUNT(students.snum) FROM students JOIN (SELECT snum FROM major JOIN (SELECT degrees.name, degrees.level FROM degrees JOIN departments ON degrees.department_code=departments.code WHERE departments.college=\"LAS\") T1 ON major.name=T1.name AND major.level=T1.level) T2 ON students.snum=T2.snum JOIN (SELECT snum FROM minor JOIN (SELECT degrees.name, degrees.level FROM degrees JOIN departments ON degrees.department_code=departments.code WHERE departments.college=\"LAS\") T3 ON minor.name=T3.name AND minor.level=T3.level) T4 ON students.snum=T4.snum WHERE students.gender=\"F\";";
			ResultSet rs = sqlStmt.executeQuery(count_managed);
			while(rs.next()){
				//Display values
				System.out.println(rs.getInt("COUNT(students.snum)"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		try {
			sqlStmt = connect.createStatement();
			
			//To execute a SELECT query, call the executeQuery(String) method with the SQL to use
			String more_male = "SELECT MALE.name, MALE.level\n"
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
			ResultSet rs = sqlStmt.executeQuery(more_male);
			while(rs.next()){
				//Display values
				System.out.println(rs.getString("name") + "|" + rs.getString("level"));
			}
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