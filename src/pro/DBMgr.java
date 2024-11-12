package pro;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBMgr {
	static String sql;
	
	static Connection con = null;
	static Statement stmt =  null;
	static ResultSet rs = null;
	
	DBCon conn;
	public DBMgr() {
		conn = new DBCon();
	}
	public void update() {
		try {
			con = conn.getConnection();
			stmt= con.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public ArrayList<Bean>a(int co){
		ArrayList<Bean>list = new ArrayList<>();
		Bean bean;
		try {
			con = conn.getConnection();
			stmt = con.createStatement();
			rs=  stmt.executeQuery(sql);
			while(rs.next()) {
				bean = new Bean();
				ArrayList<Object>ob = new ArrayList<>();
				for(int i=1; i<=co; i++) {
					ob.add(rs.getObject(i));
				}
				bean.setA(ob);
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
}
