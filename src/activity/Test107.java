package activity;

import helper.AppReq;
import helper.DataUrls;
import helper.UserInfo;
import interfaceTest.UserBaseInfo;
import net.sf.json.JSONObject;

public class Test107 {
	
	public static void getRpByShop(String name, String userID, String token) throws Exception{
		String params = DataUrls.params_30002;
		String url = DataUrls.url_shop;
		String suc = "恭喜，您的操作很完美";
		
		String hParams = "userID," + userID + ";token," + token + ";username," + name;
		String bParams = "";
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		if (!reString.contains(suc)) {
			System.err.println("Get rp failed.");
		}
	}
	
	public static void getRpNum107(String name, String userID, String token) throws Exception{
		String params = DataUrls.params_107;
		String url = DataUrls.url_user;
		String suc = "code\":0";
		
		String hParams = "userID," + userID + ";token," + token + ";username," + name;
		String bParams = "";
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		System.out.println(reString);
		if (reString.contains(suc)) {
			JSONObject obj = JSONObject.fromObject(reString);
			JSONObject data = obj.getJSONObject("data");
			int UseableCount = data.getInt("UseableCount");
			
			for (int i = 0; i < 30; i++) {
				if (UseableCount < 2) {
					System.err.println("107 rp num : " + UseableCount);
				} else {
					System.out.println("107 rp num : " + UseableCount);
				}
				getRpNum402(name, userID, token);
				Thread.sleep(2000);
			}
		}
	}
	
	public static void getRpNum402(String name, String userID, String token) throws Exception{
		String url = DataUrls.url_rp;
		String params = DataUrls.params_402;
		String suc = "code\":0";
		
		String hParams = "userID," + userID + ";token," + token;
		String bParams = "";
		
		params = AppReq.setParmas(params, hParams, bParams);
		String reString = AppReq.getResStr(url, params);
		
//		System.out.println(reString);
		if (reString.contains(suc)) {
			JSONObject obj = JSONObject.fromObject(reString);
			JSONObject data = obj.getJSONObject("data");
			int UsableCount = data.getInt("UsableCount");
			if (UsableCount < 2) {
				System.err.println("402 rp num : " + UsableCount);
			} else {
				System.out.println("402 rp num : " + UsableCount);
			}
		}
	}
	
	public static void test() throws Exception {
		String moible = "js006";
		String pw = "aaaaaa";
		
		UserInfo user = UserBaseInfo.getUserInfo(moible, pw);
		String token = user.getToken();
		String userID = user.getUserId();
		
		getRpByShop(moible, userID, token);
		
		getRpNum107(moible, userID, token);
	}
	

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 2; i++) {
			test();
		}
	}

}
