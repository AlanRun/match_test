package helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import utils.DBHelper;

public class SmsTest {

	/**
	 * 
	 * @param type: huidu/zz/aoying
	 * @param num: phone number
	 * @return
	 */
	public static String getSmsCode(String type, String num) {
		Date d = new Date();
		Long time = d.getTime();
		d = new Date(time - 6 * 60 *1000);
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss"); 
		String date = formatter.format(d);
		
		// 测试环境主站
//		String url = "jdbc:mysql://192.168.138.254:3306/";
//		String password = "jdd.com";
//		String user = "root";
		
		// 测试环境傲赢
//		String url = "jdbc:mysql://192.168.138.254:3307/";
//		String password = "jdd@3307";
//		String user = "jdd";
		
		String db = "jdd_sms";
		String sql = "SELECT s_body FROM sms_message where s_mobile_number= " + num + " and d_update_time>'" + date  + "' order by d_update_time desc;";
		
		DBHelper db1 = new DBHelper(type, db, sql);
		ResultSet ret = null;
		String code = "";
		try {
			for (int i = 0; i < 10; i++) {
				ret = db1.pst.executeQuery();
				while (ret.next()) {
					String body = ret.getString(1);
					if (body.contains("您的初始密码")) {
						continue;
					}
					System.out.println(body);
					if (body.contains(",")) {
						String s1 = body.split(",")[0];
						code = s1.split(":")[1];
					} else if (body.contains("，")) {
						String s1 = body.split("，")[0];
						code = s1.split("：")[1];
					} else {
						code = body.split(":")[1];
					}
					code = code.trim();
					break;
				}
				if (code.equals("")) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					break;
				}
			}
			ret.close();
			db1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return code;
	}

	public static void main(String[] args) {
		System.out.println(getSmsCode("aoying", "13811110021"));
	}
}