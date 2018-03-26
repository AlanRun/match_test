package activity;

import helper.AppReq;
import helper.DataUrls;
import helper.UserInfo;
import interfaceTest.UserBaseInfo;

public class OrderTest {
	
	private static String actTypeId = "116";
	private static String pw = "aaaaaa";


	public static void getActQualify(String userID, String token) throws Exception{
		String params = DataUrls.params_8000;
		String url = DataUrls.url_act;
		String suc = "领取资格成功";
		
		String hParams = "userID," + userID + ";token," + token;
		String bParams = "actTypeId," + actTypeId  ;
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
	}
	
	public static void Q(int s, int e) throws Exception{
		
		for (int i = s; i < e; i++) {
			String mobile = "1341111";
			if (i < 10) {
				mobile = mobile + "000" + i;
			} else if ( i < 100) {
				mobile = mobile + "00" + i;
			} else if (i < 1000) {
				mobile = mobile + "0" + i;
			} else if (i < 10000) {
				mobile = mobile + i;
			}
			UserInfo user = UserBaseInfo.getUserInfo(mobile, pw);
			String token = user.getToken();
			String userID = user.getUserId();
			
			getActQualify(userID, token);
		}
	}
	
	public static void O(int s, int e) throws Exception{
		for (int i = s; i < e; i++) {
			String mobile = "1341111";
			if (i < 10) {
				mobile = mobile + "000" + i;
			} else if ( i < 100) {
				mobile = mobile + "00" + i;
			} else if (i < 1000) {
				mobile = mobile + "0" + i;
			} else if (i < 10000) {
				mobile = mobile + i;
			}
			UserInfo user = UserBaseInfo.getUserInfo(mobile, pw);
			String token = user.getToken();
			String userID = user.getUserId();
			
			UserBaseInfo.buyJZ(userID, token, 1);
		}
	}

	public static void main(String[] args) throws Exception {
		int s = 1;
		int e = 1001;
		
//		Q(s, e);
		
		for (int i = 0; i < 2001; i++) {
			O(s, e);
		}
	}

}
