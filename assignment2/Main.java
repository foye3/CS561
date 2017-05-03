package assignment2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Main {
	public static void main(String[] args) {
		//database connection information
		String usr = "postgres";
		String pwd = "123456";
		String url = "jdbc:postgresql://localhost:5432/mydb";

		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Success loading Driver!");
		}

		catch (Exception e) {
			System.out.println("Fail loading Driver!");
			e.printStackTrace();
		}

		List<Sale> salelist = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(url, usr, pwd);
			System.out.println("Success connecting server!");

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Sales");

			while (rs.next()) {
				salelist.add(new Sale(rs.getString("cust"), rs.getString("prod"), rs.getInt("day"),
						rs.getInt("month"), rs.getInt("year"), rs.getInt("quant"), rs.getString("state")));
			}
		}

		catch (SQLException e) {
			System.out.println("Connection URL or username or password errors!");
			e.printStackTrace();
		}
		
		Generate ge = new Generate();
		ge.generateTable1(salelist);
		ge.generateTable2(salelist);
		ge.generateTable3(salelist);
		
	}
}
