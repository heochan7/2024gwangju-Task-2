package pro;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBCon {
	Connection con;
	public DBCon() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodfind?allowLoadLocalInfile=true&serverTimezone=UTC","root","1234");
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public Connection getConnection() {
		return con;
	}
}
