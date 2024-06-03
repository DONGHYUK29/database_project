import java.io.*;
import java.sql.*;

public class sample2 {
	public static void main(String[] args) {
		Statement stmt;
		Connection conn;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/db1?serverTimezone=UTC","root","1234");
			System.out.println("DB connection complete");
			
			stmt = conn.createStatement();
			
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC drive road error");
		} catch (SQLException e) {
			System.out.println("DB connection error");
		} 

	
	}

	private static void printData(ResultSet srs, String col1, String col2, String col3) throws SQLException {
		while(srs.next()) {
			if (!col1.equals(""))
				System.out.print(srs.getString("고객이름"));
			if (!col2.equals(""))
				System.out.print("\t\t" + srs.getString("고객아이디"));
			if (!col3.equals(""))
				System.out.println("\t\t" + srs.getString("직업"));
		}
	}

}
