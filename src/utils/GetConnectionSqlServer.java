package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class GetConnectionSqlServer {
	public Connection getConnectionSqlServer() {

		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbURL = "jdbc:sqlserver://116.90.86.15:1433;DatabaseName=DataCenter_Odds";
		String userName = "sa01";
		String userPwd = "5jdk#18kg6fcd(1p%7q!x8";

		Connection dbConn = null;
		try {
			Class.forName(driverName).newInstance();
		} catch (Exception ex) {
			System.out.println("驱动加载失败");
			ex.printStackTrace();
		}
		try {
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
//			System.out.println("成功连接数据库！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbConn;
	}
}