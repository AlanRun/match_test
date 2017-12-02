package interfaceTest;

import java.util.Date;

import helper.AppReq;
import helper.DataUrls;
import helper.UserInfo;

public class Info100626Test {
	
	public static void informByUser(String userID, String token, String mobile) throws Exception{
		
		String url = DataUrls.url_info;
		String params = DataUrls.params_100626;
		
		String hParams = "username," + mobile + ";token," + token + ";userID," + userID;
		String bParams = "";
		params = AppReq.setParmas(params, hParams, bParams);
		System.out.println(params);
		String reString = AppReq.getResStr(url, params);
		System.err.println(reString);
	}
	
	public static void test(int s) throws Exception{
		
		for (int i = s; i < s+10; i++) {
			String mobile = "1342223";
			if (i < 10) {
				mobile = mobile + "000" + i;
			} else if ( i < 100) {
				mobile = mobile + "00" + i;
			} else if (i < 1000) {
				mobile = mobile + "0" + i;
			} else if (i < 10000) {
				mobile = mobile + i;
			}
			UserInfo user = UserBaseInfo.getUserInfo(mobile, "aaaaaa");
			String token = user.getToken();
			String userID = user.getUserId();
			System.out.println(mobile);
			informByUser(userID, token, mobile);
		}
	}

	public static void main(String[] args) throws Exception {
		long t1 = System.currentTimeMillis();
		System.out.println(t1);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					test(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

}
