package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBHelper {
	public static final String name = "com.mysql.jdbc.Driver";
//	public static final String url = "jdbc:mysql://192.168.138.254:3306/";
//	public static final String user = "root";
//	public static final String password = "jdd.com";
	public static final String url = "jdbc:mysql://192.168.138.254:3307/";
	public static final String user = "jdd";
	public static final String password = "jdd@3307";
//	public static final String url = "jdbc:mysql://10.10.11.36:3306/";
//	public static final String user = "jdd_basedata_r";
//	public static final String password = "mptJAkj=v+3^WRZ&%hds";

	public Connection conn = null;
	public PreparedStatement pst = null;

	public DBHelper(String db, String sql) {
		try {
			Class.forName(name);// 指定连接类型
			conn = DriverManager.getConnection(url + db, user, password);// 获取连接
			pst = conn.prepareStatement(sql);// 准备执行语句
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DBHelper(String type, String db, String sql) {
		String URL;
		String USER;
		String PASSWORD;
		
		if (type.equals("aoying")) {
			USER = "jdd";
			PASSWORD = "jdd@3307";
			URL = "jdbc:mysql://192.168.138.254:3307/";
		} else if (type.equals("huidu")) {
			USER = "jdd_basedata_r";
			PASSWORD = "mptJAkj=v+3^WRZ&%hds";
			URL = "jdbc:mysql://10.10.11.36:3306/";
		} else {
			USER = "root";
			PASSWORD = "jdd.com";
			URL = "jdbc:mysql://192.168.138.254:3306/";
		}
		
		try {
			Class.forName(name);// 指定连接类型
			conn = DriverManager.getConnection(URL + db, USER, PASSWORD);// 获取连接
			pst = conn.prepareStatement(sql);// 准备执行语句
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DBHelper(String db, String sql, String URL, String USER, String PASSWORD) {
		try {
			Class.forName(name);// 指定连接类型
			conn = DriverManager.getConnection(URL + db, USER, PASSWORD);// 获取连接
			pst = conn.prepareStatement(sql);// 准备执行语句
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			this.conn.close();
			this.pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}