package activity;

import helper.AppReq;
import helper.DataUrls;
import helper.UserInfo;
import interfaceTest.UserBaseInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class XNJFTest {

	public static boolean getCard7091(String name, String userID, String token) throws Exception{
		String params = DataUrls.params_7091;
		String url = DataUrls.url_act;
		String suc = "code\":1";
		
		String hParams = "userID," + userID + ";token," + token + ";username," + name;
		String bParams = "";
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		boolean result = false;
		if (reString.contains(suc)) {
			result = true;
		}
		return result;
	}
	
	public static boolean checkCard7092(String name, String userID, String token, int d) throws Exception{
		String params = DataUrls.params_7092;
		String url = DataUrls.url_act;
		String suc = "code\":1";
		
		String hParams = "userID," + userID + ";token," + token + ";username," + name;
		String bParams = "";
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		boolean result = false;
		if (reString.contains(suc)) {
			JSONObject obj = JSONObject.fromObject(reString);
			JSONObject data = obj.getJSONObject("data");
			JSONArray cards = data.getJSONArray("cards");
			
			JSONObject card = (JSONObject) cards.get(d-1);
			int status = card.getInt("status");
			if (status == 1) {
				result = true;
			}
		}
		return result;
	}
	
	public static void test(int start, int end, int d) throws Exception{
		for (int i = start; i < end; i++) {
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
			System.out.println(mobile);
			UserInfo user = UserBaseInfo.getUserInfo(mobile, "aaaaaa");
			String token = user.getToken();
			String userId = user.getUserId();
			getCard7091(mobile, userId, token);
			boolean result = checkCard7092(mobile, userId, token, d);
			if (result) {
				System.err.println(mobile +" Card get ok");
			} else {
				System.err.println(mobile +" Card get failed");
			}
		}
	}
	
	public static void test2(int start, int end, int d) throws Exception{
		for (int i = start; i < end; i++) {
			String mobile = "1341113";
			if (i < 10) {
				mobile = mobile + "000" + i;
			} else if ( i < 100) {
				mobile = mobile + "00" + i;
			} else if (i < 1000) {
				mobile = mobile + "0" + i;
			} else if (i < 10000) {
				mobile = mobile + i;
			}
			System.out.println(mobile);
			UserInfo user = UserBaseInfo.getUserInfo(mobile, "aaaaaa");
			String token = user.getToken();
			String userId = user.getUserId();
			for (int j = 0; j < 5; j++) {
				getCard7091_day(mobile, userId, token);
			}
			boolean result = checkCard7092(mobile, userId, token, d);
			if (result) {
				System.err.println(mobile +" Card get ok");
			} else {
				System.err.println(mobile +" Card get failed");
			}
		}
	}
	
	public static boolean getCard7091_day(String name, String userID, String token) throws Exception{
		String params = DataUrls.params_7091_day;
		String url = DataUrls.url_act;
		String suc = "code\":1";
		
		String hParams = "userID," + userID + ";token," + token + ";username," + name;
		String bParams = "";
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		boolean result = false;
		if (reString.contains(suc)) {
			result = true;
		}
		return result;
	}
	
	public static void main(String[] args) throws Exception {
//		test(1, 70, 5);
		test2(9, 15, 2);
	}
}
